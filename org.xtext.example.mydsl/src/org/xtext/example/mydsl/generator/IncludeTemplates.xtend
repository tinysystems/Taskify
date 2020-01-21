package org.xtext.example.mydsl.generator

class IncludeTemplates {
	val static String prefix = "#include"
	
	
	def static String include()
	'''«prefix»'''
	
	def static String inkLibrary() '''
	«include» "ink.h"
	'''
	
	def static String stringLibrary()'''
	«include» "string.h"
	'''
	
	def static String standardLibrary()'''
	«include» <stdio.h>
	«include» <stdlib.h>
	'''
	
	def static String standardBoolLibrary()'''
	«include» <stdbool.h>
	'''
	
}