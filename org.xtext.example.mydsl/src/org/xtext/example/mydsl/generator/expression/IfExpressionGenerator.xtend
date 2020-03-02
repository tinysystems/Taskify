package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.IfExpression
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.CommonGenerator

class IfExpressionGenerator implements IExpressionGenerator {
    IfExpression expression;
    GeneratorSwitcher generator;
    
    new (IfExpression expression, GeneratorSwitcher generator) {
        this.expression = expression
        this.generator = generator
    }
    
    override String generate() {
        var String result = ifTemplate(this.generator, this.expression.ifcondition, this.expression.ifbody.body)
        
        val int elseifCount = this.expression.elseifcondition.size
        for (var int index = 0; index < elseifCount; index++) {
            result += elseifTemplate(this.generator, this.expression.elseifcondition.get(index), this.expression.elseifbody.get(index).body)
        }
        
        if (this.expression.elsebody !== null) {
            result += elseTemplate(this.generator, this.expression.elsebody.body)
        } else {
            result += CommonGenerator.newLine
        }
        
        return result
    }
    
    def static String ifTemplate(GeneratorSwitcher generator, EObject ifcondition, EList<EObject> ifbody) {
        '''
            if(«generator.generate(ifcondition)») {
                «FOR bodyElement: ifbody»
                    «generator.generate(bodyElement)»
                «ENDFOR»
            } '''
    }
    
    def static String elseifTemplate(GeneratorSwitcher generator, EObject elseifcondition, EList<EObject> elseifbody) {
        '''
            else if («generator.generate(elseifcondition)») {
                «FOR bodyElement: elseifbody»
                    «generator.generate(bodyElement)»
                «ENDFOR»                
            } '''
    }
    
    def static String elseTemplate(GeneratorSwitcher generator, EList<EObject> elsebody) {
        '''
            else {
                «FOR bodyElement: elsebody»
                    «generator.generate(bodyElement)»
                «ENDFOR»                
            }
        '''
    }
}