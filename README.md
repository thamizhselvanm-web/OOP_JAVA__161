# Object-Oriented Programming (OOP) Java Laboratory Manual

A comprehensive collection of Object-Oriented Programming lab experiments written in Java. This repository spans foundational concepts—such as classes, encapsulation, inheritance, polymorphism, abstract classes, and packages—up to advanced concepts including circular queue ADTs, wrapper class immutability, multithreading, inter-thread communication, file handling, and full-stack database-backed JavaFX CRUD applications.

---

## Repository Structure

```text
OOP_JAVA__161/
├── .gitignore
├── README.md
├── Ex_1_Telephone_Bill/
│   └── TelephoneBill.java
├── Ex_2_Temperature_Converter/
│   ├── TemperatureMain.java
│   └── temperature/
│       └── Converter.java
├── Ex_3_Vehicle_Inheritance/
│   └── VehicleDemo.java
├── Ex_4_Abstract_Library_Member/
│   └── LibraryDemo.java
├── Ex_5_ADT_Queue_Exception_Handling/
│   └── Main.java
├── Ex_6_Wrapper_Immutable_Demo/
│   └── WrapperImmutableDemo.java
├── Ex_7_Multithreading/
│   └── ArrayThreadDemo.java
├── Ex_8_Inter_Thread_Communication/
│   └── RailwayBooking.java
├── Ex_9_String_Operations_ArrayList/
│   └── StringMenu.java
├── Ex_10_File_Handling_ListFiles/
│   └── File_Handling_ListFiles.java
└── Ex_11_JavaFX_JDBC_CRUD/
    ├── database_setup.sql
    ├── StudentManagementApp.java
    └── lib/
```

---

## Experiments Index

| Exp No. | Topic | Core Concepts | Primary Class / Files |
| :--- | :--- | :--- | :--- |
| **Ex 1** | Telephone Bill Calculator | Classes, Objects, Constructors, Tiered Billing Logic | `TelephoneBill.java` |
| **Ex 2** | Temperature Converter Utility | Java Packages, Static Utility Methods, Unit Conversion | `Converter.java`, `TemperatureMain.java` |
| **Ex 3** | Vehicle Tax & Insurance Hierarchy | Inheritance (`extends`), Method Overriding, Subtyping | `VehicleDemo.java` |
| **Ex 4** | Library Membership System | Abstract Classes, Polymorphism, Mandatory Method Contract | `LibraryDemo.java` |
| **Ex 5** | Circular Queue ADT with Exception Handling | Interfaces (`implements`), Fixed Array Queue, `try-catch` | `Main.java` |
| **Ex 6** | Wrapper Classes & Immutability | Object Identity, Unboxing/Reboxing, `identityHashCode` | `WrapperImmutableDemo.java` |
| **Ex 7** | Multithreaded Array Processing | Thread Creation (`extends Thread`), `start()`, `join()` | `ArrayThreadDemo.java` |
| **Ex 8** | Inter-Thread Railway Booking System | Synchronized Monitors, `wait()`, `notifyAll()`, Thread States | `RailwayBooking.java` |
| **Ex 9** | Interactive String Operations | Dynamic Collections (`ArrayList`), Filtering, Buffer Flushing | `StringMenu.java` |
| **Ex 10** | Directory File Listing Utility | Java File API (`java.io.File`), `isDirectory()`, `isFile()` | `File_Handling_ListFiles.java` |
| **Ex 11** | Student Management CRUD Application | JavaFX GUI, JDBC Database Connectivity, MySQL CRUD | `StudentManagementApp.java`, `database_setup.sql` |

---

## Detailed Experiment Specifications

### Experiment 1: Telephone Bill Calculator
- **Directory**: `Ex_1_Telephone_Bill/`
- **Main Class**: `TelephoneBill`
- **Concepts**: Classes, Objects, Instance Variables, Parameterized Constructors, Conditional Rate Calculation.
- **Description**: Calculates telephone billing amounts based on call usage minutes and plan tier (`prepaid` vs `postpaid`). Features multi-tiered tariff calculations for calls below 100 minutes, between 101–200 minutes, and exceeding 200 minutes.
- **Compilation & Execution**:
  ```bash
  cd Ex_1_Telephone_Bill
  javac TelephoneBill.java
  java TelephoneBill
  ```

