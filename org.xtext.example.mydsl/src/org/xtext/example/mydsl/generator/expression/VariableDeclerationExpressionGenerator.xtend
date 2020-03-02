package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.generator.common.VariableAssignmentGenerator

class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
    VariableDeclerationExpression expression
    GeneratorSwitcher generator
    
    new (VariableDeclerationExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        var String result = ""
        
        val boolean isArray = this.expression.dimension !== null
        
//        Generate variable type and name
        result += CommonGenerator.getVariableTypeName(this.expression.type, this.expression.name)
        if (isArray) {
            result += CommonGenerator.getDimension(this.expression.dimension, -1)
        } 
        
//        Generate right side of definition
        if (this.expression.assignment !== null) {
            result += " = " + VariableAssignmentGenerator.generate(this.expression.assignment, this.generator)
        }
        
        result += ";" + CommonGenerator.newLine
        return result
    }

}