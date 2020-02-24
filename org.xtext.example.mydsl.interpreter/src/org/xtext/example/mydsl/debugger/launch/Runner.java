package org.xtext.example.mydsl.debugger.launch;

import org.xtext.example.mydsl.myDsl.InkApp;


public class Runner extends Launcher {    
    String threadName;
    
    public Runner(InkApp app) {
        super(app);
        setMode(MODES.RUN);
    }
    
}
