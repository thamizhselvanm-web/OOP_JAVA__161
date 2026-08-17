/*
 * =====================================================================
 * EX.NO: 4
 * TITLE: CREATE AN ABSTRACT CLASS - LIBRARY MEMBER
 * =====================================================================
 */

abstract class LibraryMember {

    int memberId;
    String name, email, phone;

    LibraryMember(int id, String name, String email, String phone) {
        memberId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    abstract void generateSummary();
}

class StudentMember extends LibraryMember {

    StudentMember(int id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    void generateSummary() {
        System.out.println("Member Type       : Student");
        System.out.println("Member ID         : " + memberId);
        System.out.println("Name              : " + name);
        System.out.println("Email             : " + email);
        System.out.println("Phone             : " + phone);
        System.out.println("Borrowing Limit   : 5 books");
        System.out.println("Penalty per Day   : Rs.2");
        System.out.println("Annual Membership : Rs.200");
    }
}

class FacultyMember extends LibraryMember {

    FacultyMember(int id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    void generateSummary() {
        System.out.println("Member Type       : Faculty");
        System.out.println("Member ID         : " + memberId);
        System.out.println("Name              : " + name);
        System.out.println("Email             : " + email);
        System.out.println("Phone             : " + phone);
        System.out.println("Borrowing Limit   : 10 books");
        System.out.println("Penalty per Day   : Rs.3");
        System.out.println("Annual Membership : Rs.500");
    }
}

class ExternalMember extends LibraryMember {

    ExternalMember(int id, String name, String email, String phone) {
        super(id, name, email, phone);
    }

    void generateSummary() {
        System.out.println("Member Type       : External");
        System.out.println("Member ID         : " + memberId);
        System.out.println("Name              : " + name);
        System.out.println("Email             : " + email);
        System.out.println("Phone             : " + phone);
        System.out.println("Borrowing Limit   : 3 books");
        System.out.println("Penalty per Day   : Rs.5");
        System.out.println("Annual Membership : Rs.1000");
    }
}

public class LibraryDemo {

    public static void main(String[] args) {

        StudentMember s = new StudentMember(
                101, "Arun", "arun@mail.com", "9876543210"
        );

        FacultyMember f = new FacultyMember(
                201, "Kumar", "kumar@mail.com", "9876501234"
        );

        ExternalMember e = new ExternalMember(
                301, "Ravi", "ravi@mail.com", "9876512345"
        );

        s.generateSummary();
        System.out.println();

        f.generateSummary();
        System.out.println();

        e.generateSummary();
    }
}

/*
OUTPUT:

Member Type       : Student
Member ID         : 101
Name              : Arun
Email             : arun@mail.com
Phone             : 9876543210
Borrowing Limit   : 5 books
Penalty per Day   : Rs.2
Annual Membership : Rs.200

Member Type       : Faculty
Member ID         : 201
Name              : Kumar
Email             : kumar@mail.com
Phone             : 9876501234
Borrowing Limit   : 10 books
Penalty per Day   : Rs.3
Annual Membership : Rs.500

Member Type       : External
Member ID         : 301
Name              : Ravi
Email             : ravi@mail.com
Phone             : 9876512345
Borrowing Limit   : 3 books
Penalty per Day   : Rs.5
Annual Membership : Rs.1000
*/
