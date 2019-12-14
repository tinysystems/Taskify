package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Arrays;
import java.util.List;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;


public class OperationExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	OperationExpression expression;
	ExpressionSwitcher executor;
	
	final static String[] arithmeticOperators = new String[] {"+", "-", "*", "/"};
	
	public OperationExpressionExecutor(OperationExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	private String getParentId() {
		CallStackItem item = CallStack.getCallStack().getLast();
		return item.getId();
	}
	
	private boolean isArithmetic(String operator) {
		final List<String> list = Arrays.asList(OperationExpressionExecutor.arithmeticOperators);
		return list.contains(operator);
	}
	
//	private Object arithmeticCalculate(Object left, String operator, Object right) {
//		Object result = calculate(left, operator, right);
//		return result;
//	}
	
	public int arithmeticCalculate(int left, String operator, int right) {
		int result = 0;

		switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
		}
		return result;
	}
	
	public double arithmeticCalculate(double left, String operator, double right) {
		double result = 0.00;
		
		switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
		}
		return result;
	}
	
//	private Object calculate(Object left, String operator, Object right) {
//		double result = 0;
//		double leftVal = Double.parseDouble(left.toString());
//		double rightVal = Double.parseDouble(right.toString());
//		
//		switch(operator) {
//			case "+":
//				result = leftVal + rightVal;
//				break;
//			case "-":
//				result = leftVal - rightVal;
//				break;
//			case "*":
//				result = leftVal * rightVal;
//				break;
//			case "/":
//				result = leftVal / rightVal;
//				break;
//		}
//		return (Object) result;
//	}
	
	@Override
	public void execute(String id) {
		if (expression instanceof Operation) {
			Atomic atomic = ((Operation) expression).getLeft();
			Symbol symbol = lookupSymbolByAtomic(atomic, id);
			Object result = null;
			Object rightVal = null;
			
			for (String operator: ((Operation) expression).getOperator()) {
				int operatorIndex = ((Operation) expression).getOperator().indexOf(operator);
				Atomic atomicValue = ((Operation) expression).getRight().get(operatorIndex);
				
				
				rightVal = decoupleAtomic(atomicValue, id);
				if (operator.equals("=")) {
					result = (Object) rightVal;
				} else if (isArithmetic(operator)) {
					switch (symbol.getType()) {
						case "integer":
							result = arithmeticCalculate((int) result, operator, (int) rightVal);
							break;
						case "double":
							result = arithmeticCalculate((double) result, operator, (double) rightVal);
							break;
					}
					
				}
			}
			
			
			if (atomic instanceof ArrayReference) {
				updateCallStackByArray((ArrayReference) atomic, result, getParentId());
			} else {
				updateCallStackByAtomic(atomic, result, getParentId());
			}
		}
	}
}
