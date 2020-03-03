package org.xtext.example.mydsl.debugger.processing.expression;


import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction;
import org.xtext.example.mydsl.myDsl.CustomFunctionCallExpression;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression;;


public class VariableDeclerationExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    VariableDeclerationExpression expression;
    ExpressionSwitcher executor;
    
    public VariableDeclerationExpressionExecutor(VariableDeclerationExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }

    @Override
    public void execute(String id) {
        Symbol symbol = executeVariableDeclarationExpression(this.expression, this.executor, id);
        addCallStackBySymbol(symbol, id);
    }
    
    public static Symbol executeVariableDeclarationExpression(VariableDeclerationExpression expression, ExpressionSwitcher executor, String id) {
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
        
        if (expression.getAssignment() != null) {
            EObject assignmentExpression = expression.getAssignment().getExpression();
            if (assignmentExpression != null) {
                //  expression = (OperationExpression | ArrayAssignment | BuiltinRandomFunction | CustomFunctionCallExpression)
                if (assignmentExpression instanceof Operation) {
                    value = VariableAssignmentExpressionExecutor.evaluateValue((Operation) assignmentExpression, id, value, type);
                } else if (assignmentExpression instanceof ArrayAssignment){                    
                    value = VariableAssignmentExpressionExecutor.evaluateValue((ArrayAssignment) assignmentExpression, id, (Object[]) value, size, type);
                } else if (assignmentExpression instanceof BuiltinRandomFunction) {
                    value = BuiltinFunctionCallExpressionExecutor.builtinRandomFunction();
                } else if (assignmentExpression instanceof CustomFunctionCallExpression) {
                    if (CustomFunctionCallExpressionExecutor.validateParameters((CustomFunctionCallExpression) assignmentExpression, id)) {
                        value = CustomFunctionCallExpressionExecutor.call((CustomFunctionCallExpression) assignmentExpression, executor, id);
                    }
                }
            } else {
                System.out.println("Typo while defining local variable " + name);
                System.exit(0);
            }
        }
        
        Symbol symbol = new Symbol(name, type, value, scope);
        return symbol;
    }
    
}
