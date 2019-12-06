package org.xtext.example.mydsl.debugger.processing;

import java.util.HashMap;
import java.util.Map;

import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.myDsl.VariableDecleration;

public class VariableDeclerationProcessing extends AbstractStackHelper implements IBody {
	VariableDecleration variableDecleration;
	
	public VariableDeclerationProcessing(VariableDecleration variableDecleration) {
		this.variableDecleration = variableDecleration;
	}

	@Override
	public void execute(String functionId) {
		String type = variableDecleration.getType().toString();
		String name = variableDecleration.getName();
		
		Symbol symbol = new Symbol(type, name);
		
		if(variableDecleration.getDimension().size() > 0) {
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
