/*
 * =====================================================================
 * EX.NO: 3
 * TITLE: INHERITANCE PROGRAM FOR AN EMPLOYEE CLASS
 * =====================================================================
 */

class Vehicle {

    String vehicleNumber, model, manufacturer;
    double price;

    Vehicle(String number, String model, String manufacturer, double price) {
        this.vehicleNumber = number;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    void printBill(double taxRate, double insuranceRate) {

        double tax = price * taxRate / 100;
        double insurance = price * insuranceRate / 100;
        double total = price + tax + insurance;

        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Model : " + model);
        System.out.println("Manufacturer : " + manufacturer);
        System.out.println("Price : " + price);
        System.out.println("Road Tax : " + tax);
        System.out.println("Insurance : " + insurance);
        System.out.println("Total Cost : " + total);
        System.out.println();
    }
}

class Car extends Vehicle {

    Car(String number, String model, String manufacturer, double price) {
        super(number, model, manufacturer, price);
    }

    void calculateBill() {
        printBill(5, 3);
    }
}

class Bike extends Vehicle {

    Bike(String number, String model, String manufacturer, double price) {
        super(number, model, manufacturer, price);
    }

    void calculateBill() {
        printBill(10, 2);
    }
}

class Truck extends Vehicle {

    Truck(String number, String model, String manufacturer, double price) {
        super(number, model, manufacturer, price);
    }

    void calculateBill() {
        printBill(10, 4);
    }
}

public class VehicleDemo {

    public static void main(String[] args) {

        Car car = new Car("TN01AB1234", "Swift", "Maruti", 800000);
        Bike bike = new Bike("TN02CD5678", "R15", "Yamaha", 200000);
        Truck truck = new Truck("TN03EF9012", "Tata 407", "Tata", 1500000);

        System.out.println("CAR BILL");
        car.calculateBill();

        System.out.println("BIKE BILL");
        bike.calculateBill();

        System.out.println("TRUCK BILL");
        truck.calculateBill();
    }
}

/*
OUTPUT:

CAR BILL
Vehicle Number : TN01AB1234
Model : Swift
Manufacturer : Maruti
Price : 800000.0
Road Tax : 40000.0
Insurance : 24000.0
Total Cost : 864000.0

BIKE BILL
Vehicle Number : TN02CD5678
Model : R15
Manufacturer : Yamaha
Price : 200000.0
Road Tax : 20000.0
Insurance : 4000.0
Total Cost : 224000.0

TRUCK BILL
Vehicle Number : TN03EF9012
Model : Tata 407
Manufacturer : Tata
Price : 1500000.0
Road Tax : 150000.0
Insurance : 60000.0
Total Cost : 1710000.0
*/
