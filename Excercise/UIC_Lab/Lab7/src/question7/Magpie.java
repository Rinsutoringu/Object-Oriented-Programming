package question7;

// The class Magpie derives from the class Bird.
public class Magpie extends Bird {
	
	public Magpie(String name)
	{
		super(name,6,true);
	}
	
	public static void testMagpie() {
		Magpie m = new Magpie("Maggie");
		System.out.println(m.getName() == "Maggie");
		System.out.println(m.getLegs() == 2);
		System.out.println(m.getNumOfEggs() == 6);
		System.out.println(m.canFly() == true);
		}
}