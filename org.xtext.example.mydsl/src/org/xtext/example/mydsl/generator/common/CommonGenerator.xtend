package org.xtext.example.mydsl.generator.common

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

class CommonGenerator {
    static String LONG = "uint32_t"
    static String FLOAT = "float"
    static String STRING = "char"
    static String BOOL = "bool"
    
    def static String getCType(String type) {
        var String result
        
        if (type == "integer") {
            result = LONG
        } else if (type == "float") {
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
    
    def static String getVariableTypeName(String DslType) {
        var String result = ""
        val String type = getCType(DslType)

        if (type == BOOL) {
            IncludeTable.add(IncludeTemplates.standardBoolLibrary)    
        }
        
        result += type
        return result
    }
    
    def static String dimension(long index)
    '''[«index as int»]'''    
    
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
    
    def static void stopGeneration(String reason) {
        System.err.println("Error while processing, reason: " + reason);
    }
    
    def static int getLineNumber(EObject expression) {
        val INode node = NodeModelUtils.getNode(expression);
        if (node !== null) {
            return node.getStartLine();
        }
        return -1
    }
    
    def static String getLineNumberText(EObject expression) {
        return " at line #" + getLineNumber(expression);
    }
    
    def static String getExpressionText(EObject expression) {
        val INode node = NodeModelUtils.getNode(expression);
        if (node !== null) {
            return node.getText();
        }
        return null
    }
    
}