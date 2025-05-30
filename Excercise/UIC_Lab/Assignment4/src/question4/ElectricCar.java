package question4;

public class ElectricCar extends Car {

	private double chargeLevel;

	public ElectricCar(String brand){
		super(brand);
	}

	@Override
	public boolean start() {
		if (chargeLevel<=0) {
			System.out.println("Error: Empty battery!");
			return false;
		}
		setSpeed(80);
		chargeLevel -=0.1;
		System.out.println(getBrand()+" starts!");
		return true;
	}

	@Override
	public void getSupply(){
		this.chargeLevel = 1;
	}
	
	public static void testElectricCar() {
		// Create a new ElectricCar instance
		ElectricCar car = new ElectricCar("BYD");
		// Test the constructor
		System.out.println(car.getBrand() == "BYD");
		System.out.println(car.getSpeed() == 0);
		System.out.println(car.chargeLevel == 0);

		// Test getSupply() by recharging
		car.getSupply();
		System.out.println(car.chargeLevel == 1);

		// Test start() when battery is available
		car.start();
		System.out.println(car.getSpeed() == 80);
		System.out.println(car.chargeLevel == 0.9);

		// Test start() when battery is empty
		car.chargeLevel = 0;
		car.stop();
		car.start();
		System.out.println(car.chargeLevel == 0);
		System.out.println(car.getSpeed() == 0);
	}
}
