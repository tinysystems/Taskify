package org.xtext.example.mydsl.debugger.processing;

public abstract class AbstractLogicalHelper extends AbstractStackHelper {

	protected boolean logicalCompare(boolean leftValue, String operator, boolean rightValue) {
		boolean result = true;
		
		if(operator.equals("AND")) {
			result = (leftValue & rightValue);
		} else if(operator.equals("OR")) {
			result = (leftValue | rightValue);
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

//		DELETE
//	protected boolean compare(int value) {
//		return value > 0;
//	}
//	
//	protected boolean compare(double value) {
//		return value > 0;
//	}
//	
//	protected boolean compare(boolean value) {
//		return value == true;
//	}
}
