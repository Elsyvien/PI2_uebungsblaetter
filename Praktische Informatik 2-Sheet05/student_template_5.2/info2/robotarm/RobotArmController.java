package info2.robotarm;

import info2.Point;

/**
 * @author Sebastian Otte
 */
public class RobotArmController {
    
    public static final int JOINTS = 5;
    public static final double TARGET_THRESHOLD = 1e-3;
    
    private static final int ITERATIONS_MAX = 10000 * 5 / JOINTS;
    private static final int STEPS = 100;
    
    private RobotArm arm;
    private boolean useSleep;
    
    public RobotArm getRobotArm() {
        return this.arm;
    }
    
    public boolean getUseSleep() {
        return this.useSleep;
    }
    
    public void setUseSleep(final boolean value) {
        this.useSleep = value;
    }
    
    public RobotArmController() {
        this.arm = new RobotArm(JOINTS);
        this.useSleep = true;
    }
    
    /**
     * Performs the navigation process that causes the robot arm
     * to move towards the target point with its end effector. 
     * This methods is blocking and returns true/false after the
     * performing the navigation trajectory. 
     */
    public boolean navigateToTarget(final Point target) {
        
        if (target == null) {
            return false; 
        }
            
        final double[] targetVec = target.toArray();
        
        final double[] origin = new double[this.arm.getJoints()];
        this.arm.readState(origin, 0);
        //
        // Estimates the best possible configuration to fulfill
        // the navigation request (inverse kinematics)
        //
        final double[] solution = this.estimateInverseKinematics(targetVec);
        //
        // Realizes a smooth transition (navigation) from the current pose
        // to the desired pose using linear interpolation in configuration
        // space.
        //
        for (int i = 0; i < STEPS; i++) {
            final double alpha = smootherstep(1.0 - ((double)(i) / (double)(STEPS - 1)));
            synchronized (arm) {
                for (int j = 0; j < origin.length; j++) {
                    arm.setJointAngle(j, alpha * origin[j] + (1.0 - alpha) * solution[j]);
                }
                arm.update();
            }
            if (this.useSleep) {
                try {
                    Thread.yield();
                    Thread.sleep(10);
                    Thread.yield();
                } catch (InterruptedException e) {
                }
            }
        }
        
        final boolean reached = isTargetReached(
            arm.currentPosition(),
            targetVec
        );
        return reached;
    }
    
    private static double smootherstep(final double x) {
        if (x <= 0) return 0.0;
        if (x >= 1.0) return 1.0;
        final double x3 = x * x * x;
        final double x4 = x3 * x;
        return 6.0 * x4 * x - 15.0 * x4 + 10.0 * x3; 
    }
    
    /**
     * This methods realizes the estimation of the inverse kinematics
     * problem by means gradient-based optimization (gradient descent
     * with moment) using numerical gradients. 
     */
    private double[] estimateInverseKinematics(
        final double[] target
    ) {
        
        final double EPSILON = 1e-8;
        final double ETA = 1e-2;
        final double MU = 0.6;
        
        final double[] best    = new double[this.arm.getJoints()];
        final double[] update  = new double[this.arm.getJoints()];
        final double[] current = new double[this.arm.getJoints()];
        final double[] origin  = new double[this.arm.getJoints()];
        
        synchronized (this.arm) {
    
            this.arm.readState(best, 0);
            this.arm.readState(origin, 0);
            this.arm.update();
            
            final double[] grad = new double[this.arm.getJoints()];
            
            double bestDist = dist(this.arm.currentPosition(), target); 
            
            for (int i = 0; i < ITERATIONS_MAX; i++) {
                //
                // compute numerical gradient.
                //
                for (int j = 0; j < JOINTS; j++) {
                    final double x  = current[j];
                    final double x1 = current[j] - EPSILON;
                    final double x2 = current[j] + EPSILON;
                    
                    this.arm.setJointAngle(j, x1);
                    this.arm.update();
                    final double fx1 = dist(this.arm.currentPosition(), target);

                    this.arm.setJointAngle(j, x2);
                    this.arm.update();
                    final double fx2 = dist(this.arm.currentPosition(), target);
                    
                    grad[j] = (fx2 - fx1) / (2.0 * EPSILON);
                    
                    this.arm.setJointAngle(j, x);
                }
                //
                // optimization step.
                //
                for (int j = 0; j < JOINTS; j++) {
                    update[j] = -ETA * grad[j] + MU * update[j];
                    current[j] += update[j];  
                }
                this.arm.writeState(current, 0);
                this.arm.update();

                final double dist = dist(this.arm.currentPosition(), target);
                if (dist < bestDist) {
                    bestDist = dist;
                    this.arm.readState(best, 0);
                }
            }
            
            this.arm.writeState(origin, 0);
            this.arm.update();
        }

        return best;
    }
    
    private static double sq(final double x) {
        return x * x;
    }
    
    public double dist(final double[] position, final double[] target) {
        if (position == null || target == null) {
            return 0.0;
        }
        return Math.sqrt(
            sq(target[0] - position[0]) +
            sq(target[1] - position[1])
        );
    }
    
    public boolean isTargetReached(final double[] position, final double[] target) {
        final double dist = this.dist(position, target);
        return (dist < TARGET_THRESHOLD);
    }
    
    
}


