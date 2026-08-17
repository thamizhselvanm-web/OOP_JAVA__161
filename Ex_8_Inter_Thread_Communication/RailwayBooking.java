class Railway {
    int seats = 2;

    synchronized void bookTicket(String name) {
        while (seats == 0) {
            try {
                System.out.println(name + " waiting for a seat.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        seats--;
        System.out.println(name + " booked a ticket.");
        System.out.println("Available seats: " + seats);
    }

    synchronized void cancelTicket(String name) {
        seats++;
        System.out.println(name + " cancelled a ticket.");
        System.out.println("Available seats: " + seats);
        notifyAll();
    }
}

class Booking extends Thread {
    Railway railway;
    String name;

    Booking(Railway railway, String name) {
        this.railway = railway;
        this.name = name;
    }

    public void run() {
        railway.bookTicket(name);
    }
}

class Cancellation extends Thread {
    Railway railway;
    String name;

    Cancellation(Railway railway, String name) {
        this.railway = railway;
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        railway.cancelTicket(name);
    }
}

public class RailwayBooking {
    public static void main(String[] args) {
        Railway railway = new Railway();

        Booking b1 = new Booking(railway, "Passenger 1");
        Booking b2 = new Booking(railway, "Passenger 2");
        Booking b3 = new Booking(railway, "Passenger 3");
        Cancellation c1 = new Cancellation(railway, "Passenger 1");

        b1.start();
        b2.start();
        b3.start();
        c1.start();
    }
}
