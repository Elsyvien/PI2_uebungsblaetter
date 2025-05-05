package info2;

public class MultidimensionalArrays {

    // ----------------------------------------------------------------
    // Exercise 3 (a)
    //
    //  C = A x B :
    //
    //              b11 b12
    //              b21 b22
    //              b31 b32
    //
    //  a11 a12 a13   c11 c12
    //  a21 a22 a23   c21 c22
    //  a31 a32 a33   c31 c32
    //
    //  General rule: c_ij = sum_k (a_ik * b_kj)
    // i = 1,2,3 and j = 1,2.
    //  Examples: 
    //     c11 = (a11 * b11) + (a12 * b21) + (a13 * b31) 
    //     c22 = (a21 * b12) + (a22 * b22) + (a23 * b32)
    //
    // ----------------------------------------------------------------
    public static double[][] matrixMul(double[][] A, double[][] B) {
        
        final int rowsA = A.length;
        final int colsA = A[0].length;
        final int rowsB = B.length;
        final int colsB = B[0].length;
        
        // TODO: Implement me.
        
        return null;
    }

    // ----------------------------------------------------------------
    // Exercise 3 (b)
    // ----------------------------------------------------------------
    public static int[] flatten(int[][][] array) {
        
        // TODO: Implement me.
        
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
    // Helper method for converting 2d-double-arrays into strings.
    //
    public static String asString(final double[][] array) {
        if (array == null) { return "null"; }

        final StringBuilder out = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                out.append(System.lineSeparator());
            }
            if (array[i] == null) {
                out.append("null");
            } else {
                for (int j = 0; j < array[i].length; j++) {
                    if (j > 0) {
                        out.append("\t");
                    }
                    out.append(String.valueOf(array[i][j]));
                }
            }
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

            final double[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
            
            final double[][] B = {
                {1, 2},
                {3, 4},
                {5, 6}
            };
            
            final double[][] C = matrixMul(A, B);
            
            System.out.println(asString(A));
            System.out.println("x");
            System.out.println(asString(B));
            System.out.println("=");
            System.out.println(asString(C));
            System.out.println();
            
        }
        
        
        
        // ----------------------------------------------------------------
        // Exercise 3 (b)
        // ----------------------------------------------------------------
        {
            System.out.println("-------- Exercise 3 (b) --------");
            System.out.println();

            int[][][] array = {
                {
                    {0},
                    {1, 2},
                    null,
                    {3, 4, 5},
                },
                {
                    {7, 8, 9, 10},
                    {11}
                },
                {
                    {12, 13, 14, 15},
                    {16, 17, 18, 19},
                    {20, 21}
                },
                null
            };
            
            int[] flat = flatten(array);
            
            System.out.println(asString(flat));
            
            System.out.println();
        } 
    }

}
