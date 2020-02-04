package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction
import org.xtext.example.mydsl.myDsl.BuiltinPrintFunction

class BuiltinFunctionCallExpressionGenerator implements IExpressionGenerator {
	BuiltinFunctionCallExpression expression
	
	new (BuiltinFunctionCallExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	
//	Do nothing since there will not be print functionality in target language
	override String generate() {
		var result = ""
		if (this.expression instanceof BuiltinRandomFunction) {
			result = builtinRandomFunction
		} else if (this.expression instanceof BuiltinPrintFunction) {
			
		}
		
		return result
	}
	
	def builtinPrintFunction() {
		'''
		/* Built-in function call for print(), fo nothing for the moment */
		'''
	}
	
	def static String builtinRandomFunction() {
		IncludeTable.add(IncludeTemplates.standardLibrary)
		'''rand()'''
	}
}