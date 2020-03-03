package org.xtext.example.mydsl.generator.common

import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.myDsl.CustomFunctionCallExpression
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression
import org.xtext.example.mydsl.myDsl.VariableAssignment
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.generator.expression.OperationExpressionGenerator

class VariableAssignmentGenerator {
   
    def static generate(VariableAssignment expr, GeneratorSwitcher generator) {
        var String result = ""
        val EObject expression = expr.expression
        //  expression = (OperationExpression | ArrayAssignment | BuiltinRandomFunction | CustomFunctionCallExpression)
        if (expression instanceof OperationExpression) {
            result = generator.generate(expression as OperationExpression)
        } else if (expression instanceof ArrayAssignment) {
            result = OperationExpressionGenerator.getAssignment(expression as ArrayAssignment)
        } else if (expression instanceof BuiltinFunctionCallExpression) {
            result = generator.generate(expression as BuiltinFunctionCallExpression)
        } else if (expression instanceof CustomFunctionCallExpression) {
            result = expression.function.name + "("
            val int parameterCount = expression.parameters.parameter.size
            if (parameterCount > 0) {
                result += AtomicGenerator.generate(expression.parameters.parameter.get(0), true)
                for (var int i = 1; i < parameterCount; i++) {
                    result += ", " + AtomicGenerator.generate(expression.parameters.parameter.get(i), true)   
                }
            }
            result += ")"
        }
        return result
    }

}