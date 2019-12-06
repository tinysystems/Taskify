package org.xtext.example.mydsl.debug.ui.pda.breakpoints;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.xtext.example.mydsl.debug.core.pda.breakpoints.PDALineBreakpoint;

public class PDABreakpointAdapter implements IToggleBreakpointsTarget {

	@Override
	public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection arg1) {
		return (getEditor(part) != null);
	}

	@Override
	public boolean canToggleMethodBreakpoints(IWorkbenchPart arg0, ISelection arg1) {
		return false;
	}

	@Override
	public boolean canToggleWatchpoints(IWorkbenchPart arg0, ISelection arg1) {
		return false;
	}

	@Override
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		ITextEditor textEditor = getEditor(part);
		if (textEditor != null) {
			IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
			ITextSelection textSelection = (ITextSelection) selection;
			int lineNumber = textSelection.getStartLine();
			
			IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(DebugCorePlugin.ID_PDA_DEBUG_MODEL);
			for (int i = 0; i < breakpoints.length; i++) {
				IBreakpoint breakpoint = breakpoints[i];
//				TODO check casting to Object inside equals 
				if (breakpoint instanceof ILineBreakpoint && resource.equals((Object) breakpoint.getMarker().getResource())) {
					if (((ILineBreakpoint)breakpoint).getLineNumber() == (lineNumber + 1)) {
						// remove
						breakpoint.delete();
						return;
					}
				}
			}
			// create line breakpoint (doc line numbers start at 0)
			PDALineBreakpoint lineBreakpoint = new PDALineBreakpoint(resource, lineNumber + 1);
			DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
		}
	}

	@Override
	public void toggleMethodBreakpoints(IWorkbenchPart arg0, ISelection arg1) throws CoreException {
	}

	@Override
	public void toggleWatchpoints(IWorkbenchPart arg0, ISelection arg1) throws CoreException {
	}
	
	/**
	 * Returns the editor being used to edit a DSL file, associated with the
	 * given part, or <code>null</code> if none.
	 *  
	 * @param part workbench part
	 * @return the editor being used to edit a DSL file, associated with the
	 * given part, or <code>null</code> if none
	 */
	private ITextEditor getEditor(IWorkbenchPart part) {
		if (part instanceof ITextEditor) {
			ITextEditor editorPart = (ITextEditor) part;
			IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("mydsl")) {
					return editorPart;
				}
			}
		}
		return null;		
	}
}
