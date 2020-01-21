package org.xtext.example.mydsl.generator

import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.myDsl.Operation
import org.xtext.example.mydsl.myDsl.Atomic

class OperationGenerator {
	def static generate(EObject expression) {
		if (expression instanceof OperationExpression) {
			
		} else if (expression instanceof ArrayAssignment) {
			
		}
	}
	
	def static generate(EObject expression, String type) {
		
	}
	
	
	
	
	
	
	
	def static String getAssignment(Operation operation) {
		var String result = " = "
		var Atomic left = operation.left
		result += AtomicGenerator.generate(left)
		
		for (var int i = 0; i < operation.operator.size; i++) {
			val Atomic right = operation.right.get(i)
			result += " " + operation.operator.get(i)
			result += " " + AtomicGenerator.generate(right)
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
	
}