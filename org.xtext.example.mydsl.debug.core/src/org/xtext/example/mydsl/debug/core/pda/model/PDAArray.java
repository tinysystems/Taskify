package org.xtext.example.mydsl.debug.core.pda.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;


/**
 * Logical stucture to translate a string into its words.
 */
public class PDAArray extends PDAValue {
	public PDAArray(PDAValue value) throws DebugException{
		super(value.getPDADebugTarget(), value.getValueString());
	}
	
	public boolean hasVariables() throws DebugException {
		return true;
	}

	public IVariable[] getVariables() throws DebugException {
		String str = getValueString();
		str = str.substring(1, str.length() - 1); //remove first and last char "{" and "}"
		
		String[] words = str.split(",");
		IVariable[] variables = new IVariable[words.length];
		
		for (int i = 0; i < words.length; i++) {
			String wordforSplit = words[i];
			String word = wordValueGen(wordforSplit);
			variables[i] = new PDAArrayEntry(getPDADebugTarget(), i, new PDAValue(getPDADebugTarget(), word));
		}
		return variables;
	}

	private String wordValueGen(String wordforSplit) {
		String key = wordforSplit.split("=")[0].trim(); 
		String value = wordforSplit.split("=")[1].trim();
		
		key = "index:" + key.replace("_", ",");
		value = "value:" + value;
		
		return key + " " + value;
	}
}
