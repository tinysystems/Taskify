package org.xtext.example.mydsl.debugger.processing;

import org.xtext.example.mydsl.myDsl.Break;

public class BreakProcessing extends AbstractStackHelper implements IBody {

	Break m_break;
	public BreakProcessing(Break m_break) {
		this.m_break = m_break;
	}

	@Override
	public void execute(String functionId) {
		isBreak = true;
	}

}
