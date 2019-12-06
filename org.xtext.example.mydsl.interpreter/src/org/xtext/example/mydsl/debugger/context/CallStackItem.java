package org.xtext.example.mydsl.debugger.context;

public class CallStackItem {
	private String functionId;
	private SymbolTable symbolTable;
	
	public CallStackItem(String functionId, SymbolTable symbolTable){
		this.functionId = functionId;
		this.symbolTable = symbolTable;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Override
	public String toString() {
		return "CallStackItem [" + 
				"  FunctionId=" + this.functionId + 
				", SymbolTable=" + symbolTable + 
				", toString()=" + super.toString() + 
				"  ]";
	}
}
