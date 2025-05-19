package info2.robotarm;

import java.util.Random;

/**
 * @author Sebastian Otte
 */
public class RobotArm {
	
	private double[] jointmaxangle;
	private double[] jointangle;
	private double[][] jointposition;
	private double[] limblength;
	private double[][] limbposition;
	private double[][] limbdirection;
	
	private int joints;
	
	/**
	 * Turns matrix R into a 2D rotation matrix.
	 */
	private static void rotationMatrix(final double phi, final double[][] R) {
	    
		final double a    = phi;
		final double cosa = Math.cos(a);
		final double sina = Math.sin(a);
		//
		R[0][0] = cosa;	R[0][1] = -sina;
		R[1][0] = sina;	R[1][1] = cosa;
	}
	
	/**
	 * Performs 2D matrix-vector multiplication y = Ax
	 */
	private static void matrixMul(final double[][] A, final double[] x, final double[] y) {
	    final double y1 = A[0][0] * x[0] + A[0][1] * x[1];
        final double y2 = A[1][0] * x[0] + A[1][1] * x[1];
        y[0] = y1;
        y[1] = y2;
	}
	
	
	public RobotArm(final int joints) {
		//
		this.joints    	   = joints;
		//
		this.jointmaxangle = new double[this.joints];
		this.jointangle    = new double[this.joints];
		this.jointposition = new double[this.joints][];
		//
		this.limblength    = new double[this.joints];
		this.limbposition  = new double[this.joints][];
		this.limbdirection = new double[this.joints][];
		//
		final double seglength = 1.0 / (double)(joints);
		//
		for (int i = 0; i < joints; i++) {
			this.jointmaxangle[i]  = Math.PI * 0.9;
			this.jointangle[i]     = 0.0;
			this.jointposition[i]  = new double[2]; 
			this.limblength[i]     = seglength;
			this.limbposition[i]   = new double[2];
			this.limbdirection[i]  = new double[2];
		}
		//
		this.update();
	}
	
	public double[] getJointPosition(final int joint) {
		return this.jointposition[joint];
	}

	public double[] getLimbPosition(final int limb) {
		return this.limbposition[limb];
	}

	public double[] getLimbDirection(final int limb) {
		return this.limbdirection[limb];
	}

	private static void copy(final double[] u, final double[] v) {
		final int min = Math.min(u.length, v.length);
		for (int i = 0; i < min; i++) {
			v[i] = u[i];
		}
	}
	
	/**
	 * Recalculates the kinematic chain based on the current
	 * angle configuration.
	 */
	public void update() {
		//
		final double[] p = new double[2];
		final double[] v = new double[2];
		final double[][] M = new double[2][2];
		//
		p[0] = 0.0;
		p[1] = 0.0;
		//
		v[0] = 0.0;
		v[1] = 1.0;
		//
		for (int i = 0; i < this.joints; i++) {
			//
			copy(p, this.jointposition[i]);
			rotationMatrix(this.jointangle[i], M);
			matrixMul(M, v, v);
			//
			copy(v, this.limbdirection[i]);
			//
			p[0] += v[0] * this.limblength[i];
            p[1] += v[1] * this.limblength[i];
			//
			copy(p, this.limbposition[i]);
		}
		//
	
	}
	
	public int getJoints() {
		return this.joints;
	}
	
	public void setJointAngle(final int joint, final double angle) {
		if (Math.abs(angle) > this.jointmaxangle[joint]) {
			this.jointangle[joint] = Math.signum(angle) * this.jointmaxangle[joint];
		} else {
			this.jointangle[joint] = angle;
		}
	}
	
	public double getJointAngle(final int joint) {
		return this.jointangle[joint];
	}
	
	public double getLimbLength(final int joint) {
		return this.limblength[joint];
	}
	
	public void setLimbLength(final int joint, final double length) {
		this.limblength[joint] = length;
	}
	
	public void setJointMaxAngle(final int joint, final double max) {
		this.jointmaxangle[joint] = max;
		this.setJointAngle(joint, this.jointangle[joint]);
	}
	
	public double getJointMaxAngle(final int joint) {
		return this.jointmaxangle[joint];
	}

    public void readState(final double[] data, final int offset) {
        for (int i = 0; i < this.joints; i++) {
            data[i + offset] = this.getJointAngle(i);
        }
    }
    
    public void writeState(final double[] data, final int offset) {
        for (int i = 0; i < this.joints; i++) {
            this.setJointAngle(i, data[i + offset]);
        }
        this.update();
    }
    
    /**
     * Generates a random arm configuration within the
     * tolerated angle ranges. 
     */
    public double[] randomPosition(final Random rnd) {
        //
        for (int i = 0; i < this.joints; i++) {
            final double min   = -this.getJointMaxAngle(i);
            final double max   = this.getJointMaxAngle(i);
            final double range = max - min;
            //
            this.setJointAngle(i, min + (range * rnd.nextDouble()));
        }
        this.update();
        return currentPosition().clone();
    }
    
    public double[] currentPosition() {
        return this.getLimbPosition(this.joints - 1).clone();
    }

    /**
     * Sets all angles to 0 (relative to "forward direction")
     * and updates arm.
     */
    public void resetArm() {
        //
        // reset arm.
        //
        for (int i = 0; i < joints; i++) {
            this.setJointAngle(i, 0);
        } 
        //
        this.update();
    }
	
}