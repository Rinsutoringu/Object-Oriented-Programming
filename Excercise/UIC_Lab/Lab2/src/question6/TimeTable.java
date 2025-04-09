package question6;

public class TimeTable {
    public static void main(String[] args) {
        // Print the multiplication table
        for (int i = 1; i <= 9; i++) {
            // Print the row
            for (int j = 1; j <= 9; j++) {
                // Print the product
                System.out.printf("%d ", i*j);
            }
            // Print a space
            System.out.println();
        }
    }  
}
