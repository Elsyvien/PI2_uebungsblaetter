package info2.swarm;

/**
 * This a simple swarm simulation class. It contains an array for storing
 * particles. The maximum number of particles is defined via the constructor. 
 * Particles can be added to the swarm using the method add. The method 
 * simulationStep invokes the movement of all particles within the swarm.
 * @author Sebastian Otte
 */
public class Swarm {
	
    private Particle[] particles;
    private int numParticles;
    private int maxNumParticles;
    
    public Swarm(final int maxNumParticles) {
        this.numParticles = 0;
        this.maxNumParticles = maxNumParticles;
        this.particles = new Particle[maxNumParticles];
    }
    
    /**
     * Return the maximum number of particles within the swarm.
     */
    public int getMaxNumParticles() {
        return this.maxNumParticles;
    }
    
    /**
     * Return the current nummer of particles within the swarm.
     */
    public int getNumParticles() {
        return this.numParticles;
    }
    
    /**
     * This methods add a new particle to the swarm.
     * @param particle Instance of Particle. 
     */
    public void add(final Particle particle) {
        this.particles[this.numParticles] = particle;
        this.numParticles++;
    }
    
    /**
     * Returns the i-the particle within the swarm.
     * @param i Index of the particle.
     * @return Instance of Particle.
     */
    public Particle get(final int i) {
        return this.particles[i];
    }

    /**
     * Performs the swarm movement. This is done in two phases:
     * First, all particles calculate their movement forces.
     * Second, all particles perform their actual movements. 
     * Due to this two-staged process all particles move 
     * "synchronously".
     */
    public void simulationStep() {
        //
        // first, movement is planned synchronously for all particles
        // based on the current situation.
        //
        for (Particle p : this.particles) {
            if (p != null) {
                p.updateForces();
            }
        }
        //
        // second, planned movement is executed.
        //
        for (Particle p : this.particles) {
            if (p != null) {
                p.move();
            }
        }
    }
    
}