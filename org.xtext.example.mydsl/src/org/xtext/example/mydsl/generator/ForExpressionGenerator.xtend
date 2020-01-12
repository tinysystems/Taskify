package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.ForExpression

class ForExpressionGenerator implements IExpressionGenerator {
	ForExpression expression
	
	new (ForExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}