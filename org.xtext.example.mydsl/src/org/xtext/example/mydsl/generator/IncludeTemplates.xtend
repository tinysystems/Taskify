package org.xtext.example.mydsl.generator

class IncludeTemplates {
	val static String prefix = "#include"
	
	
	def static String include()
	'''«prefix»'''
	
	def static String inkLibaray() '''
	«include» "ink.h"
	'''
	
	def static String stringLibrary()'''
	«include» "string.h"
	'''
	
	def static String standardLibrary()'''
	«include» "stdio.h"
	'''
	
}