package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression;
import org.xtext.example.mydsl.myDsl.StringReference;
import org.xtext.example.mydsl.myDsl.VariableReference;


public class BuiltinFunctionCallExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	BuiltinFunctionCallExpression expression;
	ExpressionSwitcher executor;
	
	public BuiltinFunctionCallExpressionExecutor(BuiltinFunctionCallExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}
	@Override
	public void execute(String id) {
		if (expression instanceof BuiltinFunctionCallExpression) {
			builtinFunctionExecutor(expression, id);
		}
	}
	
	private void builtinFunctionExecutor(BuiltinFunctionCallExpression expression, String id) {
		String functionName = expression.getFunction();
		
		switch (functionName) {
			case "print":
				print(expression.getParameters(), id);
				break;
		}
	}
	
	private void print(EList<Atomic> parameters, String id) {
		Symbol symbol = null;;
		String output = null;
		int parameterCount = parameters.size();
		
		for (int i = 0; i < parameterCount; i++) {
			Atomic atomic = parameters.get(i);
			if(atomic instanceof StringReference) {
				System.out.print(((StringReference) atomic).getValue().toString());
			} else {
				symbol = lookupSymbolByAtomic(atomic, id);
				
				if (symbol != null) {			
					Object value = symbol.getVariableValue();
					
					if (value != null) {
						if (atomic instanceof VariableReference) {
							if (value instanceof Object[]) {
								// Print an entire array
								output = Arrays.toString((Object[]) value);
							} else {
								// Print a variable
								output = value.toString();
							}
						} else if (atomic instanceof ArrayReference) {
							// Print an element of an array
							int index = ((ArrayReference) atomic).getIndex().getSize();
							Object values[] = (Object[]) value;
							output = values[index].toString();
						}						
					} else {
						output = "Variable is not defined.";
					}
					System.out.print(output);
				}
			}
		}
		System.out.println();
	}

}
