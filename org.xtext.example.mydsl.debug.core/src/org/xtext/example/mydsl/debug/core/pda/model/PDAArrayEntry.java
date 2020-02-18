package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class PDAArrayEntry extends PDADebugElement implements IVariable {
    private IValue fValue;
    private int fIndex;
    
    public PDAArrayEntry(IDebugTarget debugTarget, int index, IValue value) {
        super(debugTarget);
        this.fIndex = index;
        this.fValue = value;
    }
    @Override
    public void setValue(String expression) throws DebugException {
    }

    @Override
    public void setValue(IValue value) throws DebugException {
    }

    @Override
    public boolean supportsValueModification() {
        return false;
    }

    @Override
    public boolean verifyValue(String arg0) throws DebugException {
        return false;
    }

    @Override
    public boolean verifyValue(IValue arg0) throws DebugException {
        return false;
    }

    @Override
    public String getName() throws DebugException {
        return "[" + this.fIndex + "]";
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        return "Array";
    }

    @Override
    public IValue getValue() throws DebugException {
        return fValue;
    }

    @Override
    public boolean hasValueChanged() throws DebugException {
        return false;
    }

}
