package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.WhileExpression

class WhileExpressionGenerator implements IExpressionGenerator {
	
	new (WhileExpression expression, GeneratorSwitcher generator)  {

	}
	
	override String generate() {
		return this.class.name + "\n"
	}
}