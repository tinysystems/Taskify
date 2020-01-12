package org.xtext.example.mydsl.generator

import java.util.HashMap


class SymbolTable {
	val public static GLOBAL = "global"
	val public static CONSTANT = "constant"
	val public static LOCAL = "local"
	var static HashMap<String, Symbol> variables = null
	
	def static void resetTable() {
		variables = new HashMap<String, Symbol>
	}
	
	def static void addSymbol(String name, String dataType, String id) {
		val Symbol symbol = new Symbol(name, dataType, id)
		variables.put(name, symbol)
	}
	
	def static Symbol getSymbol(String name) {
		var Symbol symbol = null
		
		if (variables.containsKey(name)) {
			symbol = variables.get(name)
		}
		return symbol
	}
	
	def static String getType(String name) {
		var String type = null
		
		if (variables.containsKey(name)) {
			val Symbol symbol = variables.get(name)
			type = symbol.getType()
		}
		return type;
	}
	
	def static getAll() {
		return variables
	}
	
	def static String toStringg() {
		var String result = "[ "
		for (Symbol symbol: variables.values()) {
			result += symbol.toString()
		}
		result +=  " ]" 
		
		return result
	}
	
}