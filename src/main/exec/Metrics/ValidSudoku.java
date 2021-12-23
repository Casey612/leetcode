package exec.Metrics;

/**
 * @author yuki
 * Created on 2021-12-23
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !isValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col) {
        char c = board[row][col];
        //同一行
        for (int t = 0; t < col; t++) {
            if (board[row][t] == c) {
                return false;
            }
        }
        //同一列
        for (int t = 0; t < row; t++) {
            if (board[t][col] == c) {
                return false;
            }
        }
        //9宫格
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i == row && j == col) {
                    continue;
                } else if (board[i][j] == c){
                    return false;
                }
            }
        }
        return true;
    }

}
