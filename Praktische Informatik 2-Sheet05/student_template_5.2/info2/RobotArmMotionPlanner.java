package info2;

import info2.robotarm.RobotArmController;
import info2.robotarm.RobotArmSimulation;

public class RobotArmMotionPlanner {

    public static final int POINTS_MAX_NUM = 5;
    
    private Point[] controlPoints = new Point[POINTS_MAX_NUM];

    private int pointsNum = 0;
    private int nextPoint = -1;
    private RobotArmController controller;

    public int getPointsNum() {
        return this.pointsNum;
    }
    
    public int getNextPoint() {
        return this.nextPoint;
    }
    
    public RobotArmController getController() {
        return this.controller;
    }
    
    public RobotArmMotionPlanner(final RobotArmController controller) {
        this.controller = controller;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (b)
    // ----------------------------------------------------------------
    // Add Points to the Point array
    public void addControlPoint(final double x, final double y) {
        Point point = new Point(x, y);
        if (this.pointsNum < POINTS_MAX_NUM) {
            this.controlPoints[this.pointsNum] = point;
            this.pointsNum++;
            System.out.println("Added control point: " + point.getX() + ", " + point.getY()); // Debug Message
        } else {
            System.out.println("Maximum number of points reached."); // Error Case
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    public Point getControlPoint(final int i) {
        if (i >= 0 && i < this.pointsNum) {
            System.out.println("Control Point has been accesed and returned: " + this.controlPoints[i].getX() + ", " + this.controlPoints[i].getY()); // Debug Message
            return this.controlPoints[i];
        } else {
            System.out.println("Invalid index: " + i); // Error Case
            return null;
        }
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    public int performMotion() {
        int count = 0;
        int controlPointLength = this.controlPoints.length;

        for(int i = 0; i < controlPointLength; i++) {
            this.nextPoint = i;
            if(controller.navigateToTarget(this.controlPoints[i]) == true) {
                count++;
                System.out.println("Robot Arm has reached the control point: " + this.controlPoints[i].getX() + ", " + this.controlPoints[i].getY()); // Debug Message
            }
        }
        nextPoint = -1; // Reset nextPoint after motion
        System.out.println("Robot Arm has reached all control points." + this.nextPoint); // Debug Message
        pointsNum = 0; // Reset pointsNum after motion
        for (int i = 0; i < controlPointLength; i++) {
            this.controlPoints[i] = null; // Clear the control points
        }
        System.out.println("All control points have been cleared."); // Debug Message
        return count;
    }
    
    
    
    public static void main(String[] args) {
        
        RobotArmController controller = new RobotArmController();
        RobotArmMotionPlanner planner = new RobotArmMotionPlanner(controller);
        
        RobotArmSimulation.runSimulation(planner);
        
    }
}