package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction
import org.xtext.example.mydsl.myDsl.BuiltinPrintFunction
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.IncludeTable
import org.xtext.example.mydsl.generator.common.IncludeTemplates

class BuiltinFunctionCallExpressionGenerator implements IExpressionGenerator {
    BuiltinFunctionCallExpression expression
    
    new (BuiltinFunctionCallExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
    }
    

    override String generate() {
        var result = ""
        if (this.expression instanceof BuiltinRandomFunction) {
            result = builtinRandomFunction
        } else if (this.expression instanceof BuiltinPrintFunction) {
            //    Do nothing since there will not be print functionality in target language
        }
        
        return result
    }
    
    def builtinPrintFunction() {
        '''
        /* Built-in function call for print(), fo nothing for the moment */
        '''
    }
    
    def static String builtinRandomFunction() {
        IncludeTable.add(IncludeTemplates.standardLibrary)
        '''rand()'''
    }
}