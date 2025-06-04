package info2.swarm;

public class FlockingParticle extends Particle {
    private Swarm swarm;
    private double outerDist, innerDist, cohesionRate, alignmentRate, seperationRate;
    public static final double DEFAULT_OUTER_DIST = 0.2;
    public static final double DEFAULT_INNER_DIST = 0.12;
    public static final double DEFAULT_COHESION_RATE = 0.25;
    public static final double DEFAULT_ALIGNMENT_RATE = 0.35;
    public static final double DEFAULT_SEPARATION_RATE = 0.6;


    FlockingParticle(Swarm swarm) {
        super();
        this.swarm = swarm;
        this.outerDist = DEFAULT_OUTER_DIST;
        this.innerDist = DEFAULT_INNER_DIST;
        this.cohesionRate = DEFAULT_COHESION_RATE;
        this.alignmentRate = DEFAULT_ALIGNMENT_RATE;
        this.seperationRate = DEFAULT_SEPARATION_RATE;
    }

    Swarm getSwarm() {
        return this.swarm;
    }

    public void setSwarm(Swarm swarm) {
        this.swarm = swarm;
    }

    public double getOuterDist() {
        return outerDist;
    }

    public void setOuterDist(double outerDist) {
        this.outerDist = outerDist;
    }

    public double getInnerDist() {
        return innerDist;
    }

    public void setInnerDist(double innerDist) {
        this.innerDist = innerDist;
    }

    public double getCohesionRate() {
        return cohesionRate;
    }

    public void setCohesionRate(double cohesionRate) {
        this.cohesionRate = cohesionRate;
    }

    public double getAlignmentRate() {
        return alignmentRate;
    }

    public void setAlignmentRate(double alignmentRate) {
        this.alignmentRate = alignmentRate;
    }

    public double getSeperationRate() {
        return seperationRate;
    }

    public void setSeperationRate(double seperationRate) {
        this.seperationRate = seperationRate;
    }

    @Override
    public Vector2d calculateParticleForce() {
        return SwarmTools.calculateFlockingForce(
            this,
            this.swarm,
            this.outerDist,
            this.innerDist,
            this.getMaxSpeed(),
            this.getMaxForce(),
            this.cohesionRate,
            this.alignmentRate,
            this.seperationRate
        );
    }
}
