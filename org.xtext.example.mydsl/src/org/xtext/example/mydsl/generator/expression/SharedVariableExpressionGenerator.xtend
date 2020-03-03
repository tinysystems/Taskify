package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.SharedVariableExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.SymbolTable
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.generator.common.AtomicGenerator

class SharedVariableExpressionGenerator implements IExpressionGenerator {
    SharedVariableExpression expression
    
    new (SharedVariableExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
    }
    
    override String generate() {
        val String scope = SymbolTable.SHARED
        val String name = this.expression.name
        val String type = this.expression.type
        SymbolTable.addSymbol(name, type, scope)
        
        // By-pass it to VariableDeclerationExpressionGenerator
        var String result = ""
        
        // Generate variable type and name
        result += CommonGenerator.getVariableTypeName(this.expression.type) + " " + this.expression.name
        
        // Generate dimension part
        if (this.expression.dimension !== null) {
            result += AtomicGenerator.generateDimension(this.expression.dimension.index, false)
        } 
        
        result += ";" + CommonGenerator.newLine
        
        return result
    }
    
}