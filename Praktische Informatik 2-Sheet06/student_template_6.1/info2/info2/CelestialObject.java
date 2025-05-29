public class   CelestialObject {
    private double mass;

    public void setMass(double mass) {
        if(mass < 0) {
            System.out.println("Invalid mass");
            return;
        }
        this.mass = mass;
    }
    public double getMass() {
        return mass;
    }
}
