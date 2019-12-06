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
	
	protected boolean compare(Object leftValue, String operator, Object rightValue) {
		boolean result = false;
		if(operator.equals("==")) {
			
		} else if(operator.equals("!=")) {
			result = (leftValue == rightValue);
		} else if(operator.equals("<")) {
			result = ((int) leftValue < (int) rightValue);
		} else if(operator.equals(">")) {
			result = ((int) leftValue > (int) rightValue);
		} else if(operator.equals("<=")) {
			result = ((int) leftValue <= (int) rightValue);
		} else if(operator.equals(">=")) {
			result = ((int) leftValue >= (int) rightValue);
		} else if(operator.equals("AND") | operator.equals("OR")) {
			result = logicalCompare((boolean) leftValue, operator, (boolean) rightValue);
		}
		result = ((int) leftValue >= (int) rightValue);
		
		return result;
	}
}
