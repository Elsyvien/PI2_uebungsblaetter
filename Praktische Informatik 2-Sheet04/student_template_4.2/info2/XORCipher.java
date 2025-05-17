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

    // TODO: implement valueToChar
    // Take Value from CHAR_TABLE and return the corresponding character
    public static char valueToChar(int value) {
        return CHAR_TABLE[value];
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (b) 
    // ----------------------------------------------------------------
    
    // TODO: implement charToValue
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

        int[] values = new int [str.length()];
        for (int i = 0; i < str.length(); i++) {
            values[i] = charToValue(str.charAt(i));
        }
        return values;
    }
  
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
    // TODO: implement valuesToString
    public static String valuesToString(int[] values) {
        if(values == null) return null;
        
        String result = "";
        for(int i = 0; i < values.length; i++) {
            result += valueToChar(i);
        }
        return result;
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

    // TODO: implement encryptDecrypt for type int[]
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
    
    // TODO: implement encryptDecryot for type String
    public static String encryptDecrypt(String msg, String key) {
        int[] values = stringToValue(msg);
        int[] keyValues = stringToValue(key);
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