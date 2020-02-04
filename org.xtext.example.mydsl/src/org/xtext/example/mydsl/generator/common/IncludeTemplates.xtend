package org.xtext.example.mydsl.generator.common

class IncludeTemplates {
	val static String prefix = "#include"
	
	
	def static String include()
	'''«prefix»'''
	
	def static String inkLibrary() '''
	«include» "ink.h"
	'''
	
	def static String stringLibrary() '''
	«include» "string.h"
	'''
	
	def static String standardInputOutputLibrary() '''
	«include» <stdio.h>
	'''
	
	def static String standardLibrary() '''
	«include» <stdlib.h>
	'''
	
	def static String standardBoolLibrary() '''
	«include» <stdbool.h>
	'''
	
}