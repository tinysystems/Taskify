package org.xtext.example.mydsl.debugger.launch;

import java.net.Socket;

import org.xtext.example.mydsl.myDsl.InkApp;


public class Debugger extends Launcher {
    Thread thread;
    String threadName;
    Socket socket;
    
    public Debugger(InkApp app, Socket socket) {
        super(app);
        setMode(MODES.DEBUG);
        this.suspended = true;
        this.socket = socket;
    }
    
}
