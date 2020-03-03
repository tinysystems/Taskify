package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.generator.common.VariableAssignmentGenerator
import org.xtext.example.mydsl.generator.common.AtomicGenerator

class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
    VariableDeclerationExpression expression
    GeneratorSwitcher generator
    
    new (VariableDeclerationExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        var String result = ""
        
        // Generate variable type and name
        result += CommonGenerator.getVariableTypeName(this.expression.type) + " " + this.expression.name
        
        // Generate dimension part
        if (this.expression.dimension !== null) {
            result += AtomicGenerator.generateDimension(this.expression.dimension.index, true)        
        } 
        
        // Generate right side of definition
        if (this.expression.assignment !== null) {
            result += " = " + VariableAssignmentGenerator.generate(this.expression.assignment, this.generator)
        }
        
        result += ";" + CommonGenerator.newLine
        return result
    }

}