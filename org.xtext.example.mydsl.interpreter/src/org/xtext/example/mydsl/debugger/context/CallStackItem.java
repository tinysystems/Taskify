package org.xtext.example.mydsl.debugger.context;

public class CallStackItem {
    private String id;
    private SymbolTable symbolTable;
    
    public CallStackItem(String id, SymbolTable symbolTable){
        this.id = id;
        this.symbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CallStackItem [" + 
                "  id=" + this.id + 
                ", SymbolTable=" + this.symbolTable + 
                "  ]";
    }
}
