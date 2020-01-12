package org.xtext.example.mydsl.generator

import java.util.HashSet

class IncludeTable {
	var includes = new HashSet<String>
	
	
	
	
	
//	def getStandardLibrary {
//		val lib = '''
//		#include <stdio.h>
//		'''
//		return lib
//	}
//	
	def getink() {
	val lib = '''
	#include "ink.h"
	'''
	
	return lib
	
	}
}