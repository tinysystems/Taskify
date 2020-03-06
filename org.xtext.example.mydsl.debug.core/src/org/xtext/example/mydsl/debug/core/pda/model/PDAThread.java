package org.xtext.example.mydsl.debug.core.pda.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class PDAThread extends PDADebugElement implements IThread, IPDAEventListener {
    /**
     * Breakpoint this thread is suspended at or <code>null</code>
     * if none.
     */
    private IBreakpoint fBreakpoint;

    /**
     * Whether this thread is stepping
     */
    private boolean fStepping = false;

    /**
     * Whether this thread is suspended
     */
    private boolean fSuspended = false;

    /**
     * Most recent error event or <code>null</code>
     */
    private String fErrorEvent;

    /**
     * Table mapping stack frames to current variables
     */
    private Map<IStackFrame, IVariable[]> fVariables = new HashMap<IStackFrame, IVariable[]>();
    
    /**
     * Constructs a new thread for the given target
     *
     * @param target VM
     */
    public PDAThread(PDADebugTarget target) {
        super(target);
        getPDADebugTarget().addEventListener(this);
    }    
    
    /**
     * Notifies this thread it has been suspended by the given breakpoint.
     * 
     * @param breakpoint
     *            breakpoint
     */
    public void suspendedBy(IBreakpoint breakpoint) {
        // System.out.println("can1");
        fBreakpoint = breakpoint;
        if (fSuspended == false) {
            fSuspended = true;
        }
        suspended(DebugEvent.BREAKPOINT);
    }
    
    @Override
    public boolean canResume() {
        return isSuspended();
    }

    @Override
    public boolean canSuspend() {
        return false;
    }

    @Override
    public boolean isSuspended() {
        return fSuspended && !isTerminated();
    }

    @Override
    public void resume() throws DebugException {
        sendRequest("resume");
    }

    @Override
    public void suspend() throws DebugException {
        sendRequest("suspend");
    }

    @Override
    public boolean canStepInto() {
        return false;
    }

    @Override
    public boolean canStepOver() {
        return isSuspended();
    }

    @Override
    public boolean canStepReturn() {
        return false;
    }

    @Override
    public boolean isStepping() {
        return fStepping;
    }

    @Override
    public void stepInto() throws DebugException {
//        throw new DebugException(new Status(IStatus.ERROR, "org.xtext.example.mydsl.debug.core", "stepInto() is not supported"));
    }

    @Override
    public void stepOver() throws DebugException {
        sendRequest("step");
    }

    @Override
    public void stepReturn() throws DebugException {
//        throw new DebugException(new Status(IStatus.ERROR, "org.xtext.example.mydsl.debug.core", "stepReturn() is not supported"));
    }

    @Override
    public boolean canTerminate() {
        return !isTerminated();
    }

    @Override
    public boolean isTerminated() {
        return getDebugTarget().isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
        sendRequest("exit");
    }
    
    /**
     * Sets whether this thread is stepping
     * 
     * @param stepping
     *            whether stepping
     */
    private void setStepping(boolean stepping) {
        fStepping = stepping;
    }

    /**
     * Sets whether this thread is suspended
     * 
     * @param suspended
     *            whether suspended
     */
    private void setSuspended(boolean suspended) {
        fSuspended = suspended;
    }

    /**
     * Sets the most recent error event encountered, or <code>null</code> to
     * clear the most recent error
     * 
     * @param event
     *            one of 'unimpinstr' or 'nosuchlabel' or <code>null</code>
     */
    private void setError(String event) {
        fErrorEvent = event;
    }

    /**
     * Returns the most revent error event encountered since the last suspend,
     * or <code>null</code> if none.
     * 
     * @return the most revent error event encountered since the last suspend,
     *         or <code>null</code> if none
     */
    public Object getError() {
        return fErrorEvent;
    }
    
    /**
     * Notification the target has resumed for the given reason. Clears any
     * error condition that was last encountered and fires a resume event, and
     * clears all cached variables for stack frames.
     * 
     * @param detail
     *            reason for the resume
     */
    private void resumed(int detail) {
        setError(null);
//        synchronized (fVariables) {
//            fVariables.clear();
//        }
        fireResumeEvent(detail);
    }
    
    /**
     * Notification an error was encountered. Fires a breakpoint suspend event.
     */
    private void exceptionHit() {
        suspended(DebugEvent.BREAKPOINT);
    }
    
    /**
     * Notification the target has suspended for the given reason
     * 
     * @param detail
     *            reason for the suspend
     */
    private void suspended(int detail) {
        fireSuspendEvent(detail);
    }


    @Override
    public void handleEvent(String event) {
        // clear previous state
        fBreakpoint = null;
        setStepping(false);

        // handle events
        if (event.startsWith("resumed")) {
            setSuspended(false);
            if (event.endsWith("step")) {
                setStepping(true);
                resumed(DebugEvent.STEP_OVER);
            } else if (event.endsWith("client")) {
                resumed(DebugEvent.CLIENT_REQUEST);
            }
        } else if (event.startsWith("suspended")) {
            setSuspended(true);

            if (event.endsWith("client")) {
                suspended(DebugEvent.CLIENT_REQUEST);
            } else if (event.endsWith("step")) {
                suspended(DebugEvent.STEP_END);
            } else if (event.startsWith("suspended event") && getError() != null) {
                exceptionHit();
            }
        } else if (event.equals("started")) {
            fireCreationEvent();
        } else {
            setError(event);
        }
    }

    @Override
    public IBreakpoint[] getBreakpoints() {
        if (fBreakpoint == null) {
            return new IBreakpoint[0];
        }
        return new IBreakpoint[] { fBreakpoint };
    }

    @Override
    public String getName() {
        return "PDA thread";
    }

    @Override
    public int getPriority() throws DebugException {
        return 0;
    }

    @Override
    public IStackFrame[] getStackFrames() throws DebugException {
        if (isSuspended()) {
            String framesData = sendRequest("stack");
            if (framesData != null) {
                String[] frames = framesData.split("#");
                IStackFrame[] theFrames = new IStackFrame[frames.length];
                for (int i = 0; i < frames.length; i++) {
                    String data = frames[i];
                    theFrames[frames.length - i - 1] = new PDAStackFrame(this, data, i);
                }
                return theFrames;
            }
        }
        return new IStackFrame[0];
    }

    @Override
    public IStackFrame getTopStackFrame() throws DebugException {
        IStackFrame[] frames = getStackFrames();
        if (frames.length > 0) {
            return frames[0];
        }

        System.out.println("top stack is null");
        return null;
    }

    @Override
    public boolean hasStackFrames() throws DebugException {
        return isSuspended();
    }

    /**
     * Sets the current variables for the given stack frame. Called by PDA stack
     * frame when it is created.
     * 
     * @param frame
     * @param variables
     */
    protected void setVariables(IStackFrame frame, IVariable[] variables) {
        synchronized (fVariables) {
            fVariables.put(frame, variables);
        }
    }

    /**
     * Returns the current variables for the given stack frame, or
     * <code>null</code> if none.
     * 
     * @param frame
     *            stack frame
     * @return variables or <code>null</code>
     */
    protected IVariable[] getVariables(IStackFrame frame) {
        synchronized (fVariables) {
            return (IVariable[]) fVariables.get(frame);
        }
    }
}
