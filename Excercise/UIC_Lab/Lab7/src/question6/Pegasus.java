package question6;

// The class Pegasus derives from the class Bird.
public class Pegasus extends Bird {

	private int legs;
	
	public Pegasus(String name){
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

	public static void TestPegasus() {
		Pegasus p = new Pegasus("Peggie");
		// The getName method is inherited from Bird, which inherits it
		// from Animal.
		// The getLegs method is from Pegasus, which overrides the getLegs// method
		// inherited from Bird, which overrides the abstract
		// getLegs method inherited by Bird from Animal.
		// The getNumOfEggs method is from Pegasus, which overrides the
		// getNumOfEggs method inherited from Bird.
		// The canFly method is from Pegasus, which overrides the abstract// canFly
		// method inherited from Bird, which inherits it from
		// Animal.
		System.out.println(p.getName() == "Peggie");
		System.out.println(p.getLegs() == 4);
		System.out.println(p.getNumOfEggs() == 0); // Message printed here.System.out.println(p.canFly() == true);
	}
}