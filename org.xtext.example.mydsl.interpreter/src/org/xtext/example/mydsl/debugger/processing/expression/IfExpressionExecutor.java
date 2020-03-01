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
        boolean ifOrElseifExecuted = false;
        int elseIfConditionCount = this.expression.getElseifcondition().size();
        if (checkCondition(this.expression.getIfcondition(), id)) {
            ifOrElseifExecuted = true;
            executeBody(this.expression.getIfbody().getBody(), id);
        } else if (elseIfConditionCount > 0) {
            // TODO
            List<ComparisionExpression> elseIfConditions = this.expression.getElseifcondition();
            for (int index = 0; index < elseIfConditionCount; index++) {
                EObject exp = elseIfConditions.get(index);
                
                if (checkCondition(exp, id)) {
                    ifOrElseifExecuted = true;
                    EList<EObject> bodyList = this.expression.getElseifbody().get(index).getBody();
                    executeBody(bodyList, id);
                    break;
                }
            }
        }
        
        if (!ifOrElseifExecuted) {
            if (this.expression.getElsebody() != null) {
                executeBody(this.expression.getElsebody().getBody(), id);
            }
        }
    }
    
    private void executeBody(EList<EObject> bodyList, String id) {
        if (bodyList.size() > 0){
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
    }
}
