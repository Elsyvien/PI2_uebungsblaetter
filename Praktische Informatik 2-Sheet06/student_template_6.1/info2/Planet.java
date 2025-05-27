public class Planet extends CelestialObject {
    private long degreeOfInfection;

    public long getDegreeOfInfection() {
        return degreeOfInfection;
    }

    public void infectWithHumans(long degree) {
        if (degree < 0) {
            this.degreeOfInfection = 0;
            System.out.println("Infection degree cannot be negative. Resetting to 0.");
            return;
        }
        this.degreeOfInfection += degree;
    }

    public void heal() {
        this.degreeOfInfection = 0;
        System.out.println("Planet healed. Infection degree reset to 0.");
    }
}
