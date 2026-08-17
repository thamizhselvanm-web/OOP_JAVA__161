public class TelephoneBill {
    int customerNo, previousMinutes, currentMinutes;
    String customerName, type;

    TelephoneBill(int no, String name, int prev, int curr, String type) {
        customerNo = no;
        customerName = name;
        previousMinutes = prev;
        currentMinutes = curr;
        this.type = type;
    }

    double calculateBill() {
        int minutes = currentMinutes - previousMinutes;
        double bill;

        if (type.equalsIgnoreCase("prepaid")) {
            if (minutes <= 100)
                bill = minutes * 1;
            else if (minutes <= 200)
                bill = 100 + (minutes - 100) * 1.5;
            else
                bill = 100 + 100 * 1.5 + (minutes - 200) * 2;
        } else {
            if (minutes <= 100)
                bill = minutes * 0.75;
            else if (minutes <= 200)
                bill = 100 * 0.75 + (minutes - 100) * 1.25;
            else
                bill = 100 * 0.75 + 100 * 1.25 + (minutes - 200) * 1.75;
        }
        return bill;
    }

    public static void main(String[] args) {
        TelephoneBill t = new TelephoneBill(201, "Anu", 300, 550, "postpaid");
        System.out.println("Customer No: " + t.customerNo);
        System.out.println("Customer Name: " + t.customerName);
        System.out.println("Minutes: " + (t.currentMinutes - t.previousMinutes));
        System.out.println("Bill Amount: Rs." + t.calculateBill());
    }
}
