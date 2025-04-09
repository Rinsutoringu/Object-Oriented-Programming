package question7;

// The class Dog derives from the class Animal.
public class Dog extends Animal {
		
	private int legs;
	private boolean canFly;

	public Dog(String name)
	{
		super(name);
		this.legs = 4;
		this.canFly = false;
	}

	@Override
	public int getLegs() {
		return this.legs;
	}

	@Override
	public boolean canFly() {
		return this.canFly;
	}

	public static void testDog() {
		Dog d = new Dog("Nice Doggy");
		// The getName method is inherited from Animal.
		// The getLegs and canFly methods come from Dog itself.
		System.out.println(d.getName() == "Nice Doggy");
		System.out.println(d.getLegs() == 4);
		System.out.println(d.canFly() == false);
		}
}