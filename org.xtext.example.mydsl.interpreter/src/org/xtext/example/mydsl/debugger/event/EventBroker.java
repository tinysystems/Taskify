package org.xtext.example.mydsl.debugger.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EventBroker extends Thread {
    ServerSocket inServerSocket, outServerSocket;
    Socket inSocket, outSocket;
    boolean serverOn, isFirst;
    public static enum State { INIT, SUSPEND, RESUME, STEP };
    
    public EventBroker (ServerSocket server, ServerSocket client) {
        this.inServerSocket = server;
        this.outServerSocket = client;
        this.serverOn = true;
    }
    
    public void run() {
        String data = null;
        
        try {
            inSocket = inServerSocket.accept();
            outSocket = outServerSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        EventHandler eventHandler = new EventHandler();
        new EventStateHandler(outSocket); // dummy initialize EventStateHandler
        eventHandler.handle("start", outSocket);
        
        while(serverOn) {
            try {
                Thread.sleep(1000);
                BufferedReader requestReader = new BufferedReader(new InputStreamReader(inSocket.getInputStream()));
                
                while((data = requestReader.readLine()) != null) {
                    System.out.println("org.xtext.example.mydsl.interpreter in: " + data);
                    
                    // A request came from UI for data, send response via socket.
                    if(data.equalsIgnoreCase("stack")) {
                        eventHandler.handle(data, inSocket);
                    } else if(data.equals("exit")) {
                        System.out.println("Debugging is terminated.");
                        exit();
                    } else {
                        eventHandler.sendMessage(inSocket, "ok");
                        eventHandler.handle(data, outSocket);
                    }
                }
            } catch (Exception e) {
                eventHandler.sendMessage(outSocket, "error");
                e.printStackTrace();
            } finally {
                exit();
            }
        }
    }
    
    public void exit() {
        try {
            serverOn = false;
            outSocket.close();
            inSocket.close();
            outServerSocket.close();
            inServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
