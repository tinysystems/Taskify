package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ComparisionExpression;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.ForExpression;
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression;


public class ForExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    ForExpression expression;
    ExpressionSwitcher executor;
    
    public ForExpressionExecutor(ForExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    
    @Override
    public void execute(String id) {
        VariableAssignmentExpression initial = this.expression.getInitial();
        VariableAssignmentExpression update = this.expression.getUpdate();
        ComparisionExpression test = this.expression.getTest();
        
        // Execute initial part(VariableAssignmentExpression)
        this.executor.execute(initial, id);
        
        while (checkCondition(test, id)) {
            // Execute body of for loop
            for (Object exp: this.expression.getBody().getBody()) {
                if (isBreak) {
                    break;
                }
                // Execute update part(VariableAssignmentExpression)
                executor.execute((Expression) exp, id);
            }
            
            // Last executor may be break.
            if (isBreak) {
                break;
            }
            
            // Update update_symbol
            this.executor.execute(update, id);
        }
        isBreak = false;
    }
    
}
