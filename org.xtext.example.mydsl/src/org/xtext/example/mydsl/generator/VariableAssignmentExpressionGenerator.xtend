package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.Operation
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.Variable

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
			val EObject rightExpression = this.expression.assignment.expression
			if (rightExpression instanceof Operation) {
				result += " = " + generator.generate(this.expression.assignment.expression as Operation)	
			}
		}
		result += ";" + CommonGenerator.newLine
		
		return result
	}
}