//package org.xtext.example.mydsl.debugger.processing;
//
//import java.util.List;
//
//import org.eclipse.emf.ecore.EObject;
//import org.xtext.example.mydsl.debugger.processing.expression.OperationExpressionExecutor;
//import org.xtext.example.mydsl.myDsl.Atomic;
//import org.xtext.example.mydsl.myDsl.ComparisionExpression;
//import org.xtext.example.mydsl.myDsl.Operation;
//
//
//public abstract class AbstractLogicalHelper extends AbstractStackHelper {
//    
//    protected boolean checkCondition(EObject expression, String id) {
//        boolean isApplicable = false;
//        
//        if(expression instanceof Operation) {
//            Atomic atomicLeft = (Atomic) ((Operation) expression).getLeft();
//            Object left = decoupleAtomic(atomicLeft, id);
//            List<Atomic> atomicList = ((Operation) expression).getRight();
//            
//            if(atomicList.size() > 0) {
//                Atomic atomicRight = atomicList.get(0);            
//
//                Object right = decoupleAtomic(atomicRight, id);
//                String operator = ((Operation) expression).getOperator().get(0);
//                
//                if (left instanceof Integer) {
//                    isApplicable = (Boolean) Calculator.calculate((int) left, operator, (int) right);
//                } else if (left instanceof Double) {
//                    isApplicable = (Boolean) Calculator.calculate((double) left, operator, (double) right);
//                } else if (left instanceof Boolean) {
//                    isApplicable = (Boolean) Calculator.calculate((boolean) left, operator, (boolean) right);
//                }  else {
//                    stopExecution("Type of '" + left + "' could not be recognized.");
//                }
//            } else {
////                ex: if(boolean)
//                isApplicable = Calculator.booleanCalculate(left);
//            }
//        } else if (expression instanceof ComparisionExpression) {
//            isApplicable = OperationExpressionExecutor.evaluateComparisionExpression((ComparisionExpression) expression, id, "double");
//        }
//        return isApplicable;
//    }
//    
//}
