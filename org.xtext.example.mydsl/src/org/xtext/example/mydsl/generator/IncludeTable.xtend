package org.xtext.example.mydsl.generator

import java.util.HashSet
import java.util.Set

class IncludeTable {
	var static Set<String> includes = new HashSet<String>
	
	def static add(String include) {
		includes.add(include)
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