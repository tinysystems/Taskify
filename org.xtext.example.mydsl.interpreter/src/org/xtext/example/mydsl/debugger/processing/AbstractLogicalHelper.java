package org.xtext.example.mydsl.debugger.processing;

import java.util.List;

import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;

public abstract class AbstractLogicalHelper extends AbstractStackHelper {

	protected boolean logicalCompare(boolean leftValue, String operator, boolean rightValue) {
		boolean result = true;
		
		if(operator.equals("AND")) {
			result = (leftValue & rightValue);
		} else if(operator.equals("OR")) {
			result = (leftValue | rightValue);
		} else if (operator.equals("==")) {
			result = (leftValue == rightValue);
		}
		
		return result;
	}
	
	protected boolean compare(double leftValue, String operator, double rightValue) {
		boolean result = false;
		switch (operator) {
			case "==":
				result = leftValue == rightValue;
				break;
			case "!=":
				result = leftValue != rightValue;
				break;
			case "<":
				result = leftValue < rightValue;
				break;
			case ">":
				result = leftValue > rightValue;
				break;
			case "<=":
				result = leftValue <= rightValue;
				break;
			case ">=":
				result = leftValue >= rightValue;
				break;
		}		
		return result;
	}
	
	protected boolean compare(int leftValue, String operator, int rightValue) {
		boolean result = false;
		switch (operator) {
			case "==":
				result = leftValue == rightValue;
				break;
			case "!=":
				result = leftValue != rightValue;
				break;
			case "<":
				result = leftValue < rightValue;
				break;
			case ">":
				result = leftValue > rightValue;
				break;
			case "<=":
				result = leftValue <= rightValue;
				break;
			case ">=":
				result = leftValue >= rightValue;
				break;
		}		
		return result;
	}
	
	protected boolean compare(boolean leftValue, String operator, boolean rightValue) {
		boolean result = logicalCompare((boolean) leftValue, operator, (boolean) rightValue);
		return result;
	}
	
	protected boolean compare(Object value) {
		boolean result = false;
		if (value != null) {
			if (value instanceof Integer) {
				result = ((int) value) > 0;
			} else if (value instanceof Double) {
				result = ((double) value) > 0;
			} else if (value instanceof Boolean) {
				result = (boolean) value;
			}
		}
		return result;
	}

	protected boolean checkCondition(OperationExpression expression, String id) {
		boolean isApplicable = false;
		
		if(expression instanceof Operation) {
			Atomic atomicLeft = (Atomic) ((Operation) expression).getLeft();
			Object left = decoupleAtomic(atomicLeft, id);
			List<Atomic> atomicList = ((Operation) expression).getRight();
			
			Atomic atomicRight = null;
			if(atomicList != null && atomicList.size() > 0) {
				atomicRight = atomicList.get(0);			
			
				Object right = decoupleAtomic(atomicRight, id);
				String operator = ((Operation) expression).getOperator().get(0);
				
				if (left instanceof Integer) {
					isApplicable = compare((int) left, operator, (int) right);
				} else if (left instanceof Double) {
					isApplicable = compare((double) left, operator, (double) right);
				} else if (left instanceof Boolean) {
					isApplicable = compare((boolean) left, operator, (boolean) right);
				}
			} else {
//				ex: if(boolean)
				isApplicable = compare(left);
			}
			
		}
		return isApplicable;
	}
	
}
