package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression

class GlobalVariableExpressionGenerator implements IExpressionGenerator {
	GlobalVariableExpression expression
	GeneratorSwitcher generator
	
	new (GlobalVariableExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	override String generate() {
		val String scope = SymbolTable.GLOBAL
		val String name = this.expression.declarationExpression.name
		val String type = this.expression.declarationExpression.type.type
		SymbolTable.addSymbol(name, type, scope)
		
		// By-pass it to VariableDeclerationExpressionGenerator
		val String result = this.generator.generate(this.expression.declarationExpression)
		
		return result
	}
	
}