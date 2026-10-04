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
├── Ex_11_JavaFX_JDBC_CRUD/
│   ├── database_setup.sql
│   ├── StudentManagementApp.java
│   └── lib/
└── Ex_12_MINI_PROJECT/
    ├── CineBook_Implementation_Plan.md
    └── CineBook/
        ├── pom.xml
        ├── database_setup.sql
        ├── cinebook.db
        ├── README.md
        ├── resources/
        │   └── styles/
        │       └── application.css
        └── src/
            ├── application/
            │   ├── AppRouter.java
            │   └── Main.java
            ├── controller/
            │   ├── AdminController.java
            │   ├── BookingController.java
            │   ├── LoginController.java
            │   └── MovieController.java
            ├── dao/
            │   ├── BookingDAO.java
            │   ├── DatabaseConnection.java
            │   ├── MovieDAO.java
            │   ├── ShowDAO.java
            │   ├── TheatreDAO.java
            │   └── UserDAO.java
            ├── dsa/
            │   ├── BookingHashMap.java
            │   ├── BookingQueue.java
            │   ├── BookingSearch.java
            │   ├── CancellationStack.java
            │   └── UndoStack.java
            ├── model/
            │   ├── Admin.java
            │   ├── Bookable.java
            │   ├── Booking.java
            │   ├── CashPayment.java
            │   ├── Customer.java
            │   ├── Movie.java
            │   ├── OnlinePayment.java
            │   ├── Payable.java
            │   ├── Payment.java
            │   ├── Seat.java
            │   ├── Show.java
            │   ├── Theatre.java
            │   └── User.java
            └── service/
                ├── BookingService.java
                └── PaymentService.java
