package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class PDAValue extends PDADebugElement implements IValue {
    private String fValue;
    
    public PDAValue(PDADebugTarget target, String expression) {
        super(target);
        this.fValue = expression;
    }
    
    @Override
    public String getReferenceTypeName() throws DebugException {
//        TODO return type in the future
        String type = null;
        try {
            Integer.parseInt(fValue);
            type = "integer";
        } catch (NumberFormatException e) {
            try {
                Boolean.parseBoolean(fValue);
                type = "boolean";
            } catch (Exception e1) {
                try {
                    Double.parseDouble(fValue);
                    type = "double";
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }
        return type;
    }

    @Override
    public String getValueString() throws DebugException {
        return this.fValue;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        return new IVariable[0];
    }

    @Override
    public boolean hasVariables() throws DebugException {
        if (getVariables().length != 0) {
            return true;
        }
        return fValue.split("\\W+").length > 1;
    }

    @Override
    public boolean isAllocated() throws DebugException {
        return true;
    }
    
    public boolean equals(Object obj) {
        return obj instanceof PDAValue && ((PDAValue)obj).fValue.equals(fValue);
    }

    public int hashCode() {
        return fValue.hashCode();
    }

}
