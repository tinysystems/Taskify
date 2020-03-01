package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.myDsl.NextTaskExpression;
import org.xtext.example.mydsl.myDsl.Task;
import org.xtext.example.mydsl.myDsl.TaskBody;


public class NextTaskExpressionExecutor extends AbstractStackHelper implements IExpressionExecutor {
    NextTaskExpression expression;
    ExpressionSwitcher executor;
    private static boolean endTaskExist = false;
    
    public NextTaskExpressionExecutor(NextTaskExpression expression, ExpressionSwitcher executor) {
        this.expression = expression;
        this.executor = executor;
    }
    
    @Override
    public void execute(String id) {
        finishPreviousTask(id);
        
        Task task = this.expression.getNexttask();
        checkEndTask();
        
        if (task != null) {
            id = task.getName();
            System.out.println("Task '" + id + "' is being executed.");
            TaskBody taskBody = task.getTaskbody();
            this.executeBody(taskBody, id);
        }
    }
    
    private void executeBody(TaskBody taskBody, String id) {
        addCallStackItem(id);
        
        for(EObject bodyElement: taskBody.getBody()) {
            this.executor.execute(bodyElement, id);
            
            /* Leave task if execution is jumped to another one */
            if (bodyElement instanceof NextTaskExpression) {
                break;
            }
        }
    }
    
    private void finishPreviousTask(String id) {
        System.out.println("Task '" + id + "' is executed.");
        System.out.println();
        
        removeCallStack(id);
    }
    
    private void checkEndTask() {
        if (this.expression.getEndtask() != null) {
            endTaskExist = true;
        }
    }
    
    public static boolean isEndTaskExist() {
        return endTaskExist;
    }
}
