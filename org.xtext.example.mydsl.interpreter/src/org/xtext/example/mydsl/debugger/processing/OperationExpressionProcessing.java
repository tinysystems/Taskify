package org.xtext.example.mydsl.debugger.processing;

import java.util.Arrays;
import java.util.List;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.myDsl.AbstractFunctionCall;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.AtomicOrFunctionCall;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;


public class OperationExpressionProcessing extends AbstractLogicalHelper implements IBody {

	OperationExpression operationExpression;
	BodySwitcher bodySwitcher;
	
	public OperationExpressionProcessing(OperationExpression operationExpression, BodySwitcher bodySwitcher) {
		this.operationExpression = operationExpression;
		this.bodySwitcher = bodySwitcher;
	}
	
	@Override
	public void execute(String functionId) {
		if(operationExpression instanceof Operation) {
			Object returnValue = null;
			Object rightValue = null;
			
			for(String operator:((Operation) operationExpression).getOperator()) {
			
				int operatorIndex = ((Operation) operationExpression).getOperator().indexOf(operator);
				AtomicOrFunctionCall atomicOrFunctionCall = ((Operation) operationExpression).getRight().get(operatorIndex);
				
				if(atomicOrFunctionCall instanceof Atomic) {
					rightValue = decoupleAtomic((Atomic) atomicOrFunctionCall, functionId);
				} else if(atomicOrFunctionCall instanceof AbstractFunctionCall) {
					rightValue = atomicOrFunctionExecutor(atomicOrFunctionCall, functionId);
				}
				

				if(operator.equals("=")) {
					returnValue = rightValue;
				} else if(isArithmetic(operator)) {
					returnValue = arithmeticCalculate(returnValue, operator, rightValue);
				}
				
//				Atomic atomic = ((Operation) operationExpression).getLeft();
//				if(atomic instanceof ArrayReference) {
//					updateCallStackByArray((ArrayReference) atomic, returnValue, getCallerFunctionId());
//				} else {
//					updateCallStackByAtomic(atomic, returnValue, functionId);
//				}		
			}	
			Atomic atomic = ((Operation) operationExpression).getLeft();
			if(atomic instanceof ArrayReference) {
				updateCallStackByArray((ArrayReference) atomic, returnValue, getCallerFunctionId());
			} else {
				updateCallStackByAtomic(atomic, returnValue, functionId);
			}
		}
	}
	
	private String getCallerFunctionId() {
		CallStackItem item = CallStack.getCallStack().getLast();
		return item.getFunctionId();
	}
	
	private boolean isArithmetic(String operator) {
		final String[] arithmeticOperators = new String[]{"+", "-", "*", "/"};
		final List<String> list = Arrays.asList(arithmeticOperators);
		return list.contains(operator);
	}
	
	@SuppressWarnings("unused")
	private int arithmeticCalculate(int left, String operator, int right) {
		int result = (int) calculate(left, operator, right);
		return result;
	}
	
	private Object arithmeticCalculate(Object left, String operator, Object right) {
		Object result = calculate(left, operator, right);
		return result;
	}
	
	private Object calculate(Object left, String operator, Object right) {
		double result = 0;
//		double leftVal = (double) left;
		double leftVal = (double) Double.parseDouble(left.toString());
		double rightVal = (double) Double.parseDouble(right.toString());
		
		switch(operator) {
			case "+":
				result = leftVal + rightVal;
				break;
			case "-":
				result = leftVal - rightVal;
				break;
			case "*":
				result = leftVal * rightVal;
				break;
			case "/":
				result = leftVal / rightVal;
				break;
		}
		return (Object) result;
	}
	
	protected Object atomicOrFunctionExecutor(AtomicOrFunctionCall atomicOrFunctionCall, String functionId) {
		Object returnValue = null;
		
		if(atomicOrFunctionCall instanceof Atomic) {
			returnValue = decoupleAtomic((Atomic) atomicOrFunctionCall, functionId);
		} else if (atomicOrFunctionCall instanceof AbstractFunctionCall) {
			bodySwitcher.executor((AbstractFunctionCall) atomicOrFunctionCall, functionId);
			returnValue = lastFunctionReturn;
		}
		
		return returnValue;
	}

}
