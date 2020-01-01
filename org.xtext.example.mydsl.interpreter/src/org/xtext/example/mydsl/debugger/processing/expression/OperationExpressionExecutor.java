package org.xtext.example.mydsl.debugger.processing.expression;

//import java.util.Arrays;
//import java.util.List;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.Calculator;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;


public class OperationExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	OperationExpression expression;
	ExpressionSwitcher executor;
	
//	final static String[] arithmeticOperators = new String[] {"+", "-", "*", "/"};
	
	public OperationExpressionExecutor(OperationExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	private String getParentId() {
		CallStackItem item = CallStack.getCallStack().getLast();
		return item.getId();
	}
	
//	private boolean isArithmetic(String operator) {
//		final List<String> list = Arrays.asList(OperationExpressionExecutor.arithmeticOperators);
//		return list.contains(operator);
//	}
	
//	public int arithmeticCalculate(int left, String operator, int right) {
//		int result = 0;
//
//		switch(operator) {
//			case "+":
//				result = left + right;
//				break;
//			case "-":
//				result = left - right;
//				break;
//			case "*":
//				result = left * right;
//				break;
//			case "/":
//				result = left / right;
//				break;
//		}
//		return result;
//	}
//	
//	public double arithmeticCalculate(double left, String operator, double right) {
//		double result = 0.00;
//		
//		switch(operator) {
//			case "+":
//				result = left + right;
//				break;
//			case "-":
//				result = left - right;
//				break;
//			case "*":
//				result = left * right;
//				break;
//			case "/":
//				result = left / right;
//				break;
//		}
//		return result;
//	}
	
	@Override
	public void execute(String id) {
		if (expression instanceof Operation) {
			Atomic variable = ((Operation) expression).getLeft();
			Symbol symbol = lookupSymbolByAtomic(variable, id);
			Object value = null;
			Object rightVal = null;
			
//			TODO: Add operator priority 8+3*5 is calculated wrong as 55 instead of 23
			for (String operator: ((Operation) expression).getOperator()) {
				int operatorIndex = ((Operation) expression).getOperator().indexOf(operator);
				Atomic atomicValue = ((Operation) expression).getRight().get(operatorIndex);
				
				rightVal = decoupleAtomic(atomicValue, id);
				if (operator.equals("=")) {
					value = (Object) rightVal;
				} else if (Calculator.isArithmetic(operator)) {
					switch (symbol.getType()) {
						case "integer":
							value = Calculator.arithmeticCalculate((int) value, operator, (int) rightVal);
							break;
						case "double":
							value = Calculator.arithmeticCalculate((double) value, operator, (double) rightVal);
							break;
					}
					
				}
			}
			
			if (variable instanceof ArrayReference) {
				updateCallStackByArray((ArrayReference) variable, value, getParentId());
			} else {
				updateCallStackByAtomic(variable, value, getParentId());
			}
		}
	}
}
