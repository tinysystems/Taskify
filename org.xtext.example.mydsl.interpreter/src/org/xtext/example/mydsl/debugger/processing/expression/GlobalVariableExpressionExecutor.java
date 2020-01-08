package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression;
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression;


public class GlobalVariableExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	GlobalVariableExpression expression;
	ExpressionSwitcher executor;
	
	public GlobalVariableExpressionExecutor(GlobalVariableExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}

	@Override
	public void execute(String id) {
		VariableDeclerationExpression variableDeclerationExpression = this.expression.getDeclarationExpression();
		Symbol symbol = VariableDeclerationExpressionExecutor.executeVariableDeclarationExpression(variableDeclerationExpression, id);
		addCallStackBySymbol(symbol, id);
	}
	
}
