package question9;

public class Airplane implements Flyer {

	private String name;
	private boolean canFly;
	private boolean isDangerous;

	public Airplane(String name){
		this.name = name;
		this.canFly = true;
		this.isDangerous = false;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean canFly() {
		return this.canFly;
	}

	@Override
	public boolean isDangerous() {
		return this.isDangerous;
	}

	public static void testAirplane() {
		Airplane a = new Airplane("Aircar");
		System.out.println(a.getName() == "Aircar");
		System.out.println(a.canFly() == true);
		System.out.println(a.isDangerous() == false);
	}
}