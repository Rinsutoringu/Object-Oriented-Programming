package question9;

public class Start {
	public static void main(String[] args) {
		Animal.testAnimal();
		Dog.testDog();
		Bird.testBird();
		Magpie.testMagpie();
		Ostrich.testOstrich();
		Pegasus.testPegasus();
		Airplane.testAirplane();
		// Testing magpies, ostriches, pegasi, airplanes when viewed
		// through the Flyer interface. Animals and birds are not
		// tested because the Animal and Bird classes are abstract.
		// Dogs are not tested because the Dog class does not
		// implement (directly or indirectly) the interface.
		Flyer m = new Magpie("Maggie");
		System.out.println(m.getName() == "Maggie");
		System.out.println(m.canFly() == true);
		Flyer o = new Ostrich("Ossie");
		System.out.println(o.getName() == "Ossie");
		System.out.println(o.canFly() == false);
		Flyer p = new Pegasus("Peggie");
		System.out.println(p.getName() == "Peggie");
		System.out.println(p.canFly() == true);
		Flyer a = new Airplane("Aircar");
		System.out.println(a.getName() == "Aircar");
		System.out.println(a.canFly() == true);
		System.out.println(m.isDangerous() == false);
		System.out.println(o.isDangerous() == true);
		System.out.println(p.isDangerous() == false);
		System.out.println(a.isDangerous() == false);
	}
}