```

---

## Experiments Index

| Exp No. | Topic | Core Concepts | Primary Class / Files |
| :--- | :--- | :--- | :--- |
| [**Ex 1**](#ex1) | [Telephone Bill Calculator](#ex1) | Classes, Objects, Constructors, Tiered Billing Logic | `TelephoneBill.java` |
| [**Ex 2**](#ex2) | [Temperature Converter Utility](#ex2) | Java Packages, Static Utility Methods, Unit Conversion | `Converter.java`, `TemperatureMain.java` |
| [**Ex 3**](#ex3) | [Vehicle Tax & Insurance Hierarchy](#ex3) | Inheritance (`extends`), Method Overriding, Subtyping | `VehicleDemo.java` |
| [**Ex 4**](#ex4) | [Library Membership System](#ex4) | Abstract Classes, Polymorphism, Mandatory Method Contract | `LibraryDemo.java` |
| [**Ex 5**](#ex5) | [Circular Queue ADT with Exception Handling](#ex5) | Interfaces (`implements`), Fixed Array Queue, `try-catch` | `Main.java` |
| [**Ex 6**](#ex6) | [Wrapper Classes & Immutability](#ex6) | Object Identity, Unboxing/Reboxing, `identityHashCode` | `WrapperImmutableDemo.java` |
| [**Ex 7**](#ex7) | [Multithreaded Array Processing](#ex7) | Thread Creation (`extends Thread`), `start()`, `join()` | `ArrayThreadDemo.java` |
| [**Ex 8**](#ex8) | [Inter-Thread Railway Booking System](#ex8) | Synchronized Monitors, `wait()`, `notifyAll()`, Thread States | `RailwayBooking.java` |
| [**Ex 9**](#ex9) | [Interactive String Operations](#ex9) | Dynamic Collections (`ArrayList`), Filtering, Buffer Flushing | `StringMenu.java` |
| [**Ex 10**](#ex10) | [Directory File Listing Utility](#ex10) | Java File API (`java.io.File`), `isDirectory()`, `isFile()` | `File_Handling_ListFiles.java` |
| [**Ex 11**](#ex11) | [Student Management CRUD Application](#ex11) | JavaFX GUI, JDBC Database Connectivity, MySQL CRUD | `StudentManagementApp.java`, `database_setup.sql` |
| [**Ex 12**](#ex12) | [CineBook — Movie Ticket Booking System](#ex12) | JavaFX Desktop MVC, OOP Abstractions, DSA (Queue, UndoStack, BinarySearch, HashMap), SQLite JDBC | `Main.java`, `AppRouter.java`, `cinebook.db` |

---

## Detailed Experiment Specifications

<a id="ex1"></a>
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

<a id="ex2"></a>
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

<a id="ex3"></a>
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

<a id="ex4"></a>
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

<a id="ex5"></a>
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

<a id="ex6"></a>
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

<a id="ex7"></a>
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

<a id="ex8"></a>
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

<a id="ex9"></a>
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

<a id="ex10"></a>
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

<a id="ex11"></a>
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

  3. **Or Compile & Run Manually**:
     ```powershell
     cd Ex_11_JavaFX_JDBC_CRUD
     javac --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" StudentManagementApp.java
     java --module-path lib --add-modules javafx.controls,javafx.fxml -cp ".;lib/*" StudentManagementApp Thamizh1.
     ```

---

<a id="ex12"></a>
### Experiment 12 / Mini Project: CineBook — Movie Ticket Booking System
- **Directory**: `Ex_12_MINI_PROJECT/CineBook/`
- **Main Class**: `application.Main`
- **Architecture**: Multi-layered Model-View-Controller (MVC) + Data Access Object (DAO) + Business Service Layer + Data Structures and Algorithms (DSA) Engine.
- **Key Concepts Demonstrated**:
  - **Object-Oriented Programming (OOP)**:
    - *Encapsulation*: Domain entities (`User`, `Movie`, `Show`, `Seat`, `Booking`, `Payment`) with strict private fields, validation, and getters/setters.
    - *Inheritance*: Base abstract class `User` extended by `Customer` and `Admin`; abstract class `Payment` extended by `OnlinePayment` and `CashPayment`.
    - *Polymorphism & Interfaces*: Contract enforcement via `Bookable` and `Payable` interfaces with dynamic method dispatch during runtime transaction handling.
    - *Abstraction*: Clear separation between database connectivity, data access objects, business rules, controllers, and JavaFX presentation.
  - **Data Structures & Algorithms (DSA)**:
    - *Binary Search (`BookingSearch.java`)*: $O(\log N)$ logarithmic search over alphabetically sorted movie collections for lightning-fast search bar lookups.
    - *Undo Stack (`UndoStack.java`)*: LIFO Stack (`push`/`pop`) enabling dynamic seat selection rollback directly from the seat grid.
    - *Booking Queue (`BookingQueue.java`)*: FIFO Queue (`offer`/`poll`) data structure ensuring sequential processing of ticket reservation requests.
    - *Cancellation Stack (`CancellationStack.java`)*: LIFO Stack recording canceled bookings for audit and state rollback.
    - *Hash Map Lookup (`BookingHashMap.java`)*: Key-value hash indexing for $O(1)$ fast retrieval of active customer bookings.
  - **GUI & Database**:
    - *JavaFX 21*: Responsive, Figma-inspired Crimson Dark UI with interactive seat grid matrices, movie cards, and modal dialogs.
    - *SQLite JDBC*: Embedded relational database (`cinebook.db`) with zero external configuration requirements, seeded automatically on startup.

#### Updated Project Files & Component Explanations

| Category | File | Short Explanation |
| :--- | :--- | :--- |
| **Application Layer** | [`AppRouter.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/application/AppRouter.java) | Central navigation manager controlling stage routing and smooth transitions across views (Login, Customer, Admin). |
| | [`Main.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/application/Main.java) | Primary JavaFX application entry point launching the desktop window, loading stylesheets, and displaying views. |
| **Domain Models & OOP** | [`User.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/User.java) | Abstract base entity encapsulating common user identity fields (`id`, `name`, `email`, `password`, `role`). |
| | [`Customer.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Customer.java) | Domain entity extending `User` representing cinema patrons browsing movies and reserving seats. |
| | [`Admin.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Admin.java) | Domain entity extending `User` granting administrative privileges for movie, showtime, and booking management. |
| | [`Movie.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Movie.java) | Entity modeling movie records with title, genre, runtime duration, censorship rating, and poster color codes. |
| | [`Show.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Show.java) | Entity linking movies to specific screening schedules, theatre locations, screen numbers, and ticket tariffs. |
| | [`Seat.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Seat.java) | Models an individual seat node within the 5x8 auditorium matrix with row, column, seat code, and availability state. |
| | [`Booking.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Booking.java) | Entity tracking confirmed ticket reservations, allocated seats, payment amounts, timestamps, and active/canceled status. |
| | [`Theatre.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Theatre.java) | Models cinema premises, auditorium screen configurations, and seating layouts. |
| | [`Bookable.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Bookable.java) | Core interface defining the contractual behaviors for reservable resources (`book()`, `cancel()`, `isAvailable()`). |
| | [`Payable.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Payable.java) | Core interface specifying payment processing contracts (`processPayment()`, `getPaymentStatus()`). |
| | [`Payment.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/Payment.java) | Abstract base transaction class implementing `Payable` with shared transaction attributes. |
| | [`OnlinePayment.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/OnlinePayment.java) | Polymorphic payment subclass handling UPI, Credit Card, and Net Banking digital transactions. |
| | [`CashPayment.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/model/CashPayment.java) | Polymorphic payment subclass managing over-the-counter cash settlements and receipt verification. |
| **DSA Implementations** | [`BookingSearch.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dsa/BookingSearch.java) | Implements Binary Search ($O(\log N)$) across sorted movie titles for real-time search bar filtering. |
| | [`UndoStack.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dsa/UndoStack.java) | Custom LIFO Stack supporting interactive seat selection undo operations in the booking interface. |
| | [`BookingQueue.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dsa/BookingQueue.java) | Custom FIFO Queue ensuring synchronized, order-preserved booking request handling. |
| | [`CancellationStack.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dsa/CancellationStack.java) | Custom LIFO Stack logging recently canceled bookings for administrative audit and rollback tracking. |
| | [`BookingHashMap.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dsa/BookingHashMap.java) | Custom hash table structure providing $O(1)$ fast lookups for active user bookings. |
| **Data Access Objects** | [`DatabaseConnection.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/DatabaseConnection.java) | Singleton SQLite JDBC connection provider featuring automated table schema execution and default dataset seeding. |
| | [`UserDAO.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/UserDAO.java) | Executes user authentication queries, credential verification, and customer registration inserts. |
| | [`MovieDAO.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/MovieDAO.java) | Data access layer for movie records (insertion, title lookup, update, deletion, total count). |
| | [`ShowDAO.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/ShowDAO.java) | Manages showtime scheduling persistence, screen assignments, and screening queries by movie ID. |
| | [`TheatreDAO.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/TheatreDAO.java) | Queries auditorium specifications, screen numbers, and seating capacities. |
| | [`BookingDAO.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/dao/BookingDAO.java) | Manages booking persistence, seat reservation tracking, ticket cancellation updates, and revenue summation. |
| **Service Layer** | [`BookingService.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/service/BookingService.java) | Validates booking prerequisites, prevents double booking, and handles transaction completion. |
| | [`PaymentService.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/service/PaymentService.java) | Payment gateway coordinator dispatching to cash or digital payment handlers. |
| **Controllers** | [`LoginController.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/controller/LoginController.java) | Validates user credentials, handles registration form submissions, and redirects based on user role. |
| | [`MovieController.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/controller/MovieController.java) | Coordinates movie browsing, card generation, and real-time binary search querying. |
| | [`BookingController.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/controller/BookingController.java) | Handles seat grid matrix clicks, undo actions, payment selection, ticket receipt modal, and cancellation. |
| | [`AdminController.java`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/src/controller/AdminController.java) | Powers the admin control centre: analytics metric cards, movie CRUD, showtime manager, and audit log. |
| **Configuration & Assets** | [`pom.xml`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/pom.xml) | Maven build descriptor specifying Java 21, JavaFX 21 controls, and SQLite JDBC driver dependencies. |
| | [`database_setup.sql`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/database_setup.sql) | DDL SQL schema defining tables (`users`, `movies`, `shows`, `bookings`, `payments`, `theatres`). |
| | [`cinebook.db`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/cinebook.db) | Embedded SQLite database file pre-populated with movies, showtimes, seats, and test accounts. |
| | [`application.css`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook/resources/styles/application.css) | Custom Crimson Dark JavaFX CSS styling with glassmorphism, accent cards, and responsive buttons. |
| | [`CineBook_Implementation_Plan.md`](file:///d:/OOP_LAB/OOP_JAVA__161/Ex_12_MINI_PROJECT/CineBook_Implementation_Plan.md) | Exhaustive implementation blueprint detailing system architecture, data flow, and specifications. |

#### Compilation & Execution Procedure

1. **Navigate to Project Directory**:
   ```powershell
   cd Ex_12_MINI_PROJECT\CineBook
   ```

2. **Launch Application via Maven**:
   ```powershell
   mvn clean javafx:run
   ```
   *(SQLite database `cinebook.db` is initialized and pre-seeded automatically on first startup)*

3. **Pre-configured Demo Credentials**:
   | Role | Email | Password | Access Scope |
   | :--- | :--- | :--- | :--- |
   | **Customer** | `user@cinebook.com` | `user123` | Movie Browsing, Binary Search, Interactive Seat Booking, Undo Stack, E-Ticket, My Bookings, Cancellation |
   | **Admin** | `admin@cinebook.com` | `admin123` | Real-time Analytics Cards (Movies, Shows, Bookings, Revenue), Movie CRUD, Showtime Management, Audit Logs |

---

## System Requirements

- **JDK Version**: Java Development Kit (JDK 21 recommended; JDK 17+ compatible).
- **Build Tool**: Apache Maven 3.8+.
- **Environment**: Windows PowerShell, Command Prompt, or Linux/macOS Bash.
- **Database**: Embedded SQLite (self-contained, zero configuration required; pre-seeded in `cinebook.db`) & MySQL Server 8.0+ (for Ex 11).
- **GUI Framework**: OpenJFX / JavaFX SDK 21 (managed seamlessly via Maven).

---

## Verification & Build Quality

All 12 experiments—spanning foundational OOP constructs, multithreading, inter-thread synchronization monitors, dynamic collections, and the full-featured **CineBook JavaFX + SQLite Mini Project**—have been thoroughly verified, compiled, and tested for execution correctness across clean, isolated Java runtime environments.

