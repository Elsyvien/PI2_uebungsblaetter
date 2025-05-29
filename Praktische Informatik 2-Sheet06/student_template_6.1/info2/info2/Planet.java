public class Planet extends CelestialObject{
    private long degreeOfInfection;

    public long getDegreeOfInfection() {
        return degreeOfInfection;
    }

    public void infectWithHumans(long degree) {
        if(degree < 0 || degreeOfInfection > Long.MAX_VALUE - degree) {
            this.degreeOfInfection = 0;
            System.out.println("Invalid degree of infection. Resetting to 0.");
            return;
        }
        this.degreeOfInfection += degree;
    }

    public void heal{
        this.degreeOfInfection = 0;
        System.out.println("Healing planet. Infection degree reset to 0.");
    }

    )
}
