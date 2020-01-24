/*
 * generated by Xtext 2.19.0
 */
package org.xtext.example.mydsl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.xtext.example.mydsl.myDsl.InkApp
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.EntryTask
import org.xtext.example.mydsl.myDsl.Task
import org.eclipse.emf.ecore.EObject

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class MyDslGenerator extends AbstractGenerator {
	val static String APPINIT = "appinit.c"
	val static String THREAD = "thread1.c"

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		initialize(fsa)
		var model = resource.contents.head as InkApp
		
		try {
			if (model !== null) {
				fsa.generateFile(APPINIT, CommonGenerator.appinit())
				fsa.generateFile(THREAD, thread1(model, fsa))			
			}
		} catch (RuntimeException e) {
			e.printStackTrace
		} catch (Exception e) {
			e.printStackTrace()
		}

		
//		fsa.generateFile('greetings.txt', 'People to greet: ' + 
//			resource.allContents
//				.filter(InkApp)
//				.map[name]
//				.join(', '))

	}
	
	
	def void initialize(IFileSystemAccess2 fsa) {
		try {
			TaskTable.resetTaskTable()
			SymbolTable.resetTable()
			fsa.deleteFile(APPINIT)
			fsa.deleteFile(THREAD)
			
		} catch (RuntimeException e) {
			throw e
		}
	}
	
	def thread1(InkApp model, IFileSystemAccess2 fsa) {
		var String threadContent = ""
		val GeneratorSwitcher generator = new GeneratorSwitcher()
		
		var String includeContent = ""
		var String globalContent = ""
		var String constantContent = ""
		var String taskDeclerationContent = ""
		var String tasksContent = ""
		
		
		try {
			IncludeTable.add(IncludeTemplates.inkLibrary)
			
			globalContent = "__shared(" + "\n"
			for (GlobalVariableExpression global: model.globals) {				
				globalContent += CommonGenerator.tab + generator.generate(global)
			}
			globalContent += ")" + CommonGenerator.newLine
			
			for (ConstantVariableExpression constant: model.constants) {
				constantContent += generator.generate(constant)
			}
			
			
			val TaskTable taskTable = TaskTable.taskTable
			val EntryTask entry = model.entry
			if (entry !== null) {
				var Task task = entry.task
				taskTable.add(task.name)
				
				
				var String taskBody = ""
				for (EObject bodyElement: task.taskbody.body){
					taskBody += generator.generate(bodyElement)
				}
				
				var nextTask = task.taskbody.nexttask
				while (nextTask !== null) {
					if (!taskTable.isAdded(nextTask.name)) {
						tasksContent += taskTable.generateTask(task.name, taskBody, nextTask.name) + CommonGenerator.newLine
						taskBody = ""
						task = nextTask
						nextTask = null
						
						taskTable.add(task.name)
						
						for (EObject bodyElement: task.taskbody.body){
							taskBody += generator.generate(bodyElement)
						}
					}
					nextTask = task.taskbody.nexttask
				}
				
				tasksContent += taskTable.generateTask(task.name, taskBody, null) + CommonGenerator.newLine
			}
			
			
			
			
			
			
			
			// println(SymbolTable.toStringg())
			includeContent = IncludeTable.generate
			taskDeclerationContent = taskTable.generate

			threadContent = HeaderComment.headerThread
			threadContent += includeContent + CommonGenerator.doubleNewLine
			
			threadContent += HeaderComment.headerGlobal
			threadContent += globalContent + CommonGenerator.doubleNewLine
			
			threadContent += HeaderComment.headerConstant
			threadContent += constantContent + CommonGenerator.doubleNewLine
			
			threadContent += HeaderComment.headerTaskDecleration
			threadContent += taskDeclerationContent + CommonGenerator.doubleNewLine
			
			threadContent += HeaderComment.headerThreadInit
			threadContent += CommonGenerator.thread1_init(taskTable.entry)			
			
			threadContent += HeaderComment.headerTaskDefinition
			threadContent += tasksContent
		
		} catch (Exception e) {
			throw e
		}
		return threadContent
	}
	
}
