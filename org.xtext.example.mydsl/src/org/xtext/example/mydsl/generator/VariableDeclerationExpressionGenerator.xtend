package org.xtext.example.mydsl.generator

import org.xtext.example.mydsl.generator.IExpressionGenerator
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ArrayAssignment
import org.xtext.example.mydsl.myDsl.Operation
import org.xtext.example.mydsl.myDsl.IntegerReference
import org.xtext.example.mydsl.myDsl.ArrayReference
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.VariableReference
import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.GlobalVariableExpression
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.DoubleReference
import org.xtext.example.mydsl.myDsl.StringReference
import org.xtext.example.mydsl.myDsl.BooleanReference
import org.xtext.example.mydsl.myDsl.ArrayDimension

class VariableDeclerationExpressionGenerator implements IExpressionGenerator {
	VariableDeclerationExpression expression
	GeneratorSwitcher generator
	
	static String INT = "int"
	static String FLOAT = "float"
	static String STRING = "char"
	
	new (VariableDeclerationExpression expression, GeneratorSwitcher generator) {
		this.expression = expression
		this.generator = generator
	}
	
	override String generate() {
		var String result = ""
		val String name = this.expression.name
		val String type = this.expression.type.type
		
		val boolean isArray = this.expression.dimension !== null
//		result += getCType(type)
//		result += " " + name
		
		result += getVariableTypeName()
		if (isArray) {
			result += getDimension(this.expression.dimension)
		} 
		
		if (this.expression.assignment !== null) {
			if (isArray) {
				result += getAssignment(this.expression.assignment.expression as ArrayAssignment)	
			} else {
				result += getAssignment(this.expression.assignment.expression as Operation)
			}
		}
		
		result += ";"
//		println(result)
		return result
	}
	
	def static String getCType(String type) {
		var String result
		
		if (type == "integer") {
			result = INT
		} else if (type == "double") {
			result = FLOAT
		} else if (type == "string") {
			result = STRING
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
	
//	def String dimension() 
//	'''[ «this.expression.dimension» ]'''
//	

	
	def static String getDimension(ArrayDimension dimension) {
		var String result = ""
		val Object index = dimension.size
		if (index instanceof VariableSymbol) {
			result = dimension(index as VariableSymbol)
		} else if (index instanceof Integer) {
			result = dimension(index)
		}
		
		return result
	}
	
	def static String dimension(int index)
	'''[«index»]'''
	
	def static String dimension(VariableSymbol index)
	'''[«getVariableSymbol(index)»]'''
	
//	def String getDimension(int index) {
//		return "[" + index + "]"
//	}
	
	def static String getVariableSymbol(VariableSymbol symbol) {
		var String result = ""
		if (symbol instanceof VariableDeclerationExpression) {
			result = (symbol as VariableDeclerationExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension) : ""
		} else if (symbol instanceof ConstantVariableExpression) {
			result = (symbol as ConstantVariableExpression).name
			result += symbol.dimension !== null ? getDimension(symbol.dimension) : ""
		} else if (symbol instanceof GlobalVariableExpression) {
			
		}
		return result
	}
	
	def static String getPrimitiveVariable(EObject atomic) {
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
	
	def static String getAtomic(Atomic atomic) {
		var String result = ""
		
		if (atomic instanceof Variable) {
			val Variable atomicVar = (atomic as Variable).value
			if (atomicVar instanceof ArrayReference) {
				val VariableSymbol symbol = (atomicVar as ArrayReference).arrayReference
				result = getVariableSymbol(symbol)
			} else if (atomicVar instanceof VariableReference) {
				val VariableSymbol symbol = (atomicVar as VariableReference).variableReference
				result = getVariableSymbol(symbol)
			}
		} else {
			result = getPrimitiveVariable(atomic)
		}
		return result
	}
	
	def static String getAssignment(Operation operation) {
		var String result = " = "
		var Atomic left = operation.left
		result += getAtomic(left)
		
		for (var int i = 0; i < operation.operator.size; i++) {
			val Atomic right = operation.right.get(i)
			result += operation.operator.get(i)
			result += getAtomic(right)
		}
		
		return result
	}
	
	def static String getAssignment(ArrayAssignment array) {
		var String result = " = "
		
		result += "{"
		result += getAtomic(array.variable.get(0))
		
		for (var int i = 1; i < array.variable.size; i++) {
			result += ", " + getAtomic(array.variable.get(i))
		}
		
		result += "}"
		
		return result
	}
	
	def String variable() 
	'''«getCType(this.expression.type.type)» «this.expression.name»'''
	
	
	def String getVariableTypeName() {
		var String result = ""
		val String type = getCType(this.expression.type.type)

		result += type
		result += " " + this.expression.name
		
		if (type == STRING) {
			result += dimension(100)
		}		
		return result
	}
	
	
	
//	def String value(ArrayAssignment assignment) '''
//	 = { 
//	 	«assignment.variable.get(0)» «FOR i: 1..< assignment.variable.size», «assignment.variable.get(i)»«ENDFOR»
//	 }
//	'''
	
//	def String value(Operation operation) '''
//	 = «operation.left» 
//	 «FOR i: 0..< operation.operator.size»
//	 	«IF operation.operator.get(i) instanceof ArrayReference»
//	 	
//	 	«ENDIF»
//	 	«operation.operator.get(i)» «operation.right.get(i)»
//	 «ENDFOR»
//	 '''
	
//	def static String generateVariableDeclarationExpression(VariableDeclerationExpression expression) {
//		return ""
//	}
}