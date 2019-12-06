package org.xtext.example.mydsl.debug.ui.pda.editor;

import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

public class PDAEditor extends AbstractDecoratedTextEditor {
	public PDAEditor() {
		super();
        setSourceViewerConfiguration(new PDASourceViewerConfiguration());
		setRulerContextMenuId("pda.editor.rulerMenu");
        setEditorContextMenuId("pda.editor.editorMenu");
	}
}
