package org.xtext.example.mydsl.generator

import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.BreakExpression
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.ForExpression
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression
import org.xtext.example.mydsl.myDsl.OperationExpression
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.WhileExpression
import org.xtext.example.mydsl.myDsl.IfExpression


class GeneratorSwitcher {
	
	def generate(EObject expression) {
		var IExpressionGenerator generator = null
		
		try {
			if (expression instanceof BreakExpression) {
				generator = new BreakExpressionGenerator(expression as BreakExpression) as IExpressionGenerator
			} else if (expression instanceof BuiltinFunctionCallExpression) {
				generator = new BuiltinFunctionCallExpressionGenerator(expression as BuiltinFunctionCallExpression) as IExpressionGenerator
			} else if (expression instanceof ConstantVariableExpression) {
				generator = new ConstantVariableExpressionGenerator(expression as ConstantVariableExpression) as IExpressionGenerator
			} else if (expression instanceof ForExpression) {
				generator = new ForExpressionGenerator(expression as ForExpression) as IExpressionGenerator
			} else if (expression instanceof GlobalVariableExpression) {
				generator = new GlobalVariableExpressionGenerator(expression as GlobalVariableExpression) as IExpressionGenerator
			} else if (expression instanceof IfExpression) {
				generator = new IfExpressionGenerator(expression as IfExpression) as IExpressionGenerator
			} else if (expression instanceof OperationExpression) {
				generator = new OperationExpressionGenerator(expression as OperationExpression) as IExpressionGenerator
			} else if (expression instanceof VariableAssignmentExpression) {
				generator = new VariableAssignmentExpressionGenerator(expression as VariableAssignmentExpression) as IExpressionGenerator
			} else if (expression instanceof VariableDeclerationExpression) {
				generator = new VariableDeclerationExpressionGenerator(expression as VariableDeclerationExpression) as IExpressionGenerator
			} else if (expression instanceof WhileExpression) {
				generator = new WhileExpressionGenerator(expression as WhileExpression) as IExpressionGenerator
			} else {
	//			throw new InterpreterException("Unsupported expression " + expression.toString());
			}
			
			return generator.generate()
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
}


