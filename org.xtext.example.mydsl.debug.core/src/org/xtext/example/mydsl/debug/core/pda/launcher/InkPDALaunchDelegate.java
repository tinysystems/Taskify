package org.xtext.example.mydsl.debug.core.pda.launcher;

import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;
import org.xtext.example.mydsl.debug.core.pda.model.PDADebugTarget;

public class InkPDALaunchDelegate extends LaunchConfigurationDelegate{
    @Override
    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor mnonitor)
            throws CoreException {
        
        File interpreterExecutableFile = null;
        String interpreterExecutableAbsolutePath = "";
        URL workbenchRootPath = Platform.getInstallLocation().getURL();
        String interpreterExecutableAppendPath = "debug/interpreter.jar";
        
//        Try to get absolute full path of interpreter executable jar file.
        try {
            URL workbenchPlusInterpreterURL = new URL(workbenchRootPath.toExternalForm() + interpreterExecutableAppendPath); 
            interpreterExecutableFile = new File(workbenchPlusInterpreterURL.toURI());
            interpreterExecutableAbsolutePath = interpreterExecutableFile.getAbsolutePath();
        } catch (Exception e) {
            abort("Specified debugger executable " + interpreterExecutableAbsolutePath + " does not exist", null);
        }
        
//        if(!interpreterExecutableFile.exists()) {
//            abort("Specified debugger executable {0} does not exist. Check value of interpreter.jar", null);
//        }
        
//        ATTR_PDA_PROGRAM attribute is set by PDAMainTab::performApply() method
        String dslApplicationRelativePath = configuration.getAttribute(DebugCorePlugin.ATTR_PDA_PROGRAM, (String) null);
        if(dslApplicationRelativePath == null) {
            abort("InK Application unspecified.", null);
        }
        
        IFile dslApplicationFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(dslApplicationRelativePath));
        if(!dslApplicationFile.exists()) {
            abort("InK Application file " + dslApplicationFile.getFullPath().toString()+ " does not exist.", null);
        }
        
        String dslApplicationAbsolutePath = dslApplicationFile.getLocation().toOSString();
//        String argValue = configuration.getAttribute(DebugCorePlugin.ATTR_PDA_ARG_VALUE, "7,8,9,10");
        
        String debugMode = "run";
        if (mode.equals(ILaunchManager.DEBUG_MODE)) {
            debugMode = "debug";
        }
        
        List<String> command = new ArrayList<>();
        command.add("java");
        command.add("-Xss10m");
        
        // TODO remove below in the future
        interpreterExecutableAbsolutePath = "/home/ink/Desktop/interpreter.jar";
        command.add("-Xdebug");
        command.add("-Xrunjdwp:transport=dt_socket,address=8000,server=y");
        // TODO remove above in the future
        
        command.add("-jar");
        command.add(interpreterExecutableAbsolutePath);
        command.add("-i");
        command.add(dslApplicationAbsolutePath);
        command.add("-m");
        command.add(debugMode);
        
        ProcessBuilder pb = new ProcessBuilder(command);
        
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IProcess pluginProcess = DebugPlugin.newProcess(launch, process, interpreterExecutableAbsolutePath);
        // if in debug mode, create a debug target
        if (mode.equals(ILaunchManager.DEBUG_MODE)) {
            int requestPort = 29777;
            int eventPort = 29888;
            
            IDebugTarget debugTarget = launch.getDebugTarget();
            
//            Check its applicability.
            if(debugTarget != null) {
                launch.removeDebugTarget(debugTarget);
                debugTarget = null;
            }
            
            debugTarget = new PDADebugTarget(launch, pluginProcess, requestPort, eventPort);
            launch.addDebugTarget(debugTarget);
        }
    }
    
    private void abort(String message, Throwable exception) throws CoreException {
        throw new CoreException(new Status(IStatus.ERROR, DebugCorePlugin.getDefault().getBundle().getSymbolicName(), 0, message, exception));
    }
    
//    Returns a free port number on localhost, or -1 if unable to find a free port
    public static int findFreePort() {
        try(ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }catch (Exception e) {
        }
        return -1;
    }
    
    @Override
    public boolean buildForLaunch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) throws CoreException {
        return false;
    }

}
