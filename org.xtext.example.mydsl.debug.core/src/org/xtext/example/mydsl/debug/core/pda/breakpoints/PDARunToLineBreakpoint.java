package org.xtext.example.mydsl.debug.core.pda.breakpoints;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;

public class PDARunToLineBreakpoint extends PDALineBreakpoint {
	private IFile fSourceFile;
	
	/**
	 * Constructs a run-to-line breakpoint in the given PDA program.
	 * 
	 * @param resource PDA source file
	 * @param lineNumber line to run to
	 * @exception DebugException if unable to create the breakpoint
	 */
	public PDARunToLineBreakpoint(final IFile resource, final int lineNumber) throws DebugException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				// associate with workspace root to avoid drawing in editor ruler
				IMarker marker = ResourcesPlugin.getWorkspace().getRoot().createMarker("org.xtext.example.mydsl.debug.core.pda.markerType.lineBreakpoint");
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				setRegistered(false);
				fSourceFile = resource;
			}
		};
		run(getMarkerRule(resource), runnable);		
	}
	
	public boolean isRunToLineBreakpoint() {
		return true;
	}
	
	/**
	 * Returns the source file this breakpoint is contained in.
	 * 
	 * @return the source file this breakpoint is contained in
	 */
	public IFile getSourceFile() {
		return fSourceFile;
	}
}
