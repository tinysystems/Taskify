package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression

class VariableAssignmentExpressionGenerator implements IExpressionGenerator {
	
	new (VariableAssignmentExpression expression, GeneratorSwitcher generator) {
		
	}
	
	override String generate() {
		return this.class.name + "\n"
	}
}