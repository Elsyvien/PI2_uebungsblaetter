package info2;

public class ArrayTools {
    
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
    
    
}