package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.processing.AbstractLogicalHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ForExpression;
import org.xtext.example.mydsl.myDsl.OperationExpression;


public class ForExpressionExecutor extends AbstractLogicalHelper implements IExpressionExecutor {
	ForExpression expression;
	ExpressionSwitcher executor;
	
	public ForExpressionExecutor(ForExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	@Override
	public void execute(String id) {
		// TODO Auto-generated method stub
		OperationExpression initial = expression.getInitial();
		this.executor.execute(initial, id);
		
		
		
		OperationExpression test = expression.getTest();
		System.out.println(test.toString());
		
		OperationExpression update = expression.getUpdate();
		this.executor.execute(update, id);
	}
	
//	private Symbol getInitialSymbol(OperationExpression expression, String id) {
//		Symbol symbol = null;
//		if (expression instanceof Operation) {
//			Atomic atomic = ((Operation) expression).getLeft();
//			symbol = lookupSymbolByAtomic(atomic, id);
//		} 
//		return symbol;
//	}
//	
//	private int getInitialValue(OperationExpression expression, String id) {
//		int value = 0;
//		if (expression instanceof Operation) {
//			Atomic atomic = ((Operation) expression).getRight().get(0);
//			Symbol symbol = lookupSymbolByAtomic(atomic, id);
//			value = (int) symbol.getVariableValue();
//		}
//		return value;
//	}
//	
//	private Symbol getTestSymbol(Operaration)

}
