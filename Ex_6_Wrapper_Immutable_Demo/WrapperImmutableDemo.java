/*
 * =====================================================================
 * EX.NO: 6
 * TITLE: DEMONSTRATE THE IMMUTABLE NATURE OF WRAPPER CLASSES
 *        (INTEGER AND DOUBLE)
 * =====================================================================
 */

public class WrapperImmutableDemo {

    public static void main(String[] args) {

        // Integer Wrapper
        Integer i = Integer.valueOf(100);

        System.out.println("Before Modification");
        System.out.println("Integer Value : " + i);
        System.out.println("Hash Code : " +
                System.identityHashCode(i));

        // Modification
        i = i + 50;

        System.out.println("\nAfter Modification");
        System.out.println("Integer Value : " + i);
        System.out.println("Hash Code : " +
                System.identityHashCode(i));

        // Double Wrapper
        Double d = Double.valueOf(25.5);

        System.out.println("\nBefore Modification");
        System.out.println("Double Value : " + d);
        System.out.println("Hash Code : " +
                System.identityHashCode(d));

        // Modification
        d = d * 2;

        System.out.println("\nAfter Modification");
        System.out.println("Double Value : " + d);
        System.out.println("Hash Code : " +
                System.identityHashCode(d));
    }
}

/*
OUTPUT:

Before Modification
Integer Value : 100
Hash Code : <runtime-dependent>

After Modification
Integer Value : 150
Hash Code : <runtime-dependent>

Before Modification
Double Value : 25.5
Hash Code : <runtime-dependent>

After Modification
Double Value : 51.0
Hash Code : <runtime-dependent>
*/
