package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression
import org.xtext.example.mydsl.myDsl.IntegerReference
import org.xtext.example.mydsl.myDsl.DoubleReference
import org.xtext.example.mydsl.myDsl.StringReference
import org.xtext.example.mydsl.myDsl.BooleanReference
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.ArrayReference
import org.xtext.example.mydsl.myDsl.VariableReference

class AtomicGenerator {
	def static String generate(Atomic atomic) {
		return getAtomic(atomic)
	}
	
	
	

	
	/*
	 * Get target code of primitive INT, DOUBLE, STRING, BOOLEAN
	 * returns: 5 | 5.0 | Five | true
	 */
	def private static String getTypedVariable(Atomic atomic) {
		var String result = ""
		if (atomic instanceof IntegerReference) {
			result = atomic.value.toString
		} else if (atomic instanceof DoubleReference) {
			result = atomic.value.toString
		} else if (atomic instanceof StringReference) {
			result = atomic.value.toString
		} else if (atomic instanceof BooleanReference) {
			result = atomic.value.toString
		} 
		return result
	}
	
	/*
	 * Get target of variable name 
	 * returns: value | value[0]
	 */
	def private static String getVariable(Variable atomicVar) {
		var String result = ""
		
		if (atomicVar instanceof ArrayReference) {
			val VariableSymbol symbol = (atomicVar as ArrayReference).arrayReference
			result = CommonGenerator.getVariableSymbol(symbol)
		} else if (atomicVar instanceof VariableReference) {
			val VariableSymbol symbol = (atomicVar as VariableReference).variableReference
			result = CommonGenerator.getVariableSymbol(symbol)
		}
		return result
	}
	
//	Most general function to get target code
	def private static String getAtomic(Atomic atomic) {
		var String result = ""
		
		if (atomic instanceof Variable) {
			result = getVariable((atomic as Variable).value)
		} else {
			result = getTypedVariable(atomic)
		}
		return result
	}
	
	
	
}