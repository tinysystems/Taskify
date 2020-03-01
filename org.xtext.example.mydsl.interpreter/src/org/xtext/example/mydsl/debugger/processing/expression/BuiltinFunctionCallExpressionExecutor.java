package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Arrays;
import java.util.Random;

import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression;
import org.xtext.example.mydsl.myDsl.BuiltinPrintFunction;
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction;
import org.xtext.example.mydsl.myDsl.FunctionCallParameters;


public class BuiltinFunctionCallExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    BuiltinFunctionCallExpression expression;
    ExpressionSwitcher executor;
    
    public BuiltinFunctionCallExpressionExecutor(BuiltinFunctionCallExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    @Override
    public void execute(String id) {
        if (this.expression instanceof BuiltinPrintFunction) {
            builtinPrintFunction((BuiltinPrintFunction) this.expression, id);
        } else if (this.expression instanceof BuiltinRandomFunction) {
            //builtinRandomFunction((BuiltinRandomFunction) this.expression, id);
        }
    }

    private void builtinPrintFunction(BuiltinPrintFunction function, String id) {
        FunctionCallParameters parameters = function.getParameters();
        int parameterCount = parameters.getParameter().size();
        
        for (int i = 0; i < parameterCount; i++) {
            String output = null;
            Object value = null;
            Atomic atomic = parameters.getParameter().get(i);
            
            value = decoupleAtomic(atomic, id);
            
            if (value instanceof Object[]) {
                // Print an entire array
                output = Arrays.toString((Object[]) value);
            } else {
                // Print a variable
                output = value.toString();
            }
            System.out.print(output);
        }
        System.out.println();
    }
    
    public static int builtinRandomFunction() {
        Random random=new Random();
        int value = random.nextInt(RAND_MAX);
        return value;
    }
}
