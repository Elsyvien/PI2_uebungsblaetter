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
        if (value < 0 || value >= CHAR_TABLE.length) {
            return ' ';
        }
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
        // Character not in table → treat like SPACE
        return 0;
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    public static int[] stringToValues(String str) {
        if (str == null) {
            return new int[0];
        }
        int[] values = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            values[i] = charToValue(str.charAt(i));
        }
        return values;
    }
  
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
   
    public static String valuesToString(int[] values) {
        if (values == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(values.length);
        for (int v : values) {          // ←  nicht den Index, sondern den Wert nehmen!
            sb.append(valueToChar(v));
        }
        return sb.toString();
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------
    public static int[] encryptDecrypt(int[] msg, int[] key) {
        if (msg == null || key == null) {
            // Requirement: return message unchanged
            return msg;
        }
        int[] result = new int[msg.length];
        for (int i = 0; i < msg.length; i++) {
            result[i] = msg[i] ^ key[i % key.length];
        }
        return result;
    }

    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
  
    public static String encryptDecrypt(String msg, String key) {
        if (msg == null || key == null) {
            return msg;
        }
        int[] msgVals  = stringToValues(msg);
        int[] keyVals  = stringToValues(key);
        int[] cipher   = encryptDecrypt(msgVals, keyVals);
        return valuesToString(cipher);
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