package question3;

public abstract class Car implements Movable {
    private String brand;
    private double speed;

    public Car(String brand){
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public abstract void getSupply();

    @Override
    public void stop() {
        this.speed = 0;
    }
}
