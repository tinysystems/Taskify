package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.IfExpression

class IfExpressionGenerator implements IExpressionGenerator {
	IfExpression expression
	
	new (IfExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}