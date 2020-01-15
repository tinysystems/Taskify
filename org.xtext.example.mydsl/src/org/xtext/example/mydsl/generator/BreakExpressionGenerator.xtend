package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BreakExpression

class BreakExpressionGenerator implements IExpressionGenerator {
	BreakExpression expression
	GeneratorSwitcher generator
	
	new (BreakExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return "break;"
	}
}