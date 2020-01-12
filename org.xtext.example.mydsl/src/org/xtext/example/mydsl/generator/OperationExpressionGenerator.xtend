package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.OperationExpression

class OperationExpressionGenerator implements IExpressionGenerator {
	OperationExpression expression
	
	new (OperationExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}