package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression

class GlobalVariableExpressionGenerator implements IExpressionGenerator {
	GlobalVariableExpression expression;
	
	new (GlobalVariableExpression expression) {
		this.expression = expression
	}
	override String generate() {
		val String scope = SymbolTable.GLOBAL
		val String name = this.expression.declarationExpression.name
		val String type = this.expression.declarationExpression.type.type
		SymbolTable.addSymbol(name, type, scope)
		return ""
	}
}