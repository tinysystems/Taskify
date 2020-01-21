package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.IfExpression

class IfExpressionGenerator implements IExpressionGenerator {
	
	new (IfExpression expression, GeneratorSwitcher generator) {

	}
	
	override String generate() {
		return this.class.name + "\n"
	}
}