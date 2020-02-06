package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.WhileExpression;


public class WhileExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	WhileExpression expression;
	ExpressionSwitcher executor;
	
	public WhileExpressionExecutor(WhileExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	@Override
	public void execute(String id) {	
		while (checkCondition(this.expression.getTest(), id)) {			
			for (Object exp: this.expression.getBody().getBody()) {
				if (isBreak) {
					break;
				}
				executor.execute((Expression) exp, id);
			}
			
			if (isBreak) {
//				Last executor may be break.
				break;
			}
		}
//		Make it reset
		isBreak = false;
	}
}
