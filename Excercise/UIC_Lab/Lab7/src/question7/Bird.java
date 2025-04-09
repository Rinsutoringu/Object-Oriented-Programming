package question7;

// The class Bird derives from the class Animal.
// The class must be abstract because it does not have any code for
// the canFly method (see below).
public abstract class Bird extends Animal implements Flyer {
			
	private int legs;
	private boolean canFly;
	private int numOfEggs;
	public Bird(String name, int numOfEggs, boolean canFly)
	{
		super(name);
		this.legs = 2;
		this.numOfEggs = numOfEggs;
		this.canFly = canFly;
	}
	@Override
	public int getLegs() {
		return this.legs;
	}

	public int getNumOfEggs() {
		return this.numOfEggs;
	}

	@Override
	public boolean canFly() {
		return this.canFly;
	}

	public static void testBird() {
		// Bird b = new Bird("Twitter", 3); // This does not work!
	}
}