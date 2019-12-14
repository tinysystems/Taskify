package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractLogicalHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.IfExpression;
import org.xtext.example.mydsl.myDsl.OperationExpression;


public class IfExpressionExecutor extends AbstractLogicalHelper implements IExpressionExecutor {
	IfExpression expression;
	ExpressionSwitcher executor;
	
	public IfExpressionExecutor(IfExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	
	@Override
	public void execute(String id) {
		List<OperationExpression> elseIfConditions = this.expression.getElseifcondition();
		if (checkCondition(this.expression.getIfcondition(), id)) {
			for (Object body: this.expression.getIfbody().getBody()) {
				if (isBreak) {
					break;
				} else {
					executor.execute((Expression) body, id);
				}
			}
		} else if (elseIfConditions.size() > 0) {
			for (int index = 0; index < elseIfConditions.size(); index++) {
				OperationExpression exp = elseIfConditions.get(index);
				
				if (checkCondition(exp, id)) {
					EList<EObject> bodyList = this.expression.getElseifbody().get(index).getBody();
					for (Object body: bodyList) {
						if (isBreak) {
							break;
						} else {
							executor.execute((Expression) body, id);
						}
						if (isBreak) {
							break;
						}
					}
				}
				if (isBreak) {
					break;
				}
			}
		} else if (this.expression.getElsebody() != null){
			for (Object body: this.expression.getElsebody().getBody()) {
				if (isBreak) {
					break;
				} else {
					executor.execute((Expression) body, id);
				}
			}
		}
	}
}
