package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
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
        val cType = CommonGenerator.getVariableTypeName(this.expression.type)
        val String name = this.expression.name
        var String dimension = null
        if (this.expression.dimension !== null) {
            dimension = AtomicGenerator.generateDimension(this.expression.dimension.index, true)       
        }
        val String value = VariableAssignmentGenerator.generate(this.expression.assignment, this.generator)

        if (dimension === null) {
            return template(name, value)
        } else {
            return template(cType, name + dimension, value)
        }
    }
    
    def String template(String name, String value) {
        '''
        #define «name» «value»
        '''
    }
    
    def String template(String dataType, String variableName, String value) {
        '''
        __nv «dataType» «variableName» = «value»;
        '''
    }
    
}