public class Star extends CelestialObject{
    private long age;
    private long maxAge;

    public Star(long maxAge){
        if(maxAge < 0){
            System.out.println("Max age cannot be less than 0");
            return;
        }
        this.age = 0;
        this.maxAge = maxAge;
    }

    public long getAge() {
        return age;
    }
    public void setAge(long age) {
        if(age < 0){
            System.out.println("Age cannot be less than 0");
        }
        this.age = age;
    }

    public long getMaxAge() {
        return this.maxAge;
    }

    public long estimateLifeTime(){
        long remainingLifeTime = this.maxAge - age;
        return remainingLifeTime >= 0 ? remainingLifeTime : 0;
    }
}
