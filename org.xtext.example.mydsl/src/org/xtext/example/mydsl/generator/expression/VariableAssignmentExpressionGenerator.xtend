package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.ArrayReference
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.SymbolTable
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignment
import org.xtext.example.mydsl.generator.common.VariableAssignmentGenerator
import org.xtext.example.mydsl.generator.common.AtomicGenerator

class VariableAssignmentExpressionGenerator implements IExpressionGenerator {
    VariableAssignmentExpression expression
    GeneratorSwitcher generator
    
    new (VariableAssignmentExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        var String result = generateExpression(this.expression, this.generator)
        result += ";" + CommonGenerator.newLine
        return result
    }
    
    def static String generateExpression(VariableAssignmentExpression expression, GeneratorSwitcher generator) {
        var String result = ""
        
        val Variable leftVariable = expression.variable
        result = AtomicGenerator.generate(leftVariable, false)
        
        // Generate right side of definition
        if (expression.assignment !== null) {
            
            val String right = VariableAssignmentGenerator.generate(expression.assignment, generator)
            
            var String variableName = result
            if (leftVariable instanceof ArrayReference) {
                // Get only reference name 
                variableName = AtomicGenerator.generateReferenceName(leftVariable)
                // variableName: array[1] (remove last 3 character), variable
                // variableName = variableName.substring(0, variableName.length - 3)
            }
            
            val String scope = SymbolTable.getScope(variableName)

            if (scope == SymbolTable.CONSTANT) {
                throw new Exception("Assigning to constant variable " + variableName + " is not possible.");
            } else if (scope == SymbolTable.SHARED) {
                result = generateSharedAssignment(result, right)
            } else {
                result += " = " + right
            }
        }
        
        return result
    }
    
    def static String generateRightExpressio(VariableAssignment rightExpression) {
        var String result = ""
        
        if (rightExpression !== null) {
            
        }
        
        return result
    }
    
    def static String generateSharedAssignment(String variable, String value) {
        '''__SET(«variable», «value»)'''
    }
}