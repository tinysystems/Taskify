package org.xtext.example.mydsl.debugger.processing;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.xtext.example.mydsl.debugger.context.StateContext;
import org.xtext.example.mydsl.debugger.event.EventStateHandler;

public abstract class ProcessHandler extends ThreadLauncher {

	protected void ThreadStateForDebugging(EObject bodyOrGlobal) {
		waitOrResumeExpression(bodyOrGlobal);
		
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
	
	protected void waitOrResumeExpression(EObject bodyOrGlobal) {
		INode node = (INode) NodeModelUtils.getNode(bodyOrGlobal);
		StateContext.setSrcline(node.getStartLine());
		
		if (!isResumeStepping()) {
			findBreakLines(StateContext.getSrcline());
			
			if (StateContext.getSrcline() == StateContext.getBreakpointSuspendedline()) {
				EventStateHandler.update("state#suspended breakpoint " + StateContext.getSrcline());
				suspend();
			}
		}
	}
	
	private boolean isResumeStepping() {
		boolean isSuspended = false;
		
		if (StateContext.getState().equals("RESUME_STEP")) {
			resume();
		} else if (StateContext.getState().equals("SUSPEND_STEP")) {
			suspendStep();
			isSuspended = true;
		}
		
		return isSuspended;
	}
	
	private void findBreakLines(int srcLine) {
		Iterator<Integer> iterator = StateContext.getBreaklines().iterator();
		
		while (iterator.hasNext()) {
			int breakLine = iterator.next();
			
			if (srcLine == breakLine) {
				StateContext.setBreakpointSuspendedline(breakLine);
				StateContext.setState("SUSPEND");
				break;
			}
		}
	}
	
	public synchronized void suspend() {
		suspended = true;
		StateContext.setState("SUSPEND");
	}
	
	public synchronized void resume() {
		suspended = false;
		notify();

		if (StateContext.getState().equals("RESUME_STEP")) {
			EventStateHandler.update("state#resumed step");
			StateContext.setState("SUSPEND_STEP");
		} else if (StateContext.getState().equals("RESUME")) {
			EventStateHandler.update("state#resumed client");
		}
	}

	private void suspendStep() {
		suspended = true;
		EventStateHandler.update("state#suspended step");
	}
}
