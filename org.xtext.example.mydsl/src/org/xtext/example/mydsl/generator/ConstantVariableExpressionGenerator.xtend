package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression

class ConstantVariableExpressionGenerator implements IExpressionGenerator {
	ConstantVariableExpression expression
	GeneratorSwitcher generator
	
	new (ConstantVariableExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return this.class.name
	}
}