package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.generator.expression.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.ComparisionExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher

class ComparisionExpressionGenerator implements IExpressionGenerator {
	ComparisionExpression expression
	GeneratorSwitcher generator
	
	new (ComparisionExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override generate() {
		val String operator = this.expression.operator
		val String left = this.generator.generate(this.expression.left)
		val String right = this.generator.generate(this.expression.right)
		
		return template(left, operator, right)
	}
	
	def String template(String left, String operator, String right) {
		'''«left» «operator» «right»'''
	}
		
}