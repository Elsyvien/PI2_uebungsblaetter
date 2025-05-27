import java.lang.Math;

public class Earth extends Planet {
    public Earth() {
        super();
        this.infectWithHumans((long)(8 * Math.pow(10, 9))); // Initialize with no infection
        this.setMass(6 * Math.pow(10, 24)); // Set mass to 2.5 * 10^24 kg
    }
}
