package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.myDsl.Operation


class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
	VariableDeclerationExpression expression
	
	new (VariableDeclerationExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	
	override String generate() {
		var String result = ""
		
		val boolean isArray = this.expression.dimension !== null
		
//		Generate variable type and name
		result += CommonGenerator.getVariableTypeName(this.expression.type.type, this.expression.name)
		if (isArray) {
			result += CommonGenerator.getDimension(this.expression.dimension, -1)
		} 
		
//		Generate right side of definition
		if (this.expression.assignment !== null) {
			if (isArray) {
				result += OperationGenerator.getAssignment(this.expression.assignment.expression as ArrayAssignment)	
			} else {
				result += OperationGenerator.getAssignment(this.expression.assignment.expression as Operation)
			}
		}
		
		result += ";" + CommonGenerator.newLine
//		println(result)
		return result
	}
	
//	def String variable() 
//	'''«CommonGenerator.getCType(this.expression.type.type)» «this.expression.name»'''

}