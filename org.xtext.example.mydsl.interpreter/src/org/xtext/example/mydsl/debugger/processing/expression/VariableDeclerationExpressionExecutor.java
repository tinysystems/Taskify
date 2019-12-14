package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.HashMap;
import java.util.Map;

import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression;;


public class VariableDeclerationExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	VariableDeclerationExpression expression;
	ExpressionSwitcher executor;
	
	public VariableDeclerationExpressionExecutor(VariableDeclerationExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}

	@Override
	public void execute(String id) {
		String name = this.expression.getName();
		String type = this.expression.getType().getType();
		
		Symbol symbol = new Symbol(name, type);
		symbol.setScope("local");
		if(this.expression.getDimension().size() > 0) {
//			?? generateArrayValue(symbol); since 'symbol' is not primitive type
			symbol = generateArrayValue(symbol);
		}
		addToSymbolTable(symbol, id);
	}
	
	private Symbol generateArrayValue(Symbol symbol) {
//		Array is converted into map internally.
//		integer[10][20][30] => Map<"10,20,30", value>
		Map<String, Object> array = new HashMap<String, Object>();
		symbol.setVariableValue(array);
		return symbol;
	}

	private void addToSymbolTable(Symbol symbol, String id) {
		CallStackItem item = lookupStackItem(id);
		
		if (item != null) {
			SymbolTable symbolTable = item.getSymbolTable();
			symbolTable.getSymbolTable().add(symbol);
		}
	}

}
