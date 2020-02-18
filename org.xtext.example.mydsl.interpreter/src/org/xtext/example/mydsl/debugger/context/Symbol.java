package org.xtext.example.mydsl.debugger.context;


public class Symbol {
    private String name;
    private String type; // int | string | double | boolean
    private Object variableValue; // value of the variable
    private String scope; // shared | local
    
    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public Symbol(String name, String type, String scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }
    
    public Symbol(String name, String type, Object variableValue, String scope) {
        this.name = name;
        this.type = type;
        this.variableValue = variableValue;
        this.scope = scope;
    }
    
    public Symbol() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(Object variableValue) {
        this.variableValue = variableValue;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @Override
    public String toString() {
        return "Symbol [" + 
                "  type=" + type + 
                ", name=" + name + 
                ", variableValue=" + variableValue + 
                ", scope=" + scope + 
                "  ]";
    }
}
