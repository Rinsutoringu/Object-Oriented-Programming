package question1;

public class Code {

    private boolean canRun;
    private boolean canCompile;
    private int lines;

    public Code() {
        this.canRun = false;
        this.canCompile = false;
        this.lines = 0;
    }

    public Code(boolean canCompile, boolean canRun, int lines) {
        this.canRun = canRun;
        this.canCompile = canCompile;
        this.lines = lines;
    }

    // Can compile
    public boolean compile() {
        return this.canCompile;
    }

    // Can work
    public boolean run() {
        return this.canRun;
    }

    // Debug
    public void debug() {
        this.canRun = true;
        this.canCompile = true;
    }

    // Code the code
    public void coding(int lines) {
        this.lines = lines;
    }

    // Count the lines
    public int countLines() {
        return this.lines;
    }

    // Tests
    public static void testCode() {
        // Test for the first constructor,the default values both should be false.
        Code code1 = new Code();
        System.out.println(code1.compile() == false);
        System.out.println(code1.run() == false);
        
        // Test for the second constructor,set the values
        Code code2 = new Code(true, false, 100);
        System.out.println(code2.compile() == true);
        System.out.println(code2.run() == false);
        System.out.println(code2.countLines() == 100);

        // Test Debug.
        code2.debug();

        // Then code should can both compile and run.
        System.out.println(code2.compile() == true);
        System.out.println(code2.run() == true);
        
        // Test Coding & countLines
        code2.coding(15);
        System.out.println(code2.countLines() == 15);
    }
}