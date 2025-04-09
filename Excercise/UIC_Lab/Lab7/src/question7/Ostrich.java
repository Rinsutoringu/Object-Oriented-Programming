package question7;

// The class Ostrich derives from the class Bird.
public class Ostrich extends Bird {

	public Ostrich(String name){
		super(name, 10, false);
	}
	public static void testOstrich() {
		Ostrich o = new Ostrich("Ossie");
		System.out.println(o.getName() == "Ossie");
		System.out.println(o.getLegs() == 2);
		System.out.println(o.getNumOfEggs() == 10);
		System.out.println(o.canFly() == false);
		}
}