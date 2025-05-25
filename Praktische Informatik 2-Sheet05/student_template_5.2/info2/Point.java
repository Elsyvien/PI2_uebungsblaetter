package info2;

public class Point {
    private double x, y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /*public Point(Double x, Double y) {
        // hier wird unboxed; bei null gäbe es absichtlich eine NullPointerException (schlechtes Design aber naja)
        this(x.doubleValue(), y.doubleValue());
    }*/
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double[] toArray() {
        double[] array = new double[2];
        array[0] = this.x;
        array[1] = this.y;
        return array;
    }


}
