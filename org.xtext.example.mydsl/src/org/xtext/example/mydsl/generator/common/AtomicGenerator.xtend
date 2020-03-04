package org.xtext.example.mydsl.generator.common

import org.xtext.example.mydsl.myDsl.Atomic
import org.xtext.example.mydsl.myDsl.StringReference
import org.xtext.example.mydsl.myDsl.BooleanReference
import org.xtext.example.mydsl.myDsl.Variable
import org.xtext.example.mydsl.myDsl.ArrayReference
import org.xtext.example.mydsl.myDsl.PrimitiveReference
import org.xtext.example.mydsl.myDsl.LongReference
import org.xtext.example.mydsl.myDsl.FloatReference
import org.xtext.example.mydsl.myDsl.VariableReference
import org.xtext.example.mydsl.myDsl.VariableSymbol
import org.xtext.example.mydsl.myDsl.VariableDeclerationExpression
import org.xtext.example.mydsl.myDsl.ConstantVariableExpression
import org.xtext.example.mydsl.myDsl.SharedVariableExpression
import org.xtext.example.mydsl.myDsl.FunctionDefinitionParameter
import org.xtext.example.mydsl.myDsl.HexadecimalReference

class AtomicGenerator {
    def static String generate(Atomic atomic, boolean checkShared) {
        return generateAtomic(atomic, checkShared)
    }
    
    def static String generateDimension(Atomic atomic, boolean checkShared) {
        return "[ " + generate(atomic, checkShared) + " ]"
    }
    
    /*
     * Get target code of primitive LONG, FLOAT, STRING, BOOLEAN
     * returns: 5 | 5.0 | "Five" | true
     */
    def private static String generatePrimitiveReference(PrimitiveReference atomic) {
        var String result = ""
        
        if (atomic instanceof LongReference) {
            result = atomic.value.toString
        } else if (atomic instanceof FloatReference) {
            result = atomic.value.toString
        } else if (atomic instanceof StringReference) {
            result = atomic.value.toString
        } else if (atomic instanceof BooleanReference) {
            result = atomic.value.toString
        } else if (atomic instanceof HexadecimalReference) {
            result = atomic.value.toString
        }
        return result
    }
    
    def private static String generateVariableSymbol(VariableSymbol variableSymbol, boolean checkShared) {
        var String result = ""

        if (variableSymbol instanceof VariableDeclerationExpression) {
            result = (variableSymbol as VariableDeclerationExpression).name
        } else if (variableSymbol instanceof ConstantVariableExpression) {
            result = (variableSymbol as ConstantVariableExpression).name
        } else if (variableSymbol instanceof SharedVariableExpression) {
            result = (variableSymbol as SharedVariableExpression).name
        } else if (variableSymbol instanceof FunctionDefinitionParameter) {
            result = (variableSymbol as FunctionDefinitionParameter).name
        }
        
        if (checkShared) {
            val String scope = SymbolTable.getScope(result)
            if (scope == SymbolTable.SHARED) {
                result = sharedGetCall(result)
            }
        }
        
        return result
    }
    
    def private static String generateArrayReference(ArrayReference arrayReference, boolean checkShared) {
        var String result = generateVariableSymbol(arrayReference.arrayReference, checkShared)
        
        if (arrayReference.index !== null) {
            result += generateDimension(arrayReference.index.index, checkShared)
        }
        
        return result
    }
    
    def private static String generateVariable(Variable variable, boolean checkShared) {
        var String result = ""
        
        if (variable instanceof VariableReference) {
            result = generateVariableSymbol(variable.variableReference, checkShared)
        } else if (variable instanceof ArrayReference) {
            result = generateArrayReference(variable as ArrayReference, checkShared)
        }
        
        return result
    }
    
    // Most general function to get atomic generation
    def private static String generateAtomic(Atomic atomic, boolean checkShared) {
        var String result = ""
        if (atomic instanceof PrimitiveReference) {
            result = generatePrimitiveReference(atomic as PrimitiveReference)
        } else if (atomic instanceof Variable) {
            result = generateVariable(atomic as Variable, checkShared)
        }
        return result
    }
    
    def private static String sharedGetCall(String variable) {
        '''__GET(«variable»)'''
    }
    
}