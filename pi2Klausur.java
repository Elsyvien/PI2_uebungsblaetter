class pi2Klausur {
    boolean isSymmetric(double[][] A) {
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < i; j++) {
                if(A[i][j] != A[j][i]) {
                    return false; // If any pair is not equal, return false immediately
                }
            }
        }
        return true; // All pairs are symmetric
    }

    int[] rotateRight(int[] values, int n) {
        int[] result = new int[values.length];
        if (values == null || values.length == 0) return result;
        n = n % values.length; // Handle n greater than length

        for (int i = 0; i < values.length; i++) {
            result[(i + n) % values.length] = values[i];
        }
        return result;
    }


    public static void main(String[] args) {
        double[][] matrix = { // Symmetric matrix
            {1, 2, 3},
            {2, 4, 5},
            {3, 5, 6}
        };

        pi2Klausur checker = new pi2Klausur();
        boolean isSymmetric = checker.isSymmetric(matrix);
        System.out.println("Is the matrix symmetric? " + isSymmetric);

        int[] values = {1, 2, 3, 4, 5};
        int n = 2; // Rotate right by 2 positions
        int[] rotatedValues = checker.rotateRight(values, n);
        System.out.print("Rotated values: ");
        for (int value : rotatedValues) {
            System.out.print(value + " ");
        }

    }
}