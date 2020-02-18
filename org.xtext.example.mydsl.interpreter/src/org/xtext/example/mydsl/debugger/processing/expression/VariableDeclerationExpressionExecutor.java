package org.xtext.example.mydsl.debugger.processing.expression;


import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
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
        Symbol symbol = executeVariableDeclarationExpression(this.expression, id);
        addCallStackBySymbol(symbol, id);
    }
    
    public static Symbol executeVariableDeclarationExpression(VariableDeclerationExpression expression, String id) {
        String name = expression.getName();
        String type = expression.getType();
        String scope = id;
        Object value = null;
        int size = 0;
        if (expression.getDimension() != null && expression.getDimension().getSize() > 0) {
            size = expression.getDimension().getSize();
            Object array[] = generateArrayValue(type, size);
            value = array;
        } else {
            value = generateValue(type);
        }
        
        if (expression.getAssignment() != null) {
            EObject assignmentExpression = expression.getAssignment().getExpression();
            if (assignmentExpression != null) {
                if (assignmentExpression instanceof Operation) {
                    value = VariableAssignmentExpressionExecutor.evaluateValue((Operation) assignmentExpression, id, value, type);
                } else if (assignmentExpression instanceof ArrayAssignment){                    
                    value = VariableAssignmentExpressionExecutor.evaluateValue((ArrayAssignment) assignmentExpression, id, (Object[]) value, size, type);
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
