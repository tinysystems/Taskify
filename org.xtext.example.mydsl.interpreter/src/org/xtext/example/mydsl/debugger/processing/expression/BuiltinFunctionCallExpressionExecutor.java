package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.Arrays;

import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.BuiltinFunctionCallExpression;
//import org.xtext.example.mydsl.myDsl.Atomic;
//import org.xtext.example.mydsl.myDsl.StringReference;


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
		Symbol symbol = null;;
		String output = null;
		
		switch (functionName) {
			case "print":
//				For multiple parameters
//				int parameterCount = expression.getParameters().size();
//				for (int i = 0; i < parameterCount; i++) {
//					Atomic atomic = expression.getParameters().get(i);
//					if(atomic instanceof StringReference) {
//						System.out.print(atomic.toString());
//					} else {
//						symbol = lookupSymbolByAtomic(atomic, id);
//						Object value = symbol.getVariableValue();
//						if (value instanceof Object[]) {
//							output = Arrays.toString((Object[]) value);
//						} else {
//							output = value.toString();
//						}
//						System.out.print(value);
//					}
//				}
//				System.out.println();
				
				if(expression.getParameters().size() > 0) {
					symbol = lookupSymbolByAtomic(expression.getParameters().get(0), id);
					Object value = symbol.getVariableValue();
					if (value instanceof Object[]) {
						output = Arrays.toString((Object[]) value);
					} else {
						if (value != null) {
							output = value.toString();
						}
					}
					System.out.println(output);
				}
				break;
		}
	}

}
