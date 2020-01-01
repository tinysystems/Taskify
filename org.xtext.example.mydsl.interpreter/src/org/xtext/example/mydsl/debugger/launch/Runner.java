package org.xtext.example.mydsl.debugger.launch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression;
import org.xtext.example.mydsl.myDsl.EntryTask;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.xtext.example.mydsl.myDsl.Task;
import org.xtext.example.mydsl.myDsl.TaskBody;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;


public class Runner extends ExpressionSwitcher {	
	String threadName;
	InkApp app;
	
	public Runner(InkApp app) {
		super();
		setMode(MODES.RUN);
		this.app = app;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		EList<GlobalVariableExpression> globalVariables = app.getGlobals();
		if (globalVariables.size() > 0) {
			this.execute((EList<Expression>)(EList<?>) globalVariables, "global");
		}
		
		EList<ConstantVariableExpression> constantVariables = app.getConstants();
		if (constantVariables.size() > 0) {
			this.execute((EList<Expression>)(EList<?>) constantVariables, "constant");
		}
		
		EntryTask entryTask = app.getEntry();
		if (entryTask != null) {
			boolean endTaskFound = false;
		
			TaskBody taskBody = entryTask.getTask().getTaskbody();
			System.out.println("Entry task '" + entryTask.getTask().getName() + "' is being executed.");
			this.execute(taskBody, entryTask.getTask().getName());
			
			Task task = taskBody.getNexttask();
			while(task != null) {
				taskBody = task.getTaskbody();
				String end = taskBody.getEndtask();
				System.out.println("Task '" + task.getName() + "' is being executed.");
				this.execute(taskBody, task.getName());
				
				if (end != null) {
					endTaskFound = true;
				}
				
				task = taskBody.getNexttask();
			}
			
			if (endTaskFound) {
				System.out.println("Execution is finished.");
			} else {
				System.out.println("No end task found, exactly one is expected.");
			}
		} else {
			System.out.println("No entry task found.");
		}
		System.exit(0);
	}
	
	private void execute(EList<Expression> variables, String id) {
		CallStack.getCallStack().add(new CallStackItem(id, new SymbolTable()));
		
		for(Expression variable: variables) {
			super.execute(variable, id);
		}
	}
	
	private void execute(TaskBody taskBody, String id) {
		CallStack.getCallStack().add(new CallStackItem(id, new SymbolTable()));
		
		for(EObject bodyElement: taskBody.getBody()) {
			super.execute(bodyElement, id);
		}
	}
	
}
