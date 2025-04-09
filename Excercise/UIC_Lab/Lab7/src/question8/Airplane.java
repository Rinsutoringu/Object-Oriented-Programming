package question8;

public class Airplane implements Flyer {

	private String name;
	private boolean canFly;

	public Airplane(String name){
		this.name = name;
		this.canFly = true;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean canFly() {
		return this.canFly;
	}

	public static void testAirplane() {
		Airplane a = new Airplane("Aircar");
		System.out.println(a.getName() == "Aircar");
		System.out.println(a.canFly() == true);
	}
}