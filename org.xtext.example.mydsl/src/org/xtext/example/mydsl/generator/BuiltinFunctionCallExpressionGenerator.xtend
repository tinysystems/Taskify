package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression


class BuiltinFunctionCallExpressionGenerator implements IExpressionGenerator {
	
	new (BuiltinFunctionCallExpression expression, GeneratorSwitcher generator) {
	}
	
//	Do nothing since there will not be print functionality in target language
	override String generate() {
		return ""
	}
}