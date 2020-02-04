package org.xtext.example.mydsl.generator.common

class HeaderComment {
	
	def static String headerThread() {
		'''
		// This file is part of InK.
		
		/*
		 * InK is free software: you ca	n redistribute it and/or modify
		 * it under the terms of the GNU General Public License as published *
		 * the Free Software Foundation, either version 3 of the License, or
		 * (at your option) any later version.
		 * 
		 * This program is distributed in the hope that it will be useful,
		 * but WITHOUT ANY WARRANTY; without even the implied warranty of
		 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
		 * GNU General Public License for more details.
		 * 
		 * You should have received a copy of the GNU General Public License
		 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
		 */
		
		
		'''
	}
	
	def static String headerShared() {
		'''
		// Define task-shared persistent variables.
		'''
	}
	
	def static String headerConstant() {
		'''
		// Define constant persistent variables that are stored in non-volatile memory.
		'''
	}
	
	def static String headerTaskDecleration() {
		'''
		// Declare tasks that will be implemented
		'''
	}
	
	def static String headerThreadInit() {
		'''
		// Called at the very first boot
		'''
	}
	
	def static String headerTaskDefinition() {
		'''
		// Implementation of all tasks that are declared above
		'''
	}
}