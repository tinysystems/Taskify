package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.ArrayReference
import org.xtext.example.mydsl.myDsl.VariableReference

class VariableGenerator {
	def static String generate(Variable variable) {
		return getVariable(variable)
	}
	
	/*
	 * Get target of variable name 
	 * returns: value | value[0]
	 */
	def private static String getVariable(Variable variable) {
		var String result = ""
		
		if (variable instanceof ArrayReference) {
			var VariableSymbol symbol = (variable as ArrayReference).arrayReference
			result = CommonGenerator.getVariableSymbol(symbol, variable.index.size)
		} else if (variable instanceof VariableReference) {
			val VariableSymbol symbol = (variable as VariableReference).variableReference
			result = CommonGenerator.getVariableSymbol(symbol, -1)
		}
		return result
	}
}