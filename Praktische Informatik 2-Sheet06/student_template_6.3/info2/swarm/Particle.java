package info2.swarm;

/**
 * Base class that provides the fundamental functionality
 * for dynamic particles. 
 * @author Sebastian Otte
 */
public class Particle {
    
    public static final double DEFAULT_CENTER_RELEVANCE = 0.001;
    public static final double DEFAULT_MAX_FORCE = 0.001;
    public static final double DEFAULT_MAX_SPEED = 0.015;
    public static final double DEFAULT_TURNAROUND_DIST = 1.1;
    
    private Vector2d position;
    private Vector2d velocity;
    private Vector2d acceleration;
    
    private double centerRelevance = DEFAULT_CENTER_RELEVANCE;
    private double maxForce = DEFAULT_MAX_FORCE;
    private double maxSpeed = DEFAULT_MAX_SPEED;
    private double turnAroundDistance = DEFAULT_TURNAROUND_DIST;
    
    /**
     * Returns whether the particle is a predator or not.
     */
    public boolean isPredator() {
        return false;
    }
    
    /**
     * Returns the maximum allowed length of force vectors.
     */
    final public double getMaxForce() {
        return this.maxForce;
    }
    
    /**
     * Sets the maximum allowed length of force vectors.
     * @param maxForce Maximum allowed length.
     */
    final public void setMaxForce(final double maxForce) {
        this.maxForce = maxForce;
    }

    /**
     * Return the maximum allowed length of velocity vectors. 
     */
    final public double getMaxSpeed() {
        return this.maxSpeed;
    }
    
    /**
     * Returns the maximum allowed length of velocity vectors.
     * @param maxSpeed Maximum allowed length.
     */
    final public void setMaxSpeed(final double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    /**
     * Returns the turn around distance. Particles will be dragged back towards
     * the center (0, 0) when the length of their position vectors will exceed
     * the given distance.
     * @return Turn around distance.
     */
    final public double getTurnAroundDistance() {
        return this.turnAroundDistance;
    }
    
    /**
     * Sets the turn around distance.
     * @param turnAroundDistance Turn around distance.
     */
    final public void setTurnAroundDistance(final double turnAroundDistance) {
        this.turnAroundDistance = turnAroundDistance;
    }
    
    /**
     * Returns the current position of the particle.
     * @return Position as Vector2d.
     */
    final public Vector2d getPosition() {
        return this.position;
    }
    
    /**
     * Returns the current velocity of the particle.
     * @return Velocity as Vector2d.
     */
    final public Vector2d getVelocity() {
        return this.velocity;
    }
    
    /**
     * Returns the current acceleration (or here aka as movement force) of the particle.
     * @return Acceleration as Vector2d.
     */
    final public Vector2d getAcceleration() {
        return this.acceleration;
    }
    
    public Particle() {
        this.position = new Vector2d();
        this.velocity = new Vector2d();
        this.acceleration = new Vector2d();
    }
    
    /**
     * Calculates the movement force of the particle
     * based on the particle's specific behavior. It 
     * first calculates the force that pulls the particle 
     * back to the center and than invokes the method
     * calculateParticleForce, which can be overwritten
     * and implemented specifically in every subclass
     * of the class Particle. The final movement force is
     * stored with this.acceleration
     */
    final public void updateForces() {
        final Vector2d force = new Vector2d();
        final Vector2d centerForce = this.calculateCenterForce();
        final Vector2d particleForce = this.calculateParticleForce();
        
        Vector2d.add(particleForce, centerForce, force);
        Vector2d.add(this.acceleration, force, this.acceleration);
    }
    
    /**
     * Models the particle specific behavior. Can be overwritten.
     * @return The particles movement force as Vector2d.
     */
    public Vector2d calculateParticleForce()  {
        return new Vector2d();
    }
    
    /**
     * Calculates the force that drags the particle back to
     * the center. 
     */
    private Vector2d calculateCenterForce() {
        final Vector2d force = new Vector2d();
        
        final double length = this.position.length();
        if (length > this.turnAroundDistance) {
            final double rate = this.centerRelevance * Math.log(1.0 + 
                (length - this.turnAroundDistance)
            );
            force.x = -this.position.x * rate;
            force.y = -this.position.y * rate;
            Vector2d.limit(force, this.maxForce, force);
        }
        return force;
    }
    
    /**
     * Performs the movement of the particle. The actual 
     * movement is based on the previously calculated 
     * movement force.
     */
    final public void move() {
        //
        // integrate acceleration (force) -> velocity.
        //
        Vector2d.add(this.velocity, this.acceleration, this.velocity);
        this.acceleration.x = 0.0;
        this.acceleration.y = 0.0;
        //
        // speed clamping.
        //
        Vector2d.limit(this.velocity, this.maxSpeed, this.velocity);
        //
        // integrate velocity -> position.
        //
        Vector2d.add(this.position, this.velocity, this.position);
    }
    
    
   
}