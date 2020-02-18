package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.BreakExpression;


public class BreakExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    BreakExpression expression;
    ExpressionSwitcher executor;
    
    public BreakExpressionExecutor(BreakExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }

    @Override
    public void execute(String id) {
        isBreak = true;
    }

}
