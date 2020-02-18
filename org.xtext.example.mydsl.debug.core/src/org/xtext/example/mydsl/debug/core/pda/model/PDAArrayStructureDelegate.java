package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ILogicalStructureTypeDelegate;
import org.eclipse.debug.core.model.IValue;

public class PDAArrayStructureDelegate implements ILogicalStructureTypeDelegate {

    @Override
    public IValue getLogicalStructure(IValue value) throws CoreException {
        return new PDAArray((PDAValue) value);
    }

    @Override
    public boolean providesLogicalStructure(IValue value) {
        try {
            String string = value.getValueString();
            String[] words = string.split(",");
            return words.length > 1;
        } catch (DebugException e) {
        }
        return false;
    }

}
