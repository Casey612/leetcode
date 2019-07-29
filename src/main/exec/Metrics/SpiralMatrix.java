package exec.Metrics;


import java.util.*;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int m = matrix.length, n = matrix[0].length;
        int startRow = 0, startCol = 0, endRow = m - 1, endCol = n - 1;
        while (startRow < endRow && startCol < endCol) {
            int curRow = startRow, curCol = startCol;
            result.add(matrix[curRow][curCol]);
            while (curCol < endCol) {
                curCol++;
                result.add(matrix[curRow][curCol]);
            }
            while (curRow < endRow) {
                curRow++;
                result.add(matrix[curRow][curCol]);
            }
            while (curCol > startCol) {
                curCol--;
                result.add(matrix[curRow][curCol]);
            }
            while (curRow > (startRow + 1)) {
                curRow--;
                result.add(matrix[curRow][curCol]);
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        if (startRow == endRow && startCol <= endCol) {
            while (startCol <= endCol) {
                result.add(matrix[startRow][startCol]);
                startCol++;
            }
        } else if (startCol == endCol && startRow <= endRow) {
            while (startRow <= endRow) {
                result.add(matrix[startRow][startCol]);
                startRow++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(spiralOrder(matrix));
    }

}
