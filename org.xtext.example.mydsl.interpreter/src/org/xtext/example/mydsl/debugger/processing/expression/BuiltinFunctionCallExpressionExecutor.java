package org.xtext.example.mydsl.debugger.processing.expression;

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
		Symbol symbol = null;
		String value;
		
		switch (functionName) {
			case "print":
//				For multiple parameters
//				int parameterCount = expression.getParameters().size();
//				for (int i = 0; i < parameterCount; i++) {
//					Atomic atomic = expression.getParameters().get(i);
//					if(atomic instanceof StringReference) {
//						System.out.print(atomic.toString());
//					} else {
//						symbol = lookupSymbolByAtomic(expression.getParameters().get(i), id);
//						value = symbol.getVariableValue().toString();
//						System.out.print(value);
//					}
//				}
				symbol = lookupSymbolByAtomic(expression.getParameters().get(0), id);
				value = symbol.getVariableValue().toString();
				System.out.println(value);
				break;
		}
	}

}
