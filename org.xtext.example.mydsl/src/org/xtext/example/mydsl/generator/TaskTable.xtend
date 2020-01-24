package org.xtext.example.mydsl.generator

import java.util.List
import java.util.ArrayList

class TaskTable {
	var static List<String> tasks = null
	var static TaskTable taskTable = null
	
	private new () {
		tasks = new ArrayList	
	}
	
	def static TaskTable getTaskTable() {
		if (taskTable === null) {
			taskTable = new TaskTable
		} 
		
		return taskTable
	}
	
	def static resetTaskTable() {
		taskTable = null
		tasks = null
	}
	
	def add(String taskName) {
		if (!isAdded(taskName)) {
			tasks.add(taskName)
		}
	}
	
	def remove(String taskName) {
		if (isAdded(taskName)) {
			tasks.remove(taskName)
		}
	}
	
	def isAdded(String taskName) {
		return tasks.contains(taskName)
	}
	
	def getEntry() {
		return tasks.get(0)
	}
	
	def String generate() {
		var result = "ENTRY_TASK(" + tasks.get(0) + ");" + CommonGenerator.newLine
		for (var i = 1; i < tasks.size; i++) {
			result += "TASK(" + tasks.get(i) + ");" + CommonGenerator.newLine
		}
		
		return result
	}
	
	def String generateTask(String taskName, String taskBody, String nextTaskName) {
		var String result = null
		if (isAdded(taskName)) {
			if (tasks.indexOf(taskName) == 0) {
				result = generateEntryTask(taskName, taskBody, nextTaskName)
			} else {
				result = generateRegularTask(taskName, taskBody, nextTaskName)
			}
		}
		return result
	}
	
	def String nextTask(String nextTaskName) {
		'''
		return «nextTaskName»;
		'''
	}
	
	def String nextTask() {
		'''
		return NULL;
		'''
	}
	
	def String generateRegularTask(String taskName, String body, String nextTaskName) {
		'''
		TASK(«taskName») {
		    «body»
		    «IF nextTaskName === null»
		        «nextTask()»
		    «ELSE»
		        «nextTask(nextTaskName)»
		    «ENDIF»
		}
		'''
	}
	
	def String generateEntryTask(String taskName, String body, String nextTaskName) {
		'''
		ENTRY_«generateRegularTask(taskName, body, nextTaskName)»
		'''
	}
	
	
}