package question2;

import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        // Read an integer from the user
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        // Close the scanner
        input.close();
        // Calculate the sum of digits
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            // Remove the last digit
            num /= 10;
        }
        
        System.out.println("Sum of digits: " + sum);
    }
}
