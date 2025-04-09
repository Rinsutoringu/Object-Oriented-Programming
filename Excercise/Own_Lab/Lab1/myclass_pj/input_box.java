package myclass;

import java.util.Scanner;

public class input_box {
    public String scan;
    
    public void getstring() {
        System.out.println("Enter a string: ");
        Scanner scanner = new Scanner(System.in);
        scan = scanner.nextLine();
        scanner.close();
    }

    public void print() {
        System.out.println("You entered: " + scan);
    }

}

class Innerinput_box {
    public static void main(String[] args){
        input_box box = new input_box();
        box.getstring();
        box.print();
        System.out.printf("%s is your input", box.scan);

    }

    
}