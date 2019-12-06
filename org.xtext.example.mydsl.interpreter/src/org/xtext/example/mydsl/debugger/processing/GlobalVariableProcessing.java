package org.xtext.example.mydsl.debugger.processing;

import java.util.HashMap;
import java.util.Map;

import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.myDsl.GlobalVariable;
import org.xtext.example.mydsl.myDsl.VariableDecleration;

public class GlobalVariableProcessing extends AbstractStackHelper implements IBody {
	GlobalVariable globalVariable;
	
	public GlobalVariableProcessing(GlobalVariable globalVariable) {
		this.globalVariable = globalVariable;
	}
	
	@Override
	public void execute(String functionId) {
		VariableDecleration typedVariable = globalVariable.getVariable(); 
		String type = typedVariable.getType().toString();
		String name = typedVariable.getName();
		String scope = "global";
		
		Symbol symbol = new Symbol(type, name, scope);
		
		if(typedVariable.getDimension().size() > 0) {
//			?? generateArrayValue(symbol); since 'symbol' is not primitive type
			symbol = generateArrayValue(symbol);
		}
		addSymbolTable(symbol, functionId);
		
	}
	
	private Symbol generateArrayValue(Symbol symbol) {
//		Array is converted into map internally.
//		integer[10][20][30] => Map<"10,20,30", value>
		Map<String, Object> array = new HashMap<String, Object>();
		symbol.setVariableValue(array);
		return symbol;
	}
	
	private void addSymbolTable(Symbol symbol, String functionId) {
		CallStackItem callStackItem = lookupStackItem(functionId);
		
		if(callStackItem != null) {
			SymbolTable symbolTable = callStackItem.getSymbolTable();
			symbolTable.getSymbolTable().add(symbol);
		}
	}

}
