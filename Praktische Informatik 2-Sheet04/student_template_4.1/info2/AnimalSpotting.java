package info2;

public class AnimalSpotting {

    // ----------------------------------------------------------------
    // Exercise 1 (b)
    // ----------------------------------------------------------------
    
    // TODO: Implement generateRandomZoo
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (c)
    // ----------------------------------------------------------------
    
    // TODO: Implement printZoo
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (d)
    // ----------------------------------------------------------------
    
    // TODO: Implement countAnimals
    
    
    
    // ----------------------------------------------------------------
    // Exercise 1 (e)
    // ----------------------------------------------------------------
    
    // TODO: Implement mostFrequentAnimal

    

    // ----------------------------------------------------------------
    // Exercise 1 (f)
    // ----------------------------------------------------------------
    
    // TODO: Implement calculateBiomass



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