package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression

class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
	VariableDeclerationExpression expression
	
	new (VariableDeclerationExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}