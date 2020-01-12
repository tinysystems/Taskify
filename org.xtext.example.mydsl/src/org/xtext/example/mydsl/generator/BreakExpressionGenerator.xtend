package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BreakExpression

class BreakExpressionGenerator implements IExpressionGenerator {
	var BreakExpression expression
	
	new (BreakExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}