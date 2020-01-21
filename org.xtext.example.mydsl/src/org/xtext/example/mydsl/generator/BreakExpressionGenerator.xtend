package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BreakExpression

class BreakExpressionGenerator implements IExpressionGenerator {
	
	new (BreakExpression expression, GeneratorSwitcher generator) {
		
	}
	
	override String generate() {
		'''break;'''
	}
}