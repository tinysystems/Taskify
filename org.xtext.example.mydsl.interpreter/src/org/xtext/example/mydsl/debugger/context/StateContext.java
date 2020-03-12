package org.xtext.example.mydsl.debugger.context;

import java.util.HashSet;

public class StateContext {
    final public static String INIT_STATE = "INIT";
    final public static String SUSPEND_STATE = "SUSPEND";
    final public static String GO_STATE = "GO";
    final public static String RESUME_STATE = "RESUME";
    final public static String RESUME_STEP_STATE = "RESUME_STEP";
    final public static String SUSPEND_STEP_STATE = "SUSPEND_STEP";
    final public static String SETTING_STATE = "SETTING";
    
    final public static String END_MODEL_STATE = "END";
    final public static String NOT_END_MODEL_STATE = "NOT_END";
    
    private static String state = "INIT";
    private static String modelState = "NOT_END"; //NOT_END END 
    private static HashSet<Integer> breaklines = new HashSet<Integer>();
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
    
    public static HashSet<Integer> getBreaklines() {
        HashSet<Integer> temp = new HashSet<Integer>();
        for (int line: breaklines) {
            temp.add(line);
        }
        return temp;
    }
    
    public static void addBreakline(int breakline) {
        breaklines.add(breakline);
    }
    
    public static void addBreaklines(int breakliness[]) {
        for (int breakline: breaklines) {
            breaklines.add(breakline);
        }
    }
    
    public static void removeBreakline(int breakline) {
        breaklines.remove(breakline);
    }
    
    public static void removeBreaklines(int breakliness[]) {
        for (int breakline: breaklines) {
            breaklines.remove(breakline);
        }
    }

    public static void setBreaklines(HashSet<Integer> breaklines) {
        StateContext.breaklines = breaklines;
    }
    
    public static int getSrcline() {
        return srcline;
    }

    public static void setSrcline(int line) {
        srcline = line;
    }
    
    public static int getBreakpointSuspendedline() {
        return breakpointSuspendedline;
    }

    public static void setBreakpointSuspendedline(int suspendedline) {
        breakpointSuspendedline = suspendedline;
    }
    
    public static void resetBreakpointSuspendedline() {
        breakpointSuspendedline = 0;
    }
    
    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String path) {
        filePath = path;
    }
    
}
