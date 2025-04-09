package question4;

import java.util.Scanner;

public class CheckOddEven {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Read an integer from the user
        int number = input.nextInt();
        input.close();
        // Check if the number is odd or even
        if (number%2 != 0) {System.out.println("Odd number");}
        else {System.out.println("Even number");}
        // Print BYE!
        System.out.println("BYE!");
    }
}
