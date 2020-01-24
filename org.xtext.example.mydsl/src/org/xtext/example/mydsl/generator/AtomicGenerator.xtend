package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.IntegerReference
import org.xtext.example.mydsl.myDsl.DoubleReference
import org.xtext.example.mydsl.myDsl.StringReference
import org.xtext.example.mydsl.myDsl.BooleanReference
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.ArrayReference

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
	
	// Most general function to get target code
	def private static String getAtomic(Atomic atomic) {
		var String result = ""
		
		if (atomic instanceof Variable) {
			result = VariableGenerator.generate((atomic as Variable).value)
			
			// __GET function call is required to get value of global variables
			var String variableName = result
			if (atomic instanceof ArrayReference) {
				variableName = variableName.substring(0, variableName.length - 3)
			}

			if (SymbolTable.getScope(variableName) == SymbolTable.GLOBAL) {
				result = globalGetCall(result)
			} 
		} else {
			result = getTypedVariable(atomic)
		}
		return result
	}
	
	def private static String globalGetCall(String variable) {
		'''__GET(«variable»)'''
	} 
	
	
	
	
}