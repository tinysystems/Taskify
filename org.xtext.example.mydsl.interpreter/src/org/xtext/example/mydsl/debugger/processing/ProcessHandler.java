package org.xtext.example.mydsl.debugger.processing;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.xtext.example.mydsl.debugger.context.StateContext;
import org.xtext.example.mydsl.debugger.event.EventStateHandler;

public abstract class ProcessHandler extends ThreadLauncher {

    protected void threadStateForDebugging(EObject expression) {
        waitOrResumeExpression(expression);
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception: Thread.sleep(1)");
            e.printStackTrace();
        }
        
        synchronized (this) {
            while (suspended) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException: suspended");
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected void waitOrResumeExpression(EObject expression) {
        INode node = (INode) NodeModelUtils.getNode(expression);
        StateContext.setSrcline(node.getStartLine());
        
        if (!isResumeStepping()) {
            // Reset first, then findBreakLines() will fill if
            StateContext.resetBreakpointSuspendedline();
            findBreakLines(StateContext.getSrcline());
            
            if (StateContext.getSrcline() == StateContext.getBreakpointSuspendedline()) {
                EventStateHandler.update("state#suspended breakpoint " + StateContext.getSrcline());
                suspend();
            }
        }
    }
    
    private boolean isResumeStepping() {
        boolean isSuspended = false;
        
        if (StateContext.getState().equals(StateContext.RESUME_STEP_STATE)) {
            resume();
        } else if (StateContext.getState().equals(StateContext.RESUME_STEP_STATE)) {
            suspendStep();
            isSuspended = true;
        }
        
        return isSuspended;
    }
    
    private void findBreakLines(int srcLine) {        
        if (StateContext.getBreaklines().contains(srcLine)) {
            StateContext.setBreakpointSuspendedline(srcLine);
            StateContext.setState(StateContext.SUSPEND_STATE);
        }
    }
    
    public synchronized void suspend() {
        suspended = true;
        StateContext.setState(StateContext.SUSPEND_STATE);
    }
    
    public synchronized void resume() {
        suspended = false;
        notify();

        if (StateContext.getState().equals(StateContext.RESUME_STEP_STATE)) {
            EventStateHandler.update("state#resumed step");
            StateContext.setState(StateContext.SUSPEND_STEP_STATE);
        } else if (StateContext.getState().equals(StateContext.RESUME_STATE)) {
            EventStateHandler.update("state#resumed client");
        }
    }

    private void suspendStep() {
        suspended = true;
        EventStateHandler.update("state#suspended step");
    }
}
