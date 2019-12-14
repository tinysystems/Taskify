package org.xtext.example.mydsl.debugger.context;


public class Symbol {
	private String name;
	private String type;
	private Object variableValue;
	private String scope;
	
	public Symbol(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public Symbol() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(Object variableValue) {
		Object val = null;

		switch (this.type) {
			case "string":
				val = (String) variableValue; 
				break;
			case "integer":
				val = (int) variableValue;
				break;
			case "boolean":
				val = (Boolean) variableValue;
				break;
			case "double":
				val = (double) variableValue;
				break;
			default:
				val = variableValue;
		} 
		this.variableValue = val;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Override
	public String toString() {
		return "Symbol [" + 
				"  type=" + type + 
				", name=" + name + 
				", variableValue=" + variableValue + 
				", scope=" + scope + 
				"  ]";
	}
}
