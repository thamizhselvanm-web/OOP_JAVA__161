import java.util.Arrays;
import java.util.Random;

class ArrayGenerator extends Thread {
    static int[] a = new int[5];

    public void run() {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }
        System.out.println("Original Array: " + Arrays.toString(a));
    }
}

class AscendingSort extends Thread {
    int[] a;

    AscendingSort(int[] a) {
        this.a = a;
    }

    public void run() {
        int[] b = a.clone();
        Arrays.sort(b);
        System.out.println("Ascending Order: " + Arrays.toString(b));
    }
}

class DescendingSort extends Thread {
    int[] a;

    DescendingSort(int[] a) {
        this.a = a;
    }

    public void run() {
        int[] b = a.clone();
        Arrays.sort(b);

        for (int i = 0; i < b.length / 2; i++) {
            int temp = b[i];
            b[i] = b[b.length - 1 - i];
            b[b.length - 1 - i] = temp;
        }

        System.out.println("Descending Order: " + Arrays.toString(b));
    }
}

public class ArrayThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayGenerator g = new ArrayGenerator();
        g.start();
        g.join();

        AscendingSort asc = new AscendingSort(ArrayGenerator.a);
        DescendingSort desc = new DescendingSort(ArrayGenerator.a);

        asc.start();
        desc.start();

        asc.join();
        desc.join();
    }
}
