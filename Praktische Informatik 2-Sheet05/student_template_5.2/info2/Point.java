package info2;

public class Point {
    private double x, y;

    public point() {
        this.x = 0;
        this.y = 0;
    }
    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public getX() {
        return this.x;
    }
    public getY() {
        return this.y;
    }
    public double[] toArray() {
        double[] array = new double[2];
        array[0] = this.x;
        array[1] = this.y;
        return array;
    }
}
