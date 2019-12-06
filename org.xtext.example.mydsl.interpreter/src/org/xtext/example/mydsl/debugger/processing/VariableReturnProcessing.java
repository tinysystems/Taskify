package org.xtext.example.mydsl.debugger.processing;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.VariableReturn;

public class VariableReturnProcessing extends AbstractStackHelper implements IBody {
	VariableReturn variableReturn;
	
	public VariableReturnProcessing(VariableReturn variableReturn) {
		this.variableReturn = variableReturn;
	}

	@Override
	public void execute(String functionId) {
		isReturn = true;
		Atomic atomic = variableReturn.getReturn();
		Object returnValue = getReturnValue(atomic, functionId);
		
		lastFunctionReturn = returnValue;
		CallStack.getCallStack().removeLast();
	}
	
	private Object getReturnValue(Atomic atomic, String functionId) {
		Object value = decoupleAtomic(atomic, functionId);
		return value;
	}

}
