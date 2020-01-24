package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.Operation
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.ArrayReference

class VariableAssignmentExpressionGenerator implements IExpressionGenerator {
	VariableAssignmentExpression expression
	GeneratorSwitcher generator
	
	new (VariableAssignmentExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		var String result = ""
		val Variable leftVariable = this.expression.variable
		result = VariableGenerator.generate(leftVariable)
		
		//		Generate right side of definition
		if (this.expression.assignment !== null) {
			
			val String right = generateRightOperation
			// variableName: array[1] (remove last 3 character), variable
			var String variableName = result
			if (leftVariable instanceof ArrayReference) {
				variableName = variableName.substring(0, variableName.length - 3)
			}
			
			val String scope = SymbolTable.getScope(variableName)

			if (scope == SymbolTable.CONSTANT) {
				throw new Exception("Assigning to constant variable " + variableName + " is not possible.");
			} else if (scope == SymbolTable.GLOBAL) {
				result = generateGlobalAssignment(result, right)
			} else {
				result += " = " + right
			}
		}
		result += ";" + CommonGenerator.newLine
		
		return result
	}
	
	def String generateRightOperation() {
		var String result = ""
		
		val EObject rightExpression = this.expression.assignment.expression
		if (rightExpression instanceof Operation) {
			result += this.generator.generate(this.expression.assignment.expression as Operation)
		}
		
		return result
	}
	
	def static String generateGlobalAssignment(String variable, String value) {
		'''__SET(«variable», «value»)'''
	}
}