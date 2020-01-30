package org.xtext.example.mydsl.generator

import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.BreakExpression
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.ForExpression
import org.xtext.example.mydsl.myDsl.SharedVariableExpression
import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.WhileExpression
import org.xtext.example.mydsl.myDsl.IfExpression


class GeneratorSwitcher {
	
	def generate(EObject expression) {
		var IExpressionGenerator generator = null

		if (expression instanceof BreakExpression) {
			generator = new BreakExpressionGenerator(expression as BreakExpression, this) as IExpressionGenerator
		} else if (expression instanceof BuiltinFunctionCallExpression) {
			generator = new BuiltinFunctionCallExpressionGenerator(expression as BuiltinFunctionCallExpression, this) as IExpressionGenerator
		} else if (expression instanceof ConstantVariableExpression) {
			generator = new ConstantVariableExpressionGenerator(expression as ConstantVariableExpression, this) as IExpressionGenerator
		} else if (expression instanceof ForExpression) {
			generator = new ForExpressionGenerator(expression as ForExpression, this) as IExpressionGenerator
		} else if (expression instanceof SharedVariableExpression) {
			generator = new SharedVariableExpressionGenerator(expression as SharedVariableExpression, this) as IExpressionGenerator
		} else if (expression instanceof IfExpression) {
			generator = new IfExpressionGenerator(expression as IfExpression, this) as IExpressionGenerator
		} else if (expression instanceof OperationExpression) {
			generator = new OperationExpressionGenerator(expression as OperationExpression, this) as IExpressionGenerator
		} else if (expression instanceof VariableAssignmentExpression) {
			generator = new VariableAssignmentExpressionGenerator(expression as VariableAssignmentExpression, this) as IExpressionGenerator
		} else if (expression instanceof VariableDeclerationExpression) {
			generator = new VariableDeclerationExpressionGenerator(expression as VariableDeclerationExpression, this) as IExpressionGenerator
		} else if (expression instanceof WhileExpression) {
			generator = new WhileExpressionGenerator(expression as WhileExpression, this) as IExpressionGenerator
		} else {
			throw new Exception("Code generation process is interrupted due to 'Unsupported expression " + expression.toString() + "'.");
		}
		
		return generator.generate()
	}
	
}


