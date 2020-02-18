package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.ComparisionExpression;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.ForExpression;
import org.xtext.example.mydsl.myDsl.Operation;


public class ForExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    ForExpression expression;
    ExpressionSwitcher executor;
    
    public ForExpressionExecutor(ForExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    
    @Override
    public void execute(String id) {
        Atomic initial_atomic = this.expression.getInitial_variable();
        Atomic update_atomic = this.expression.getUpdate_variable();
        
        Symbol initial_symbol = lookupSymbolByAtomic(initial_atomic, id);
        Symbol update_symbol = lookupSymbolByAtomic(update_atomic, id);
        Object value = null;
        
        value = VariableAssignmentExpressionExecutor.evaluateValue((Operation) this.expression.getInitial_value().getExpression(), id, value, initial_symbol.getType());
        updateCallStackBySymbol(initial_symbol, value);
        
        ComparisionExpression test = expression.getTest();
        
        while (checkCondition(test, id)) {
            // Execute body of for loop
            for (Object exp: this.expression.getBody().getBody()) {
                if (isBreak) {
                    break;
                }
                executor.execute((Expression) exp, id);
            }
            
            if (isBreak) {
//                Last executor may be break.
                break;
            }
            
            // Update update_symbol
            value = VariableAssignmentExpressionExecutor.evaluateValue((Operation) this.expression.getUpdate_value().getExpression(), id, value, update_symbol.getType());
            updateCallStackBySymbol(update_symbol, value);
        }
    
        isBreak = false;
    }
    
}
