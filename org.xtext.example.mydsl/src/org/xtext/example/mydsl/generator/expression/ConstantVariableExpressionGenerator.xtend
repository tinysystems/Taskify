package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.SymbolTable
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.generator.common.AtomicGenerator
import org.xtext.example.mydsl.generator.common.VariableAssignmentGenerator

class ConstantVariableExpressionGenerator implements IExpressionGenerator {
    ConstantVariableExpression expression
    GeneratorSwitcher generator
    
    new (ConstantVariableExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        val String scope = SymbolTable.CONSTANT
        val String name = this.expression.name
        val String type = this.expression.type
        SymbolTable.addSymbol(name, type, scope)

        // var String result = "__nv "
        var String result = ""
        
        // Generate variable type and name
        result += CommonGenerator.getVariableTypeName(this.expression.type) + " " + this.expression.name
        
        // Generate dimension part
        if (this.expression.dimension !== null) {
            result += AtomicGenerator.generateDimension(this.expression.dimension.index, true)       
        }
        
        // Generate right side of definition if exists
        if (this.expression.assignment !== null) {
            result += " = " + VariableAssignmentGenerator.generate(this.expression.assignment, this.generator)
        }
        
        result += ";" + CommonGenerator.newLine    
        return result
    }
    
}