package question1;

import java.util.Scanner;

public class InchesToMeters{
    public static void main(String[] args) {
        // ask for inch number
        System.out.print("input a value for inches: ");
        Scanner input = new Scanner(System.in);
        // get inch number
        double inch = input.nextDouble();
        input.close();
        System.out.printf("%.1f inches is %.1f meters", inch, inch * 0.0254 );
        // convert inch to meters
    }
    
    
}