package org.xtext.example.mydsl.debugger.processing.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.Calculator;
import org.xtext.example.mydsl.myDsl.ArrayAssignment;
import org.xtext.example.mydsl.myDsl.Atomic;
import org.xtext.example.mydsl.myDsl.ComparisionExpression;
import org.xtext.example.mydsl.myDsl.InParanthesisOperation;
import org.xtext.example.mydsl.myDsl.Operation;
import org.xtext.example.mydsl.myDsl.OperationExpression;

public class OperationExpressionExecutor extends AbstractStackHelper {
    public final static int BINARY_P = 6;
    public final static int SQRT_P = 5;
    public final static int MULT_DIV_P = 4;
    public final static int SUM_SUB_P = 3;
    public final static int LOGICAL_P = 3;
    public final static int CLOSE_BRACKETS_P = 200;
    public final static int OPEN_BRACKETS_P = 100;
    public final static int INVALID_P = -1;
    
    public final static int MULT_DIV_MOD_P = 3;
    public final static int ADD_SUB_P = 4;
    public final static int SHIFT_P = 5;
    public final static int BITWISE_AND_P = 8;
    public final static int BITWISE_XOR_P = 9;
    public final static int BITWISE_OR_P = 10;
    public final static int LOGICAL_AND_P = 11;
    public final static int LOGICAL_OR_P = 12;
    
    public static int getPrecedence(Object item) {
        int precedence = INVALID_P;
        if (item instanceof String) {
            String operator = (String) item;
            switch (operator) {
                case Calculator.BITWISE_AND:
                    precedence = BITWISE_AND_P;
                    break;
                case Calculator.BITWISE_OR:
                    precedence = BITWISE_OR_P;
                    break;
                case Calculator.BITWISE_XOR:
                    precedence = BITWISE_XOR_P;
                    break;    
                case Calculator.SHIFT_LEFT:
                case Calculator.SHIFT_RIGHT:
                    precedence = SHIFT_P;
                    break;
                case Calculator.OPEN_BRACKET:
                    precedence = OPEN_BRACKETS_P;
                    break;
                case Calculator.CLOSE_BRACKET:
                    precedence = CLOSE_BRACKETS_P;
                    break;
                case Calculator.MULT:
                case Calculator.DIV:
                case Calculator.MOD: 
                    precedence = MULT_DIV_MOD_P;
                    break;
                case Calculator.PLUS:
                case Calculator.MINUS:
                    precedence = ADD_SUB_P;
                    break;
                case Calculator.AND:
                    precedence = LOGICAL_AND_P;
                    break;
                case Calculator.OR:
                    precedence = LOGICAL_OR_P;
                    break;
                default:
                    precedence = INVALID_P;
            }
        }
        return precedence;
    }

    public static Object[] evaluateArrayValues(ArrayAssignment expression, String id, int size) {
        Object values[] = null;
        if (expression != null) {
            if (size > 0) {
                values = new Object[size];
                int index = 0;
                for (Atomic atomicValue : expression.getVariable()) {
                    Object value = decoupleAtomic(atomicValue, id);
                    values[index] = value;
                    index++;
                }
            }
        }
        return values;
    }

    /*
     * Function to operate comparision expression. Can be used inside if/while
     * condition Example: 4 + 4 == 6
     */
    public static boolean evaluateComparisionExpression(ComparisionExpression expression, String id, String type) {
        OperationExpression leftExpression = expression.getLeft();
        OperationExpression rightExpression = expression.getRight();
        String comparisionOperator = expression.getOperator();

        Object leftValue = evaluateOperationExpression((Operation) leftExpression, id, type);
        Object rightValue = evaluateOperationExpression((Operation) rightExpression, id, type);
        boolean result = false;
        if (leftValue instanceof Boolean || rightValue instanceof Boolean) {
            result = (boolean) Calculator.calculate(Boolean.valueOf(leftValue.toString()), comparisionOperator,
                    Boolean.valueOf(rightValue.toString()));
        } else {
            result = (boolean) Calculator.calculate(Float.valueOf(leftValue.toString()), comparisionOperator,
                    Float.valueOf(rightValue.toString()));
        }

        return result;
    }

