package info2.swarm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class contains some GUI stuff for rendering
 * the swarm simulation as well as the timer that
 * drives the simulation.
 * @author Sebastian Otte
 */
public class SwarmSimulationGUI {
	
    private static final int CANVAS_HEIGHT = calcScreenHeight();
    private static final int CANVAS_WIDTH = (int)(1.2 * CANVAS_HEIGHT);
    private static final double DRAW_SCALE = CANVAS_HEIGHT / 3.0;
    
    private static int calcScreenHeight() {
        return Math.max(
            600,
            (int)(0.8 * Toolkit.getDefaultToolkit().getScreenSize().getHeight())
        );
    }
    
    private static final Color CANVAS_COLOR    = Color.WHITE;
    private static final Color PARTICLE_COLOR  = Color.BLACK;
    private static final Color PARTICLE2_COLOR = new Color(0.8f, 0.0f, 0.0f);
    
    private static void drawParticle(
        final int offsetx, final int offsety,
        final Particle p, final double scale, 
        final Graphics2D g
    ) {
        final Vector2d pos = p.getPosition();
        final Vector2d dir = p.getVelocity().copy();
        Vector2d.normalize(dir, dir);
        final Vector2d odir = new Vector2d(-dir.y, dir.x);
        
        final int[] pointsX = new int[4];
        final int[] pointsY = new int[4];
        
        final boolean isPredator = p.isPredator();
        
        final double s = (isPredator)?(1.6):(1.0);
        
        pointsX[0] = offsetx + (int)(scale * (pos.x + dir.x * s * 0.0175));
        pointsY[0] = offsety - (int)(scale * (pos.y + dir.y * s * 0.0175));
        
        pointsX[1] = offsetx + (int)(scale * (pos.x - dir.x * s * 0.0175 + odir.x * s * 0.0085));
        pointsY[1] = offsety - (int)(scale * (pos.y - dir.y * s * 0.0175 + odir.y * s * 0.0085));

        pointsX[2] = offsetx + (int)(scale * (pos.x - dir.x * s * 0.0085));
        pointsY[2] = offsety - (int)(scale * (pos.y - dir.y * s * 0.0085));

        pointsX[3] = offsetx + (int)(scale * (pos.x - dir.x * s * 0.0175 - odir.x * s * 0.0085));
        pointsY[3] = offsety - (int)(scale * (pos.y - dir.y * s * 0.0175 - odir.y * s * 0.0085));
        
        if (isPredator) {
            g.setColor(PARTICLE2_COLOR);
        } else {
            g.setColor(PARTICLE_COLOR);
        }
        
        g.fillPolygon(pointsX, pointsY, 4);

    }
	
    /**
     * Starts the simulation for a given swarm. While the
     * simulation runs the method simulationStep of the swarm
     * class is invoked with every new "time tick".
     * @param sim Instance of swarm.
     */
    public static void runSimulation(
        final Swarm sim
    ) {
        //
        final int xoffset = CANVAS_WIDTH / 2;
        final int yoffset = CANVAS_HEIGHT / 2;
        //
        final double drawscale = DRAW_SCALE;
        //
        final JFrame frame = new JFrame("Praktische Informatik 2 - Swarm Simulation");
        final JPanel canvas = new JPanel() {
            private static final long serialVersionUID = 1L;
            //
            @Override
            protected void paintComponent(final Graphics g) {
                final Graphics2D g2 = (Graphics2D)g;
                super.paintComponent(g2);
                //
                g2.setColor(SwarmSimulationGUI.CANVAS_COLOR);
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());
                //
                synchronized (sim) {
                    
                    for (int i = 0; i < sim.getNumParticles(); i++) {
                        final Particle p = sim.get(i);
                        drawParticle(xoffset, yoffset, p, drawscale, g2);
                    }
                }   
            }
            private Dimension dim = new Dimension(
                SwarmSimulationGUI.CANVAS_WIDTH, 
                SwarmSimulationGUI.CANVAS_HEIGHT
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
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        //
        // The timer is used as general simulation loop. Every 10
        // ms the actionPerformed method is invoked, which causes
        // the simulation to be rendered (painted).
        //
        final Timer t = new Timer(1000 / 120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (sim) {
                    sim.simulationStep();
                    frame.repaint();
                }
            }
        });
        t.start();
        //
        frame.setVisible(true);
    }
    
    
}
