package org.xtext.example.mydsl.debugger.launch;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.debugger.processing.BodySwitcher;
import org.xtext.example.mydsl.myDsl.Body;
import org.xtext.example.mydsl.myDsl.GlobalVariable;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.xtext.example.mydsl.myDsl.Main;

public class Runner extends BodySwitcher {
	
	String threadName;
	InkApp app;
	
	public Runner(InkApp app) {
		this.app = app;
	}
	
	@Override
	public void run() {
		EList<GlobalVariable> globalVariables = app.getGlobals();
		if(globalVariables != null) {
			execute(globalVariables);
		}
		
		for(Main main: app.getMain()) {
			execute(main);
		}
		System.exit(0);
	}
	
	private void execute(Main main) {
		CallStack.getCallStack().add(new CallStackItem("main", new SymbolTable()));
		
		for(Body body: main.getBody()) {
			executor(body, "main");
		}
	}
	
	private void execute(EList<GlobalVariable> globalVariables) {
		CallStack.getCallStack().add(new CallStackItem("global", new SymbolTable()));
		
		for(GlobalVariable global: globalVariables) {
			executor(global, "global");
		}
	}
}
