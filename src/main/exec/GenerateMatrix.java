package exec;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class GenerateMatrix {

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }
        int num = 0;
        int startRow = 0, startCol = 0, endRow = n - 1, endCol = n - 1;
        while (startRow < endRow && startCol < endCol) {
            int curRow = startRow, curCol = startCol;
            while (curCol <= endCol) {
                result[curRow][curCol] = ++num;
                curCol++;
            }
            curCol--;
            while (curRow < endCol) {
                curRow++;
                result[curRow][curCol] = ++num;
            }
            while (curCol > startCol) {
                curCol--;
                result[curRow][curCol] = ++num;
            }
            while (curRow > (startRow + 1)) {
                curRow--;
                result[curRow][curCol] = ++num;
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        if (n % 2 == 1 && startCol == startRow) {
            result[startRow][startCol] = ++num;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            int[][] result = generateMatrix(i);
            print(result);
            System.out.println("-------------");
        }
    }

    public static void print(int[][] arrays) {
        for(int i = 0; i < arrays.length; i++)
            System.out.println(Arrays.toString(arrays[i]));
    }

}
