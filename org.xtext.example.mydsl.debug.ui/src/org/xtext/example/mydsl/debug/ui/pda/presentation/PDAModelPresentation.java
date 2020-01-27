package org.xtext.example.mydsl.debug.ui.pda.presentation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.internal.ui.sourcelookup.SourceLookupManager;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.xtext.example.mydsl.debug.core.pda.breakpoints.PDALineBreakpoint;
import org.xtext.example.mydsl.debug.core.pda.model.PDADebugTarget;
import org.xtext.example.mydsl.debug.core.pda.model.PDAStackFrame;
import org.xtext.example.mydsl.debug.core.pda.model.PDAThread;

/**
 * Renders PDA debug elements
 */
public class PDAModelPresentation extends LabelProvider implements IDebugModelPresentation {

	@Override
	public String getEditorId(IEditorInput input, Object element) {
		if (element instanceof IFile || element instanceof ILineBreakpoint) {
			/*
			 * In order to open default XTextEditor once go to file from breakpoint, 
			 * return the targetID specified extension point org.eclipse.ui.editorActions in
			 * /org.xtext.example.mydsl.debug.ui/plugin.xml
			 * */
//			return "pda.editor";
			return "org.xtext.example.mydsl.MyDsl";
		}
		return null;
	}

	@Override
	public IEditorInput getEditorInput(Object element) {
		if (element instanceof IFile) {
			return new FileEditorInput((IFile) element);
		}
		if (element instanceof ILineBreakpoint) {
			return new FileEditorInput((IFile) ((ILineBreakpoint) element).getMarker().getResource());
		}
		return null;
	}

	@Override
	public void computeDetail(IValue value, IValueDetailListener listener) {
		String detail = "";
		try {
			detail = value.getValueString();
		} catch (DebugException e) {
		}
		listener.detailComputed(value, detail);
	}

	@Override
	public void setAttribute(String attribute, Object value) {
	}

	public String getText(Object element) {
		if (element instanceof PDADebugTarget) {
			return getTargetText((PDADebugTarget) element);
		} else if (element instanceof PDAThread) {
			return getThreadText((PDAThread) element);
		} else if (element instanceof PDAStackFrame) {
			lookup((PDAStackFrame) element); // add:kim for testing
			return getStackFrameText((PDAStackFrame) element);
		}
		return null;
	}
	
	/**
	 * Returns a label for the given debug target
	 * 
	 * @param target
	 *            debug target
	 * @return a label for the given debug target
	 */
	private String getTargetText(PDADebugTarget target) {
		try {
			String pgmPath = target.getLaunch().getLaunchConfiguration().getAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, (String) null);
			if (pgmPath != null) {
				IPath path = new Path(pgmPath);
				String label = "";
				if (target.isTerminated()) {
					label = "<terminated>";
				}
				return label + "PDA [" + path.lastSegment() + "]";
			}
		} catch (CoreException e) {
		}
		return "PDA";
	}
	
	/**
	 * Returns a label for the given stack frame
	 * 
	 * @param frame
	 *            a stack frame
	 * @return a label for the given stack frame
	 */
	private String getStackFrameText(PDAStackFrame frame) {
		try {
			return frame.getName() + " (line: " + frame.getLineNumber() + ")";
		} catch (DebugException e) {
		}
		return null;
	}
	
	/**
	 * this is not recommend way to highlight source line. 
	 * maybe The Source code lookup try to find file's cache first before launching SourceLookupManager. 
	 * so it could not trigger highlighting source code line after resume or breakpoint action. 
	 * so this method forcedly re-trigger highlighting action.	 * 
	 * */	 
	private void lookup(PDAStackFrame frame) {
		try {
			IWorkbench wb = PlatformUI.getWorkbench();
			IWorkbenchWindow[] win = wb.getWorkbenchWindows();
			IWorkbenchPage page = win[0].getActivePage();

//			TODO
			SourceLookupManager.getDefault().displaySource(frame, page, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a label for the given thread
	 * 
	 * @param thread
	 *            a thread
	 * @return a label for the given thread
	 */
	private String getThreadText(PDAThread thread) {
		String label = thread.getName();
		if (thread.isStepping()) {
			label += " (stepping)";
		} else if (thread.isSuspended()) {
			IBreakpoint[] breakpoints = thread.getBreakpoints();
			if (breakpoints.length == 0) {
				if (thread.getError() == null) {
					label += " (suspended)";
				} else {
					label += " (" + thread.getError() + ")";
				}
			} else {
				IBreakpoint breakpoint = breakpoints[0];
				if (breakpoint instanceof PDALineBreakpoint) {
					PDALineBreakpoint dslBreakpoint = (PDALineBreakpoint) breakpoint;
					if (breakpoint instanceof PDALineBreakpoint) {
						label += " (suspended at line breakpoint)";
					} else if (dslBreakpoint.isRunToLineBreakpoint()) {
						label += " (run to line)";
					}
				}
			}
		} else if (thread.isTerminated()) {
			label = "<terminated> " + label;
		}
		return label;
	}
}
