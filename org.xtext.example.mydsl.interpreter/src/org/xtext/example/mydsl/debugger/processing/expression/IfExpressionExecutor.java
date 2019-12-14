package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractLogicalHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.IfExpression;
import org.xtext.example.mydsl.myDsl.Operation;
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
		if (checkIfCondition(this.expression.getIfcondition(), id)) {
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
				
				if (checkIfCondition(exp, id)) {
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
//			for (OperationExpression exp: elseIfConditions) {
//				if (checkIfCondition(exp, id)) {
//					for (Object body: this.expression.getElseifbody()) {
//						if (isBreak) {
//							break;
//						} else {
//							executor.execute((Expression) body, id);
//						}
//					}
//					break;
//				}
//				if (isBreak) {
//					break;
//				}
//			}
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
	
	private boolean checkIfCondition(OperationExpression expression, String id) {
		boolean isApplicable = false;
		
		if(expression instanceof Operation) {
			Atomic atomicLeft = (Atomic) ((Operation) expression).getLeft();
			Object left = decoupleAtomic(atomicLeft, id);
			List<Atomic> atomicList = ((Operation) expression).getRight();
			
			Atomic atomicRight = null;
			if(atomicList != null && atomicList.size() > 0) {
				atomicRight = atomicList.get(0);			
			
				Object right = decoupleAtomic(atomicRight, id);
				String operator = ((Operation) expression).getOperator().get(0);
				
				if (left instanceof Integer) {
					isApplicable = compare((int) left, operator, (int) right);
				} else if (left instanceof Double) {
					isApplicable = compare((double) left, operator, (double) right);
				} else if (left instanceof Boolean) {
					isApplicable = compare((boolean) left, operator, (boolean) right);
				}
			} else {
//				ex: if(boolean)
				isApplicable = compare(left);
			}
			
		}
		return isApplicable;
	}
}
