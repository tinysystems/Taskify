package org.xtext.example.mydsl.debugger.context;

import java.util.LinkedHashSet;

public class StateContext {
	private static String state = ""; //INIT, SUSPEND, GO, RESUME_STEP, SUSPEND_STEP, SETTING
	private static String modelState = "NOT_END"; //NOT_END END 
	//Set is not duplicate value, and LinkedHash is ordered list 
	private static LinkedHashSet<Integer> breaklines = new LinkedHashSet<Integer>();
	private static int srcline = 0;
	private static int breakpointSuspendedline = 0;
	private static String filePath = "";
	
	public static String getState() {
		return state;
	}

	public static void setState(String state) {
		StateContext.state = state;
	}
	
	public static String getModelState() {
		return modelState;
	}
	
	public static void setModelState(String modelState) {
		StateContext.modelState = modelState;
	}
	
	public static LinkedHashSet<Integer> getBreaklines() {
		return breaklines;
	}

	public static void setBreaklines(LinkedHashSet<Integer> breaklines) {
		StateContext.breaklines = breaklines;
	}
	
	public static int getSrcline() {
		return srcline;
	}

	public static void setSrcline(int srcline) {
		StateContext.srcline = srcline;
	}
	
	public static int getBreakpointSuspendedline() {
		return breakpointSuspendedline;
	}

	public static void setBreakpointSuspendedline(int breakpointSuspendedline) {
		StateContext.breakpointSuspendedline = breakpointSuspendedline;
	}
	
	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		StateContext.filePath = filePath;
	}
	
}
