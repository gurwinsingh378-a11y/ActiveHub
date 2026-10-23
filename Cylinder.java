public class Cylinder {

    private double cradius;
    private double cheight;

    // Default constructor
    public Cylinder() {
        cradius = 1.0;
        cheight = 1.0;
    }

    // Constructor with two arguments
    public Cylinder(double radius, double height) {
        cradius = radius;
        cheight = height;
    }

    // Calculate surface area
    public double calculateSurfaceArea() {
        return 2 * Math.PI * cradius * (cradius + cheight);
    }

    // Calculate volume
    public double calculateVolume() {
        return Math.PI * cradius * cradius * cheight;
    }
}