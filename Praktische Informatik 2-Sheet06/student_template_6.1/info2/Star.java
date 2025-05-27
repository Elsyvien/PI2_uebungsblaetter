public class Star extends CelestialObject {
    private long age, maxAge;

    public Star(long maxAge) {
        if (maxAge < 0) {
            System.out.println("Max age should be a positive number. Returning.");
            return;
        }
        this.age = 0;
        this.maxAge = maxAge;
    }

    public long getAge() {
        return this.age;
    }

    public long estimateLifetime() {
        long remainingLifetime = this.maxAge - this.age;
        return remainingLifetime >= 0 ? remainingLifetime : 0;
    }
}
