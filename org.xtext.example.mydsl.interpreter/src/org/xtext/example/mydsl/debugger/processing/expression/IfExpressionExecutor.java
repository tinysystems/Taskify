package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ComparisionExpression;
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
        int index = -1;
        EList<EObject> bodyList = null;
        int elseIfConditionCount = this.expression.getElseifcondition().size();

        if (checkCondition(this.expression.getIfcondition(), id)) {
            bodyList = this.expression.getIfbody().getBody();
        } else if (elseIfConditionCount > 0) {
            List<ComparisionExpression> elseIfConditions = this.expression.getElseifcondition();
            
            for (index = 0; index < elseIfConditionCount; index++) {
                EObject exp = elseIfConditions.get(index);
                if (checkCondition(exp, id)) {
                    bodyList = this.expression.getElseifbody().get(index).getBody();
                    break;
                }
            }
        }
        
        if (bodyList == null && this.expression.getElsebody() != null) {
            bodyList = this.expression.getElsebody().getBody();
        }
        
        if (bodyList != null) {
            executeBody(bodyList, id);
        }
    }
    
    private void executeBody(EList<EObject> bodyList, String id) {
        executeExpressionList(bodyList, this.executor, id);
    }
}
