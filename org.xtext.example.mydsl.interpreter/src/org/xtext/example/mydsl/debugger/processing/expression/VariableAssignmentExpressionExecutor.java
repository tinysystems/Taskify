package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.Symbol;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
import org.xtext.example.mydsl.myDsl.ArrayReference;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.BuiltinRandomFunction;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.Variable;
import org.xtext.example.mydsl.myDsl.VariableAssignmentExpression;

public class VariableAssignmentExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
	VariableAssignmentExpression expression;
	ExpressionSwitcher executor;
	
	public VariableAssignmentExpressionExecutor(VariableAssignmentExpression expression, ExpressionSwitcher executor) {
		this.expression = expression;
		this.executor = executor;
	}

	@Override
	public void execute(String id) {	
		Object value = new Object();
		Variable variable = this.expression.getVariable();
		Symbol symbol = lookupSymbolByAtomic((Atomic) variable, id);
		if (symbol != null) { 
			String type = symbol.getType();		
			
			if (expression.getAssignment() != null) {
				EObject assignmentExpression = expression.getAssignment().getExpression();
				if (assignmentExpression != null) {
					if (assignmentExpression instanceof Operation) {
						value = evaluateValue((Operation) assignmentExpression, id, value, type);
						
					} else if (assignmentExpression instanceof BuiltinRandomFunction) {
						value = BuiltinFunctionCallExpressionExecutor.builtinRandomFunction();
					}
					
					if (variable instanceof ArrayReference) {
						updateCallStackByArray((ArrayReference) variable, value, getParentId());
					} else {
						updateCallStackBySymbol(symbol, value);
					}
				} else {
					stopExecution("Typo while assgining value to " + symbol.getName());
					System.exit(0);
				}
			}
		} else {
			stopExecution("Variable is not in the scope of " + id);
		}
	}
	
	public static Object evaluateValue(Operation expression, String id, Object value, String type) {
		if (expression != null) {
			Atomic atomicValue = expression.getLeft();
			value = decoupleAtomic(atomicValue, id);
			
			if (expression.getOperator().size() > 0) {
				value = OperationExpressionExecutor.evaluateOperationExpression(expression, id, type);
			}
		}
		return value;
	}
	
	public static Object[] evaluateValue(ArrayAssignment expression, String id, Object[] values, int size, String type) {
		if (expression != null) {
			if (size > 0) {
				int index = 0;
				for (Atomic atomicValue: ((ArrayAssignment) expression).getVariable()) {
					values[index] = decoupleAtomic(atomicValue, id);
					index++;
				}
			}
		}
		return values;
	}


}
