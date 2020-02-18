package org.xtext.example.mydsl.debug.core.pda.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IBreakpointManagerListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.xtext.example.mydsl.debug.core.pda.breakpoints.PDALineBreakpoint;
import org.xtext.example.mydsl.debug.core.pda.breakpoints.PDARunToLineBreakpoint;

public class PDADebugTarget extends PDADebugElement implements IDebugTarget, IBreakpointManagerListener, IPDAEventListener {
    // associated system process (VM)
    private IProcess fProcess;

    // containing launch object
    private ILaunch fLaunch;

    // sockets to communicate with VM
    private Socket fRequestSocket;
    private PrintWriter fRequestWriter;
    private BufferedReader fRequestReader;
    private Socket fEventSocket;
    private BufferedReader fEventReader;

    // terminated state
    private boolean fTerminated = false;
    // threads
    private IThread[] fThreads;
    private PDAThread fThread;
    // event dispatch job
    private EventDispatchJob fEventDispatch;
    // event listeners
    private Vector<IPDAEventListener> fEventListeners = new Vector<IPDAEventListener>();
    
    public PDADebugTarget(ILaunch launch, IProcess process, int requestPort, int eventPort) throws CoreException {
        super(null);
        fLaunch = launch;
        fProcess = process;
        addEventListener(this);
        try {
            // give interpreter a chance to start
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            fRequestSocket = new Socket("127.0.0.1", requestPort);
            fRequestWriter = new PrintWriter(fRequestSocket.getOutputStream());
            fRequestReader = new BufferedReader(new InputStreamReader(fRequestSocket.getInputStream()));
            // give interpreter a chance to open next socket
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            fEventSocket = new Socket("127.0.0.1", eventPort);
            fEventReader = new BufferedReader(new InputStreamReader(fEventSocket.getInputStream()));
        } catch (UnknownHostException e) {
            requestFailed("Unable to connect to DSL VM", e);
        } catch (IOException e) {
            requestFailed("Unable to connect to DSL VM", e);
        }

        fThread = new PDAThread(this);
        fThreads = new IThread[] { fThread };
        fEventDispatch = new EventDispatchJob();
        fEventDispatch.schedule();

        IBreakpointManager breakpointManager = getBreakpointManager();
        breakpointManager.addBreakpointListener(this);
        breakpointManager.addBreakpointManagerListener(this);
    }
    
    public PDADebugTarget(ILaunch launch, Thread thread, int requestPort, int eventPort) {
        super(null);
        
    }
    
    class EventDispatchJob extends Job {
        public EventDispatchJob() {
            super("DSL PDA Event Dispatch");
            setSystem(true);
        }
        
        @Override
        protected IStatus run(IProgressMonitor monitor) {
            String event = "";
            while (!isTerminated() && event != null) {
                try {
                    event = fEventReader.readLine();
                    if (event != null && !event.isEmpty()) {
                        System.out.println("org.xtext.example.mydsl.debug.core in event: " + event); 
                        // debugging
                        Object[] listeners = fEventListeners.toArray();
                        for (int i = 0; i < listeners.length; i++) {
                            ((IPDAEventListener) listeners[i]).handleEvent(event);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("exception occur");
                    debuggerTerminated();
                }
            }
            return Status.OK_STATUS;
        }
    }
    
    /**
     * Called when this debug target terminates.
     */
    private void debuggerTerminated() {
        fTerminated = true;
        fThreads = new IThread[0];
        IBreakpointManager breakpointManager = getBreakpointManager();
        breakpointManager.removeBreakpointListener(this);
        breakpointManager.removeBreakpointManagerListener(this);
        fireTerminateEvent();
        removeEventListener(this);
    }
    
    /**
     * Registers the given event listener. The listener will be notified of
     * events in the program being interpretted. Has no effect if the listener
     * is already registered.
     *
     * @param listener event listener
     */
    public void addEventListener(IPDAEventListener listener) {
        synchronized(fEventListeners) {
            if (!fEventListeners.contains(listener)) {
                fEventListeners.add(listener);
            }
        }
    }

    /**
     * Deregisters the given event listener. Has no effect if the listener is
     * not currently registered.
     *
     * @param listener event listener
     */
    public void removeEventListener(IPDAEventListener listener) {
        fEventListeners.remove(listener);
    }
    
    @Override
    public boolean canTerminate() {
        return getProcess().canTerminate();
    }

    @Override
    public boolean isTerminated() {
        return (fTerminated || getProcess().isTerminated());
    }

    @Override
    public void terminate() throws DebugException {
        fThread.terminate();
    }

    @Override
    public boolean canResume() {
        return (!isTerminated() && isSuspended());
    }

    @Override
    public boolean canSuspend() {
        return (!isTerminated() && !isSuspended());
    }
    
    protected synchronized PDAThread getThread() {
        return fThread;
    }

    @Override
    public boolean isSuspended() {
        return (!isTerminated() && getThread().isSuspended());
    }

    @Override
    public void resume() throws DebugException {
        getThread().resume();
    }

    @Override
    public void suspend() throws DebugException {
        getThread().suspend();
    }

    @Override
    public void breakpointAdded(IBreakpoint breakpoint) {
        if (supportsBreakpoint(breakpoint)) {
            try {
                if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled()) || !breakpoint.isRegistered()) {
                    PDALineBreakpoint lineBreakpoint = (PDALineBreakpoint) breakpoint;
                    lineBreakpoint.install(this);
                }
            } catch (CoreException e) {
            }
        }
    }

