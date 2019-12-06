package org.xtext.example.mydsl.debug.core.pda.model;

public interface IPDAEventListener {
	/**
	 * Listeners are notified of events occurring in a PDA program
	 * being interpreted.
	 *
	 * @see org.eclipse.debug.examples.core.protocol.PDAVMStarted
	 * @see org.eclipse.debug.examples.core.protocol.PDAVMTerminated
	 * @see org.eclipse.debug.examples.core.protocol.PDAVMSuspneded
	 * @see org.eclipse.debug.examples.core.protocol.PDAVMResumed
	 * @see org.eclipse.debug.examples.core.protocol.PDAStarted
	 * @see org.eclipse.debug.examples.core.protocol.PDAExited
	 * @see org.eclipse.debug.examples.core.protocol.PDASuspended
	 * @see org.eclipse.debug.examples.core.protocol.PDAResumed
	 * @see org.eclipse.debug.examples.core.pda.protocol.PDAUnimplementedInstructionEvent
	 * @see org.eclipse.debug.examples.core.pda.protocol.PDARegisterData
	 * @see org.eclipse.debug.examples.core.pda.protocol.PDANoSuchLabelEvent
	 * @see org.eclipse.debug.examples.core.pda.protocol.PDAEvalResultEvent
	 */
	public void handleEvent(String event);
}
