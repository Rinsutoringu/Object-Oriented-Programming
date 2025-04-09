package question4;
import java.util.ArrayList;

public class ManyShapes {
	
	private ArrayList<Shape> allShapes;

	public ManyShapes(){
		this.allShapes = new ArrayList<Shape>();
	};

	public void addShape(Shape s)
	{
		allShapes.add(s);
	}

	public void listAllShapes()
	{
		for (int i = 0; i < allShapes.size(); i++) {
			System.out.println(allShapes.get(i).toString());
		}
	}


	public static void testManyShapes() {
		ManyShapes m = new ManyShapes();
		m.addShape(new Circle(1.2, 3.4, 4.0));         // Upcast from Circle to Shape.
		m.addShape(new Rectangle(1.2, 3.4, 4.0, 5.0)); // Upcast from Rectangle to Shape.
		m.addShape(new Shape(1.2, 3.4));
		m.listAllShapes();
	}
}