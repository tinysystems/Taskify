package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.WhileExpression
import org.xtext.example.mydsl.myDsl.OperationExpression
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject


class WhileExpressionGenerator implements IExpressionGenerator {
	WhileExpression expression
	GeneratorSwitcher generator
	
	new (WhileExpression expression, GeneratorSwitcher generator)  {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return template(this.generator, this.expression.test, this.expression.body.body)
	}
	
	def static String template(GeneratorSwitcher generator, OperationExpression test, EList<EObject> content) {
		'''
		
		while(«generator.generate(test)») {
			«FOR bodyElement: content»
				«generator.generate(bodyElement)»
			«ENDFOR»
		}
		'''
	}
}