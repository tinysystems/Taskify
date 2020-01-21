package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.OperationExpression

class OperationExpressionGenerator implements IExpressionGenerator {
	
	new (OperationExpression expression, GeneratorSwitcher generator) {
		
	}
	
	override String generate() {
		return this.class.name
	}
}