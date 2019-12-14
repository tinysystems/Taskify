package org.xtext.example.mydsl.debugger.processing;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
import org.xtext.example.mydsl.myDsl.ArrayDimension;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BooleanReference;
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
	
	protected CallStackItem lookupStackItem(String id) {
		Iterator<CallStackItem> callStackIterator = CallStack.getCallStack().iterator();
		
		while(callStackIterator.hasNext()) {
			CallStackItem item = callStackIterator.next();
			
			if(item.getId() == id) {
				return item;
			}
		}
		
		return null;
	}
	
	protected Symbol lookupSymbolByAtomic(Atomic atomic, String id) {		
//		if Atomic is StringReference or IntegerReference or DoubleReference or BooleanReference, 
//		the value is not inside call stack. So make dummy symbol and return it.
		if(isDummy(atomic)) {
			Symbol dummy = dummySymbol(atomic);
			return dummy;
		}
		
		String variableName = getAtomicName(atomic);
		Symbol symbol = lookupSymbolByString(variableName, id);
		
		return symbol;
	}
	
	protected Symbol lookupSymbolByString(String variableName, String id) {
		Symbol symbol = null;
		
//		Check if variable is in local symbol table
		CallStackItem callStackItem = lookupStackItem(id);
		Iterator<Symbol> iterator = callStackItem.getSymbolTable().getSymbolTable().iterator();
		
		while(iterator.hasNext()) {
			symbol = iterator.next();
			
			if(symbol.getName() == variableName) {
				return symbol;
			}
			
		}
		
//		Not found in local symbol table
//		Check if variable is in global symbol table
		callStackItem = lookupStackItem("global");
		iterator = callStackItem.getSymbolTable().getSymbolTable().iterator();
		
		while(iterator.hasNext()) {
			symbol = iterator.next();
			
			if(symbol.getVariableValue() == variableName) {
				return symbol;
			}
		}
		
//		Return null
		return symbol;
	}
	
	private Symbol dummySymbol(Atomic atomic) {
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
	
	private boolean isDummy(Atomic atomic) {
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
	
	protected void pushCallStackItem(String id) {
		CallStack.getCallStack().add(new CallStackItem(id, new SymbolTable()));
	}

	protected void popCallStackItem(String id) {
		CallStackItem item = lookupStackItem(id);
		CallStack.getCallStack().remove(item);
	}

	protected void updateCallStackByAtomic(Atomic atomic, Object value, String id) {
		Symbol sm = lookupSymbolByAtomic(atomic, id);
		sm.setVariableValue((Object) value);
	}

	protected void addCallStackBySymbol(Symbol symbol, String id) {
		CallStackItem item = lookupStackItem(id);
		item.getSymbolTable().getSymbolTable().add(symbol);
	}
	
	@SuppressWarnings("unchecked")
	protected Object decoupleAtomic(Atomic atomic, String id) {
		Object m_return = null; 
		
		if(atomic instanceof StringReference) {
			m_return = ((StringReference) atomic).getValue();
		} else if(atomic instanceof BooleanReference) {
			m_return = ((BooleanReference) atomic).isValue();
		} else if(atomic instanceof IntegerReference) {
			m_return = ((IntegerReference) atomic).getValue();
		} else if(atomic instanceof ArrayReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, id);
			Object dimensionMap = symbol.getVariableValue();
			String dimensionIndex = arrayKeyGenerator((ArrayReference) atomic, id);
			
			for(Entry<String, Object> entry: ((Map<String, Object>) dimensionMap).entrySet()) {
				if(entry.getKey().equals(dimensionIndex)) {
					m_return = entry.getValue();
				}
			}			
		} else if(atomic instanceof VariableReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, id);
			m_return = symbol.getVariableValue();
		}
		return m_return;
	}
	
	@SuppressWarnings("unchecked")
	protected void updateCallStackByArray(ArrayReference arrayLeft, Object returnValue, String callerId) {
		String dimensionIndex = arrayKeyGenerator(arrayLeft, callerId);
		String target = ((VariableDeclerationExpression) arrayLeft.getVariableReference()).getName();
		CallStackItem callStackItem = lookupStackItem(callerId);
		
		for(Symbol symbol: callStackItem.getSymbolTable().getSymbolTable()) {
			if(target.equals(symbol.getName())) {
				Map<String, Object> map = (Map<String, Object>) symbol.getVariableValue();
				map.put(dimensionIndex, returnValue);
				symbol.setVariableValue(map);
			}
		}
	}
	
	protected String arrayKeyGenerator(ArrayReference arrayLeft, String functionCallerId) {
		String dimensionIndex = "";
		
		for(ArrayDimension dimension: arrayLeft.getDimension()) {
			int index = 0;
			VariableSymbol variableSymbol = dimension.getIndex();
			
			if(variableSymbol != null) {
				VariableSymbol m_variableSymbol = dimension.getIndex();
				String variableName = getVariableSymbolName(m_variableSymbol);
				Symbol symbol = lookupSymbolByString(variableName, functionCallerId);
				index = (int) symbol.getVariableValue();
			} else {
				index = dimension.getSize();
			}
			dimensionIndex += String.valueOf(index);
			
			if(arrayLeft.getDimension().size() != arrayLeft.getDimension().indexOf(dimension) + 1) {
				dimensionIndex += "_";
			}
		}
		return dimensionIndex;
	}
	
	private String getAtomicName(Atomic atomic) {
		String atomicName = "";
		
		if(atomic instanceof VariableReference) {
			atomicName = getVariableSymbolName(((VariableReference) atomic).getVariableReference());
		} else if(atomic instanceof ArrayReference) {
			atomicName = getVariableSymbolName(((ArrayReference) atomic).getVariableReference());
		}
		return atomicName;
	}
	
	private String getVariableSymbolName(VariableSymbol variableSymbol) {
		String symbolName = "";
		
		if(variableSymbol instanceof VariableDeclerationExpression) {
			symbolName = ((VariableDeclerationExpression) variableSymbol).getName();
		}
		return symbolName;
	}
}
