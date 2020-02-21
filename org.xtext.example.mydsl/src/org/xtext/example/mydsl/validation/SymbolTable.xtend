package org.xtext.example.mydsl.validation

import java.util.List
import java.util.ArrayList

class SymbolTable {
    val public static SHARED = "shared"
    val public static CONSTANT = "constant"
    val public static LOCAL = "local"
    var static List<String> variables = null
    
    def static void resetTable() {
        variables = new ArrayList<String>()
    }
    
    def static void addName(String name) {
        if (!variables.contains(name)) {
            variables.add(name);
        }
    }
    
    def static boolean isExist(String name) {
        return variables.contains(name);
    }
    
    
    
}