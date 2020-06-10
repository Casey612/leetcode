package exec.NumberArray;

import java.util.Arrays;

public class SpicalOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int m = matrix.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = matrix[0].length;
        int[] result = new int[m * n];
        boolean[][] flags = new boolean[m][n];
        int index = 0, i = 0, j = 0;
        while (index < result.length) {
            while (j < n) {
                if (!flags[i][j]) {
                    result[index++] = matrix[i][j];
                    flags[i][j] = true;
                    j++;
                } else {
                    break;
                }
            }
            j--;
            i++;

            while (i < m) {
                if (!flags[i][j]) {
                    result[index++] = matrix[i][j];
                    flags[i][j] = true;
                    i++;
                } else {
                    break;
                }
            }
            i--;
            j--;

            while (j >= 0) {
                if (!flags[i][j]) {
                    result[index++] = matrix[i][j];
                    flags[i][j] = true;
                    j--;
                } else {
                    break;
                }
            }
            i--;
            j++;

            while (i >= 0) {
                if (!flags[i][j]) {
                    result[index++] = matrix[i][j];
                    flags[i][j] = true;
                    i--;
                } else {
                    break;
                }
            }
            i++;
            j++;
        }
        return result;
    }

    public static void main(String[] args) {
        SpicalOrder spicalOrder = new SpicalOrder();
//        System.out.println(Arrays.toString(spicalOrder.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
        System.out.println(Arrays.toString(spicalOrder.spiralOrder(new int[][]{})));
    }

}
