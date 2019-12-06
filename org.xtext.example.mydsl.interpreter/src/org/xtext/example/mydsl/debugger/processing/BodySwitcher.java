package org.xtext.example.mydsl.debugger.processing;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.InterpreterException;
import org.xtext.example.mydsl.myDsl.AbstractFunctionCall;
import org.xtext.example.mydsl.myDsl.Break;
import org.xtext.example.mydsl.myDsl.GlobalVariable;
import org.xtext.example.mydsl.myDsl.OperationExpression;
import org.xtext.example.mydsl.myDsl.VariableDecleration;
import org.xtext.example.mydsl.myDsl.VariableReturn;


public class BodySwitcher extends ProcessHandler {
	
	public void executor(EObject bodyOrGlobal, String functionId) {
		ThreadStateForDebugging(bodyOrGlobal);
		IBody executor = null;
		
		try {
			if(bodyOrGlobal instanceof GlobalVariable) {
				executor = (IBody) new GlobalVariableProcessing((GlobalVariable) bodyOrGlobal); 
			} else if(bodyOrGlobal instanceof OperationExpression) {
				executor = (IBody) new OperationExpressionProcessing((OperationExpression) bodyOrGlobal, this);
			} else if(bodyOrGlobal instanceof VariableReturn) {
				executor = (IBody) new VariableReturnProcessing((VariableReturn) bodyOrGlobal); 
			} else if(bodyOrGlobal instanceof VariableDecleration) {
				executor = (IBody) new VariableDeclerationProcessing((VariableDecleration) bodyOrGlobal);
			} else if(bodyOrGlobal instanceof AbstractFunctionCall) {
				executor = (IBody) new AbstractFunctionCallProcessing((AbstractFunctionCall) bodyOrGlobal, this);
			} else if(bodyOrGlobal instanceof Break) {
				executor = (IBody) new BreakProcessing((Break) bodyOrGlobal);
			} else {
				new InterpreterException("Unsupported body or global expression.");
			}
			executor.execute(functionId);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	@Override
	public void run() {
		super.start();
	}

}
