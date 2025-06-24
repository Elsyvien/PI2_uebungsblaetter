package info2.blob.gui;

/**
 * This class represents a vector of two double values x, y.
 * It contains some static methods for operating in R2 (2 dimensional space).
 * <br></br>
 * @author Sebastian Otte
 */
public class Vector2d {
    
    public double x = 0.0d;
    public double y = 0.0d;
    
    /**
     * Create an instance of Vector2f with zero values.
     */
    public Vector2d() {
        //
    }
    
    /**
     * Normalizes a vector and stores the result (that is a vector
     * in the same direction with length of 1) in ret.
     * @param v Source vector.
     * @param ret Normalized vector.
     */
    public static void normalize(final Vector2d v, final Vector2d ret) {
        final double l = v.length();
        final double s = (l != 0)?(1.0 / l):(0.0); 
        
        ret.x = v.x * s;
        ret.y = v.y * s;
    }
    /**
     * Normalizes a vector and returns the result (that is a vector
     * in the same direction with length of 1) as a new vector.
     * @param v The source vector.
     * @return A new normalized vector.
     */
    public static Vector2d normalize(final Vector2d v) {
        final Vector2d ret = new Vector2d(v);
        normalize(ret, ret);
        return ret;
    }
    
    /**
     * Creates an instance of Vector2d for a given x and y value.
     * @param x The x value.
     * @param y The y value.
     */
    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Creates an instance of Vector2f by a given Vector2d.
     * <br></br>
     * @param v An Instance of Vector2d.
     */
    public Vector2d(final Vector2d v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    /**
     * Sets the values of a given Vector2d.
     * <br></br>
     * @param v An instance of Vector2d.
     */ 
    final public void set(final Vector2d v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    /**
     * Creates a copy this instance.
     * <br></br>
     * @return Copy of this instance.
     */
    final public Vector2d copy() {
        return new Vector2d(this.x, this.y);
    }
    
    /**
     * Sets x and y with values from a double array. The array 
     * is required to have at least 2 elements.
     * <br></br>
     * @param d Values as double[].
     */
    final public void set(final double[] d) {
        this.x = d[0];
        this.y = d[1];
    }
    
    /**
     * Sets x and y with values from a double array at a specific offset.
     * The array is required to have at least offset + 2 elements.
     * <br></br>
     * @param d Values as double[].
     * @param offset The values offset.
     */
    final public void set(final double[] d, final int offset) {
        this.x = d[offset];
        this.y = d[offset + 1];
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    
    /**
     * Computes the length of the vector.
     * <br></br>
     * @return Vector length.
     */
    final public double length() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    
    /**
     * Computes the squared length of the vector. Can be used as a 
     * faster variant then length, if only the relative order
     * of the vectors is needed. 
     * <br></br>
     * @return Vector length without final sqrt.
     */
    final public double length2() {
        return (this.x * this.x) + (this.y * this.y);
    }
    
    /**
     * Computes the (Euclidean) distance between two vectors.
     * <br></br>
     * @return Distance as positive double value.
     */
    public static double dist(final Vector2d v1, final Vector2d v2) {
        final double dx = v2.x - v1.x;
        final double dy = v2.y - v1.y;
        
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Computes the sequared distance between two vectors. Can be used
     * as a faster metric then dist, if only the relative order
     * of the vectors is needed.      
     * <br></br>
     * @return Squared distance as positive double value.
     */
    public static double dist2(final Vector2d v1, final Vector2d v2) {
        final double dx = v2.x - v1.x;
        final double dy = v2.y - v1.y;
        
        return (dx * dx) + (dy * dy);
    }

    /**
     * Adds two given Vector2d instances and stores the result in a third instance. 
     * <br></br>
     * @param v1 The first instance.
     * @param v2 The second instance.
     * @param ret The result instance.
     */
    public static void add(
        final Vector2d v1, 
        final Vector2d v2,
        final Vector2d ret
    ) {
        ret.x = v1.x + v2.x;
        ret.y = v1.y + v2.y;
    }

    /**
     * Adds two given Vector2d instances and returns the result as a new instance. 
     * <br></br>
     * @param v1 The first instance.
     * @param v2 The second instance.
     * @return Resulting vector of v1 + v2.
     */
    public static Vector2d add(final Vector2d v1, final Vector2d v2) {
        return new Vector2d(v1.x + v2.x, v1.y + v2.y);
    }
    
    /**
     * Subtracts two given Vector2d instances and stores the result 
     * in a third instance. 
     * <br></br>
     * @param v1 The first instance.
     * @param v2 The second instance.
     * @param ret The Third instance (result).
     */
    public static void sub(
        final Vector2d v1,
        final Vector2d v2,
        final Vector2d ret
    ) {
        ret.x = v1.x - v2.x;
        ret.y = v1.y - v2.y;
    }

    /**
     * Subtracts two given Vector2d instances and returns the result
     * as a new instance.
     * <br></br>
     * @param v1 The first instance.
     * @param v2 The second instance.
     * @return Resulting vector of v1 - v2.
     */
    public static Vector2d sub(final Vector2d v1, final Vector2d v2) {
        return new Vector2d(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Multiplies a Vector2d instance with a given scalar value and stores
     * the result in a second Vector2d instance.
     * <br></br>
     * @param v An instance of Vector2d.
     * @param s A scalar value.
     * @param ret The second instance (result).
     */
    public static void mul(final Vector2d v, final double s, final Vector2d ret) {
        ret.x = v.x * s;
        ret.y = v.y * s;
    }
    
    /**
     * Limits the length of a Vector2d instance to a given value and stores
     * the result in a second Vector2d instance.
     * <br></br>
     * @param v An instance of Vector2d.
     * @param s A scalar value.
     * @param ret The second instance (result).
     */
    public static void limit(final Vector2d v,final double s, final Vector2d ret) {
        final double length = v.length();
        
        ret.x = v.x;
        ret.y = v.y;
        
        if (length > s) {
            final double ils = s / length;
            ret.x *= ils;
            ret.y *= ils;
        }
    }
    
    /**
     * Limits the length of a Vector2d instance to a given value and returnd a
     * new Vector2d instance.
     * <br></br>
     * @param v An instance of Vector2d.
     * @param s A scalar value.
     * @return The vector with limited length.
     */
    public static Vector2d limit(final Vector2d v, final double s) {
        final Vector2d ret = new Vector2d();
        limit(v, s, ret);
        return ret;
    }
    
    /**
     * Returns the scalar product of two vectors.
     * <br></br>
     * @param v1 Left operand vector.
     * @param v2 Right operand vector.
     * @return The scalar product.
     */
    public static double scalar(final Vector2d v1, final Vector2d v2) {
        return (v1.x * v2.x) + (v1.y * v2.y);
    }
    
    /**
     * Returns the cosine of the angle between two vectors.
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The angle.
     */
    public static double cosAngle(final Vector2d v1, final Vector2d v2) {
        final double scalar = scalar(v1, v2);
        final double length = (v1.length() * v2.length());
        return scalar / length;
    }
    
    /**
     * Returns the angle between two vectors.
     * <br></br>
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The angle.
     */
    public static double angle(final Vector2d v1, final Vector2d v2) {
        return Math.acos(cosAngle(v1, v2));
    }
    
    /**
     * Returns the signed angle between two vectors signed (counterclockwise).
     * <br></br>
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The angle.
     */
    public static double signedAngle(final Vector2d v1, final Vector2d v2) {
        //
        final double a1 = Math.atan2(v2.y, v2.x);
        final double a2 = Math.atan2(v1.y, v1.x);
        //
        return (double)(a1 - a2);
    }
    
    /**
     * Multiplies a given vector with a scalar value. The method returns the
     * result as a new instance.
     * <br></br>
     * @param v The vector.
     * @param s The scalar value.
     * @return The scaled vector.
     */
    public static Vector2d mul(final Vector2d v, final double s) {
        return new Vector2d(v.x * s, v.y * s);
    }
    
    /**
     * Rotates a vector and returns the result as new vector.
     * <br></br>
     * @param v The vector which is to rotate.
     * @param angle The rotation angle (radian).
     * @return The return vector.
     */    
    public static Vector2d rotate(final Vector2d v, final double angle) {
        Vector2d ret = new Vector2d();
        rotate(v, angle, ret);
        return ret;
    }
    
    /**
     * Rotates a vector and stores the result into ret.
     * <br></br>
     * @param v The vector which is to rotate.
     * @param angle The rotation angle (radian).
     * @param ret The return vector.
     */    
    public static void rotate(
        final Vector2d v,
        final double angle,
        final Vector2d ret
    ) {
        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        //
        final double x = v.x;
        final double y = v.y;
        //
        ret.x = (x * cos) + (y * -sin);  
        ret.y = (x * sin) + (y * cos);         
    }
            
}
