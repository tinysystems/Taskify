package org.xtext.example.mydsl.debugger.processing;

import java.util.Iterator;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BooleanReference;
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression;
import org.xtext.example.mydsl.myDsl.DoubleReference;
import org.xtext.example.mydsl.myDsl.IntegerReference;
import org.xtext.example.mydsl.myDsl.StringReference;
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression;
import org.xtext.example.mydsl.myDsl.VariableReference;
import org.xtext.example.mydsl.myDsl.VariableSymbol;


public abstract class AbstractStackHelper {
	protected static boolean isBreak = false;
	static boolean isReturn = false;
	static Object lastFunctionReturn = null;
	
	protected static CallStackItem lookupStackItem(String id) {
//		System.out.println("lookupStackItem: id: " + id);
		Iterator<CallStackItem> callStackIterator = CallStack.getCallStack().iterator();
		
		while(callStackIterator.hasNext()) {
			CallStackItem item = callStackIterator.next();
			
			if(item.getId() == id) {
				return item;
			}
		}
		
		return null;
	}
	
	protected static String getParentId() {
		CallStackItem item = CallStack.getCallStack().getLast();
		return item.getId();
	}
	
	protected static Symbol lookupSymbolByAtomic(Atomic atomic, String id) {		
//		if Atomic is StringReference or IntegerReference or DoubleReference or BooleanReference, 
//		the value is not inside call stack. So make dummy symbol and return it.
//		System.out.println("lookupSymbolByAtomic: atomic: " + atomic.toString() + " id: " + id);
		if(isDummy(atomic)) {
			Symbol dummy = dummySymbol(atomic);
			return dummy;
		}
		
		String variableName = getAtomicName(atomic);
		Symbol symbol = lookupSymbolByString(variableName, id);
		
		return symbol;
	}
	
	protected static Symbol lookupSymbolByString(String variableName, String id) {
		Symbol symbol = null;
		
		symbol = searchByString(variableName, id);
		
		if (symbol == null) {
			// Look for GLOBAL variables
			symbol = searchByString(variableName, "global");
		}
		
		if (symbol == null) {
			// Look for CONSTANT variables
			symbol = searchByString(variableName, "constant");
		}
		return symbol;
	}
	
	private static Symbol searchByString(String variableName, String id) {
		Symbol symbol = null;
		CallStackItem callStackItem = lookupStackItem(id);
		Iterator<Symbol> iterator = callStackItem.getSymbolTable().getSymbolTable().iterator();
		
		while(iterator.hasNext()) {
			symbol = iterator.next();
			
			if(symbol.getName() == variableName) {
				return symbol;
			}
		}
		return null;
	}
	
	private static Symbol dummySymbol(Atomic atomic) {
		Object value = null;
		String type = null;
		
		if(atomic instanceof StringReference) {
			value = (String) ((StringReference) atomic).getValue();
			type = "string";
		} else if (atomic instanceof IntegerReference) {
			value = (int) ((IntegerReference) atomic).getValue();
			type = "integer";
		} else if (atomic instanceof DoubleReference) {
			value = (double) ((DoubleReference) atomic).getValue();
			type = "double";
		} else if (atomic instanceof BooleanReference) {
			value = (boolean) ((BooleanReference) atomic).isValue();
			type = "boolean";
		}	
		
		Symbol dummySymbol = new Symbol("dummy", type);
		dummySymbol.setVariableValue(value);
		return dummySymbol;
	}
	
	private static boolean isDummy(Atomic atomic) {
		boolean result = false;
		
		if(atomic instanceof StringReference) {
			result = true;
		} else if (atomic instanceof IntegerReference) {
			result = true;
		} else if (atomic instanceof DoubleReference) {
			result = true;
		} else if (atomic instanceof BooleanReference) {
			result = true;
		}		
		
		return result;
	}
	
	protected static void pushCallStackItem(String id) {
		CallStack.getCallStack().add(new CallStackItem(id, new SymbolTable()));
	}

	protected static void popCallStackItem(String id) {
		CallStackItem item = lookupStackItem(id);
		CallStack.getCallStack().remove(item);
	}
	
	protected static void updateCallStackBySymbol(Symbol symbol, Object value) {
		symbol.setVariableValue(value);
	}
	
	protected static void updateCallStackByAtomic(Atomic atomic, Object value, String id) {
		Symbol symbol = lookupSymbolByAtomic(atomic, id);
		updateCallStackBySymbol(symbol, value);
	}
	
