package info2;

public class Venus extends Planet {
    public Venus() {
        super();
        this.infectWithHumans(0); // Initialize with no infection
        this.setMass(4.87 * Math.pow(10, 24)); // Set mass to 4.87 * 10^24 kg
    }
}
