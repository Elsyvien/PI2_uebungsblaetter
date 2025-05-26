public class Planet extends CelestialObject {
    private long degreeOfInfection;

    public Planet(double mass) {
        super(mass);
        this.degreeOfInfection = 0;
    }

    public long getDegreeOfInfection() {
        return degreeOfInfection;
    }
    public void setDegreeOfInfection(long degreeOfInfection) {
        this.degreeOfInfection = degreeOfInfection;
    }
    public void infect() {
        degreeOfInfection++;
    }
}
