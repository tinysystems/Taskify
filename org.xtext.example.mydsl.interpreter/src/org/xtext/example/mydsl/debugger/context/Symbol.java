package org.xtext.example.mydsl.debugger.context;

public class Symbol {
	private String type;
	private String name;
	private Object variableValue;
	
	public Symbol(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public Symbol(String type, String name, Object variableValue) {
		this.type = type;
		this.name = name;
		this.variableValue = variableValue;
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
		this.variableValue = variableValue;
	}

	@Override
	public String toString() {
		return "Symbol [" + 
				"  type=" + type + 
				", name=" + name + 
				", variableValue=" + variableValue + 
				"  ]";
	}
	
	
}
