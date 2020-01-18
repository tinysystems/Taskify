package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.myDsl.Operation
import org.xtext.example.mydsl.myDsl.Atomic

class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
	VariableDeclerationExpression expression
	GeneratorSwitcher generator
	
	new (VariableDeclerationExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		var String result = ""
		
		val boolean isArray = this.expression.dimension !== null
		
//		Generate variable type and name
		result += CommonGenerator.getVariableTypeName(this.expression.type.type, this.expression.name)
		if (isArray) {
			result += CommonGenerator.getDimension(this.expression.dimension)
		} 
		
//		Generate right side of definition
		if (this.expression.assignment !== null) {
			if (isArray) {
				result += getAssignment(this.expression.assignment.expression as ArrayAssignment)	
			} else {
				result += getAssignment(this.expression.assignment.expression as Operation)
			}
		}
		
		result += ";"
//		println(result)
		return result
	}
	
	def static String getAssignment(Operation operation) {
		var String result = " = "
		var Atomic left = operation.left
		result += AtomicGenerator.generate(left)
		
		for (var int i = 0; i < operation.operator.size; i++) {
			val Atomic right = operation.right.get(i)
			result += operation.operator.get(i)
			result += AtomicGenerator.generate(right)
		}
		
		return result
	}
	
	def static String getAssignment(ArrayAssignment array) {
		var String result = " = "
		
		result += "{"
		result += AtomicGenerator.generate(array.variable.get(0))
		
		for (var int i = 1; i < array.variable.size; i++) {
			result += ", " + AtomicGenerator.generate(array.variable.get(i))
		}
		
		result += "}"
		
		return result
	}
	
//	def String variable() 
//	'''«CommonGenerator.getCType(this.expression.type.type)» «this.expression.name»'''

}