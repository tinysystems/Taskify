package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.BreakExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher

class BreakExpressionGenerator implements IExpressionGenerator {
	
	new (BreakExpression expression, GeneratorSwitcher generator) {
		
	}
	
	override String generate() {
		'''break;'''
	}
}