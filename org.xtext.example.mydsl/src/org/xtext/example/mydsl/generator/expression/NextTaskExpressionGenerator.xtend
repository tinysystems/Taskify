package org.xtext.example.mydsl.generator.expression

import org.xtext.example.mydsl.myDsl.NextTaskExpression
import org.xtext.example.mydsl.generator.GeneratorSwitcher

class NextTaskExpressionGenerator implements IExpressionGenerator {
	NextTaskExpression expression
	
	new (NextTaskExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
	}
	
	
	override generate() {
		var result = ""
		if (this.expression.nexttask !== null) {
			result = nextTask(this.expression.nexttask.name)
		} else if (this.expression.endtask !== null) {
			result = endTask
		}
		return result

	}
	
	def String nextTask(String nextTaskName) {
		'''
		return «nextTaskName»;
		'''
	}
	
	def String endTask() {
		'''
		return NULL;
		'''
	}
	
}