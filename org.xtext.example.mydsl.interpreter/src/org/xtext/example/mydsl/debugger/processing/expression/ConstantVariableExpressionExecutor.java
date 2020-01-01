package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.Calculator;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression;
import org.xtext.example.mydsl.myDsl.Operation;


public class ConstantVariableExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	ConstantVariableExpression expression;
	ExpressionSwitcher executor;
	
	public ConstantVariableExpressionExecutor(ConstantVariableExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}

	@Override
	public void execute(String id) {
		String name = expression.getName();
		String type = expression.getType().getType();
		String scope = "constant";
		Symbol symbol = new Symbol(name, type, scope);
		
		if(expression.getDimension() != null && expression.getDimension().getSize() > 0) {
			int size = expression.getDimension().getSize();
			setArrayValue(symbol, size);
		}
		addToSymbolTable(symbol, id);
		
		if (expression.getOperator() != null) {
			EObject rightExpression= expression.getValue().getExpression();
			if (rightExpression != null) {
				if (rightExpression instanceof Operation) {
					operateValue((Operation) rightExpression, symbol, id);
				} else if (rightExpression instanceof ArrayAssignment){					
					operateValue((ArrayAssignment) rightExpression, symbol, id);
				}
			} else {
				System.out.println("Typo while defining constant variable " + name);
				System.exit(0);
			}
		}
	}
	
	private void operateValue(Operation expression, Symbol symbol, String id) {
		if (expression != null) {
			Object value = null;
			Object rightVal = null;
			
			Atomic atomicValue = expression.getLeft();
			value = decoupleAtomic(atomicValue, id);
			
			if (expression.getOperator().size() > 0) {
				for (String operator: expression.getOperator()) {
					int operatorIndex = expression.getOperator().indexOf(operator);
					atomicValue = expression.getRight().get(operatorIndex);
					
					rightVal = decoupleAtomic(atomicValue, id);
					if (Calculator.isArithmetic(operator)) {
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
			}
			updateCallStackBySymbol(symbol, value);
		}
	}
	
	private void operateValue(ArrayAssignment expression, Symbol symbol, String id) {
		if (expression != null) {
			int size = ((Object[])symbol.getVariableValue()).length;
			if (size > 0) {
				Object values[] = new Object[size];
				int index = 0;
				for (Atomic atomicValue: ((ArrayAssignment) expression).getVariable()) {
					Object value = decoupleAtomic(atomicValue, id);
					values[index] = value;
					index++;
				}
				updateCallStackBySymbol(symbol, values);
			}
		}
	}

}
