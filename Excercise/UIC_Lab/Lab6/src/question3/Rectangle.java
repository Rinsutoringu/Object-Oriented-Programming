package question3;
// The Rectangle class derives from the Shape class.
public class Rectangle extends Shape {

	private double width;
	private double length;

	public Rectangle(double x,double y, double width, double length)
	{
		super(x, y);
		this.length = length;
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	public double area()
	{
		return length*width;
	}

	public boolean equals(Rectangle otherObject)
	{
		if (!(this.getWidth() == otherObject.getWidth())) {return false;}
		if (!(this.getLength() == otherObject.getLength())) {return false;}
		return true;
	}

	public static void testRectangle() {
		Rectangle r = new Rectangle(1.2, 3.4, 4.0, 5.0);
		Rectangle r1 = new Rectangle(10, 11, 4.0, 5.0);
		Rectangle r2 = new Rectangle(10, 11, 8.0, 5.0);
		Circle c = new Circle(1.2, 3.4, 5.0);
		// getX and getY are inherited from Shape.
		// area comes from Rectangle itself.
		System.out.println(r.getX() == 1.2);
		System.out.println(r.getY() == 3.4);
		System.out.println(r.area() == 20.0);
		
		System.out.println(r.equals(r1));
		System.out.println(!r1.equals(r2));
		System.out.println(!r1.equals(c));
	}
}