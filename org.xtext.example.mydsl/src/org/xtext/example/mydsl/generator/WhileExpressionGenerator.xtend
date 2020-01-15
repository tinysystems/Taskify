package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.WhileExpression

class WhileExpressionGenerator implements IExpressionGenerator {
	WhileExpression expression
	GeneratorSwitcher generator
	
	new (WhileExpression expression, GeneratorSwitcher generator)  {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		return this.class.name
	}
}