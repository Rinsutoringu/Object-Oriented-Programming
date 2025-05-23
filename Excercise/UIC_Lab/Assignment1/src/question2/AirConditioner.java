// Author: RinChord
// Date: 2025/3/6
// Description: Student class for question 2

package question2;

public class AirConditioner {

    private boolean power;
    private int mode;
    private int temperature;

    public AirConditioner() {
        // constructor
        power = false;
        mode = 0;
        // 0 = cool, 1 = dry, 2 = fans
        temperature = 16;
    }

    public void turnOn() {
        power = true;
    }

    public void turnOff() {
        power = false;
    }

    public boolean getPower() {
        return power;
    }

    public void setMode(int mode) {
        
        if (power == false){
            System.out.println("Please turn on the AC first.");
            return;
        }
        // 0 = cool, 1 = dry, 2 = fans
        if (mode < 0 || mode > 2){
            System.out.println("Invalid mode.");
            return;
        }
        this.mode = mode;
        return;

    }

    public int getMode() {
        return mode;
    }

    
    public void setTemperature(int temperature) {
        if (power == false){
            // if the power is off, print the following message and return
            System.out.println("Please turn on the AC first.");
            return;
        }
        if (mode == 2){
            // if the mode is cool, the temperature can be set between 16 and 30
            System.out.println("Cannot set the temperature in the dry mode.");
            return;
        }
        if (temperature < 16 || temperature > 30){
            // if the temperature is not within the range, print the following message and return
            System.out.println("Invalid temperature.");
            return;
        }
        this.temperature = temperature;
        return;
    }

    public int getTemperature() {
        // return the temperature
        return temperature;
    }
        
    
    // testAirConditioner is a public static method that tests all the code in the
    // AirConditioner class.
    public static void testAirConditioner() {
        AirConditioner ac = new AirConditioner();
        // test the constructor
        System.out.println(ac.getPower() == false);
        System.out.println(ac.getMode() == 0);
        System.out.println(ac.getTemperature() == 16);
        // test the power functions
        ac.turnOn();
        System.out.println(ac.getPower() == true);
        ac.turnOff();
        System.out.println(ac.getPower() == false);
        // test the mode functions
        ac.setMode(1);
        System.out.println(ac.getMode() == 0);
        ac.turnOn();
        ac.setMode(5);
        System.out.println(ac.getMode() == 0);
        ac.setMode(2);
        System.out.println(ac.getMode() == 2);
        // test the temperature functions
        ac.turnOff();
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 16);
        ac.turnOn();
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 16);
        ac.setMode(1);
        ac.setTemperature(31);
        System.out.println(ac.getTemperature() == 16);
        ac.setTemperature(20);
        System.out.println(ac.getTemperature() == 20);
    }
}
