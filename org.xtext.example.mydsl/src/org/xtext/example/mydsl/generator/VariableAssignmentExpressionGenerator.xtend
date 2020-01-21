package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression

class VariableAssignmentExpressionGenerator implements IExpressionGenerator {
	VariableAssignmentExpression expression
	GeneratorSwitcher generator
	
	new (VariableAssignmentExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return this.class.name
	}
}