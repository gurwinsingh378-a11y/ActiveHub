public class TestCylinder {

    public static void main(String[] args) {

        Cylinder c1 = new Cylinder();

        System.out.println("Surface Area: " + c1.calculateSurfaceArea());
        System.out.println("Volume: " + c1.calculateVolume());

        Cylinder c2 = new Cylinder(2.0, 5.0);

        System.out.println("Surface Area: " + c2.calculateSurfaceArea());
        System.out.println("Volume: " + c2.calculateVolume());
    }
}