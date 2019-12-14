package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractLogicalHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;
import org.xtext.example.mydsl.myDsl.WhileExpression;


public class WhileExpressionExecutor extends AbstractLogicalHelper implements IExpressionExecutor {
	WhileExpression expression;
	ExpressionSwitcher executor;
	
	public WhileExpressionExecutor(WhileExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	@Override
	public void execute(String id) {
		Symbol leftSymbol = getLeftSymbol(this.expression.getTest(), id);
		int rightValue = getRightSymbolValue(expression.getTest(), id);
		int leftValue = (int) leftSymbol.getVariableValue();
		
		String operator = "";
		if(expression.getTest() instanceof Operation) {
			operator = ((Operation) expression.getTest()).getOperator().get(0);
		}
		
		while (compare(leftValue, operator, rightValue)) {
			if(isBreak) {
				break;
			}
			
			for(Object exp: expression.getBody().getBody()) {
				if(isBreak) {
					break;
				}
				executor.execute((Expression)exp, id);
			}
			
			if(isBreak) {
//				Last executor may be break.
				break;
			} else {
				leftValue = (int) getLeftSymbol(this.expression.getTest(), id).getVariableValue();
			}
		}
//		Make it reset
		isBreak = false;
	}
	
	private int getRightSymbolValue(OperationExpression expression, String id) {
//		TODO it could also be boolean and double
		int value = 0;
		if(expression instanceof Operation) {
			Atomic atomicRight = ((Operation) expression).getRight().get(0);
			
			Symbol symbol = lookupSymbolByAtomic(atomicRight, id);
			value = (int) symbol.getVariableValue();
		}
		return value;
	}
	
	private Symbol getLeftSymbol(OperationExpression expression, String id) {
		Symbol symbol = null;
		if(expression instanceof Operation) {
			Atomic atomicLeft = ((Operation) expression).getLeft();
			symbol = lookupSymbolByAtomic(atomicLeft, id);
		}
		return symbol;
	}
}
