package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.generator.expression.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.FunctionDefinitionExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.CommonGenerator
import org.xtext.example.mydsl.generator.common.AtomicGenerator

class FunctionDefinitionExpressionGenerator implements IExpressionGenerator {
    FunctionDefinitionExpression expression
    GeneratorSwitcher generator
    
    new (FunctionDefinitionExpression expression, GeneratorSwitcher generator)  {
        this.expression = expression
        this.generator = generator
    }
    
    override generate() {
        return template
    }
    
    def String template() {
        '''
        int «this.expression.name» («parameters») {
            «FOR bodyElement: this.expression.body»
                «this.generator.generate(bodyElement)»
            «ENDFOR»
            
            return «AtomicGenerator.generate(this.expression.^return, false)»;
        }
        
        '''
    }
    
    def String getParameters() {
        var String result = ""
        var parameters = this.expression.parameters.typedVariableList
        
        if (parameters.size > 0) {
            var paramater = parameters.get(0)    
            result += CommonGenerator.getCType(paramater.type) + " " + paramater.name
            
            for (var int i = 1; i < parameters.size; i++) {
                paramater = parameters.get(i)    
                result += ", " + CommonGenerator.getCType(paramater.type) + " " + paramater.name
            }
        }
        return result
    }
    
}
