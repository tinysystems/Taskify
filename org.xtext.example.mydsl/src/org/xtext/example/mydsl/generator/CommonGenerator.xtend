package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.ArrayDimension
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression

class CommonGenerator {
	static String INT = "int"
	static String FLOAT = "float"
	static String STRING = "char"
	
	def static String getCType(String type) {
		var String result
		
		if (type == "integer") {
			result = INT
		} else if (type == "double") {
			result = FLOAT
		} else if (type == "string") {
			result = STRING
		} else {
			result = null
		}
		
		return result
	}
	
	def static String cast(String target, String type) {
		var String result = ""
		if (target != type) {
			result = "(" + getCType(target) + ") "
		}
		return result
	}
	
	def static String getVariableTypeName(String DslType, String name) {
		var String result = ""
		val String type = getCType(DslType)

		result += type
		result += " " + name
		
		if (type == STRING) {
			result += dimension(100)
		}		
		return result
	}
	
		def static String dimension(int index)
	'''[«index»]'''
	
	def static String dimension(VariableSymbol index)
	'''[«getVariableSymbol(index)»]'''
	
	def static String getDimension(ArrayDimension dimension) {
		var String result = ""
		val Object index = dimension.size
		if (index instanceof VariableSymbol) {
			result = dimension(index as VariableSymbol)
		} else if (index instanceof Integer) {
			result = dimension(index)
		}
		
		return result
	}
		
	def static String getVariableSymbol(VariableSymbol symbol) {
		var String result = ""
		if (symbol instanceof VariableDeclerationExpression) {
			result = (symbol as VariableDeclerationExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension) : ""
		} else if (symbol instanceof ConstantVariableExpression) {
			result = (symbol as ConstantVariableExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension) : ""
		} else if (symbol instanceof GlobalVariableExpression) {
			
		}
		return result
	}
}