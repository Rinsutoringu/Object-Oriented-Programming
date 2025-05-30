package question3;

public class GasCar extends Car {

	private double gasLevel;
	public GasCar(String brand){
		super(brand);
	}
	@Override
	public boolean start() {
		if (gasLevel <= 0) {
			System.out.println("“Error: No gas!");
			return false;
		}
		setSpeed(60);
		gasLevel-=0.2;
		System.out.println(getBrand()+" starts!");
		return true;
	}
	@Override
	public void getSupply() {
		gasLevel = 1;
	}
	
	public static void testGasCar() {
		// Create a new GasCar instance
		GasCar car = new GasCar("BMW");
		// Test the constructor
		System.out.println(car.getBrand() == "BMW");
		System.out.println(car.getSpeed() == 0);
		System.out.println(car.gasLevel == 0);

		// Test getSupply() by refueling
		car.getSupply();
		System.out.println(car.gasLevel == 1);

		// Test start() when gas is available
		car.start();
		System.out.println(car.getSpeed() == 60);
		System.out.println(car.gasLevel == 0.8);

		// Test start() when gas is empty
		car.gasLevel = 0;
		car.stop();
		car.start();
		System.out.println(car.gasLevel == 0);
		System.out.println(car.getSpeed() == 0);
	}
}
