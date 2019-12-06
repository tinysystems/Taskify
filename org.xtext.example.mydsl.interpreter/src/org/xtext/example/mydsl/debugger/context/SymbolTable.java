package org.xtext.example.mydsl.debugger.context;

import java.util.ArrayList;

public class SymbolTable {
	private ArrayList<Symbol> symbolTable = new ArrayList<Symbol>();

	public ArrayList<Symbol> getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(ArrayList<Symbol> symbolTable) {
		this.symbolTable = symbolTable;
	}
}
