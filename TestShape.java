public class TestShape {

    public static void main(String[] args) {

        Circle circle = new Circle(5);
        System.out.println(circle.calcArea());

        Rectangle rectangle = new Rectangle(4, 5);
        System.out.println(rectangle.calcArea());

        Triangle triangle = new Triangle(6, 4);
        System.out.println(triangle.calcArea());
    }
}