package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.WhileExpression

class WhileExpressionGenerator implements IExpressionGenerator {
	WhileExpression expression
	
	new (WhileExpression expression)  {
		this.expression = expression
	}
	
	override String generate() {
		return ""
	}
}