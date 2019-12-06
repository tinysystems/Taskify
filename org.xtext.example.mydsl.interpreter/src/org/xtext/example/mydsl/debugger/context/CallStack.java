package org.xtext.example.mydsl.debugger.context;

import java.util.LinkedList;

public class CallStack {
	private static LinkedList<CallStackItem> callStack = new LinkedList<CallStackItem>();

	public static synchronized LinkedList<CallStackItem> getCallStack() {
		return callStack;
	}

	public static synchronized void setCallStack(LinkedList<CallStackItem> callStack) {
		CallStack.callStack = callStack;
	}
}
