package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression

class ConstantVariableExpressionGenerator implements IExpressionGenerator {
	ConstantVariableExpression expression
	
	new (ConstantVariableExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}