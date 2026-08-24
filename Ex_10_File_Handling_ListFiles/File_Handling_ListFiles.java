/*
 * =====================================================================
 * EX.NO: 10
 * TITLE: IMPLEMENTATION OF FILE HANDLING
 * =====================================================================
 *
 * AIM:
 * To write a Java program to implement file handling and display
 * all files present in a specified directory.
 */

import java.io.File;
import java.util.Scanner;

public class ListFiles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory path: ");
        String path = sc.nextLine();

        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {

            File[] files = directory.listFiles();

            System.out.println("\nFiles in the directory:");

            if (files != null) {
                for (File file : files) {

                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            }

        } else {
            System.out.println("Invalid directory path.");
        }

        sc.close();
    }
}

/*
 * =====================================================================
 * SAMPLE OUTPUT
 * =====================================================================
 *
 * Enter directory path: C:\Users\Documents
 *
 * Files in the directory:
 * file1.txt
 * file2.txt
 * program.java
 * notes.docx
 *
 * =====================================================================
 * OUTPUT EXPLANATION
 * =====================================================================
 *
 * 1. The program asks the user to enter a directory path.
 *
 * 2. A File object is created using the entered path.
 *
 * 3. exists() checks whether the specified path exists.
 *
 * 4. isDirectory() checks whether the specified path is a directory.
 *
 * 5. listFiles() retrieves the files and directories contained in
 *    the specified directory.
 *
 * 6. isFile() ensures that only files are displayed and directories
 *    are ignored.
 *
 * 7. getName() retrieves and displays the name of each file.
 *
 * 8. If the specified path is invalid or is not a directory, the
 *    program displays "Invalid directory path."
 *
 * =====================================================================
 * RESULT
 * =====================================================================
 *
 * Thus, the Java program for implementing file handling was
 * successfully executed, and the files present in the specified
 * directory were displayed.
 *
 * =====================================================================
 * END OF EXPERIMENT 10
 * =====================================================================
 */
