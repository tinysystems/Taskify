package org.xtext.example.mydsl.debug.core.pda.sourcelookup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.xtext.example.mydsl.debug.core.pda.model.PDAStackFrame;

public class PDASourceLookupParticipant extends AbstractSourceLookupParticipant {

    @Override
    public String getSourceName(Object arg0) throws CoreException {
        if(arg0 instanceof PDAStackFrame) {
            return ((PDAStackFrame) arg0).getSourceName();
        }
        return null;
    }

}
