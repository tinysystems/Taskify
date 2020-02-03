package org.xtext.example.mydsl.debugger.processing.expression;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.debugger.context.CallStack;
import org.xtext.example.mydsl.debugger.context.CallStackItem;
import org.xtext.example.mydsl.debugger.context.SymbolTable;
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
		Task task = this.expression.getNexttask();
		checkEndTask();
		
		if (task != null) {
			System.out.println("Task '" + task.getName() + "' is being executed.");
			TaskBody taskBody = task.getTaskbody();
			this.execute(taskBody, id);
		}
	}
	
	private void execute(TaskBody taskBody, String id) {
		CallStack.getCallStack().add(new CallStackItem(id, new SymbolTable()));
		
		for(EObject bodyElement: taskBody.getBody()) {
			this.executor.execute(bodyElement, id);
		}
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
