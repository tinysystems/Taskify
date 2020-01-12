package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression

class VariableAssignmentExpressionGenerator implements IExpressionGenerator {
	VariableAssignmentExpression expression
	
	new (VariableAssignmentExpression expression) {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}