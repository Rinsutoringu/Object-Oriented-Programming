package question8;

// The Rectangle class derives from the Shape class.
public class Rectangle extends Shape {

    // The Rectangle class is not abstract (it has code for all methods) so
    // we can test it.
    public static void testRectangle() {
        Rectangle r = new Rectangle(1.2, 3.4, 4.0, 5.0);
        // getX, getY, and move are inherited from Shape.
        // area and resize come from Rectangle itself.
        System.out.println(r.getX() == 1.2);
        System.out.println(r.getY() == 3.4);
        System.out.println(r.area() == 20.0);

        // Move the rectangle. The area does not change.
        r.move(7.8, 9.0);
        System.out.println(r.getX() == 9.0);
        System.out.println(r.getY() == 12.4);
        System.out.println(r.area() == 20.0);

        // Resize the rectangle. The area changes but not the position.
        r.resize(12.0);
        System.out.println(r.getX() == 9.0);
        System.out.println(r.getY() == 12.4);
        System.out.println(r.area() == 144.0);

        // Resize the rectangle again with different width and length.
        // The area changes but not the position.
        try {
            r.resize(10.0, 15.0);
        } catch (CannotResizeException ex) {
            System.out.println("BUG! This must never happen!");
        }
        System.out.println(r.getX() == 9.0);
        System.out.println(r.getY() == 12.4);
        System.out.println(r.area() == 150.0);
    }
}