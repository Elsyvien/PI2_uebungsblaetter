package info2;

public class Arrays {

    // ----------------------------------------------------------------
    // Exercise 3 (a)
    // ----------------------------------------------------------------
    public static int[] intRange(final int start, final int end) {
        int[] array = new int[Math.abs(end - start) + 1];
        
        for(int i = 0; i < array.length; i++) { // Fill the array with values from start to end
            if (start < end) { // Ascending order
                array[i] = start + i; // Putting the values in the array
            } else {  // Last value in the Array
                array[i] = start - i;  
            }
        }
        return array;
    }

    // ----------------------------------------------------------------
    // Exercise 3 (b)
    // ----------------------------------------------------------------
    public static int search(final int[] array, final int value) {
        final int NOT_FOUND = -1;
        int i = 0;
        
        while (i < array.length) { // Loop through the array
            if (array[i] == value) { // Check if the value is found
                return i; // Return the index of the found value
            }
            i++; // Increment the index
        }

        return NOT_FOUND;
    }

    // ----------------------------------------------------------------
    // Exercise 3 (c)
    // ----------------------------------------------------------------
    public static void mirror(final int[] array) {
        for(int i = 0; i < array.length / 2; i++) { // Loop through the first half of the array
            int temp = array[i]; // Store the current value in a temporary variable
            array[i] = array[array.length - 1 - i]; // Swap the values
            array[array.length - 1 - i] = temp; // Assign the temporary value to the other half
        }
    } // Only the second half is relevant because the first half is already swapped with the second half
    // The array is mirrored in place, so no return value is needed.

    
    // ----------------------------------------------------------------
    // Exercise 3 (d)
    // ----------------------------------------------------------------
    public static void rotateLeft(final String[] array) {
        for(int i = 0; i < array.length - 1; i++) { // Loop through the array
            String temp = array[i]; // Store the current value in a temporary variable
            array[i] = array[i + 1]; // Shift the values to the left
            array[i + 1] = temp; // Assign the temporary value to the next position
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (e)
    // ----------------------------------------------------------------
    public static void rotateRight(final String[] array) {
        for(int i = array.length - 1; i > 0; i--) { // Loop through the array
            String temp = array[i]; // Store the current value in a temporary variable
            array[i] = array[i - 1]; // Shift the values to the right
            array[i - 1] = temp; // Assign the temporary value to the previous position
        }
       
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (f)
    // ----------------------------------------------------------------
    public static double euclideanDistance(final double[] vec1, final double[] vec2) {
        double sum = 0.0;

        if ((vec1 == null) || (vec2 == null)) {
            return Double.NaN;
        }
        for(int i = 0; i < vec1.length; i++) {
            double diff = vec1[i] - vec2[i]; // Calculate the difference between the two vectors
            sum += diff * diff; // Square the difference and add it to the sum
        }

        return Math.sqrt(sum); // Return the square root of the sum
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (g)
    // ----------------------------------------------------------------
    public static double mean(final double[] array) {
        double sum = 0.0;
        if (array == null) {
            return Double.NaN; // Empty Array
        }
        for(int i = 0; i < array.length; i++) {
            sum += array[i]; // Sum all the values in the array
        }
        
        return sum / array.length; // Return the mean value
    }
   
    // ----------------------------------------------------------------
    // Exercise 3 (h)
    // ----------------------------------------------------------------
    public static double stdDev(final double[] array) {
        double sum = 0.0;
        if (array == null) {
            return Double.NaN; // Empty Array
        }
        double mean = mean(array); // Calcuting mean value wiht previous method
        for(int i = 0; i < array.length; i++) {
            double diff = array[i] - mean; // Calculate the difference between the value and the mean
            sum += diff * diff; // Square the difference and add it to the sum
        }
        sum = sum / (array.length - 1); // Divide the sum by the number of values minus 1
        return Math.sqrt(sum); // Return the square root of the sum
    }       

    // ----------------------------------------------------------------
    // Exercise 3 (i)
    // ----------------------------------------------------------------
    public static int[] append(final int[] array, int value) {
        if(array == null) {
            return new int[] {value}; // If the array is null, create a new array with the value
        }
        int[] newArray = new int[array.length + 1]; // Create a new array with one more element
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i]; // Copy the values from the old array to the new array
        }
        newArray[array.length] = value; // Assign the new value to the last position of the new array
        return newArray; // Return the new array
    }       
    
    // ----------------------------------------------------------------
    // Exercise 3 (j)
    // ----------------------------------------------------------------
        public static String[] gather(final String[] dictionary, int[] indices) {
            String[] result = new String[dictionary.length];

            for (int i = 0; i < dictionary.length; i++) {
                int targetIdx = indices[i];
    
                // Bereichs‑ und Duplikats‑Prüfung
                if (targetIdx < 0 || targetIdx >= dictionary.length) {
                    throw new IllegalArgumentException("Ungültiger Index in order: " + targetIdx);
                }
                if (result[targetIdx] != null) {
                    throw new IllegalArgumentException(
                            "order enthält Duplikate – Zielindex " + targetIdx + " taucht mehrfach auf");
                }
    
                result[targetIdx] = dictionary[i];
            }
            return result;
           
        }   

    // ----------------------------------------------------------------
    // Exercise 3 (k)
    // ----------------------------------------------------------------
    public static int[] merge(final int[] array1, int[] array2) {
        if(array1 == null) return array2;
        if(array2 == null) return array1;
        if(array1 == null && array2 == null) return null;

        int length1 = array1.length;
        int length2 = array2.length;
        int combinedLength = length1 + length2;
        int[] merged = new int[combinedLength];

        for(int i = 0; i < length1; i++) {
            merged[i] = array1[i];
        }
        for(int i = 0; i < length2; i++) {
            merged[length1 + i] = array2[i];
        }
        return merged;
    
    }
    // ----------------------------------------------------------------
    // Exercise 3 (l)
    // ----------------------------------------------------------------
    public static int[] mergeInterleaved(final int[] array1, int[] array2) {
        

        return null;
    }   
    
    // ----------------------------------------------------------------

    public static String DELIMITER = ", ";
    
    //
    // Helper function for converting int-arrays into strings.
    //
    public static String asString(final int[] array) {
        if (array == null) { return "null"; }

        final StringBuilder out = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                out.append(DELIMITER);
            }
            out.append(String.valueOf(array[i]));
        }
        return out.toString();
    }
    
