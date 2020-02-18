package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ComparisionExpression;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.IfExpression;


public class IfExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    IfExpression expression;
    ExpressionSwitcher executor;
    
    public IfExpressionExecutor(IfExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    
    @Override
    public void execute(String id) {
        int elseIfConditionCount = this.expression.getElseifcondition().size();
        if (checkCondition(this.expression.getIfcondition(), id)) {
            for (Object body: this.expression.getIfbody().getBody()) {
                if (isBreak) {
                    break;
                } else {
                    executor.execute((Expression) body, id);
                }
            }
        } else if (elseIfConditionCount > 0) {
            // TODO
            List<ComparisionExpression> elseIfConditions = this.expression.getElseifcondition();
            for (int index = 0; index < elseIfConditionCount; index++) {
                EObject exp = elseIfConditions.get(index);
                
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
