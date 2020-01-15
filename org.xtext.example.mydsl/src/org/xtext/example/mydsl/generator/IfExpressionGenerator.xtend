package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.IfExpression

class IfExpressionGenerator implements IExpressionGenerator {
	IfExpression expression
	GeneratorSwitcher generator
	
	new (IfExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return this.class.name
	}
}