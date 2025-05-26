public class CelestialObject {
    private double mass;

    public CelestialObject(double mass) {
        this.mass = mass;
    }

    public void setMass (double mass) {
        if(mass < 0)
            return;
        this.mass = mass;
    }
    public double getMass() {
        return mass;
    }

}
