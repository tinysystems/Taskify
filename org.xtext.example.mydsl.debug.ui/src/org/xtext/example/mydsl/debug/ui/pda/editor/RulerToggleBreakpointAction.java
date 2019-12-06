package org.xtext.example.mydsl.debug.ui.pda.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class RulerToggleBreakpointAction extends AbstractRulerActionDelegate {

	@Override
	protected IAction createAction(ITextEditor textEditor, IVerticalRulerInfo rulerInfo) {
		return new ToggleBreakpointAction(textEditor, rulerInfo);
	}
}
