package org.xtext.example.mydsl.debugger.processing;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    final public static String PLUS = "+";
    final public static String MINUS = "-";
    final public static String MULT = "*";
    final public static String DIV = "/";
    final public static String MOD = "%";
    final public static String AND = "AND";
    final public static String OR = "OR";
    final public static String EQUAL = "==";
    final public static String NOT_EQUAL = "!=";
    final public static String LOWER = "<";
    final public static String GREATER = ">";
    final public static String LOWER_EQUAL = "<=";
    final public static String GREATER_EQUAL = ">=";
    final public static String BITWISE_AND = "AND_BITWISE";
    final public static String BITWISE_OR = "OR_BITWISE";
    final public static String BITWISE_XOR = "XOR_BITWISE";
    final public static String SHIFT_LEFT = "SHIFT_LEFT";
    final public static String SHIFT_RIGHT = "SHIFT_RIGHT";
    final public static String OPEN_BRACKET = "(";
    final public static String CLOSE_BRACKET = ")";
    final private static String[] arithmeticOperators = new String[] { PLUS, MINUS, MULT, DIV , MOD };
    final private static String[] logicalOperators = new String[] { 
            AND, OR, EQUAL, NOT_EQUAL, LOWER, LOWER_EQUAL, GREATER, GREATER_EQUAL 
    };
    final private static String[] bitwiseOperators = new String[] { 
            BITWISE_AND, BITWISE_OR, BITWISE_XOR, SHIFT_LEFT, SHIFT_RIGHT 
    };

    public static final boolean isArithmetic(String operator) {
        final List<String> list = Arrays.asList(Calculator.arithmeticOperators);
        return list.contains(operator);
    }

    public static final boolean isLogical(String operator) {
        final List<String> list = Arrays.asList(Calculator.logicalOperators);
        return list.contains(operator);
    }

    public static final boolean isBinary(String operator) {
        final List<String> list = Arrays.asList(Calculator.bitwiseOperators);
        return list.contains(operator);
    }

    public static final long arithmeticCalculate(long leftValue, String operator, long rightValue) {
        long result = 0;

        switch (operator) {
        case PLUS:
            result = leftValue + rightValue;
            break;
        case MINUS:
            result = leftValue - rightValue;
            break;
        case MULT:
            result = leftValue * rightValue;
            break;
        case DIV:
            result = leftValue / rightValue;
            break;
        case MOD:
            result = leftValue % rightValue;
            break;
        }
        return result;
    }

    public static final float arithmeticCalculate(float leftValue, String operator, float rightValue) {
        float result = 0.00F;

        switch (operator) {
            case PLUS:
                result = leftValue + rightValue;
                break;
            case MINUS:
                result = leftValue - rightValue;
                break;
            case MULT:
                result = leftValue * rightValue;
                break;
            case DIV:
                result = leftValue / rightValue;
                break;
            case MOD:
                result = leftValue % rightValue;
                break;
        }
        return result;
    }

    protected static final boolean logicalCalculate(float leftValue, String operator, float rightValue) {
        boolean result = false;
        switch (operator) {
            case EQUAL:
                result = leftValue == rightValue;
                break;
            case NOT_EQUAL:
                result = leftValue != rightValue;
                break;
            case LOWER:
                result = leftValue < rightValue;
                break;
            case GREATER:
                result = leftValue > rightValue;
                break;
            case LOWER_EQUAL:
                result = leftValue <= rightValue;
                break;
            case GREATER_EQUAL:
                result = leftValue >= rightValue;
                break;
            default:
                AbstractStackHelper.stopExecution("Logical operator '" + operator + "' could not be recognized.");
        }
        return result;
    }

    protected static final boolean logicalCalculate(long leftValue, String operator, long rightValue) {
        boolean result = false;
        switch (operator) {
        case EQUAL:
            result = leftValue == rightValue;
            break;
        case NOT_EQUAL:
            result = leftValue != rightValue;
            break;
        case LOWER:
            result = leftValue < rightValue;
            break;
        case GREATER:
            result = leftValue > rightValue;
            break;
        case LOWER_EQUAL:
            result = leftValue <= rightValue;
            break;
        case GREATER_EQUAL:
            result = leftValue >= rightValue;
            break;
        default:
            AbstractStackHelper.stopExecution("Logical operator '" + operator + "' could not be recognized.");
        }
        return result;
    }

    protected static final boolean logicalCalculate(boolean leftValue, String operator, boolean rightValue) {
        boolean result = false;
        switch (operator) {
        case EQUAL:
            result = (leftValue == rightValue);
            break;
        case NOT_EQUAL:
            result = (leftValue != rightValue);
            break;
        case AND:
            result = (leftValue && rightValue);
            break;
        case OR:
            result = (leftValue || rightValue);
            break;
        default:
            AbstractStackHelper.stopExecution("Logical operator '" + operator + "' could not be recognized.");
        }
        return result;
    }

    public static final boolean booleanCalculate(Object value) {
        boolean result = false;
        if (value != null) {
            if (value instanceof Long) {
                result = (Long.valueOf(value.toString())) > 0;
            } else if (value instanceof Float) {
                result = (Float.valueOf(value.toString())) > 0;
            } else if (value instanceof Boolean) {
                result = Boolean.valueOf(value.toString());
            } else {
                AbstractStackHelper.stopExecution("Type of '" + value + "' could not be recognized.");
            }
        }
        return result;
    }

    public static final long bitwiseCalculate(long leftValue, String operator, long rightValue) {
        long result = 0;
        switch (operator) {
        case BITWISE_AND:
            result = (leftValue & rightValue);
            break;
        case BITWISE_OR:
            result = (leftValue | rightValue);
            break;
        case BITWISE_XOR:
            result = (leftValue ^ rightValue);
            break;
        case SHIFT_LEFT:
            result = ((int) leftValue << rightValue);
            break;
        case SHIFT_RIGHT:
            result = ((int) leftValue >>> rightValue);
            break;
        default:
            AbstractStackHelper.stopExecution("Bitwise operator '" + operator + "' could not be recognized.");
        }
        return result;
    }

    public static final Object calculate(long leftValue, String operator, long rightValue) {
        Object result = null;
        if (isArithmetic(operator)) {
            result = arithmeticCalculate(leftValue, operator, rightValue);
        } else if (isLogical(operator)) {
            result = logicalCalculate(leftValue, operator, rightValue);
        } else if (isBinary(operator)) {
            result = bitwiseCalculate(leftValue, operator, rightValue);
        } else {
            AbstractStackHelper.stopExecution("Operator '" + operator + "' could not be recognized.");
        }
        return result;
    }

    public static final Object calculate(float leftValue, String operator, float rightValue) {
        Object result = null;
        if (isArithmetic(operator)) {
            result = arithmeticCalculate(leftValue, operator, rightValue);
        } else if (isLogical(operator)) {
            result = logicalCalculate(leftValue, operator, rightValue);
        } else {
            AbstractStackHelper.stopExecution(
                    "Operator '" + operator + "' could not be recognized or operand(s) is/are not integer.");
        }
        return result;
    }

    public static final Object calculate(boolean leftValue, String operator, boolean rightValue) {
        Object result = null;
        if (isLogical(operator)) {
            result = logicalCalculate(leftValue, operator, rightValue);
        } else {
            AbstractStackHelper.stopExecution("Operator '" + operator + "' can not be used except boolean variables");
        }
        return result;
    }

    public static final Object calculate(Object leftValue, String operator, Object rightValue, String type) {
        Object result = null;
        if (isArithmetic(operator)) {
            switch (type) {
            case "integer":
                result = arithmeticCalculate(Long.valueOf(leftValue.toString()), operator, Long.valueOf(rightValue.toString()));
                break;
            case "float":
                result = arithmeticCalculate(Float.valueOf(leftValue.toString()), operator,
                        Float.valueOf(rightValue.toString()));
                break;
            default:
                AbstractStackHelper.stopExecution("Type '" + type + "' is not applicable to calculate arithmatically.");
            }
        } else if (isBinary(operator)) {
            if (type.equals("integer")) {
                result = bitwiseCalculate(Long.valueOf(leftValue.toString()), operator, Long.valueOf(rightValue.toString()));
            } else {
                AbstractStackHelper
                        .stopExecution("Type '" + type + "' is not applicable to calculate binary. Must be integer.");
            }
        } else if (isLogical(operator)) {
            if (type.equals("boolean")) {
                result = logicalCalculate(Boolean.valueOf(leftValue.toString()), operator, Boolean.valueOf(rightValue.toString()));
            } else {
                AbstractStackHelper
                        .stopExecution("Type '" + type + "' is not applicable to calculate boolean. Must be boolean.");
            }
        }
        return result;
    }
}
