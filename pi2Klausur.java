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

    int magicSumRec(int n) {
        int result = n;

        if (n == 0) {
            return 0; // Base case for recursion
        }

        if (n % 3 == 0) {
            n = n / 3;
            result += magicSumRec(n - 1);
        } else {
            result += magicSumRec(n - 1);
        } 
        return result;
    }

    int[] maxInRowx(int[][] A) {
        int[] maxValues = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] > max) {
                    max = A[i][j];
                }
            }
            maxValues[i] = max;
        }
        return maxValues;
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
        System.out.println("\nMagic sum of 9: " + checker.magicSumRec(9));
        int[][] arr = {
            {1, 5, 3},
            {7, 2},
            {-1, -2, -3}
        };
        int[] result = checker.maxInRowx(arr);
        System.out.print("Max in each row: ");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}