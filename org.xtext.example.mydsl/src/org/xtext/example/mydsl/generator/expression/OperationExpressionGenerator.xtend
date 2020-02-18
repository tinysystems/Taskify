package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.Operation
import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.generator.GeneratorSwitcher
import org.xtext.example.mydsl.generator.common.AtomicGenerator

class OperationExpressionGenerator implements IExpressionGenerator {
	OperationExpression expression;
	
	new (OperationExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	
	override String generate() {
		return getOperation(this.expression as Operation)
	}
	
	def static String getOperation(Operation operation) {
		var result = ""
		// TODO
//		var Atomic left = operation.left
//		var String result = AtomicGenerator.generate(left)
//		
//		for (var int i = 0; i < operation.operator.size; i++) {
//			val Atomic right = operation.right.get(i)
//			result += " " + operation.operator.get(i)
//			result += " " + AtomicGenerator.generate(right)
//		}
		
		return result
	}
	
	def static String getAssignment(ArrayAssignment array) {		
		var String result = "{"
		result += AtomicGenerator.generate(array.variable.get(0))
		
		for (var int i = 1; i < array.variable.size; i++) {
			result += ", " + AtomicGenerator.generate(array.variable.get(i))
		}
		
		result += "}"
		
		return result
	}

}