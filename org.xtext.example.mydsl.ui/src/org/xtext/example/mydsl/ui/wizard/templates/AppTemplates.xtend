package org.xtext.example.mydsl.ui.wizard.templates

class AppTemplates {
    def static String emptyTemplate() {
        '''        
        // Define persistent variables
        // CONSTANT integer constant1 = 5
        
        // Define task shared variables
        /*
        SHARED { 
            integer shared1
        }
        */
        
        entry task first {
            print("Hello World")
            
            next second
        }
        
        task second {
            // Define local variables
            integer local_val = 5
            
            end
        }
        '''    
    }
    
    def static String helloWorldTemplate() {
        '''
        // Define persistent variables
        // CONSTANT integer constant1 = 5
        
        // Define task shared variables
        /*
        SHARED { 
            integer shared1
        }
        */
                
        entry task t_init {
            print("Hello World")
            
            end
        }
        '''
    }
    
    def static String addTwoIntegers() {
        '''
        // Define persistent variables
        CONSTANT integer op1 = 5
                
        // Define task shared variables
        SHARED {
            integer result
        }
        
        entry task t_init {
            integer op2 = 5 * 2
            
            // Copy task shared variable to local variable.
            integer _result = result
            
            // Do operation
            _result = op1 + op2
            
            // Copy result local variable to task shared variable
            result = _result
            
            // Print the result
            print("Result is: ", _result)
            
            end
        }
        '''
    }
    
    def static String functionCallTemplate() {
        '''        
        // Define persistent variables
        CONSTANT integer op1 = 5
        CONSTANT integer op2 = 5
        
        // Define task shared variables
        SHARED {
            integer result
        }
        
        // Define function definitions
        integer add (integer val1, integer val2){
            integer res
            res = val1 + val2
            return res
        }
        
        integer sub (integer val1, integer val2){
            integer res
            res = val1 - val2
            return res
        }
        
        entry task t_init {            
            // Copy task shared variable to local variable.
            integer _result = result
            
            // Do operation
            _result = add(op1, op2)
            
            // Copy result local variable to task shared variable
            result = _result
            
            // Print the result
            print("Result is: ", _result)
            
            // Jump to next task
            next do_sub
        }
        
        task do_sub {
             // Copy task shared variable to local variable.
            integer _result = result
            
            // Do operation
            _result = sub(op1, op2)
            
            // Copy result local variable to task shared variable
            result = _result
            
            // Print the result
            print("Result is: ", _result)
            
            end
        }
        '''
    }
}