    //
    // Helper method for converting double-arrays into strings.
    //
    public static String asString(final double[] array) {
        if (array == null) { return "null"; }

        final StringBuilder out = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                out.append(DELIMITER);
            }
            out.append(String.valueOf(array[i]));
        }
        return out.toString();
    }
    
    //
    // Helper method for converting double-arrays into strings.
    //
    public static String asString(final String[] array) {
        if (array == null) { return "null"; }

        final StringBuilder out = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                out.append(DELIMITER);
            }
            out.append(String.valueOf(array[i]));
        }
        return out.toString();
    }
    
    public static void main(String[] args) {
        
        // ----------------------------------------------------------------
        // Exercise 3 (a)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 3 (a) --------");
            System.out.println();
            System.out.println("int range from 0 to 10");
            System.out.println(asString(intRange(0, 10)));
            System.out.println();
            System.out.println("int range from 12 to 3");
            System.out.println(asString(intRange(12, 3)));
            System.out.println();
            System.out.println("int range from -5 to 5");
            System.out.println(asString(intRange(-5, 5)));
            System.out.println();
            System.out.println("int range from 5 to -5");
            System.out.println(asString(intRange(5, -5)));
            System.out.println();
            //System.out.println(asString(data, ", "));
            System.out.println();
        } 
        
        // ----------------------------------------------------------------
        // Exercise 2 (b)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (b) --------");
            System.out.println();

            final int[] array = {6, 3, 9, 2, 4, 7, 1, -4, 8};
            System.out.println(asString(array));

            final int [] tests = {7, -4, -2, 6, 8, 12};
            for (int i = 0; i < tests.length; i++) {
            
                final int value = tests[i];
                final int index = search(array, value);

                if (index >= 0) {
                System.out.println("found " + value + " at " + index);
                } else {
                    System.out.println("value " + value + " not found");
                }
            }
            System.out.println();
        } 
        
        
        // ----------------------------------------------------------------
        // Exercise 2 (c)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (c) --------");
            System.out.println();
            final int[] array1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            System.out.println(asString(array1));
            mirror(array1);
            System.out.println(asString(array1));
            mirror(array1);
            System.out.println(asString(array1));
            System.out.println();
            final int[] array2 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            System.out.println(asString(array2));
            mirror(array2);
            System.out.println(asString(array2));
            mirror(array2);
            System.out.println(asString(array2));
            System.out.println();

            final int[] array3 = {8};
            System.out.println(asString(array3));
            mirror(array3);
            System.out.println(asString(array3));
            mirror(array3);
            System.out.println(asString(array3));
            System.out.println();
        } 

        // ----------------------------------------------------------------
        // Exercise 2 (d)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (d) --------");
            System.out.println();
            
            final String[] array = {"bar", "idx", "xyz", "uvw", "abc", "dfg", "mau", "foo"};
            
            System.out.println(asString(array));
            rotateLeft(array);
            System.out.println(asString(array));
            rotateLeft(array);
            System.out.println(asString(array));
            rotateLeft(array);
            System.out.println(asString(array));
            
            System.out.println();
        }
        
        // ----------------------------------------------------------------
        // Exercise 2 (e)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (e) --------");
            System.out.println();
            
            final String[] array = {"bar", "idx", "xyz", "uvw", "abc", "dfg", "mau", "foo"};
            
            System.out.println(asString(array));
            rotateRight(array);
            System.out.println(asString(array));
            rotateRight(array);
            System.out.println(asString(array));
            rotateRight(array);
            System.out.println(asString(array));
            
            System.out.println();
        }
        
        // ----------------------------------------------------------------
        // Exercise 2 (f)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (f) --------");
            System.out.println();
            
            final double[] vec1 = {0, 0, 0};
            final double[] vec2 = {
                Math.sqrt(1.0 / 3.0), Math.sqrt(1.0 / 3.0), Math.sqrt(1.0 / 3.0)
            };
            
            System.out.println(asString(vec1));
            System.out.println(asString(vec2));
            System.out.println("distance " + euclideanDistance(vec1, vec2));
            System.out.println();

            final double[] vec3 = {1.0,  2.0,  3.0,  4.0, 5.0, 6.0};
            final double[] vec4 = {0.1, -2.0, -1.0, -0.6, 1.0, 3.0};

            System.out.println(asString(vec3));
            System.out.println(asString(vec4));
            System.out.println("distance : " + euclideanDistance(vec3, vec4));
            System.out.println();
        }
        
        // ----------------------------------------------------------------
        // Exercise 2 (g + h)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (g + h) --------");
            System.out.println();
            
            final double[] array1 = {1.0, 0.9, 0.8, 0.85, 1.1, 1.2, 1.15};
            
            System.out.println(asString(array1));
            System.out.println("mean   : " + mean(array1));
            System.out.println("stddev : " + stdDev(array1));
            System.out.println();

            final double[] array2 = {5.0};
            
            System.out.println(asString(array2));
            System.out.println("mean   : " + mean(array2));
            System.out.println("stddev : " + stdDev(array2));
            System.out.println();
        }
        
        // ----------------------------------------------------------------
        // Exercise 2 (i)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (i) --------");
            System.out.println();
            
            int[] array = null;
            
            array = append(array, 10);
            array = append(array, 20);
            array = append(array, 30);
            array = append(array, 40);
            array = append(array, 50);
            array = append(array, 60);
            array = append(array, 70);
            
            System.out.println(asString(array));

            System.out.println();
        }
        

        // ----------------------------------------------------------------
        // Exercise 2 (j)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (j) --------");
            System.out.println();

            final String[] dictionary1 = {"out", "of", "order", "these", "words", "are"};
            final int[] indices1 = {3, 4, 5, 0, 1, 2};
            final String[] result1 = gather(dictionary1, indices1);

            System.out.println(asString(dictionary1));
            System.out.println(asString(indices1));
            System.out.println(asString(result1));

            System.out.println();
           
            final String[] dictionary2 = {"lion", "tiger", "cheetah", "leopard"};
            final int[] indices2 = {3, 1, 0, 2, 5};  //with index 5 out of bounds

            final String[] result2 = gather(dictionary2, indices2);

            System.out.println(asString(dictionary2));
            System.out.println(asString(indices2));
            System.out.println(asString(result2));

            System.out.println();

        }


        // ----------------------------------------------------------------
        // Exercise 2 (k)
        // ----------------------------------------------------------------
        {   
            System.out.println("-------- Exercise 2 (k) --------");
            System.out.println();
            
            int[] array1 = null;
            int[] array2 = {1, 2, 3, 4};
            int[] array3 = {10, 20, 30, 40, 50};

            System.out.println(asString(array1));
            System.out.println(asString(array2));
            System.out.println(asString(array3));
            
            array1 = merge(array1, array2);
            array1 = merge(array2, array3);
            
            System.out.println(asString(array1));
            
            array1 = merge(array1, array1);
            
            System.out.println(asString(array1));
            
            System.out.println();
        }
        


        // ----------------------------------------------------------------
        // Exercise 2 (l)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 2 (l) --------");
            System.out.println();
            
            final int[] array1 = {2, 4, 6, 8, 10, 12};
            final int[] array2 = {100, 200, 300, 400, 500, 600};
            
            final int[] array3 = mergeInterleaved(array1, array2);
            
            System.out.println(asString(array1));
            System.out.println(asString(array2));
            System.out.println(asString(array3));
            
            System.out.println();

            final int[] array4 = {1, 2, 3, 4};
            final int[] array5 = {10, 20, 30, 40, 50, 60};
            
            final int[] array6 = mergeInterleaved(array4, array5);
            
            System.out.println(asString(array4));
            System.out.println(asString(array5));
            System.out.println(asString(array6));
            
            System.out.println();

        }
        
    }

}
