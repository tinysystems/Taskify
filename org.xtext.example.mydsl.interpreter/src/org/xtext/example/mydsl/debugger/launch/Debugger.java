package org.xtext.example.mydsl.debugger.launch;

import java.net.Socket;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.debugger.processing.BodySwitcher;
import org.xtext.example.mydsl.myDsl.Body;
import org.xtext.example.mydsl.myDsl.GlobalVariable;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.xtext.example.mydsl.myDsl.Main;


public class Debugger extends BodySwitcher {
	Thread thread;
	String threadName;
	InkApp app;
	Socket socket;
	BodySwitcher bodySwitcher;
	
	public Debugger(InkApp inkApp, Socket socket) {
		this.app = inkApp;
		this.suspended = true;
		this.socket = socket;
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
