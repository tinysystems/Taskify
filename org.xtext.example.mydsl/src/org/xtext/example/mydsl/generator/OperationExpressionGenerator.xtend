package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.OperationExpression

class OperationExpressionGenerator implements IExpressionGenerator {
	OperationExpression expression
	GeneratorSwitcher generator
	
	new (OperationExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
		
	}
	
	override String generate() {
		return this.class.name
	}
}