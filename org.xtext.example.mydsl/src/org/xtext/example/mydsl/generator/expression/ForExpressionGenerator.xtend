package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.ForExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher

class ForExpressionGenerator implements IExpressionGenerator {
	
	new (ForExpression expression, GeneratorSwitcher generator) {
	}
	
	override String generate() {
		return ""
	}
}