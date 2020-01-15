package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.ForExpression

class ForExpressionGenerator implements IExpressionGenerator {
	ForExpression expression
	GeneratorSwitcher generator
	
	new (ForExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return ""
	}
}