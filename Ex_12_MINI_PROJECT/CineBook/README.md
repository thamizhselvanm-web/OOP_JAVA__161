# CineBook

CineBook is a Java 21 desktop movie ticket booking system built with JavaFX, JDBC, and SQLite. It demonstrates inheritance (`User`, `Customer`, and `Admin`), DAO/service separation, a FIFO booking queue, an undo stack for seat selection, and binary search for movie titles.

## Run

Install Java 21 and Maven, then from this folder run:

```text
mvn clean javafx:run
```

The SQLite database `cinebook.db` is created and seeded on first launch.

Demo accounts:

| Role | Email | Password |
| --- | --- | --- |
| Customer | user@cinebook.com | user123 |
| Admin | admin@cinebook.com | admin123 |

Customers can search titles, choose a show, select or undo seats, simulate a UPI/Card/Cash payment, and review booking history. Administrators can add and delete catalogue titles.