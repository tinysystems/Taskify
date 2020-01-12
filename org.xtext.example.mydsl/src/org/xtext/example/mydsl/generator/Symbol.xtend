package org.xtext.example.mydsl.generator

class Symbol {
	String scope
	String name
	String type
	
	new(String name, String type, String scope) {
		this.name = name
		this.type = type
		this.scope = scope
	}
	
	def String getType() {
		return this.type
	}
	
	override String toString() {
		var String result = "{"
		
		result += "name: " + this.name + ", "
		result += "type: " + this.type + ", "
		result += "scope: " + this.scope
		
		result += "}"
		
		return result
	}
}