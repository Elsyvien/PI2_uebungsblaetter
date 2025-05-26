package info2.swarm;

import java.util.Random;

/**
 * Class for testing the swarm simulation.
 * @author Sebastian Otte
 */
public class SwarmTest {
    /**
     * Creates a random position within the area of the
     * unit circle (equally distributed).
     * @param rnd Instance of class Random.
     * @return Random vector.
     */
    public static Vector2d randomPosition(final Random rnd) {
        final Vector2d ret = randomDirection(rnd);
        final double r = rnd.nextDouble();
        final double s = Math.sqrt(r);
        Vector2d.mul(ret, s, ret);
        return ret;
    }
    
    /**
     * Calculates a random direction (random vector of length 1).
     * @param rnd Instance of class Random.
     * @return Random vector.
     */
    public static Vector2d randomDirection(final Random rnd) {
        final double angle = rnd.nextDouble() * Math.PI * 2.0;
        return new Vector2d(Math.cos(angle), Math.sin(angle));
    }
    
    public static void main(String[] args) {

        final int numParticles = 1000;
        final Random rnd = new Random(1234);
        final Swarm swarm = new Swarm(numParticles);
        
        for (int i = 0; i < numParticles; i++) {
            final Particle p = new RandomParticle(rnd);
            //final Particle p = new FlockingParticle(swarm);
            
            p.getPosition().set(randomPosition(rnd));
            p.getVelocity().set(randomDirection(rnd));
            
            swarm.add(p);
        }
        //
        // Starts the simulation. While the simulation is running
        // the method simulationStep of the swarm class is 
        // invoked with every new "time tick".
        //
        SwarmSimulationGUI.runSimulation(swarm);
    }
        
    
}