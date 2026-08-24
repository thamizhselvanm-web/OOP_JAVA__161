# OOP Java Lab

Java laboratory programs covering core Object-Oriented Programming concepts.

## Project Structure

All exercises are grouped under `experiments/` and use zero-padded directory names so they remain in order.

```text
OOP_JAVA__161/
|-- README.md
`-- experiments/
	|-- 01-telephone-bill/
	|-- 02-temperature-converter/
	|-- 03-vehicle-inheritance/
	|-- 04-abstract-library-member/
	|-- 05-adt-queue-exception-handling/
	|-- 06-wrapper-immutable-demo/
	|-- 07-multithreading/
	|-- 08-inter-thread-communication/
	|-- 09-string-operations-arraylist/
	`-- 10-file-handling-list-files/
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
cd experiments/01-telephone-bill
javac TelephoneBill.java
java TelephoneBill
```

Experiment 2 contains a package and should be compiled from its experiment directory:

```bash
cd experiments/02-temperature-converter
javac temperature/Converter.java TemperatureMain.java
java TemperatureMain
```

The other experiments follow the same pattern. Replace the directory and class name as listed below:

```text
03-vehicle-inheritance             VehicleDemo
04-abstract-library-member         LibraryDemo
05-adt-queue-exception-handling    Main
06-wrapper-immutable-demo          WrapperImmutableDemo
07-multithreading                   ArrayThreadDemo
08-inter-thread-communication       RailwayBooking
09-string-operations-arraylist      StringMenu
10-file-handling-list-files         ListFiles
```

## Notes

- Threaded programs may print output in a different order because of scheduling.
- Experiment 7 generates random values, so its array output changes each time.
- Experiment 6 uses `System.identityHashCode()`, so hash-code values are runtime-dependent.
- Experiment 2 must preserve the `temperature` package directory when compiling.

## Requirements

- Java Development Kit (JDK) 8 or later
- A terminal or Java-compatible IDE
