package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.CustomFunctionCallExpression;
import org.xtext.example.mydsl.myDsl.FunctionDefinitionParameters;

public class CustomFunctionCallExpressionExecutor extends AbstractStackHelper {    
    public static Object call(CustomFunctionCallExpression expression, ExpressionSwitcher executor, String id) {
        EList<Atomic> callParameters = expression.getParameters().getParameter();
        FunctionDefinitionParameters functionDefinitionTypedParameters = expression.getFunction().getParameters();
        String callerId = id;
        id = expression.getFunction().getName() + "_" + id;
        
        addCallStack(functionDefinitionTypedParameters, callParameters, id, callerId);
        
        EList<EObject> body = expression.getFunction().getBody();
        
        for (EObject bodyElement: body) {
            executor.execute(bodyElement, id);
        }
        
        Atomic returnValue = expression.getFunction().getReturn();
        Object value = lookupSymbolByAtomic((Atomic) returnValue, id).getVariableValue();
        
        removeCallStack(id);
        
        return value;
    }
    
    // Add all parameters to call stack with their values based on call arguments
    private static void addCallStack(FunctionDefinitionParameters functionDefinitionTypedParameters, EList<Atomic> callParameters, String id, String callerId) {
        int definitionParametersCount = functionDefinitionTypedParameters.getTypedVariableList().size();
        addCallStackItem(callerId);
        
        for (int i = 0; i < definitionParametersCount; i++) {
            String name = functionDefinitionTypedParameters.getTypedVariableList().get(i).getName();
            String type = functionDefinitionTypedParameters.getTypedVariableList().get(i).getType();
            Symbol callSymbol = lookupSymbolByAtomic((Atomic) callParameters.get(i), callerId); 
            Object value = callSymbol.getVariableValue();
            Symbol symbol = new Symbol(name, type, value, id);
            addCallStackBySymbol(symbol, id);
        }
    }
    
    // Validate all parameters between function call and its definition.
    public static boolean validateParameters(CustomFunctionCallExpression expression, String id) {
        EList<Atomic> callParameters = expression.getParameters().getParameter();
        FunctionDefinitionParameters functionDefinitionTypedParameters = expression.getFunction().getParameters();
        int definitionParametersCount = functionDefinitionTypedParameters.getTypedVariableList().size();
        
        if (callParameters.size() == definitionParametersCount) {
            for (int i = 0; i < definitionParametersCount; i++) {
                String definitionType = functionDefinitionTypedParameters.getTypedVariableList().get(i).getType();
                Symbol symbol = lookupSymbolByAtomic((Atomic) callParameters.get(i), id);
                
                if (!definitionType.equals(symbol.getType())) {
                    AbstractStackHelper.stopExecution("Parameter of " + definitionType + " is not valid.");
                }
            }
        } else {
            AbstractStackHelper.stopExecution("Parameter counts are mismatched at function call '" + expression.getFunction().getName() + "()'");
        }
        
        return true;
    }

}
