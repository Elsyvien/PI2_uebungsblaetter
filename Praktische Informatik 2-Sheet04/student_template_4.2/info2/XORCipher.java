package info2;

public class XORCipher {
    
    //
    // This table contains 64 possible characters that are
    // used as alphabet for this encryption exercise.
    // We can use 6 bits (e.g. the most right 6 bits of an
    // int value) to uniquely encode a character.
    //
    public static char[] CHAR_TABLE = {
        ' ', '\n', ',', '.', '\'', '-', ':', ';', '?', '(', ')', '!',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    // ----------------------------------------------------------------
    // Exercise 2 (a)
    // ----------------------------------------------------------------


    // Take Value from CHAR_TABLE and return the corresponding character
    public static char valueToChar(int value) {
        return CHAR_TABLE[value];
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (b) 
    // ----------------------------------------------------------------
    

    public static int charToValue(char c) {
        for (int i = 0; i < CHAR_TABLE.length; i++) {
            if (CHAR_TABLE[i] == c) {
                return i;
            }
        }
        return -1; // Return -1 if character not found
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    public static int[] stringToValues(String str) {
        if(str == null) return null;
        new StringBuilder(str.length());
        int[] values = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int value = charToValue(c);
            values[i] = value;
        }
        return values;
    }
  
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
   
    public static String valuesToString(int[] values) {
        if(values == null) return null;
        
        StringBuilder sb = new StringBuilder(values.length);
        for (int v : values) {          // ←  nicht den Index, sondern den Wert nehmen!
            sb.append(valueToChar(v));
        }
        return sb.toString();
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

  
    public int[] encryptDecrypt(int[] values, int key) {
        int[] result = new int[values.length];
        for(int i = 0; i < values.length; i++) {
            result[i] = values[i] ^ key;
        }
        return result;
    }

    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
  
    public static String encryptDecrypt(String msg, String key) {
        int[] values = stringToValues(msg);
        int[] keyValues = stringToValues(key);
        int[] result = new int[values.length];
        
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] ^ keyValues[i % keyValues.length];
        }
        
        return valuesToString(result);
    }

    
    
    // ----------------------------------------------------------------
    
    public static void main(String[] args) {
        final String msg = "Dieser Text ist sehr geheim...";
        final String key = "keyword";
        
        final String cipher = encryptDecrypt(msg, key);
        final String decrypted = encryptDecrypt(cipher, key);
        
        //
        // Wenn alles richtig implementiert wurde, sollte
        // der String decrypted wieder genau dem String 
        // msg entsprechen, waehrend der String cipher fuer
        // uns nicht lesbar ist.
        //
        
        System.out.println(msg);
        System.out.println(cipher);
        System.out.println(decrypted);
        
        System.out.println();
        
    }
}