---

### Experiment 2: Temperature Converter Utility with Packages
- **Directory**: `Ex_2_Temperature_Converter/`
- **Main Class**: `TemperatureMain`
- **Package**: `temperature`
- **Concepts**: User-defined Packages, Package Import, Utility Classes, Static Methods.
- **Description**: Implements a dedicated `temperature` package containing `Converter.java` with static methods for converting between Celsius, Fahrenheit, and Kelvin temperature scales without needing object instantiation.
- **Compilation & Execution**:
  ```bash
  cd Ex_2_Temperature_Converter
  javac temperature/Converter.java TemperatureMain.java
  java TemperatureMain
  ```

---

### Experiment 3: Vehicle Tax & Insurance Hierarchy
- **Directory**: `Ex_3_Vehicle_Inheritance/`
- **Main Class**: `VehicleDemo`
- **Concepts**: Inheritance (`extends`), Method Overriding, Dynamic Method Dispatch, Subtyping.
- **Description**: Models a vehicle classification system with base class `Vehicle` and specialized subclasses `Car` and `Motorcycle`. Each vehicle type overrides tax and insurance calculation algorithms.
- **Compilation & Execution**:
  ```bash
  cd Ex_3_Vehicle_Inheritance
  javac VehicleDemo.java
  java VehicleDemo
  ```

---

### Experiment 4: Library Membership System
- **Directory**: `Ex_4_Abstract_Library_Member/`
- **Main Class**: `LibraryDemo`
- **Concepts**: Abstract Classes (`abstract`), Abstract Methods, Contract Enforcement, Polymorphism.
- **Description**: Implements an abstract `LibraryMember` class defining common attributes and mandatory abstract methods (`calculateFee()`, `getBorrowLimit()`). Subclasses `StudentMember` and `FacultyMember` implement specialized rules.
- **Compilation & Execution**:
  ```bash
  cd Ex_4_Abstract_Library_Member
  javac LibraryDemo.java
  java LibraryDemo
  ```

---

### Experiment 5: Circular Queue ADT with Exception Handling
- **Directory**: `Ex_5_ADT_Queue_Exception_Handling/`
- **Main Class**: `Main`
- **Concepts**: Interfaces (`implements`), Abstract Data Types (ADT), Circular Buffer Array, Custom Exception Handling.
- **Description**: Implements a fixed-capacity Circular Queue ADT conforming to a `QueueADT` interface. Defines custom exception classes `QueueFullException` and `QueueEmptyException` for robust edge case handling.
- **Compilation & Execution**:
  ```bash
  cd Ex_5_ADT_Queue_Exception_Handling
  javac Main.java
  java Main
  ```

---

### Experiment 6: Wrapper Classes & Immutability Demonstration
- **Directory**: `Ex_6_Wrapper_Immutable_Demo/`
- **Main Class**: `WrapperImmutableDemo`
- **Concepts**: Primitive Wrapper Classes, Autoboxing/Unboxing, Immutable Objects, Memory Address Analysis via `System.identityHashCode()`.
- **Description**: Demonstrates wrapper class caching (Integer pool -128 to 127) and immutability behavior of String and Integer objects by comparing hash codes before and after modification operations.
- **Compilation & Execution**:
  ```bash
  cd Ex_6_Wrapper_Immutable_Demo
  javac WrapperImmutableDemo.java
  java WrapperImmutableDemo
  ```

---

