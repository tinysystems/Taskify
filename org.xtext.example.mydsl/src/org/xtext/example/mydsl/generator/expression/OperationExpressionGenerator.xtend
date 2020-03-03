package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.Operation
import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.AtomicGenerator
import org.xtext.example.mydsl.myDsl.InParanthesisOperation
import org.eclipse.emf.common.util.EList
import java.util.Map
import com.google.common.collect.ImmutableMap

class OperationExpressionGenerator implements IExpressionGenerator {
    OperationExpression expression;
    
    static Map<String, String> logicalOperators = ImmutableMap.builder.
        put("AND", "&&").
        put("OR", "||").
        build

    static Map<String, String> bitwiseOperators = ImmutableMap.builder.
        put("AND_BITWISE", "&").
        put("OR_BITWISE", "|").
        put("SHIFT_LEFT", "<<").
        put("SHIFT_RIGHT", ">>").
        build
      
    static Map<String, String> comparisionOperators = ImmutableMap.builder.
        put(">=", ">=").
        put("<=", "<=").
        put("==", "==").
        put("!=", "!=").
        put("<", "<").
        put(">", ">").
        build
    
    static Map<String, String> arithmeticOperators = ImmutableMap.builder.
        put("+", "+").
        put("-", "-").
        put("*", "*").
        put("/", "/").
        build
    
    static Map<String, String> operators = ImmutableMap.builder.
        putAll(logicalOperators).
        putAll(bitwiseOperators).
        putAll(comparisionOperators).
        putAll(arithmeticOperators).
        build
    
    
    new (OperationExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
    }
    
    override String generate() {
        var String result = ""
        result += getOperation(this.expression as Operation)
        return result
    }
    
    def static String getPrimaryOperation(Operation operation) {
        var String result = ""
        if (operation instanceof Atomic) {
            result += AtomicGenerator.generate(operation, true);
        } else if (operation instanceof InParanthesisOperation) {
            result += "(" + getOperation((operation as InParanthesisOperation).getOperation()) + ")"
        } else if (operation instanceof Operation) {
            result += getOperation(operation as Operation)
        }
        
        return result
    }
    
    def static String getOperator(String operator) {
        var String result = ""
        val String op = operators.get(operator)
        if (op !== null) {
            result = op
        } else {
            result = "<Unsupported operator>"
        }
        
        return result
    }
    
    def static String getOperation(Operation operation) {
        var String result = ""
        val Operation left = operation.left
        
        // Left primary operation
        if (left !== null) {
            result += getPrimaryOperation(left)
        }
        
        val EList<String> operators = operation.getOperator();
        if (operators.size() == 1) {
            result += " " + getOperator(operators.get(0)) + " "
        }
        
        val EList<Operation> rights = operation.getRight();
        if (rights.size() > 0) {
            for (var int i = 0; i < rights.size(); i++) {
                val Operation right = rights.get(i);
                result += getPrimaryOperation(right);
            }
        }

        return result
    }
    
    def static String getAssignment(ArrayAssignment array) {        
        var String result = "{"
        result += AtomicGenerator.generate(array.variable.get(0), true)
        
        for (var int i = 1; i < array.variable.size; i++) {
            result += ", " + AtomicGenerator.generate(array.variable.get(i), true)
        }
        
        result += "}"
        
        return result
    }

}