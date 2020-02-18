package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.xtext.example.mydsl.debug.core.pda.DebugCorePlugin;

public class PDADebugElement extends DebugElement {    
    public PDADebugElement(IDebugTarget target) {
        super(target);
    }
    
    @Override
    public String getModelIdentifier() {
        return DebugCorePlugin.ID_PDA_DEBUG_MODEL;
    }
    
    public String sendRequest(String request) throws DebugException {
        return getPDADebugTarget().sendRequest(request);
    }
    
    protected PDADebugTarget getPDADebugTarget() {
        return (PDADebugTarget) getDebugTarget();
    }
    
    protected IBreakpointManager getBreakpointManager() {
        return DebugPlugin.getDefault().getBreakpointManager();
    }
}
