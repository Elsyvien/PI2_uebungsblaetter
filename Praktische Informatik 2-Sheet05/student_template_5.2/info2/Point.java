package info2;

public class Point {
    private double x, y;

    Point() {
        this.x = 0;
        this.y = 0;
    }
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double[] toArray() {
        double[] array = new double[2];
        array[0] = this.x;
        array[1] = this.y;
        return array;
    }
}
