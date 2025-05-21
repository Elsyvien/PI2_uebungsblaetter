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
    public void addControlPoint(final double x, final double y) {

        // TODO: Implement me.
    
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    public Point getControlPoint(final int i) {

        // TODO: Implement me.
        return null;
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    public int performMotion() {
        
        // TODO: Implement me.
        return 0;
    }
    
    
    
    public static void main(String[] args) {
        
        RobotArmController controller = new RobotArmController();
        RobotArmMotionPlanner planner = new RobotArmMotionPlanner(controller);
        
        RobotArmSimulation.runSimulation(planner);
        
    }
}