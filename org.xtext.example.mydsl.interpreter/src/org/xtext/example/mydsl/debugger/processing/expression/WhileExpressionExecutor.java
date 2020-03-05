package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.WhileExpression;


public class WhileExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    WhileExpression expression;
    ExpressionSwitcher executor;
    
    public WhileExpressionExecutor(WhileExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    
    @SuppressWarnings ("unchecked")
    @Override
    public void execute(String id) {    
        while (checkCondition(this.expression.getTest(), id)) {            
            executeExpressionList((EList<EObject>)(EList<?>) this.expression.getBody().getBody(), this.executor, id);
            
            if (isBreak) {
                // Last executor may be break.
                break;
            }
        }
        // Make it reset
        isBreak = false;
    }
}
