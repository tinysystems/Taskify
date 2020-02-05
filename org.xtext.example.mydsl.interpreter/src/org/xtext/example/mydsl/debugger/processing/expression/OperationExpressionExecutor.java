package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.Calculator;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;
//import org.xtext.example.mydsl.debugger.context.Symbol;
//import org.xtext.example.mydsl.myDsl.ArrayReference;

public class OperationExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	OperationExpression expression;
	ExpressionSwitcher executor;
	
//	final static String[] arithmeticOperators = new String[] {"+", "-", "*", "/"};
	
	public OperationExpressionExecutor(OperationExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	@Override
	public void execute(String id) {
		/* Never called for the moemnt since evaluateOperationExpression() is public */
		/*if (expression instanceof Operation) {
			Object value = null;
			Object rightVal = null;
			Atomic variable = ((Operation) expression).getLeft();
			Symbol symbol = lookupSymbolByAtomic(variable, id);
			
			
			String firstOperator = ((Operation) expression).getOperator().get(0);
			
			if (firstOperator.equals("=")) {
//				value = evaluateOperationExpression(((Operation) expression).getRight(), id, symbol.getType());
			}
			
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
		}*/
	}
	
	public static int BINARY_P = 6;
	public static int SQRT_P = 5;
	public static int OPEN_BRACKETS_P = 4;
	public static int CLOSE_BRACKETS_P = 3;
	public static int MULT_DIV_P = 2;
	public static int SUM_SUB_P = 1;
	public static int INVALID_P = -1;
	
	public static int precedence(String operator) {
		int precedence;
		switch(operator) {
			case Calculator.BITWISE_AND: case Calculator.BITWISE_OR: case Calculator.SHIFT_LEFT: case Calculator.SHIFT_RIGHT:
				precedence = BINARY_P;
				break;
			case Calculator.SQUARE:
				precedence = SQRT_P;
				break;
			case Calculator.OPEN_BRACKET:
				precedence = OPEN_BRACKETS_P;
				break;
			case Calculator.CLOSE_BRACKET:
				precedence = CLOSE_BRACKETS_P;
				break;
			case Calculator.MULT: case Calculator.DIV:
				precedence = MULT_DIV_P;
				break;
			case Calculator.PLUS: case Calculator.MINUS:
				precedence = SUM_SUB_P;
				break;
			default:
				precedence = INVALID_P;
		}
		return precedence;
	}
	
	public static Object[] evaluateArrayValues(ArrayAssignment expression, String id, int size) {
		Object values[] = null;
		if (expression != null) {
			if (size > 0) {
				values = new Object[size];
				int index = 0;
				for (Atomic atomicValue: expression.getVariable()) {
					Object value = decoupleAtomic(atomicValue, id);
					values[index] = value;
					index++;
				}
			}
		}
		return values;
	}
	
	public static Object evaluateOperationExpression(Operation expression, String id, String type) {
		Object result = null;
		if (expression != null) {			
			Atomic atomicValue = expression.getLeft();
			result = decoupleAtomic(atomicValue, id);
			
			if (expression.getOperator().size() > 0) {
				Stack<Object> stack = new Stack<>();
				stack = infixToPostfix(expression, id);
				
				if (!stack.isEmpty()) {
					result = evaluatePostfix(stack, type);
				}			
			}
		}
		return result;
	}
	
	private static Stack<Object> infixToPostfix(Operation expression, String id) {
		Object leftVal = null;
		Object rightVal = null;
		Stack<String> operatorStack = new Stack<>();
		Stack<Object> stack = new Stack<>();
		Atomic atomicValue = expression.getLeft();
		EList<String> operators = expression.getOperator();
		
		leftVal = decoupleAtomic(atomicValue, id);
		stack.push(leftVal);
		
		for (int operatorIndex = 0; operatorIndex < operators.size(); operatorIndex++) {
			String operator = operators.get(operatorIndex);
			int precedence = precedence(operator);
			
			if (precedence > 0) {
				atomicValue = expression.getRight().get(operatorIndex);
				rightVal = decoupleAtomic(atomicValue, id);
				
				if (precedence == OPEN_BRACKETS_P) {
					operatorStack.push(operator);
				} else if (precedence == CLOSE_BRACKETS_P) {
					String op = operatorStack.pop();
					while (op != "(") {
						stack.push(op);
						op = operatorStack.pop();
					}
				} else {
					while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence) {
						stack.push(operatorStack.pop());
					}
					operatorStack.push(operator);
				}
				stack.push(rightVal);
			}
		}
		for (int i = 0; i <= operatorStack.size(); i++) {
			stack.push(operatorStack.pop());
		}
		
		return stack;
	}
	
	private static Object evaluatePostfix(Stack<Object> stack, String type) {
		Object leftValue = null, rightValue = null;
		Stack<Object> results = new Stack<>();
		
		for (int i = 0; i < stack.size(); i++) {
			Object item = stack.get(i);
			if (item instanceof String) {
				String operator = (String) item;
				rightValue =  results.pop();
				leftValue = results.pop();
				
				leftValue = Calculator.calculate(leftValue, operator, rightValue, type);
				
				results.push(leftValue);						
			} else {
				// Atomic values
				results.push(item);
			}
		}	
		return leftValue;
	}
	
}
