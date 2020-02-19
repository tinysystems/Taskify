package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.WhileExpression
import org.eclipse.emf.common.util.EList
import org.xtext.example.mydsl.myDsl.BlockExpressions
import org.xtext.example.mydsl.generator.GeneratorSwitcher

class WhileExpressionGenerator implements IExpressionGenerator {
    WhileExpression expression
    GeneratorSwitcher generator
    
    new (WhileExpression expression, GeneratorSwitcher generator)  {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        return template(this.expression.body.body)
    }
    
    def String template(EList<BlockExpressions> body) {
        '''
        
        while(«this.generator.generate(this.expression.test)») {
            «FOR bodyElement: body»
                «this.generator.generate(bodyElement)»
            «ENDFOR»
        }
        '''
    }
}