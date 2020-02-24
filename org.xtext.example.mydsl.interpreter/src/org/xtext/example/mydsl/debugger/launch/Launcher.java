package org.xtext.example.mydsl.debugger.launch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.processing.AbstractStackHelper;
import org.xtext.example.mydsl.debugger.processing.ExpressionSwitcher;
import org.xtext.example.mydsl.debugger.processing.expression.NextTaskExpressionExecutor;
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression;
import org.xtext.example.mydsl.myDsl.EntryTask;
import org.xtext.example.mydsl.myDsl.Expression;
import org.xtext.example.mydsl.myDsl.InkApp;
import org.xtext.example.mydsl.myDsl.SharedVariableExpression;
import org.xtext.example.mydsl.myDsl.TaskBody;

public class Launcher extends ExpressionSwitcher {
    InkApp app;
    
    public Launcher(InkApp app) {
        this.app = app;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        EList<SharedVariableExpression> sharedVariables = app.getShareds();
        if (sharedVariables.size() > 0) {
            this.execute((EList<Expression>)(EList<?>) sharedVariables, "shared");
        }
        
        EList<ConstantVariableExpression> constantVariables = app.getConstants();
        if (constantVariables.size() > 0) {
            this.execute((EList<Expression>)(EList<?>) constantVariables, "constant");
        }
        
        EntryTask entryTask = app.getEntry();
        if (entryTask != null) {        
            TaskBody taskBody = entryTask.getTask().getTaskbody();
            System.out.println("Entry task '" + entryTask.getTask().getName() + "' is being executed.");
            this.execute(taskBody, entryTask.getTask().getName());
            
            if (!NextTaskExpressionExecutor.isEndTaskExist()) {
                System.err.println("No end task found, at least one is required");
            } else {
                System.out.println("Execution finished.");
            }
        } else {
            System.err.println("No entry task found.");
        }
        System.exit(0);
    }
    
    private void execute(EList<Expression> variables, String id) {
        AbstractStackHelper.addCallStackItem(id);
        
        for(Expression variable: variables) {
            super.execute(variable, id);
        }
    }
    
    private void execute(TaskBody taskBody, String id) {
        AbstractStackHelper.addCallStackItem(id);
        
        for(EObject bodyElement: taskBody.getBody()) {
            super.execute(bodyElement, id);
        }
    }
}
