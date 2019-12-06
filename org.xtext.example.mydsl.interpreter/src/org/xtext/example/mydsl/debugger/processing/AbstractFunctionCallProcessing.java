package org.xtext.example.mydsl.debugger.processing;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.myDsl.AbstractFunctionCall;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Body;
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCall;
import org.xtext.example.mydsl.myDsl.FunctionCall;
import org.xtext.example.mydsl.myDsl.FunctionDefinition;
import org.xtext.example.mydsl.myDsl.FunctionParameter;

public class AbstractFunctionCallProcessing extends AbstractStackHelper implements IBody {
	AbstractFunctionCall abstractFunctionCall;
	BodySwitcher bodySwitcher;
	
	public AbstractFunctionCallProcessing(AbstractFunctionCall abstractFunctionCall, BodySwitcher bodySwitcher) {
		this.abstractFunctionCall = abstractFunctionCall;
		this.bodySwitcher = bodySwitcher; 
	}

	@Override
	public void execute(String functionId) {
		if(abstractFunctionCall instanceof FunctionCall) {
			FunctionDefinition functionDefinition = ((FunctionCall) abstractFunctionCall).getFunction();
			String functionName = functionDefinition.getName();			
			EList<FunctionParameter> functionParameters = functionDefinition.getParameters();
			EList<Atomic> calleeArguments = abstractFunctionCall.getParameters();
			functionId = pushCallStack(functionName);
			addSymbolTable(functionParameters, calleeArguments, functionId);
			
			for(Body bodyElement: functionDefinition.getBody()) {
				if(isReturn) {
					break;
				} 
				bodySwitcher.executor(bodyElement, functionId);
			}
		} else if(abstractFunctionCall instanceof BuiltinFunctionCall) {
			builtinFunctionExecutor((BuiltinFunctionCall) abstractFunctionCall, functionId);
		}
		isReturn = false;
	}
	
	private void builtinFunctionExecutor(BuiltinFunctionCall builtinFunctionCall, String functionId) {
		String functionName = builtinFunctionCall.getFunction();
		Symbol symbol = null;
		String value;
		
		switch(functionName) {
			case "print":
				symbol = lookupSymbolByAtomic(builtinFunctionCall.getParameters().get(0), functionId);
				value = (String) symbol.getVariableValue().toString();
				System.out.println(value);
				break;
		}
	}
	
	private void addSymbolTable(EList<FunctionParameter> definitionParameters, EList<Atomic> calleeArgs, String functionId) {
//		Atomic is caller, FunctionParameter is callee.
		for(Atomic atomic: calleeArgs) {
			Symbol symbol = lookupSymbolByAtomic(atomic, getCallerFunctionId(functionId));
			FunctionParameter functionParameter = definitionParameters.get(calleeArgs.indexOf(atomic));
			
			Symbol toAdd = new Symbol(functionParameter.getType().toString(), functionParameter.getName(), symbol.getVariableValue());
			addCallStackBySymbol(toAdd, functionId);
		}
	}
	
	private String getCallerFunctionId(String functionId) {
//		Caller is <last item> - 1
		int callerIndex = CallStack.getCallStack().size() - 1 - 1;
		
		CallStackItem item = CallStack.getCallStack().get(callerIndex);
		return item.getFunctionId();
	}
	
	private String pushCallStack(String functionName) {
		int size = CallStack.getCallStack().size();
		String functionId = functionName + '_' + String.valueOf(size);
		
		pushCallStackItem(functionId);
		return functionId;
	}

}
