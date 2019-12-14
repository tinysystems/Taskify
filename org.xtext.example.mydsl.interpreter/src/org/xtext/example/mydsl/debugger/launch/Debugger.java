package org.xtext.example.mydsl.debugger.launch;

import java.net.Socket;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.myDsl.EntryTask;
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.xtext.example.mydsl.myDsl.Task;
import org.xtext.example.mydsl.myDsl.TaskBody;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;


public class Debugger extends ExpressionSwitcher {
	Thread thread;
	String threadName;
	InkApp app;
	Socket socket;
	ExpressionSwitcher executor;
	
	public Debugger(InkApp inkApp, Socket socket) {
		super();
		this.app = inkApp;
		this.suspended = true;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		EList<GlobalVariableExpression> globalVariables = app.getGlobals();
		if(globalVariables != null) {
			this.execute(globalVariables);
		}
		
		EntryTask entryTask = app.getEntry();
		if (entryTask != null) {
			TaskBody taskBody = entryTask.getTask().getTaskbody();
			this.execute(taskBody);
			
			Task task = taskBody.getNexttask();
			while(task != null) {
				taskBody = task.getTaskbody();
				this.execute(taskBody);
				task = taskBody.getNexttask();			
			}
		}
		System.exit(0);
	}
	
	private void execute(TaskBody taskBody) {
		CallStack.getCallStack().add(new CallStackItem("local", new SymbolTable()));
		
		for(EObject bodyElement: taskBody.getBody()) {
			super.execute(bodyElement, "");
		}
	}
	
	private void execute(EList<GlobalVariableExpression> globalVariables) {
		CallStack.getCallStack().add(new CallStackItem("global", new SymbolTable()));
		
		for(GlobalVariableExpression global: globalVariables) {
			super.execute(global, "global");
		}
	}
}
