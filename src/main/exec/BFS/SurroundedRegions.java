package exec.BFS;

import java.util.HashSet;
import java.util.Set;

/**
 * #130
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        int rowSize = board.length;
        int colSize = board[0].length;
        if (rowSize == 1 || colSize == 1) {
            return;
        }
        Set<Position> oPostitionSet = new HashSet<>();
        boolean[][] visited = new boolean[rowSize][colSize];
        for (int i = 0; i < colSize; i++) {
            dfs(board, 0, i, oPostitionSet, visited);
            dfs(board, rowSize - 1, i, oPostitionSet, visited);
        }
        for (int i = 0; i < rowSize; i++) {
            dfs(board, i, 0, oPostitionSet, visited);
            dfs(board, i, colSize - 1, oPostitionSet, visited);
        }
        for (int i = 1; i < rowSize - 1; i++) {
            for (int j = 1; j < colSize - 1; j++) {
                if (board[i][j] == 'O') {
                    Position position = new Position(i, j);
                    if (!oPostitionSet.contains(position)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    public void dfs(char[][] board, int row, int col, Set<Position> oSet, boolean[][]visited) {
        int rowSize = board.length;
        int colSize = board[0].length;
        if (row < 0 || row >= rowSize) {
            return;
        }
        if (col < 0 || col >= colSize) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (board[row][col] == 'X') {
            return;
        }
        oSet.add(new Position(row, col));
        dfs(board, row - 1, col, oSet, visited);
        dfs(board, row + 1, col, oSet, visited);
        dfs(board, row, col - 1, oSet, visited);
        dfs(board, row, col + 1, oSet, visited);
    }

    public static class Position {
        protected int row;
        protected int col;

        public Position(int i, int j) {
            this.row = i;
            this.col = j;
        }

        @Override
        public int hashCode() {
            return row + col * 10;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Position)) {
                return false;
            }
            Position other = (Position) o;
            return row == other.row && col == other.col;
        }
    }

}
