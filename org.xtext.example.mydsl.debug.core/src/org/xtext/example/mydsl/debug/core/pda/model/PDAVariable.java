package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class PDAVariable extends PDADebugElement implements IVariable {
//    name & stack frame
    private String fName;
    private PDAStackFrame fFrame;
    private IValue fValue;
    
    public PDAVariable(PDAStackFrame frame, String name, String value) throws DebugException {
        super(frame.getPDADebugTarget());
        fFrame = frame;
        fName = name;
        this.setValue(value);
    }
    
    @Override
    public void setValue(String expression) throws DebugException {
        this.fValue = new PDAValue(this.getPDADebugTarget(), expression);
        fireChangeEvent(DebugEvent.CONTENT);
    }

    @Override
    public void setValue(IValue value) throws DebugException {
        this.fValue = value;
    }

    @Override
    public boolean supportsValueModification() {
        return false;
    }

    @Override
    public boolean verifyValue(String expression) throws DebugException {
        return false;
    }

    @Override
    public boolean verifyValue(IValue value) throws DebugException {
        return false;
    }

    @Override
    public String getName() throws DebugException {
        return this.fName;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        return "Value";
    }

    @Override
    public IValue getValue() throws DebugException {
        return this.fValue;
    }

    @Override
    public boolean hasValueChanged() throws DebugException {
        return false;
    }
    
    protected PDAStackFrame getStackFrame() {
        return this.fFrame;
    }
}
