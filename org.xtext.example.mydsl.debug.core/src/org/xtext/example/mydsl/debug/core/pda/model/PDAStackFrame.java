package org.xtext.example.mydsl.debug.core.pda.model;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class PDAStackFrame extends PDADebugElement implements IStackFrame {
	private PDAThread fThread;
	private String fFunctionName;
	private int fLineNumber;
	private String fFileName;
	private int fId;
	
	/**
	 * Constructs a stack frame in the given thread with the given
	 * frame data.
	 *
	 * @param thread
	 * @param data frame data
	 * @param id stack frame id (0 is the bottom of the stack)
	 */
	public PDAStackFrame(PDAThread thread, String data, int id) throws DebugException {
		super(thread.getPDADebugTarget());
		fId = id;
		fThread = thread;
		init(data);
	}
	
	/**
	 * Initializes this frame based on its data 
	 * 
	 * @param data
	 * @throws DebugException 
	 */
	private void init(String data) throws DebugException {
		String[] strings = data.split("&");
		String fileName = strings[0];
		fFileName = Paths.get(fileName).getFileName().toString();
		String pc = strings[1];
		fLineNumber = Integer.parseInt(pc);
		fFunctionName = strings[2]; 
		
		IVariable[] vars = getPDAVariables(strings);

		fThread.setVariables(this, vars);
	}
	
	private IVariable[] getPDAVariables(String[] strings) throws DebugException {
		ArrayList<PDAVariable> varsl = new ArrayList<PDAVariable>();
		for (int i = 3; i < strings.length; i++) {
			if (strings[i].isEmpty() == false) {
				String[] cutoffStr = strings[i].split("\\|", -1);
				String varName = cutoffStr[1].trim();
				String varValue = "";

				// when value is "" empty string.
				if (cutoffStr.length > 2) {
					varValue = cutoffStr[2].trim();
					varValue = cutoffStr[2].trim().equals("null") ? "" : varValue;
					varValue = varValue.replace("@", ""); //temporal solution, need to fix
				}

				// if variable is not exits, create new.
				// if you create all variable every time after "stack" querying,
				// it takes much time for processing.
				// because fireChangeEvent consume take a heavy load for event
				// job and queuing.
				PDAVariable variable = isContainsPDAVariable(varName, varValue);
				if (variable == null) {
					varsl.add(new PDAVariable(this, varName, varValue));
				} else {
					// if exits, compare value with old value and new value.
					String oldValue = variable.getValue().getValueString();
					if (oldValue.equals(varValue) == false) {
						variable.setValue(varValue);
					}
					varsl.add(variable);
				}
			}
		}
		// safety way to convert list to array
		PDAVariable[] vararr = varsl.toArray(new PDAVariable[varsl.size()]);

		return vararr;
	}
	
	private PDAVariable isContainsPDAVariable(String varName, String varValue) throws DebugException {
		PDAVariable isExits = null;
		IVariable[] variables = ((PDAThread) getThread()).getVariables(this);
		if (variables != null) {
			for (IVariable variable : variables) {
				if (variable != null) {
					if (variable.getName().equals(varName)) {
						return (PDAVariable) variable;
					}
				}
			}
		}
		return isExits;
	}
	
	@Override
	public boolean canStepInto() {
		return getThread().canStepInto();
	}

	@Override
	public boolean canStepOver() {
		return getThread().canStepOver();
	}

	@Override
	public boolean canStepReturn() {
		return getThread().canStepReturn();
	}

	@Override
	public boolean isStepping() {
		return getThread().isStepping();
	}

	@Override
	public void stepInto() throws DebugException {
		getThread().stepInto();
	}

	@Override
	public void stepOver() throws DebugException {
		getThread().stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {
		getThread().stepReturn();
	}

	@Override
	public boolean canResume() {
		return getThread().canResume();
	}

	@Override
	public boolean canSuspend() {
		return getThread().canSuspend();
	}

	@Override
	public boolean isSuspended() {
		return getThread().isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		getThread().resume();
	}

	@Override
	public void suspend() throws DebugException {
		getThread().suspend();
	}

	@Override
	public boolean canTerminate() {
		return getThread().canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return getThread().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		getThread().terminate();

	}

	@Override
	public int getCharEnd() throws DebugException {
		return -1;
	}

	@Override
	public int getCharStart() throws DebugException {
		return -1;
	}

	@Override
	public int getLineNumber() throws DebugException {
		return this.fLineNumber;
	}

	@Override
	public String getName() throws DebugException {
		return this.fFunctionName;
	}

	@Override
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return null;
	}

	@Override
	public IThread getThread() {
		return this.fThread;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		return this.fThread.getVariables(this);
	}

	@Override
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return (getVariables().length > 0);
	}
	
	/**
	 * Returns the name of the source file this stack frame is associated with.
	 * 
	 * @return the name of the source file this stack frame is associated with
	 */
	public String getSourceName() {
		return fFileName;
	}

	public boolean equals(Object obj) {
		if (obj instanceof PDAStackFrame) {
			PDAStackFrame pdaStackFrame = (PDAStackFrame) obj;
			return (pdaStackFrame.getThread().equals(getThread()) && 
					pdaStackFrame.getSourceName().equals(getSourceName()) && 
					pdaStackFrame.fId == fId);
		}
		return false;
	}

	public int hashCode() {
		return getSourceName().hashCode() + fId;
	}

	/**
	 * Returns this stack frame's unique identifier within its thread
	 * 
	 * @return this stack frame's unique identifier within its thread
	 */
	protected int getIdentifier() {
		return fId;
	}
}
