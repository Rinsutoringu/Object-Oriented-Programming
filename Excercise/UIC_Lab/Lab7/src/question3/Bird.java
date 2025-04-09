package question3;

// The class Bird derives from the class Animal.
// The class must be abstract because it does not have any code for
// the canFly method (see below).
public abstract class Bird extends Animal {
	
	private int legs;
	private boolean canFly;
	private int numOfEggs;
	public Bird(String name, int numOfEggs)
	{
		super(name);
		this.legs = 2;
		this.numOfEggs = numOfEggs;
	}


	public static void testBird() {
		// Bird b = new Bird("Twitter", 3); // This does not work!
	}
}