    public static Object evaluateOperationExpression(Operation expression, String id, String type) {
        Object result = null;
        if (expression != null) {
            Stack<Object> stack = new Stack<>();
            stack = infixToPostfix(expression, id);
            try {
                result = evaluatePostfix(stack, type);
            } catch (NumberFormatException e) {
                stopExecution(e.getMessage() + getLineNumberText(expression));
            }
        }
        return result;
    }
    
    private static void addObjectToList(List<Object> list, Operation item, String id) {
        if (item instanceof Atomic) {
            // list.add(left);
            list.add(decoupleAtomic((Atomic) item, id));
        } else if (item instanceof InParanthesisOperation) {
            list.add("(");
            list.addAll( getObjectListFromOperation(((InParanthesisOperation) item).getOperation(), id) );
            list.add(")");
        } else if (item instanceof Operation) {
            list.addAll( getObjectListFromOperation((Operation) item, id) );
        }
    }
    
    private static List<Object> getObjectListFromOperation(Operation operation, String id) {
        boolean rightRequired = false;
        List<Object> list = new ArrayList<>();
        Operation left = operation.getLeft();
        
        if (left != null) {
            addObjectToList(list, left, id);
        }
        
        EList<String> operators = operation.getOperator();
        if (operators.size() == 1) {
            list.add(operators.get(0));
            rightRequired = true;
        }
        
        EList<Operation> rights = operation.getRight();
        if (rights.size() > 0) {
            for (int i = 0; i < rights.size(); i++) {
                Operation right = rights.get(i);
                addObjectToList(list, right, id);
            }
        } else {
            if (rightRequired) {
                stopExecution("Missing operand at expression: " + list.stream().map(Object::toString).collect(Collectors.joining(" ")) + " at line #" + getLineNumber(operation));
            }
        }
        return list;
    }

    private static Stack<Object> infixToPostfix(Operation expression, String id) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Object> stack = new Stack<>();
        List<Object> tokens = getObjectListFromOperation(expression, id);
        
        for (int i = 0; i < tokens.size(); i++) {
            Object item = tokens.get(i);
            int precedence = getPrecedence(item);
            
            if (precedence == OPEN_BRACKETS_P) {
                operatorStack.push((String) item);
            } else if (precedence == CLOSE_BRACKETS_P) {
                String operator = operatorStack.pop();
                while (getPrecedence(operator) != OPEN_BRACKETS_P) {
                    stack.push(operator);
                    /*if (operatorStack.isEmpty()) {
                        break;
                    }*/
                    operator = operatorStack.pop();
                }
            } else if (precedence > INVALID_P) {
                // item is an operator
                while (!operatorStack.isEmpty() && getPrecedence(operatorStack.peek()) <= precedence) {
                    stack.push(operatorStack.pop());
                }
                operatorStack.push((String) item);
            } else {
                // item is an operand
                stack.push(item);
            }
        }
        
        while (!operatorStack.isEmpty()) {
            stack.push(operatorStack.pop());
        }
        return stack;
    }

    private static Object evaluatePostfix(Stack<Object> stack, String type) throws NumberFormatException {
        Object leftValue = null, rightValue = null;
        Stack<Object> results = new Stack<>();

        for (int i = 0; i < stack.size(); i++) {
            Object item = stack.get(i);
            
            if (item instanceof String) {
                String operator = (String) item;
                rightValue = results.pop();
                leftValue = results.pop();
                
                try {
                    results.push( Calculator.calculate(leftValue, operator, rightValue, type) );
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(String.format("Left %s, right %s operands are not compatible with operator %s.", leftValue.toString(), rightValue.toString(), operator));
                }
            } else {
                // Atomic values
                results.push(item);
            }
        }
        return results.pop();
    }

}
