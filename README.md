# Taskify

Taskify is an application development environment based on Eclipse. It s integrated to Eclipse Debug UI and Debug Core. Taskify allows to develop and debug intermittent applications on a PC without target deployment.

## Why Taskify ?
- Taskify enables the development of intermittent software using platform independent TaskDSL domain specific language. 
- Taskify has an interpreter which is totally integrated with TaskDSL.
- Taskify supports debugging intermittent TaskDSL applications on PC. 
- Taskify automicatically generates C code from TaskDSL application for InK runtime static library.

## Features of Taskify
### 1. TaskDSL 
TaskDSL is a domain specific language that is defined with Xtext. Below features are supported with TaskDSL editor.

    Error detection
    Syntax coloring
    Proposals (Auto-completion)
    Scoping
    Cross reference
    Formatting
    Quick fix
    New project wizard including template proejcts
    Go to generated code

### 2. Interpreter 
Allows developers to run and debug TaskDSL applications on PC. Below core components are exist in interpreter.

    Call stack
    Symbol table
    Antlr based parser (provided by Xtext)
    Request broker
    
### 3. Development Environment integreated with Eclipse Debug UI
Communicates with interpreter through socket by request-response and event mesages.

### 4. C code generator
Automatically generates C code that will be linked to InK static library.