### Experiment 7: Multithreaded Array Processing
- **Directory**: `Ex_7_Multithreading/`
- **Main Class**: `ArrayThreadDemo`
- **Concepts**: Multithreading (`extends Thread`), Parallel Task Execution, Thread Lifecycle Management (`start()`, `join()`).
- **Description**: Spawns concurrent threads to process sub-arrays in parallel. Demonstrates thread synchronization and wait-for-completion semantics using `join()`.
- **Compilation & Execution**:
  ```bash
  cd Ex_7_Multithreading
  javac ArrayThreadDemo.java
  java ArrayThreadDemo
  ```

---

### Experiment 8: Inter-Thread Railway Booking System
- **Directory**: `Ex_8_Inter_Thread_Communication/`
- **Main Class**: `RailwayBooking`
- **Concepts**: Inter-Thread Communication, Synchronized Blocks/Methods, Monitor Locks, `wait()`, `notify()`, `notifyAll()`.
- **Description**: Models a concurrent railway ticket reservation system where passenger threads attempt to book available seats while a cancellation thread releases seats, using monitor synchronization.
- **Compilation & Execution**:
  ```bash
  cd Ex_8_Inter_Thread_Communication
  javac RailwayBooking.java
  java RailwayBooking
  ```

---

### Experiment 9: Interactive String Operations using ArrayList
- **Directory**: `Ex_9_String_Operations_ArrayList/`
- **Main Class**: `StringMenu`
- **Concepts**: Dynamic Collections (`java.util.ArrayList`), String Manipulation Methods, Scanner Buffer Management.
- **Description**: Menu-driven application supporting dynamic operations on string collections including append, indexed insertion, exact-match searching, starting-letter filtering (case-insensitive), and full listing.
- **Compilation & Execution**:
  ```bash
  cd Ex_9_String_Operations_ArrayList
  javac StringMenu.java
  java StringMenu
  ```

---

### Experiment 10: Directory File Listing Utility
- **Directory**: `Ex_10_File_Handling_ListFiles/`
- **Main Class**: `File_Handling_ListFiles`
- **Concepts**: File System I/O (`java.io.File`), Path Validation, Directory Filtering (`isDirectory()`, `isFile()`).
- **Description**: Reads a user-specified directory path from console, verifies directory existence, retrieves all child nodes via `listFiles()`, and outputs only regular files while excluding subdirectories.
- **Compilation & Execution**:
  ```bash
  cd Ex_10_File_Handling_ListFiles
  javac File_Handling_ListFiles.java
  java File_Handling_ListFiles
  ```

---

### Experiment 11: JavaFX JDBC Student Management CRUD Application
- **Directory**: `Ex_11_JavaFX_JDBC_CRUD/`
- **Main Class**: `StudentManagementApp`
- **Components**:
  - `StudentManagementApp.java`: Single-file JavaFX GUI & MySQL JDBC CRUD application logic.
  - `database_setup.sql`: SQL database & table initialization script.
  - `lib/`: JavaFX 21 & MySQL Connector JAR dependencies.
- **Compilation & Execution Procedure**:
  1. **Database Setup**: Execute `database_setup.sql` in MySQL.
     ```sql
     CREATE DATABASE IF NOT EXISTS studentdb;
     USE studentdb;
     CREATE TABLE IF NOT EXISTS students (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100) NOT NULL,
         age INT NOT NULL,
         course VARCHAR(100) NOT NULL
     );
     ```
  2. **Compile Application**:
     ```bash
     cd Ex_11_JavaFX_JDBC_CRUD
     javac --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" StudentManagementApp.java
     ```
  3. **Run Application**:
     ```bash
     java --module-path lib --add-modules javafx.controls,javafx.fxml -cp ".;lib/*" StudentManagementApp
     ```

---

## System Requirements

- **JDK Version**: Java Development Kit (JDK 17 or JDK 21 recommended).
- **Environment**: PowerShell, Bash, or Command Prompt.
- **Database (Ex 11)**: MySQL Server 8.0+ & MySQL Connector/J JDBC Driver.
- **GUI Framework (Ex 11)**: OpenJFX / JavaFX SDK 17+.

---

## Verification & Build Quality

All 11 experiments have been thoroughly verified, compiled, and tested for execution correctness across clean, isolated Java virtual machine environments.
