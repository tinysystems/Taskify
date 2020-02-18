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
import org.xtext.example.mydsl.myDsl.NextTaskExpression
import org.xtext.example.mydsl.generator.expression.ForExpressionGenerator
import org.xtext.example.mydsl.generator.expression.IExpressionGenerator
import org.xtext.example.mydsl.generator.expression.BreakExpressionGenerator
import org.xtext.example.mydsl.generator.expression.BuiltinFunctionCallExpressionGenerator
import org.xtext.example.mydsl.generator.expression.ConstantVariableExpressionGenerator
import org.xtext.example.mydsl.generator.expression.IfExpressionGenerator
import org.xtext.example.mydsl.generator.expression.NextTaskExpressionGenerator
import org.xtext.example.mydsl.generator.expression.OperationExpressionGenerator
import org.xtext.example.mydsl.generator.expression.SharedVariableExpressionGenerator
import org.xtext.example.mydsl.generator.expression.VariableAssignmentExpressionGenerator
import org.xtext.example.mydsl.generator.expression.VariableDeclerationExpressionGenerator
import org.xtext.example.mydsl.generator.expression.WhileExpressionGenerator
import org.xtext.example.mydsl.myDsl.ComparisionExpression
import org.xtext.example.mydsl.generator.expression.ComparisionExpressionGenerator

class GeneratorSwitcher {
    
    def generate(EObject expression) {
        var IExpressionGenerator generator = null

        if (expression instanceof BreakExpression) {
            generator = new BreakExpressionGenerator(expression as BreakExpression, this) as IExpressionGenerator
        } else if (expression instanceof BuiltinFunctionCallExpression) {
            generator = new BuiltinFunctionCallExpressionGenerator(expression as BuiltinFunctionCallExpression, this) as IExpressionGenerator
        } else if (expression instanceof ComparisionExpression) {
            generator = new ComparisionExpressionGenerator(expression as ComparisionExpression, this) as IExpressionGenerator
        } else if (expression instanceof ConstantVariableExpression) {
            generator = new ConstantVariableExpressionGenerator(expression as ConstantVariableExpression, this) as IExpressionGenerator
        } else if (expression instanceof ForExpression) {
            generator = new ForExpressionGenerator(expression as ForExpression, this) as IExpressionGenerator
        } else if (expression instanceof IfExpression) {
            generator = new IfExpressionGenerator(expression as IfExpression, this) as IExpressionGenerator
        } else if (expression instanceof NextTaskExpression) {
            generator = new NextTaskExpressionGenerator(expression as NextTaskExpression, this) as IExpressionGenerator
        } else if (expression instanceof OperationExpression) {
            generator = new OperationExpressionGenerator(expression as OperationExpression, this) as IExpressionGenerator
        } else if (expression instanceof SharedVariableExpression) {
            generator = new SharedVariableExpressionGenerator(expression as SharedVariableExpression, this) as IExpressionGenerator
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


