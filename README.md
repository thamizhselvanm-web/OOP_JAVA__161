# Java Laboratory Programs

A structured collection of Java laboratory programs covering core Object-Oriented Programming and advanced Java concepts. The repository contains clean, individual `.java` source files organized experiment-wise for easy compilation, execution, academic reference, and GitHub submission.

## Experiments Covered

### Experiment 1 — Telephone Bill

Demonstrates the use of classes and objects to represent customer and telephone billing information.

**Concepts:**
- Classes and objects
- Instance variables
- Constructors
- Methods
- Conditional statements
- Bill calculation

**Source file:**
```text
Ex_1_Telephone_Bill/
└── TelephoneBill.java
```

### Experiment 2 — Temperature Converter

Demonstrates package creation and package utilization through a temperature conversion utility.

The program includes conversions between Celsius, Fahrenheit, and Kelvin.

**Concepts:**
- User-defined packages
- Public classes and methods
- Static methods
- Package import
- Mathematical calculations

**Source files:**
```text
Ex_2_Temperature_Converter/
├── TemperatureMain.java
└── temperature/
    └── Converter.java
```

### Experiment 3 — Vehicle Inheritance

Demonstrates inheritance using a common `Vehicle` class and specialized vehicle types.

The implementation includes Car, Bike, and Truck.

**Concepts:**
- Inheritance
- Constructors
- `super` keyword
- Method reuse
- Class hierarchy
- Object creation

**Source file:**
```text
Ex_3_Vehicle_Inheritance/
└── VehicleDemo.java
```

### Experiment 4 — Abstract Library Member

Demonstrates the use of an abstract class to represent different types of library members.

The implementation includes Student Member, Faculty Member, and External Member.

**Concepts:**
- Abstract classes
- Abstract methods
- Method implementation
- Constructor inheritance
- Object creation

**Source file:**
```text
Ex_4_Abstract_Library_Member/
└── LibraryDemo.java
```

### Experiment 7 — Multithreading

Demonstrates multithreading using Java's `Thread` class.

The program generates an array and performs ascending and descending sorting using separate threads.

**Concepts:**
- Thread creation
- `Thread` class
- `run()` method
- `start()` method
- `join()`
- Concurrent execution
- Array processing

**Source file:**
```text
Ex_7_Multithreading/
└── ArrayThreadDemo.java
```

### Experiment 8 — Inter-Thread Communication

Demonstrates communication between multiple threads using Java synchronization mechanisms.

The railway booking example demonstrates ticket booking, cancellation, waiting, and notification.

**Concepts:**
- Thread synchronization
- `synchronized`
- `wait()`
- `notifyAll()`
- Thread coordination
- Shared resources
- Concurrent access control

**Source file:**
```text
Ex_8_Inter_Thread_Communication/
└── RailwayBooking.java
```

## Repository Structure

```text
Java_Lab_Programs/
│
├── Ex_1_Telephone_Bill/
│   └── TelephoneBill.java
│
├── Ex_2_Temperature_Converter/
│   ├── TemperatureMain.java
│   └── temperature/
│       └── Converter.java
│
├── Ex_3_Vehicle_Inheritance/
│   └── VehicleDemo.java
│
├── Ex_4_Abstract_Library_Member/
│   └── LibraryDemo.java
│
├── Ex_7_Multithreading/
│   └── ArrayThreadDemo.java
│
├── Ex_8_Inter_Thread_Communication/
│   └── RailwayBooking.java
│
└── README.md
```

## Requirements

- Java Development Kit (JDK)
- Java compiler (`javac`)
- Java Runtime Environment
- Command Prompt, PowerShell, Terminal, or any Java-compatible IDE

Recommended:

```text
JDK 8+
```

## How to Compile and Run

### Experiment 1

```bash
cd Ex_1_Telephone_Bill
javac TelephoneBill.java
java TelephoneBill
```

### Experiment 2

Because `Converter.java` belongs to the `temperature` package:

```bash
cd Ex_2_Temperature_Converter
javac temperature/Converter.java TemperatureMain.java
java TemperatureMain
```

### Experiment 3

```bash
cd Ex_3_Vehicle_Inheritance
javac VehicleDemo.java
java VehicleDemo
```

### Experiment 4

```bash
cd Ex_4_Abstract_Library_Member
javac LibraryDemo.java
java LibraryDemo
```

### Experiment 7

```bash
cd Ex_7_Multithreading
javac ArrayThreadDemo.java
java ArrayThreadDemo
```

### Experiment 8

```bash
cd Ex_8_Inter_Thread_Communication
javac RailwayBooking.java
java RailwayBooking
```

## Code Quality

The programs are organized as separate source files to make them:

- Easy to understand
- Easy to compile
- Easy to execute
- Suitable for laboratory submission
- Easy to maintain
- Convenient to upload and manage through GitHub

The implementations use standard Java features and APIs without unnecessary external dependencies.

## Learning Objectives

This repository provides practical implementation of important Java concepts:

```text
Classes & Objects
        ↓
Packages
        ↓
Inheritance
        ↓
Abstract Classes
        ↓
Multithreading
        ↓
Inter-Thread Communication
```

These implementations provide practical exposure to Java's object-oriented and concurrent programming features through laboratory exercises.

## Compilation Notes

Generated `.class` files are not required to be committed to the repository. Only Java source files and documentation should normally be tracked using Git.

For example:

```bash
javac TelephoneBill.java
```

generates:

```text
TelephoneBill.class
```

The generated `.class` files can be removed after execution if required.

## GitHub Repository

This repository is maintained as an organized academic Java laboratory collection, with each experiment separated into its own directory for clarity and easy navigation.

## License

This repository is intended primarily for educational and academic use.

## Author

**Thamizh Selvan**

---

## Repository Summary

Java Laboratory Programs — Clean and structured Java implementations covering classes and objects, packages, inheritance, abstract classes, multithreading, and inter-thread communication.
