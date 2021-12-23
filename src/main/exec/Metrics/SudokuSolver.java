package exec.Metrics;

/**
 * # 37 数独
 */
public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        solver.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    boolean flag = false;
    public void solveSudoku(char[][] board) {
        backtrace(board, 0);
    }

    public void backtrace(char[][] board, int index) {
        if (index >= 81) {
            flag = true;
            return;
        }
        int col = index % 9;
        int row = (index - col) / 9;
        char c = board[row][col];
        if (c == '.') {
            //选项循环
            for(char temp = '1'; temp <= '9'; temp++) {
                board[row][col] = temp;
                if (isValidSudoku(board)) {
                   backtrace(board, index + 1);
                }
                //清理
                if (!flag) {
                    board[row][col] = '.';
                }else {
                    break;
                }
            }
        } else {
            //已填写数字
            if (isValidSudoku(board)) {
                backtrace(board, index + 1);
            }
        }
    }

    //数独表格是否合法
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
