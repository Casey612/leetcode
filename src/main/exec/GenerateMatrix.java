package exec;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class GenerateMatrix {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 0;
        int startRow = 0, startCol = 0, endRow = n - 1, endCol = n - 1;
        while (startRow < endRow && startCol < endCol) {
            int curRow = startRow, curCol = startCol;
            while (curCol <= endCol) {
                num++;
                result[curRow][curCol] = num;
                curCol++;
            }
            while(curRow <= endCol){
                num++;
                result[curRow][curCol] = num;
                curRow++;
            }
        }
        return result;
    }

}
