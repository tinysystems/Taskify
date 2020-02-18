package org.xtext.example.mydsl.debugger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.net.BindException;
import java.net.ServerSocket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.xtext.example.mydsl.MyDslStandaloneSetup;
import org.xtext.example.mydsl.debugger.context.StateContext;
import org.xtext.example.mydsl.debugger.event.EventBroker;
import org.xtext.example.mydsl.debugger.launch.Debugger;
import org.xtext.example.mydsl.debugger.launch.Runner;
import org.xtext.example.mydsl.debugger.processing.ThreadLauncher;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.Exceptions;
import com.google.inject.Injector;

public class ModelGenerator {
    public static String[] args;
    private static ThreadLauncher launcher;
    
    static String inputFilePath;
    static String argsValue;
    static String mode;
    
    public static void main(final String[] args) {
//        -i /home/ink/runtime-DSLapp/demo/src/mydsl/Model.mydsl -m debug
        init(args);
    }
    
    public static void init(final String[] args) {
        ServerSocket requestListener;
        ServerSocket eventSender;
        
        try {
            parseArguments(args);
            requestListener = new ServerSocket(29777); // server
            eventSender = new ServerSocket(29888); // client
            EventBroker eventBroker = new EventBroker(requestListener, eventSender);
            
            eventBroker.start();
//            init(argsValue);
            final InkApp app = readFile(inputFilePath);
            
            if(app != null) {
                StateContext.setModelState("END");
                StateContext.setFilePath(inputFilePath);
            }
            
            System.out.println("Debugging is going to be started.");
            
            if(mode.equals("debug")) {
                setLauncher(new Debugger(app, null));
                getLauncher().start();
                System.out.println("Done debug");
            } else if(mode.equals("run")) {
                setLauncher(new Runner(app));
                getLauncher().start();
                System.out.println("Done run");
            } else {
                System.out.println("Mode is not compatible.");
            }
        } catch (BindException e) {
            System.out.println("29777 and/or 29888 port(s) are being used by another process.");
        } catch (Throwable e) {
            throw Exceptions.sneakyThrow(e);
        }
    }
    
//    private static void init(String argumentText) {
//        String[] args = argumentText.split(",");
//
//        ModelGenerator.args = new String[args.length];
//        int i = 0;
//        for (final String arg : args) {
//            ModelGenerator.args[i] = arg;
//            ++i;
//        }
//    }
    
    private static void parseArguments(final String[] args) {
//        inputFilePath = "/";
//        argsValue = "port1, port2";
//        mode = "debug";        
        Options options = getOptions();
        CommandLineParser parser = new GnuParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine command;
        
        try {
            command = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Usage", options);
            
            System.exit(1);
            return;
        }
        
        inputFilePath = command.getOptionValue("input");
        argsValue = command.getOptionValue("args");
        mode = command.getOptionValue("mode");
        
        System.out.println("input:" + inputFilePath);
        System.out.println("args:" + argsValue);
        System.out.println("mode:" + mode);
    }
    
    private static Options getOptions() {
        Options options = new Options();
        
        Option input = new Option("i", "input", true, "file path");
        input.setRequired(true);

        Option output = new Option("a", "args", true, "sequence of arg index/arg value ex) 66/1,67/2");
        output.setRequired(true);
        
        Option mode = new Option("m", "mode", true, "set mode run or debug ex) -m run");
        mode.setRequired(true);
        
        options.addOption(input);
//        options.addOption(output);
        options.addOption(mode);
        
        return options;
    }
    
    public static InkApp readFile(final String filePath) {
        try {
            String line = null;
            String fileContent = "";
            final FileReader fileReader = new FileReader(filePath);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            try {
                final StringBuilder stringBuilder = new StringBuilder();
                
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
                fileContent = stringBuilder.toString();
            } finally {
                bufferedReader.close();
                fileReader.close();
            }
            
            final InkApp app = resourceBuilder(fileContent);
            return app;
        } catch (Throwable e) {
            throw Exceptions.sneakyThrow(e);
        }
    }

    public static InkApp resourceBuilder(final String programText) {
        InkApp inkApp = null;
        try {
            final Injector injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
            byte[] programTextAsBytes = programText.getBytes();
            final ByteArrayInputStream in = new ByteArrayInputStream(programTextAsBytes);
            final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
            
            resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
            final Resource resource = resourceSet.createResource(URI.createURI(".mydsl"));
            resource.load(in, resourceSet.getLoadOptions());
            inkApp = (InkApp) resource.getContents().get(0);
        } catch (Throwable e) {
            Exceptions.sneakyThrow(e);
        }
        return inkApp;
    }
    
    public static ThreadLauncher getLauncher() {
        return ModelGenerator.launcher;
    }

    public static void setLauncher(Debugger launcher) {
        ModelGenerator.launcher = launcher;
    }

    private static void setLauncher(Runner launcher) {
        ModelGenerator.launcher = launcher;
    }
}
