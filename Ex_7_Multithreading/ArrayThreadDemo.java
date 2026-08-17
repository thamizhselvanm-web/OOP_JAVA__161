/*
 * =====================================================================
 * EX.NO: 7
 * TITLE: IMPLEMENTATION OF A MULTI-THREAD PROGRAM
 * =====================================================================
 */

import java.util.Arrays;
import java.util.Random;

class ArrayGenerator extends Thread {

    static int[] a = new int[5];

    public void run() {

        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }

        System.out.println("Original Array: " +
                Arrays.toString(a));
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

        System.out.println("Ascending Order: " +
                Arrays.toString(b));
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

        System.out.println("Descending Order: " +
                Arrays.toString(b));
    }
}

public class ArrayThreadDemo {

    public static void main(String[] args)
            throws InterruptedException {

        ArrayGenerator g = new ArrayGenerator();

        g.start();
        g.join();

        AscendingSort asc =
                new AscendingSort(ArrayGenerator.a);

        DescendingSort desc =
                new DescendingSort(ArrayGenerator.a);

        asc.start();
        desc.start();
    }
}

/*
SAMPLE OUTPUT:

Original Array: [42, 17, 65, 8, 31]
Ascending Order: [8, 17, 31, 42, 65]
Descending Order: [65, 42, 31, 17, 8]

NOTE:
The generated array values are random, so the output values may
differ each time the program is executed.
*/
