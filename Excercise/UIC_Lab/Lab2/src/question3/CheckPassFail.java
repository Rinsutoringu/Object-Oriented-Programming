package question3;

import java.util.Scanner;

public class CheckPassFail {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Read an integer from the user
        int num = input.nextInt();
        // Close the scanner
        input.close();
        if (num>=50) {System.out.println("PASS");}
        // Compare this snippet from src/question4/CheckOddEven.java:
        else {System.out.println("FAIL");}
        System.out.println("BYE!");
        // Compare this snippet from src/question4/CheckOddEven.java:
    }
}
