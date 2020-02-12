package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Arrays;
import java.util.Random;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BooleanReference;
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression;
import org.xtext.example.mydsl.myDsl.BuiltinPrintFunction;
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction;
import org.xtext.example.mydsl.myDsl.DoubleReference;
import org.xtext.example.mydsl.myDsl.FunctionCallParameters;
import org.xtext.example.mydsl.myDsl.IntegerReference;
import org.xtext.example.mydsl.myDsl.PrimitiveReference;
import org.xtext.example.mydsl.myDsl.StringReference;
import org.xtext.example.mydsl.myDsl.Variable;
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
		if (this.expression instanceof BuiltinPrintFunction) {
			builtinPrintFunction((BuiltinPrintFunction) this.expression, id);
		} else if (this.expression instanceof BuiltinRandomFunction) {
			//builtinRandomFunction((BuiltinRandomFunction) this.expression, id);
		}
	}

	private void builtinPrintFunction(BuiltinPrintFunction function, String id) {
		FunctionCallParameters parameters = function.getParameters();
		int parameterCount = parameters.getParameter().size();
		
		for (int i = 0; i < parameterCount; i++) {
			Symbol symbol = null;;
			String output = null;
			Atomic atomic = parameters.getParameter().get(i);
			if(atomic instanceof PrimitiveReference) {
				if(atomic instanceof StringReference) {
					output = String.valueOf(((StringReference) atomic).getValue());
				} else if (atomic instanceof IntegerReference) {
					output = String.valueOf(((IntegerReference) atomic).getValue());
				} else if (atomic instanceof DoubleReference) {
					output = String.valueOf(((DoubleReference) atomic).getValue());
				} else if (atomic instanceof BooleanReference) {
					output = String.valueOf(((BooleanReference) atomic).isValue());
				}
			} else if (atomic instanceof Variable) {
				Variable atomicVar = (Variable) atomic;
				symbol = lookupSymbolByAtomic(atomicVar, id);
			
				if (symbol != null) {		
					Object value = symbol.getVariableValue();
					
					if (value != null) {
						if (atomicVar instanceof VariableReference) {
							if (value instanceof Object[]) {
								// Print an entire array
								output = Arrays.toString((Object[]) value);
							} else {
								// Print a variable
								output = value.toString();
							}
						} else if (atomicVar instanceof ArrayReference) {
							// Print an element of an array
							int index = ((ArrayReference) atomicVar).getIndex().getSize();
							Object values[] = (Object[]) value;
							output = values[index].toString();
						}						
					}
				}
			}
			System.out.print(output);
		}
		System.out.println();
	}
	
	public static int builtinRandomFunction() {
		Random random=new Random();
		int value = random.nextInt(RAND_MAX);
		return value;
	}
}
