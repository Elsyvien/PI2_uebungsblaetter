package info2;

public class AnimalSpotting {

    // ----------------------------------------------------------------
    // Exercise 1 (b)
    // ----------------------------------------------------------------
     public static Animal[] generateRandomZoo(int n) {
        Animal[] zoo = new Animal[n];
        for (int i = 0; i < n; i++) {
            int r = RandomTools.randomValue(Animal.values().length);
            zoo[i] = Animal.values()[r];
        }
        return zoo;
    }
    
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (c)
    // ----------------------------------------------------------------
    public static void printZoo(Animal[] zoo) {
        if (zoo == null) {
            return;
        }
        for(Animal animal : zoo) {
            System.out.println(animal);
        }
     }
    
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (d)
    // ----------------------------------------------------------------
    
    public static int[] countAnimals(Animal[] zoo){
        int[] counts = new int[Animal.values().length];

        if(zoo == null) {
            return counts;
        }

        for(Animal animal : zoo) {
            counts[animal.ordinal()]++;
        }
        return counts;
    }
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (e)
    // ----------------------------------------------------------------
    public static Animal mostFrequentAnimal(Animal[] zoo) {
        if (zoo == null || zoo.length == 0) {
            return null;
        }
        int[] counts = countAnimals(zoo);
        int maxCount = 0;
        int maxIndex = -1;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                maxIndex = i;
            }
        }
        return Animal.values()[maxIndex];
    }

    // ----------------------------------------------------------------
    // Exercise 1 (f)
    // ----------------------------------------------------------------
    
     public static double calculateBiomass(Animal[] zoo) {
        if (zoo == null) {
            return 0.0;
        }
        double totalebiomass = 0.0;
        for(Animal animal : zoo) {
            if(animal == null) {
                continue;
            }
            switch(animal) {
                case ELEPHANT:
                    totalebiomass +=5000.0;
                    break;
                case LION:
                    totalebiomass +=150.0;
                    break;
                case TIGER:
                    totalebiomass +=200.0;
                    break;
                case WASP:
                    totalebiomass += 6e-5;
                    break;
                case SNAKE:
                    totalebiomass +=35.0;
                    break;
                case MONKEY:
                    totalebiomass +=160.0;
                    break;
                case EMU:
                    totalebiomass +=40.0;
                    break;
            }
            }
        return totalebiomass;
    }




    // ----------------------------------------------------------------
    
    public static void main(String[] args) {

        Animal[] zoo = generateRandomZoo(100);
        
        printZoo(zoo);
        
        System.out.println();
        
        int[] counting = countAnimals(zoo);
        System.out.println(ArrayTools.asString(counting));
        System.out.println();
        
        System.out.println(mostFrequentAnimal(zoo));
        
        System.out.println();
        
        System.out.println(calculateBiomass(zoo));
    }
    
}
