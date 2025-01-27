package clock;

public class Display {
    private int value = 0;
    private int limit = 0;
    
    /**
     * init function
     * @param limit
     */
    public Display(int limit){
        this.limit = limit;
    }
    
    /**
     * increase the value of the display
     */
    public void increase(){
        value++;
        if (value == limit) value = 0;
    }

    /**
     * get object value
     * @return clock value
     */
    public int getValue(){
        return value;
    }

    /**
     * set value
     * @param new_value new value to change
     * @return A boolean represent success or fail.
     */
    public boolean setValue(int new_value){
        if (new_value > limit) {
            return false;
        }
        else if (new_value == limit) {
            value = 0;
            return true;
        }
        else if (new_value < limit) {
            value = new_value;
            return true;
        }
        else return false;
    }
    
    public static void main(String[] args) {
        Display d = new Display(24);
        while (true) {
            d.increase();
            System.out.println(d.getValue());
        }
    }

}
