package org.xtext.example.mydsl.debug.ui.pda.breakpoints;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class PDAEditorAdapterFactory implements IAdapterFactory {
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof XtextEditor) {
			ITextEditor editorPart = (ITextEditor) adaptableObject;
			IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("mydsl")) {
					if (adapterType.equals(IToggleBreakpointsTarget.class)) {
				        return (T) new PDABreakpointAdapter();
				    }
				    
				    if (adapterType.equals(IRunToLineTarget.class)) {
						return (T) new PDARunToLineAdapter();
				    }
				}
			}			
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[]{IToggleBreakpointsTarget.class};
	}
}