	protected static void updateCallStackBySymbol(Symbol symbol, Object[] values) {
		if (symbol.getVariableValue() instanceof Object[]) {
			Object[] oldValues = (Object[]) symbol.getVariableValue();
			if (oldValues.length == values.length) {
				symbol.setVariableValue(values);
			} else {
				System.out.println("Array values are out of index for variable " + symbol.getName());
			}
		} else {
			System.out.println("Symbol " + symbol.getName() + " is not an array instance.");
		}
	}

	protected static void addCallStackBySymbol(Symbol symbol, String id) {
		CallStackItem item = lookupStackItem(id);
		item.getSymbolTable().getSymbolTable().add(symbol);
	}
	
	protected static Object decoupleAtomic(Atomic atomic, String id) {
		Object m_return = null; 
		
		if(atomic instanceof StringReference) {
			m_return = ((StringReference) atomic).getValue();
		} else if(atomic instanceof BooleanReference) {
			m_return = ((BooleanReference) atomic).isValue();
		} else if(atomic instanceof IntegerReference) {
			m_return = ((IntegerReference) atomic).getValue();
		} else if(atomic instanceof ArrayReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, id);
			Object[] arrayValues = (Object[]) symbol.getVariableValue();
			int index = ((ArrayReference) atomic).getIndex().getSize();
			m_return = arrayValues[index];		
		} else if(atomic instanceof VariableReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, id);
			m_return = symbol.getVariableValue();
		}
		return m_return;
	}
	
	private static boolean updateCallStackByArray(String target, int index, CallStackItem callStackItem, Object value) {		
		for(Symbol symbol: callStackItem.getSymbolTable().getSymbolTable()) {
			if(target.equals(symbol.getName())) {
				Object[] values = (Object[]) symbol.getVariableValue();
				values[index] = value;
				symbol.setVariableValue(values);
				return true;
			}
		}
		return false;
	}
	
	protected static void updateCallStackByArray(ArrayReference array, Object value, String callerId) {
		int index = array.getIndex().getSize();
		String target = ((VariableDeclerationExpression) array.getArrayReference()).getName();
		CallStackItem callStackItem = lookupStackItem(callerId);
		
		boolean found = updateCallStackByArray(target, index, callStackItem, value);
		
//		Update global, in case of non-local
		if (!found) {
			callStackItem = lookupStackItem("global");
			updateCallStackByArray(target, index, callStackItem, value);
		}
	}
	
	private static String getAtomicName(Atomic atomic) {
		String atomicName = "";
		
		if(atomic instanceof VariableReference) {
			atomicName = getVariableSymbolName(((VariableReference) atomic).getVariableReference());
		} else if(atomic instanceof ArrayReference) {
			atomicName = getVariableSymbolName(((ArrayReference) atomic).getArrayReference());
		}
		return atomicName;
	}
	
	private static String getVariableSymbolName(VariableSymbol variableSymbol) {
		String symbolName = "";
		
		if (variableSymbol instanceof VariableDeclerationExpression) {
			symbolName = ((VariableDeclerationExpression) variableSymbol).getName();
		} else if (variableSymbol instanceof ConstantVariableExpression) {
			symbolName = ((ConstantVariableExpression) variableSymbol).getName();
		}
		return symbolName;
	}
	
	protected static Object[] generateArrayValue(String type, int size) {
		Object array[] = null;
		switch (type) {
			case "integer":
				array = new Integer[size]; 
				break;
			case "double":
				array = new Double[size];
				break;
			case "string":
				array = new String[size];
				break;
			case "boolean":
				array = new Boolean[size];
				break;
		}
		return array;
	}
	
	protected static void setArrayValue(Symbol symbol, int size) {
		Object array[] = null;
		switch (symbol.getType()) {
			case "integer":
				array = new Integer[size]; 
				break;
			case "double":
				array = new Double[size];
				break;
			case "string":
				array = new String[size];
				break;
			case "boolean":
				array = new Boolean[size];
				break;
		}
		symbol.setVariableValue(array);
	}

	protected static void addToSymbolTable(Symbol symbol, String id) {
		CallStackItem item = lookupStackItem(id);
		
		if (item != null) {
			SymbolTable symbolTable = item.getSymbolTable();
			symbolTable.getSymbolTable().add(symbol);
		}
	}
	
	public void stopExecution(String reason) {
		System.err.println("Error while processing, reason: " + reason);
		System.exit(0);
	}
}
