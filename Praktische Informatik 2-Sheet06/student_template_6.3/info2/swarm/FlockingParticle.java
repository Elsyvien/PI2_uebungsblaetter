package info2.swarm;

public class FlockingParticle extends Particle {
    private Swarm swarm;
    private double outerDist, innerDist, cohesionRate, alignmentRate, seperationRate;

    FlockingParticle(Swarm swarm) {
        super();
        this.swarm = swarm;
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
}
