public abstract class CelestialObject {
    private double mass;

    public void setMass (double mass) {
        if(mass < 0) {
            System.out.println("Mass should be a positive number. Returning.");
            return;
        }
        this.mass = mass;
    }
    public double getMass() {
        return mass;
    }

}
