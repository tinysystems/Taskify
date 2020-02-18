package org.xtext.example.mydsl.debugger.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EventBroker extends Thread {
    ServerSocket serverSocket, eventSender;
    Socket request, response;
    boolean serverOn, isFirst;
    public enum State { INIT, SUSPEND, RESUME, STEP };
    
    public EventBroker (ServerSocket server, ServerSocket client) {
        this.serverSocket = server;
        this.eventSender = client;
        this.serverOn = true;
    }
    
    public void run() {
        String data = null;
        Socket request = null;
        Socket response = null;
        
        try {
            request = serverSocket.accept();
            response = eventSender.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        EventHandler eventHandler = new EventHandler();
        new EventStateHandler(response); // dummy initialize EventStateHandler
        eventHandler.handle("start", response);
        
        while(serverOn) {
            try {
                Thread.sleep(1000);
                BufferedReader requestReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                
                while((data = requestReader.readLine()) != null) {
                    System.out.println("Request: " + data);
                    
                    // A request came from UI for data, send response via socket.
                    if(data.equalsIgnoreCase("stack")) {
                        eventHandler.handle(data, request);
                    } else if(data.equals("exit")) {
                        System.out.println("Exit debugging");
                    } else {
                        eventHandler.sendOkError(request, "ok");
                        eventHandler.handle(data, response);
                    }
                }
            } catch (Exception e) {
                eventHandler.sendOkError(request, "error");
                e.printStackTrace();
            } finally {
                try {
                    request.close();
                    response.close();
                    serverSocket.close();
                    eventSender.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }
    }
}
