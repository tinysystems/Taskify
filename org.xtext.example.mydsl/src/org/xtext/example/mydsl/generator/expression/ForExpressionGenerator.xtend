package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.ForExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.ComparisionExpression
import org.xtext.example.mydsl.myDsl.BlockExpressions
import org.eclipse.emf.common.util.EList

class ForExpressionGenerator implements IExpressionGenerator {
    ForExpression expression
    GeneratorSwitcher generator
    
    new (ForExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {        
        return template(this.expression.initial, this.expression.test, this.expression.update, this.expression.body.body)
    }
    
    def String template(VariableAssignmentExpression initial, ComparisionExpression test, VariableAssignmentExpression update, EList<BlockExpressions> body) {
        '''
        
        for («generateAssignment(initial)»; «this.generator.generate(test)»; «generateAssignment(update)») {
            «FOR bodyElement: body»
                «this.generator.generate(bodyElement)»
            «ENDFOR»
        }
        '''
    }
    
    def String generateAssignment(VariableAssignmentExpression expr) {
        return VariableAssignmentExpressionGenerator.generateExpression(expr, this.generator)
        
    }
    
    
}