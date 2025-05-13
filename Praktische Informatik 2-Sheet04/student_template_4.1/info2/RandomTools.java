package info2;

import java.util.Random;

public class RandomTools {

    private static Random rnd = new Random(3333);
    
    public static int randomValue(final int n) {
        return rnd.nextInt(n);
    }
    
}

