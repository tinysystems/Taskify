package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.WhileExpression
import org.xtext.example.mydsl.myDsl.OperationExpression
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
		return template(this.generator, this.expression.test, this.expression.body.body)
	}
	
	def static String template(GeneratorSwitcher generator, OperationExpression test, EList<BlockExpressions> content) {
		'''
		
		while(«generator.generate(test)») {
			«FOR bodyElement: content»
				«generator.generate(bodyElement)»
			«ENDFOR»
		}
		'''
	}
}