# Taskify

TaskDSL is a Domain Specific Language to allow developers to develop intermittent application which will be executed on Intermittently-powered devices. 

Taskify is an application development environment based on Eclipse which is integrated with Eclipse Debug UI and Debug Core.

## Why TaskDSL ?
Developing applications for embedded devices is hard by using lo level languages like C. Besides, debugging software requires hardware. 

Each runtime environments for executing intermittent softwares have their specifications to adapt execution of applications intermittently.
TaskDSL hides details of existing run-time environments

## Why Taskify ?
- Taskify provides developing intermittent softwares by using TaskDSL.
- Taskify has an interpreter which is totally integrated with TaskDSL.
- Taskify supports debugging intermittent applications which is written with TaskDSL to trace variables on any time. 
- Taskify automicatically generates C code belonging to TaskDSL application for InK runtime static library.

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
Allows developers to run and debug TaskDSL applications: Below core components are exist in interpreter.

    Call stack
    Symbol table
    Antlr based parser (provided by Xtext)
    Request broker
    
### 3. Development Environment integreated with Eclipse Debug UI
Communicates with interpreter through socket by request-response and event mesages

### 4. C code generator
Automatically generates C code that will be linked to InK static library
