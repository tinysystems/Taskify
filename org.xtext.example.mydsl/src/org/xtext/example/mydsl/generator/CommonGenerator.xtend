package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.ArrayDimension
import org.xtext.example.mydsl.myDsl.SharedVariableExpression

class CommonGenerator {
	static String INT = "uint32_t"
	static String FLOAT = "float"
	static String STRING = "char"
	static String BOOL = "bool"
	
	def static String getCType(String type) {
		var String result
		
		if (type == "integer") {
			result = INT
		} else if (type == "double") {
			result = FLOAT
		} else if (type == "string") {
			result = STRING
		} else if (type == "boolean") {
			result = BOOL
		} else {
			result = null
		}
		
		return result
	}
	
	def static String cast(String target, String type) {
		var String result = ""
		if (target != type) {
			result = "(" + getCType(target) + ") "
		}
		return result
	}
	
	def static String getVariableTypeName(String DslType, String name) {
		var String result = ""
		val String type = getCType(DslType)

		if (type == BOOL) {
			IncludeTable.add(IncludeTemplates.standardBoolLibrary)	
		}
		
		result += type
		result += " " + name
		
		if (type == STRING) {
			result += dimension(100)
		}		
		return result
	}
	
	def static String dimension(int index)
	'''[«index»]'''
	
	def static String dimension(VariableSymbol symbolIndex, int index)
	'''[«getVariableSymbol(symbolIndex, index)»]'''
	
	def static String getDimension(ArrayDimension dimension, int index) {
		var String result = ""
		val Object objIndex = dimension.size
		if (index >= 0) {
			result = dimension(index)
		} else {
			if (objIndex instanceof VariableSymbol) {
				result = dimension(objIndex as VariableSymbol, -1)
			} else if (objIndex instanceof Integer) {
				result = dimension(objIndex)
			}
		}
		
		return result
	}
		
	def static String getVariableSymbol(VariableSymbol symbol, int index) {
		var String result = ""
		if (symbol instanceof VariableDeclerationExpression) {
			result = (symbol as VariableDeclerationExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension, index) : ""
		} else if (symbol instanceof ConstantVariableExpression) {
			result = (symbol as ConstantVariableExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension, index) : ""
		} else if (symbol instanceof SharedVariableExpression) {
			result = (symbol as SharedVariableExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension, index) : ""
		}
		return result
	}
	
	
	def static String newLine() {
		return "\n"
	}
	
	def static String doubleNewLine() {
		return newLine + newLine
	}
	
	def static String tab() {
		'''    '''		
	}
	
	def static String appinit() {
		'''
		extern void thread1_init();
			
		// this is the function that will be called only at initial boot by the runtime.
		void __app_init(){
		    thread1_init();
		}
		
		
		'''
	}
	
	def static String thread1_init (String t_init) {
		'''
		void thread1_init(){
		    // create a thread with priority 15 and entry task «t_init»
		    __CREATE(15, «t_init»);
		    __SIGNAL(15);
		}
		
		
		'''
	}
	
}