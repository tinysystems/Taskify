package org.xtext.example.mydsl.debugger.processing.expression;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.SharedVariableExpression;


public class SharedVariableExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    SharedVariableExpression expression;
    ExpressionSwitcher executor;
    
    public SharedVariableExpressionExecutor(SharedVariableExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }

    @Override
    public void execute(String id) {
        String name = expression.getName();
        String type = expression.getType();
        String scope = id;
        Object value = null;
        int size = 0;
        if (expression.getDimension() != null) {
            Atomic atomicIndex = expression.getDimension().getIndex();
            size = Integer.parseInt(decoupleAtomic(atomicIndex, id).toString());
            Object array[] = generateArrayValue(type, size);
            value = array;
        } else {
            value = generateValue(type);
        }
        
        Symbol symbol = new Symbol(name, type, value, scope);
        addCallStackBySymbol(symbol, id);
    }
    
}
