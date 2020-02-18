package org.xtext.example.mydsl.generator.common


import java.util.ArrayList
import java.util.List

class IncludeTable {
    var static List<String> includes = new ArrayList<String>
    
    def static add(String include) {
        if (!includes.contains(include)) {
            includes.add(include)    
        }
    }
    
    def static remove(String include) {
        if (includes.contains(include)) {
            includes.remove(include)
        }
    }

    def static generate() {
        var String result = ""
        for (String include: includes) {
            result += include
        }
        
        return result
    }
}