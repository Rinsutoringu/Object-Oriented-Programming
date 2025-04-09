// Author: RinChord
// Date: 2025/3/6
// Description: Student class for question

package question1;

public class Animal {

	private String name;
	private String fastOrslow;
	private int nLegs;
	private int topSpeed;
	private boolean endangered;

	public Animal(String name, int nLegs, int topSpeed, boolean endangered) {
		this.name = name;
		this.nLegs = nLegs;
		this.topSpeed = topSpeed;
		this.endangered = endangered;
		this.fastOrslow = (topSpeed > 30) ? "fast" : "slow";
	}
	
	// getters and setters
	public String getname() {
		return name;
	}
	// setters
	public void setname(String name) {
		this.name = name;
	}
	// getters
	public int getnLegs() {
		return nLegs;
	}

	public void setnLegs(int nLegs) {
		this.nLegs = nLegs;
	}

	public int gettopSpeed() {
		return topSpeed;
	}
	
	public void settopSpeed(int topSpeed) {
		// update topSpeed
		this.topSpeed = topSpeed;
		// update fastOrslow
		this.fastOrslow = (topSpeed > 30) ? "fast" : "slow";
	}

	public String getfastOrslow() {
		return fastOrslow;
	}

	public boolean getendangered() {
		// return endangered
		return endangered;
	}

	// setters
	public void setendangered(boolean endangered) {
		// update fastOrslow
		this.endangered = endangered;
	}

	public void info() {
		// The Elephant has a top speed of 25 mph, a slow moving animal.
		System.out.println("The " + name + " has a top speed of " + topSpeed + " mph, a " + fastOrslow + " moving animal.");
	}

	// testAnimal is a public static method that tests all the code in the Animal class
	public static void testAnimal() {
	 	Animal animal = new Animal("Elephant",4,25,true);
	 	
	 	// test the constructor
	 	System.out.println(animal.getname() == "Elephant");
	 	System.out.println(animal.getnLegs() == 4);
	 	System.out.println(animal.gettopSpeed() == 25);
	 	System.out.println(animal.getendangered() == true);
	 	System.out.println(animal.getfastOrslow() == "slow");	 	
	 	animal.info();
	 
	 	// test the setters
	    animal.setname("Lion");
	    animal.settopSpeed(50);
	    animal.setendangered(false); 	
	 	System.out.println(animal.getname() == "Lion");
	 	System.out.println(animal.getnLegs() == 4);
	 	System.out.println(animal.gettopSpeed() == 50);
	 	System.out.println(animal.getendangered() == false);
	 	System.out.println(animal.getfastOrslow() == "fast");	 	
		animal.info();
	}

}
