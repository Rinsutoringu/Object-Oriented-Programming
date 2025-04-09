package question9;

// The class Pegasus derives from the class Bird.
public class Pegasus extends Bird {

	private int legs;

	public Pegasus(String name) {
		super(name, 0, true);
		this.legs = 4;
	}

	@Override
	public int getNumOfEggs() {
		System.out.println("Pegasi do not lay eggs!");
		return super.getNumOfEggs();
	}

	@Override
	public int getLegs() {
		return this.legs;
	}

	public static void testPegasus() {
		Pegasus p = new Pegasus("Peggie");
		System.out.println(p.getName() == "Peggie");
		System.out.println(p.getLegs() == 4);
		System.out.println(p.getNumOfEggs() == 0); // Message printed here.
		System.out.println(p.canFly() == true);
		System.out.println(p.isDangerous() == false);
		}
}

	