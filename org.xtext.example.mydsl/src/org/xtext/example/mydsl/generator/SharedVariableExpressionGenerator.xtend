package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.SharedVariableExpression

class SharedVariableExpressionGenerator implements IExpressionGenerator {
	SharedVariableExpression expression
	
	new (SharedVariableExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	override String generate() {
		val String scope = SymbolTable.SHARED
		val String name = this.expression.name
		val String type = this.expression.type.type
		SymbolTable.addSymbol(name, type, scope)
		
		// By-pass it to VariableDeclerationExpressionGenerator
		var String result = ""
		
		val boolean isArray = this.expression.dimension !== null
		
//		Generate variable type and name
		result += CommonGenerator.getVariableTypeName(this.expression.type.type, this.expression.name)
		if (isArray) {
			result += CommonGenerator.getDimension(this.expression.dimension, -1)
		} 
		
		result += ";" + CommonGenerator.newLine
		
		return result
	}
	
}