# OOP Java Lab

Java laboratory programs covering core Object-Oriented Programming concepts.

## Project Structure

Each exercise remains in its original numbered directory.

```text
OOP_JAVA__161/
|-- README.md
|-- Ex_1_Telephone_Bill/
|-- Ex_2_Temperature_Converter/
|-- Ex_3_Vehicle_Inheritance/
|-- Ex_4_Abstract_Library_Member/
|-- Ex_5_ADT_Queue_Exception_Handling/
|-- Ex_6_Wrapper_Immutable_Demo/
|-- Ex_7_Multithreading/
|-- Ex_8_Inter_Thread_Communication/
|-- Ex_9_String_Operations_ArrayList/
`-- Ex_10_File_Handling_ListFiles/
```

## Experiments

| No. | Topic | Main file |
| --- | --- | --- |
| 1 | Classes and objects: telephone bill | `TelephoneBill.java` |
| 2 | Packages: temperature converter | `TemperatureMain.java`, `temperature/Converter.java` |
| 3 | Inheritance: vehicle hierarchy | `VehicleDemo.java` |
| 4 | Abstract classes: library member | `LibraryDemo.java` |
| 5 | ADT queue and exception handling | `Main.java` |
| 6 | Wrapper classes and immutability | `WrapperImmutableDemo.java` |
| 7 | Multithreading with arrays | `ArrayThreadDemo.java` |
| 8 | Inter-thread communication | `RailwayBooking.java` |
| 9 | String operations with `ArrayList` | `StringMenu.java` |
| 10 | File handling and listing files | `ListFiles.java` |

## Compile and Run

Open a terminal in the selected experiment directory, then compile and run its main class.

```bash
cd Ex_1_Telephone_Bill
javac TelephoneBill.java
java TelephoneBill
```

Experiment 2 contains a package and should be compiled from its experiment directory:

```bash
cd Ex_2_Temperature_Converter
javac temperature/Converter.java TemperatureMain.java
java TemperatureMain
```

The other experiments follow the same pattern. Replace the directory and class name as listed below:

```text
Ex_3_Vehicle_Inheritance            VehicleDemo
Ex_4_Abstract_Library_Member        LibraryDemo
Ex_5_ADT_Queue_Exception_Handling   Main
Ex_6_Wrapper_Immutable_Demo         WrapperImmutableDemo
Ex_7_Multithreading                  ArrayThreadDemo
Ex_8_Inter_Thread_Communication      RailwayBooking
Ex_9_String_Operations_ArrayList     StringMenu
Ex_10_File_Handling_ListFiles        File_Handling_ListFiles
```

## Notes

- Threaded programs may print output in a different order because of scheduling.
- Experiment 7 generates random values, so its array output changes each time.
- Experiment 6 uses `System.identityHashCode()`, so hash-code values are runtime-dependent.
- Experiment 2 must preserve the `temperature` package directory when compiling.

## Requirements

- Java Development Kit (JDK) 8 or later
- A terminal or Java-compatible IDE
