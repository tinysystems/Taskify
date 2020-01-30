package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression


class BuiltinFunctionCallExpressionGenerator implements IExpressionGenerator {
	BuiltinFunctionCallExpression expression
	
	new (BuiltinFunctionCallExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	
//	Do nothing since there will not be print functionality in target language
	override String generate() {
		""
	}
	
	def template() {
		'''
		/* Built-in function call for «this.expression.function»(), fo nothing for the moment */
		'''
	}
}