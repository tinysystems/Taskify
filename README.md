# Taskify

Taskify is an application development environment based on Eclipse. It is integrated into Eclipse Debug UI and Debug Core. Taskify enables engineers to develop and debug intermittent applications on a PC without target deployment.

## Why Taskify ?
- Taskify enables the development of intermittent software using platform-independent TaskDSL domain-specific language.
- Taskify has an interpreter integrated into TaskDSL.
- Taskify supports debugging intermittent TaskDSL applications on PC.
- Taskify automatically generates C code from a TaskDSL application for the InK runtime static library.

## Features of Taskify
### 1. TaskDSL 
TaskDSL is a domain-specific language defined with Xtext. Our TaskDSl editor supports the features below.

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
Our TaskDSL interpreter enables engineers to run and debug TaskDSL applications on PC. The interpreter supports the features below.

    Call stack
    Symbol table
    Antlr based parser (provided by Xtext)
    Request broker
    
### 3. Development Environment integreated with Eclipse Debug UI
Taskify communicates with the interpreter through socket by request-response and event messages.

### 4. C code generator
Taskify automatically generates C code linked to the InK runtime static library.

## Installation
