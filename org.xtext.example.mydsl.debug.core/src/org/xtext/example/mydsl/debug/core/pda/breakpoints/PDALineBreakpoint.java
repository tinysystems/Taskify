package org.xtext.example.mydsl.debug.core.pda.breakpoints;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.LineBreakpoint;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.xtext.example.mydsl.debug.core.pda.model.IPDAEventListener;
import org.xtext.example.mydsl.debug.core.pda.model.PDADebugTarget;
import org.xtext.example.mydsl.debug.core.pda.model.PDAThread;

public class PDALineBreakpoint extends LineBreakpoint implements IPDAEventListener {
    // target currently installed in
    private PDADebugTarget fTarget;
    
    
    /**
     * Default constructor is required for the breakpoint manager
     * to re-create persisted breakpoints. After instantiating a breakpoint,
     * the <code>setMarker(...)</code> method is called to restore
     * this breakpoint's attributes.
     */
    public PDALineBreakpoint() {
    }
    
    /**
     * Constructs a line breakpoint on the given resource at the given
     * line number. The line number is 1-based (i.e. the first line of a
     * file is line number
     * 
     * @param resource file on which to set the breakpoint
     * @param lineNumber 1-based line number of the breakpoint
     * @throws CoreException if unable to create the breakpoint
     */
    public PDALineBreakpoint(final IResource resource, final int lineNumber) throws CoreException {
        IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
            @Override
            public void run(IProgressMonitor monitor) throws CoreException {
                IMarker marker = (IMarker) resource.createMarker("org.xtext.example.mydsl.debug.core.markerType.lineBreakpoint");
                setMarker(marker);
                marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
                marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
                marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
                marker.setAttribute(IMarker.MESSAGE, "Line Breakpoint: " + resource.getName() + " [line: " + lineNumber + "]");
            }
        };
        run(getMarkerRule(resource), runnable);
    }
    
    @Override
    public String getModelIdentifier() {
        return DebugCorePlugin.ID_PDA_DEBUG_MODEL;
    }
    
    /**
     * Returns whether this breakpoint is a run-to-line breakpoint
     * 
     * @return whether this breakpoint is a run-to-line breakpoint
     */
    public boolean isRunToLineBreakpoint() {
        return false;
    }
    
    /**
     * Installs this breakpoint in the given interprettor.
     * Registeres this breakpoint as an event listener in the
     * given target and creates the breakpoint specific request.
     * 
     * @param target PDA interprettor
     * @throws CoreException if installation fails
     */
    public void install(PDADebugTarget target) throws CoreException {
        fTarget = target;
        target.addEventListener(this);
        createRequest(target);
    }
    
    /**
     * Create the breakpoint specific request in the target. Subclasses
     * should override.
     * 
     * @param target PDA interprettor
     * @throws CoreException if request creation fails
     */
    protected void createRequest(PDADebugTarget target) throws CoreException {
        target.sendRequest("brk#" + getLineNumber());
    }
    
    /**
     * Removes this breakpoint's event request from the target. Subclasses
     * should override.
     * 
     * @param target PDA interprettor
     * @throws CoreException if clearing the request fails
     */
    protected void clearRequest(PDADebugTarget target) throws CoreException {
        target.sendRequest("unbrk#" + getLineNumber());
    }
    
    /**
     * Removes this breakpoint from the given interprettor.
     * Removes this breakpoint as an event listener and clears
     * the request for the interprettor.
     * 
     * @param target PDA interprettor
     * @throws CoreException if removal fails
     */
    public void remove(PDADebugTarget target) throws CoreException {
        target.removeEventListener(this);
        clearRequest(target);
        fTarget = null;
        
    }
    
    /**
     * Returns the target this breakpoint is installed in or <code>null</code>.
     * 
     * @return the target this breakpoint is installed in or <code>null</code>
     */
    protected PDADebugTarget getDebugTarget() {
        return fTarget;
    }
    
    /**
     * Notify's the PDA interpreter that this breakpoint has been hit.
     */
    protected void notifyThread() {
        if (fTarget != null) {
            try {
                IThread[] threads = fTarget.getThreads();
                if (threads.length == 1) {
                    PDAThread thread = (PDAThread)threads[0];
                    thread.suspendedBy(this);
                }
            } catch (Exception e) {
            }            
        }
    }

    public void handleEvent(String event) {
        if (event.startsWith("suspended breakpoint")) {
            handleHit(event);
        }
    }
    
    /**
     * Determines if this breakpoint was hit and notifies the thread.
     * 
     * @param event breakpoint event
     */
    private void handleHit(String event) {
        int lastSpace = event.lastIndexOf(' ');
        if (lastSpace > 0) {
            String line = event.substring(lastSpace + 1);
            int lineNumber = Integer.parseInt(line);
//            breakpoints event line numbers are 0 based, model objects are 1 based
//            TODO after running???
//            lineNumber++;
            try {
                // System.out.println("marker=: " + getLineNumber() + " / req=lineNumber: " + lineNumber);
                if (getLineNumber() == lineNumber) {
                    notifyThread();
                }
            } catch (CoreException e) {
            }
        }
    }
}
