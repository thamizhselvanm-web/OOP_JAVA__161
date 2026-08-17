# OOP JAVA LAB – Experiments 1–9

Professional Java laboratory programs for Object-Oriented Programming.

## Experiments

| No. | Experiment | File |
|---|---|---|
| 1 | Telephone Bill using Class and Object | `TelephoneBill.java` |
| 2 | Package for Temperature Converter | `Converter.java`, `TemperatureMain.java` |
| 3 | Inheritance Program for Vehicle Class | `VehicleDemo.java` |
| 4 | Abstract Class – Library Member | `LibraryDemo.java` |
| 5 | ADT Queue using Exception Handling | `Main.java` |
| 6 | Immutable Nature of Wrapper Classes | `WrapperImmutableDemo.java` |
| 7 | Multi-Thread Program | `ArrayThreadDemo.java` |
| 8 | Inter-Thread Communication | `RailwayBooking.java` |
| 9 | String Operations using ArrayList | `StringMenu.java` |

## Notes

- Each program contains a professional header and sample output in comments.
- Experiment 2 uses a Java package and must be compiled with the package directory structure.
- Experiments involving threads may produce output in a different order because of thread scheduling.
- Experiment 7 generates random values, so its array output changes between executions.
- Experiment 6 uses `System.identityHashCode()`, so hash-code values are runtime-dependent.

## Compile and Run

For normal programs:

```bash
javac TelephoneBill.java
java TelephoneBill
```

For Experiment 2:

```bash
javac temperature/Converter.java TemperatureMain.java
java TemperatureMain
```

For Experiment 5:

```bash
javac Main.java
java Main
```

For Experiment 6:

```bash
javac WrapperImmutableDemo.java
java WrapperImmutableDemo
```

For Experiment 7:

```bash
javac ArrayThreadDemo.java
java ArrayThreadDemo
```

For Experiment 8:

```bash
javac RailwayBooking.java
java RailwayBooking
```
## Experiment 9 – String Operations Using ArrayList

Experiment 9 demonstrates string manipulation using Java's `ArrayList<String>`.

### Operations Implemented

- Append a string
- Insert a string at a specified index
- Search for a string
- Display strings beginning with a specified letter
- Display all strings
- Exit the program

### File

```text
StringMenu.java
---
Academic Laboratory Repository | Java | Object-Oriented Programming
