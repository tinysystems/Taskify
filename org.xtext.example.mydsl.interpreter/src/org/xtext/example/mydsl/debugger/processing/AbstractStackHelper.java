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
import org.xtext.example.mydsl.myDsl.FunctionParameter;
import org.xtext.example.mydsl.myDsl.IntegerReference;
import org.xtext.example.mydsl.myDsl.StringReference;
import org.xtext.example.mydsl.myDsl.VariableDecleration;
import org.xtext.example.mydsl.myDsl.VariableReference;
import org.xtext.example.mydsl.myDsl.VariableSymbol;


public abstract class AbstractStackHelper {
	static boolean isBreak = false;
	static boolean isReturn = false;
	static Object lastFunctionReturn = null;
	
	protected CallStackItem lookupStackItem(String functionId) {
		Iterator<CallStackItem> callStackIterator = CallStack.getCallStack().iterator();
		
		while(callStackIterator.hasNext()) {
			CallStackItem item = callStackIterator.next();
			
			if(item.getFunctionId() == functionId) {
				return item;
			}
		}
		
		return null;
	}
	
	protected Symbol lookupSymbolByAtomic(Atomic atomic, String functionId) {		
//		if Atomic is StringReference or IntegerReference or DoubleReference or BooleanReference, 
//		the value is not inside call stack. So make dummy symbol and return it.
		if(isDummy(atomic)) {
			Symbol dummy = dummySymbol(atomic);
			return dummy;
		}
		
		String variableName = getAtomicName(atomic);
		Symbol symbol = lookupSymbolByString(variableName, functionId);
		
		return symbol;
	}
	
	protected Symbol lookupSymbolByString(String variableName, String functionId) {
		Symbol symbol = null;
		CallStackItem callStackItem = lookupStackItem(functionId);
		Iterator<Symbol> iterator = callStackItem.getSymbolTable().getSymbolTable().iterator();
		
		while(iterator.hasNext()) {
			symbol = iterator.next();
			
			if(symbol.getName() == variableName) {
				return symbol;
			}
			
		}
		
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
		Symbol dummySymbol = new Symbol();
		
		if(atomic instanceof StringReference) {
			value = (String) ((StringReference) atomic).getValue();
		} else if (atomic instanceof IntegerReference) {
			value = (int) ((IntegerReference) atomic).getValue();
		} else if (atomic instanceof DoubleReference) {
			value = (double) ((DoubleReference) atomic).getValue();
		} else if (atomic instanceof BooleanReference) {
			value = (boolean) ((BooleanReference) atomic).isValue();
		}	
		
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
	
	protected void pushCallStackItem(String functionId) {
		CallStack.getCallStack().add(new CallStackItem(functionId, new SymbolTable()));
	}

	protected void popCallStackItem(String functionId) {
		CallStackItem item = lookupStackItem(functionId);
		CallStack.getCallStack().remove(item);
	}

	protected void updateCallStackByAtomic(Atomic atomic, Object value, String functionId) {
		Symbol sm = lookupSymbolByAtomic(atomic, functionId);
		sm.setVariableValue(value);
	}

	protected void addCallStackBySymbol(Symbol symbol, String functionId) {
		CallStackItem item = lookupStackItem(functionId);
		item.getSymbolTable().getSymbolTable().add(symbol);
	}
	
	@SuppressWarnings("unchecked")
	protected Object decoupleAtomic(Atomic atomic, String functionId) {
		Object m_return = null; 
		
		if(atomic instanceof StringReference) {
			m_return = ((StringReference) atomic).getValue();
		} else if(atomic instanceof BooleanReference) {
			m_return = ((BooleanReference) atomic).isValue();
		} else if(atomic instanceof IntegerReference) {
			m_return = ((IntegerReference) atomic).getValue();
		} else if(atomic instanceof ArrayReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, functionId);
			Object dimensionMap = symbol.getVariableValue();
			String dimensionIndex = arrayKeyGenerator((ArrayReference) atomic, functionId);
			
			for(Entry<String, Object> entry: ((Map<String, Object>) dimensionMap).entrySet()) {
				if(entry.getKey().equals(dimensionIndex)) {
					m_return = entry.getValue();
				}
			}			
		} else if(atomic instanceof VariableReference) {
			Symbol symbol = lookupSymbolByAtomic(atomic, functionId);
			m_return = symbol.getVariableValue();
		}
		return m_return;
	}
	
	@SuppressWarnings("unchecked")
	protected void updateCallStackByArray(ArrayReference arrayLeft, Object returnValue, String callerFunctionId) {
		String dimensionIndex = arrayKeyGenerator(arrayLeft, callerFunctionId);
		String target = arrayLeft.getVariableReference().getName();
		CallStackItem callStackItem = lookupStackItem(callerFunctionId);
		
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
		
		if(variableSymbol instanceof VariableDecleration | variableSymbol instanceof FunctionParameter) {
			symbolName = variableSymbol.getName();
		}
		return symbolName;
	}
}
