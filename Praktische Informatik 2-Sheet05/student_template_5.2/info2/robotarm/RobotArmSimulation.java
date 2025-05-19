package info2.robotarm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import info2.Point;
import info2.RobotArmMotionPlanner;

/**
 * This class contains the simulation loop and stuff for rendering 
 * the robots arm simulation and the target points within a JFrame
 * window.
 * @author Sebastian Otte
 */
public class RobotArmSimulation {
	
    private static final Stroke OUTERSTROKE = new BasicStroke(18);
    private static final Stroke INNERSTROKE = new BasicStroke(14);
    private static final Stroke JOINTSTROKE = new BasicStroke(2);
    
    private static final int CANVAS_WIDTH = 900;
    private static final int CANVAS_HEIGHT = 600;
    private static final double DRAW_SCALE = 450.0;
    
    private static final Color COLOR_TARGET_LINE = new Color(0.0f, 0.6f, 0.0f, 0.4f);
    private static final Color COLOR_TARGET_FILL = new Color(0.0f, 1.0f, 0.0f, 0.15f);

    private static final Color COLOR_NEXTTARGET_LINE = new Color(0.5f, 0.3f, 0.0f, 0.4f);
    private static final Color COLOR_NEXTTARGET_FILL = new Color(0.9f, 0.6f, 0.0f, 0.15f);
    
    
    private static void drawJoint(final int offsetx, final int offsety, final double[] p, final double scale, final Graphics2D g) {
        
        final int rad = 12;
        
        final int x = offsetx +  (int)((scale * p[0]) - rad);
        final int y = offsety -  (int)((scale * p[1]) + rad);
        final int w = 1 + 2 * rad;
        final int h = 1 + 2 * rad;
        //
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x, y, w, h);
        g.setColor(Color.BLACK);
        g.setStroke(JOINTSTROKE);
        g.drawOval(x, y, w, h);

    }
        
    private static void drawLimb(final int offsetx, final int offsety, final double[] p, final double[] q, final double scale, final Graphics2D g) {
        
        g.setColor(Color.BLACK);
        g.setStroke(OUTERSTROKE);
        
        final int x1 = offsetx + ((int)(scale * p[0]));
        final int y1 = offsety - ((int)(scale * p[1]));
        final int x2 = offsetx + ((int)(scale * q[0]));
        final int y2 = offsety - ((int)(scale * q[1]));
        //
        g.drawLine(x1, y1, x2, y2);
        //
        g.setStroke(INNERSTROKE);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(x1, y1, x2, y2);
        //
    }   
    
    private static void draw(final RobotArm arm, final Graphics2D g, final int offsetx, final int offsety, final double scale) {
        //
        final double[] porigin = new double[2];
        final int joints = arm.getJoints();
        //
        porigin[0] = 0.0;
        porigin[1] = 0.0;
        //
        for (int i = 0; i < joints; i++) {
            drawLimb(
                offsetx, offsety, 
                arm.getJointPosition(i), arm.getLimbPosition(i),
                scale, g
            );
        }
        //
        drawJoint(offsetx, offsety, porigin, scale, g);
        //
        for (int i = 0; i < joints; i++) {
            drawJoint(
                offsetx, offsety, 
                arm.getLimbPosition(i), scale, g
            );
        }
        
    }
    
    private static void drawTarget(
        final int offsetx, final int offsety,
        final double[] p, final double scale, 
        final Graphics2D g,
        final Color fill,
        final Color line
    ) {
        final int rad = 20;
   
        final int x = offsetx + (int)((scale * p[0]));
        final int y = offsety - (int)((scale * p[1]));
        final int w = 1 + 2 * rad;
        final int h = 1 + 2 * rad;
        //
        g.setColor(fill);
        g.fillOval(x - rad, y - rad, w, h);
        g.setColor(line);
        g.setStroke(JOINTSTROKE);
        g.drawOval(x - rad, y - rad, w, h);
   
        g.setColor(line);
        g.fillOval(x - 2, y - 2, 5, 5);
    }
	
    public static void runSimulation(
        final RobotArmMotionPlanner planner
    ) {
        //
        final RobotArm arm = planner.getController().getRobotArm();
        
        final int xoffset = CANVAS_WIDTH / 2;
        final int yoffset = CANVAS_HEIGHT - 100;
        //
        final double drawscale = DRAW_SCALE;
        //
        final JFrame frame = new JFrame("Praktische Informatik 2 - Robot Arm Simulation");
        final JPanel canvas = new JPanel() {
            private static final long serialVersionUID = 1L;
            //
            @Override
            protected void paintComponent(final Graphics g) {
                final Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );
                super.paintComponent(g2);
                //
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());
                //
                for (int i = 0; i < planner.getPointsNum(); i++) {
                    if (i < planner.getNextPoint()) continue;
                    
                    Point point = planner.getControlPoint(i);
                    
                    if (point != null) {
                        Color fillColor = COLOR_TARGET_FILL;
                        Color lineColor = COLOR_TARGET_LINE;
                        
                        if (i == planner.getNextPoint()) {
                            fillColor = COLOR_NEXTTARGET_FILL;
                            lineColor = COLOR_NEXTTARGET_LINE;
                        }
                        
                        drawTarget(
                            xoffset, yoffset, point.toArray(), drawscale, g2, 
                            fillColor, lineColor
                        );

                    }
                }
                synchronized (arm) {
                    RobotArmSimulation.draw(arm, g2, xoffset, yoffset, drawscale);
                }
                //

            }
            
            private Dimension dim = new Dimension(
                RobotArmSimulation.CANVAS_WIDTH, 
                RobotArmSimulation.CANVAS_HEIGHT
            ); 
            
            @Override
            public Dimension getPreferredSize() {
                return this.dim;
            }
            
            @Override
            public Dimension getMinimumSize() {
                return this.dim;
            }
        };
        //
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                if (planner.getPointsNum() < RobotArmMotionPlanner.POINTS_MAX_NUM) {
                    
                    synchronized (arm) {
                        //
                        final double mx = e.getX();
                        final double my = e.getY();
                        //
                        final double x = (mx - xoffset) / (double)(drawscale);
                        final double y = (yoffset - my) / (double)(drawscale);
                        //
                        planner.addControlPoint(x, y);
                        frame.repaint();
                    }
                    
                    if (planner.getPointsNum() == RobotArmMotionPlanner.POINTS_MAX_NUM) {
                        new Thread(new Runnable() {
                            public void run() {
                                planner.performMotion();
                            }
                        }).start();
                    }
                }
            }
        });
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        //
        // The timer is used as general simulation loop. Every 10
        // ms the actionPerformed method is invoked, which causes
        // the simulation to rendered (painted).
        //
        final Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (arm) {
                    arm.update();
                    frame.repaint();
                }
            }
        });
        t.start();
        //
        frame.setVisible(true);
    }
    
    
}