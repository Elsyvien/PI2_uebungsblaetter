package info2.swarm;

import java.util.Random;

/**
 * This example class demonstrates how to model a specialized
 * subclass of Particle. Here, a random walk-like behavior is
 * realized.
 * @author Sebastian Otte
 */
public class RandomParticle extends Particle {
    
    public static final double DEFAULT_RANDOM_FORCE_PROBABILITY = 0.1;
    public static final double DEFAULT_RANDOM_FORCE_DECAY = 0.8;
    
    private double randomForceProbability = DEFAULT_RANDOM_FORCE_PROBABILITY;
    private double randomForceDecay = DEFAULT_RANDOM_FORCE_DECAY;
    
    private Random rnd;
    private Vector2d rndForce;
    
    /**
     * Returns the particle's instance of Random.
     */
    final public Random getRnd() {
        return this.rnd;
    }
    
    /**
     * Returns the movement force decay rate.
     */
    final public double getRandomForceDecay() {
        return randomForceDecay;
    }

    /**
     * Sets the movement force decay rate.
     * @param randomForceDecay New decay rate.
     */
    final public void setRandomForceDecay(final double randomForceDecay) {
        this.randomForceDecay = randomForceDecay;
    }

    /**
     * Returns the probability of a new movement force
     * being initiated.
     */
    final double getRandomForceProbability() {
        return this.randomForceProbability;
    }
    /**
     * Sets the movement force initiation probability.
     * @param randomForceProbability New probability value.
     */
    final public void setRandomForceProbability(
        final double randomForceProbability
    ) {
        this.randomForceProbability = randomForceProbability;
    }
    
    public RandomParticle(final Random rnd) {
        this.rnd = rnd;
        this.rndForce = new Vector2d();
    }
    
    /**
     * Here we overwrite the method calculateParticleForce
     * from Particle in order to specialize how the particle
     * behaves. With invocation of super.calculateParticleForce
     * the "behavioral proposal" from the base class is generated
     * (in this case, it's just an zero-vector).
     */
    @Override
    public Vector2d calculateParticleForce() {
        final Vector2d force = super.calculateParticleForce();
        final Vector2d nforce = new Vector2d();
        //
        // With a certain chance, generate a random force.
        //
        if (this.rnd.nextDouble() < this.randomForceProbability) {
            final double angle = this.rnd.nextDouble() * Math.PI * 2.0;
            final double scale = this.getMaxForce();
            this.rndForce.x = Math.cos(angle) * scale;
            this.rndForce.y = Math.sin(angle) * scale;
        }
        //
        // Add random force to result vector.
        //
        Vector2d.add(force, this.rndForce, nforce);
        //
        // Decay random force.
        //
        Vector2d.mul(
            this.rndForce, this.randomForceDecay,
            this.rndForce
        );
        return nforce;
    }
    
}