package org.xtext.example.mydsl.debugger.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import org.xtext.example.mydsl.debugger.ModelGenerator;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.StateContext;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;

public class EventHandler {

    public void handle(String command, Socket response) {
        
        Socket outSocket = response;
        String lowercaseCommand = command.toLowerCase();
        
        // System.out.println("command: " + command);
        
        switch (lowercaseCommand) {            
            case "resume":
                StateContext.setState(StateContext.RESUME_STATE);
                break;
            case "step":
                StateContext.setState(StateContext.RESUME_STEP_STATE);
                break;
            case "suspend":
                ModelGenerator.getLauncher().suspend();
                break;
            case "exit":
                System.exit(0);
            default:
                sendMessage(outSocket, responsehandle(command));
                break;
        }
        
        if(StateContext.getState().equals(StateContext.RESUME_STEP_STATE)) {
            ModelGenerator.getLauncher().resume();
        } else if(StateContext.getState().equals(StateContext.RESUME_STATE)) {
            checkModelAndTryResume();
        }
    }
    
    @SuppressWarnings("unused")
    private boolean isInitialized(String command) {
        boolean initialized = false;
        
        if(StateContext.getState().equals(StateContext.INIT_STATE)) {
            if(!command.equals("brk")) {
                initialized = true;
            }
        }
        
        return initialized;
    }
    
    private void checkModelAndTryResume() {
        boolean wait = true;
        
        while(wait) {
            if(StateContext.getModelState().equals(StateContext.END_MODEL_STATE)) {
                ModelGenerator.getLauncher().resume();
                wait = false;
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    
                }
            }
        }
    }
    
    private String responsehandle(String command) {
        String lowercaseCommand = command.toLowerCase();
        String responseData = "";
        StateContext.setState(StateContext.SETTING_STATE);
        
        if(lowercaseCommand.equals("start")) {
            responseData = "started";
        } else if (lowercaseCommand.startsWith("brk")) {
            int lastSpace = command.lastIndexOf('#');
            
            if (lastSpace > 0) {
                String line = command.substring(lastSpace + 1);
                int breakline = Integer.parseInt(line);
                
                StateContext.addBreakline(breakline);
                responseData = "set brk " + breakline;
            }
        } else if (lowercaseCommand.startsWith("unbrk")) {
            int lastSpace = command.lastIndexOf('#');
            
            if (lastSpace > 0) {
                String line = command.substring(lastSpace + 1);
                int breakline = Integer.parseInt(line);
                
                StateContext.removeBreakline(breakline);
                responseData = "unset brk " + breakline;
            }
        } else if (lowercaseCommand.equals("p")) {
            responseData = printDebugging(responseData);
        } else if (lowercaseCommand.equals("data")) {
            responseData = stackMsgString(CallStack.getCallStack()); 
        } else if (lowercaseCommand.equals("stack")) {
            responseData = printCallStack(responseData);
        } else if (lowercaseCommand.equals("exit")) {
            responseData = "terminated";
        } else {
            responseData = "unimplemented instruction >>" + command + "<<";
        }
        
        return responseData;
    }
    
    private String printCallStack(String responseData) {
        StringBuffer lines = new StringBuffer();
        
//        print frame info
        for(CallStackItem item : CallStack.getCallStack()){
            String functinId = item.getId();
            SymbolTable table = item.getSymbolTable();
            
            lines.append(StateContext.getFilePath());
            lines.append("&");
            lines.append(StateContext.getSrcline());
            lines.append("&");
            
            //funcId|varType|varName|varVal&
            lines.append(functinId);
            lines.append("&");
            lines = printSymbolTable(table, lines);
            lines.append("#");
            
        }
        
        return lines.toString();
    }
    
    private StringBuffer printSymbolTable(SymbolTable symbolTable, StringBuffer lines) {
        for(Symbol sym : symbolTable.getSymbolTable()){
            String line = sym.getType() + "|" + sym.getName() + "|" + sym.valueToString();
            line = line + "&";
            lines.append(line);
        }
        
        return lines;
    }
    
    private String printDebugging(String responseData) {
        responseData = "src: " + StateContext.getSrcline() + "/brks:";
        for (int brk : StateContext.getBreaklines()) {
            responseData += String.valueOf(brk) + ".";
        }
        responseData += "step: " + StateContext.getBreakpointSuspendedline();
        responseData += "|state: " + StateContext.getState();

        return responseData;
    }
    
    private String stackMsgString(LinkedList<CallStackItem> callstack) {
        StringBuffer lines = new StringBuffer();

        // this line need for sending filepath for source code lookup and
        // DSLStackFrame
        // init constructor.
        lines.append(StateContext.getFilePath());
        lines.append("#");
        // send suspended line number
        lines.append(StateContext.getSrcline());
        lines.append("#");

        for (Symbol item : callstack.get(0).getSymbolTable().getSymbolTable()) {
            String line = item.getType() + ": " + item.getName() + ": " + item.getVariableValue();
            line += "#";

            lines.append(line);
        }

        return lines.toString();
    }
    
    protected void sendMessage(Socket socket, String message) {
        try {
            System.out.println("org.xtext.example.mydsl.interpreter out: " + message);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
