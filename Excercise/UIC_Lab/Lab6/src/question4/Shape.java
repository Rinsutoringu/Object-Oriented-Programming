package question4;
public class Shape {
	
	private double x;
	private double y;

	public Shape(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double area() {
		System.out.println("An unknown shape has an unknown area!");
		return -1.0;
	}
	
	public String toString(){
		return "Shape area is "+this.area();
	}

	public static void testShape() {
		Shape s = new Shape(1.2, 3.4);
		System.out.println(s.getX() == 1.2);
		System.out.println(s.getY() == 3.4);
		System.out.println(s.area() == -1.0); // Will print an error message too：“An unknown shape has an unknown area!”
		System.out.println(s.toString().equals("Shape area is -1.0"));
	}
}