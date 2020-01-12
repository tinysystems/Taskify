package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression

class BuiltinFunctionCallExpressionGenerator implements IExpressionGenerator {
	BuiltinFunctionCallExpression expression
	
	new (BuiltinFunctionCallExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}