    @Override
    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta arg1) {
        if (supportsBreakpoint(breakpoint)) {
            try {
                PDALineBreakpoint lineBreakpoint = (PDALineBreakpoint) breakpoint;
                lineBreakpoint.remove(this);
            } catch (CoreException e) {
            }
        }
    }
    
    @Override
    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta arg1) {
        if (supportsBreakpoint(breakpoint)) {
            try {
                if (breakpoint.isEnabled() && getBreakpointManager().isEnabled()) {
                    breakpointAdded(breakpoint);
                } else {
                    breakpointRemoved(breakpoint, null);
                }
            } catch (CoreException e) {
            }
        }
    }

    @Override
    public boolean canDisconnect() {
        return false;
    }

    @Override
    public void disconnect() throws DebugException {
    }

    @Override
    public boolean isDisconnected() {
        return false;
    }

    @Override
    public IMemoryBlock getMemoryBlock(long arg0, long arg1) throws DebugException {
        return null;
    }

    @Override
    public boolean supportsStorageRetrieval() {
        return false;
    }

    @Override
    public void breakpointManagerEnablementChanged(boolean enabled) {
        IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(getModelIdentifier());
        for (int i = 0; i < breakpoints.length; i++) {
            if (enabled) {
                breakpointAdded(breakpoints[i]);
            } else {
                breakpointRemoved(breakpoints[i], null);
            }
        }
    }

    @Override
    public String getName() throws DebugException {
        return "PDA DSL";
    }

    @Override
    public IProcess getProcess() {
        return fProcess;
    }

    @Override
    public IThread[] getThreads() throws DebugException {
        return fThreads;
    }

    @Override
    public boolean hasThreads() throws DebugException {
        return (fThreads.length > 0);
    }

    @Override
    public boolean supportsBreakpoint(IBreakpoint breakpoint) {
        if (!isTerminated() && breakpoint.getModelIdentifier().equals(getModelIdentifier())) {
            try {
                String program = getLaunch().getLaunchConfiguration().getAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM,
                        (String) null);
                if (program != null) {
                    IResource resource = null;
                    if (breakpoint instanceof PDARunToLineBreakpoint) {
                        PDARunToLineBreakpoint rtl = (PDARunToLineBreakpoint) breakpoint;
                        resource = rtl.getSourceFile();
                    } else {
                        IMarker marker = breakpoint.getMarker();
                        if (marker != null) {
                            resource = marker.getResource();
                        }
                    }
                    if (resource != null) {
                        IPath p = new Path(program);
                        return resource.getFullPath().equals(p);
                    }
                }
            } catch (CoreException e) {
            }
        }
        return false;
    }

    public void handleEvent(String event) {
        if (event.equals("started")) {
            started();
        } else if (event.equals("terminated")) {
            terminated();
        }
    }
    
    /**
     * Synchronizing with the interpreter at startup
     * Install breakpoints that are already registered with the breakpoint
     * manager.
     */
    private void installDeferredBreakpoints() {
        IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(getModelIdentifier());
        for (int i = 0; i < breakpoints.length; i++) {
            breakpointAdded(breakpoints[i]);
        }
    }
    
    public void started() {
        fireCreationEvent();
        installDeferredBreakpoints();
        try {
            resume();
        } catch (DebugException e) {
        }
    }
    
    /**
     * Called when this debug target terminates.
     */
    private synchronized void terminated() {
        fTerminated = true;
        fThread = null;
        fThreads = new IThread[0];
        IBreakpointManager breakpointManager = getBreakpointManager();
        breakpointManager.removeBreakpointListener(this);
        breakpointManager.removeBreakpointManagerListener(this);
        fireTerminateEvent();
        removeEventListener(this);
    }
    
    public String sendRequest(String request) throws DebugException {
        synchronized (fRequestSocket) {
            fRequestWriter.println(request);
            fRequestWriter.flush();
            System.out.println("org.xtext.example.mydsl.debug.core sent event: " + request);
            try {
                // wait for reply
                return fRequestReader.readLine();
            } catch (IOException e) {
                requestFailed("Request failed: " + request, e);
            }
        }
        return null;
    }
    
    public IDebugTarget getDebugTarget() {
        return this;
    }

    public ILaunch getLaunch() {
        return fLaunch;
    }
}
