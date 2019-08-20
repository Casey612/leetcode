package exec.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SudokuSolver {
    
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = {
            {'.','.','9','7','4','8','.','.','.'},
            {'7','.','.','.','.','.','.','.','.'},
            {'.','2','.','1','.','9','.','.','.'},
            {'.','.','7','.','.','.','2','4','.'},
            {'.','6','4','.','1','.','5','9','.'},
            {'.','9','8','.','.','.','3','.','.'},
            {'.','.','.','8','.','3','.','2','.'},
            {'.','.','.','.','.','.','.','.','6'},
            {'.','.','.','2','7','5','9','.','.'}
        };
        solver.solveSudoku(board);
        System.out.println(board);
    }
    
    
    
    private static List<Character> INIT_LIST = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
    
    public void solveSudoku(char[][] board) {
        
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        
        Map<Position, Set<Character>> map = init(board);
        while (!map.isEmpty()) {
            List<Position> positionList = new ArrayList<>();
            for (Position position : map.keySet()) {
                Set<Character> candidate = map.get(position);
                //确定某一个位置的值
                if (candidate.size() == 1) {
                    positionList.add(position);
                }
            }
            for (Position position : positionList) {
                Set<Character> candidate = map.get(position);
                char value = candidate.iterator().next();
                alterMap(map, position, value);
                //改变矩阵的值
                alterBoard(board, position, value);
                //移除该元素
                map.remove(position);
            }
        }
        
    }
    
    
    private void alterMap(Map<Position, Set<Character>> map, Position position, char value) {
        int row = position.x, col = position.y;
        //循环看同一行数据
        for (int i = 0; i < 9; i++) {
            Position temp = new Position(row, i);
            if (map.containsKey(temp)) {
                map.get(temp).remove(value);
            }
        }
        //循环看同一列数据
        for (int i = 0; i < 9; i++) {
            Position temp = new Position(i, col);
            if (map.containsKey(temp)) {
                map.get(temp).remove(value);
            }
        }
        //所在小方格子
        int rowStart = getSubMatrixStart(row), colStart = getSubMatrixStart(col);
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                Position temp = new Position(i, j);
                if (map.containsKey(temp)) {
                    map.get(temp).remove(value);
                }
            }
        }
    }
    
    private void alterBoard(char[][] board, Position position, char value) {
        int row = position.x, col = position.y;
        board[row][col] = value;
    }
    
    private Map<Position, Set<Character>> init(char[][] board) {
        Map<Position, Set<Character>> result = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j];
                if (c == '.') {
                    Position key = new Position(i, j);
                    Set<Character> candidates = getCandidates(i, j, board);
                    result.put(key, candidates);
                }
            }
        }
        return result;
    }
    
    private Set<Character> getCandidates(int row, int col, char[][] board) {
        Set<Character> result = new HashSet<>(16);
        result.addAll(INIT_LIST);
        //循环看同一行数据
        for (int i = 0; i < 9; i++) {
            char c = board[row][i];
            if (c != '.') {
                result.remove(c);
            }
        }
        //循环看同一列数据
        for (int i = 0; i < 9; i++) {
            char c = board[i][col];
            if (c != '.') {
                result.remove(c);
            }
        }
        //所在小方格子
        int rowStart = getSubMatrixStart(row), colStart = getSubMatrixStart(col);
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                char c = board[i][j];
                if (c != '.') {
                    result.remove(c);
                }
            }
        }
        return result;
    }
    
    private int getSubMatrixStart(int index) {
        int result = index / 3;
        result *= 3;
        return result;
    }
    
    private static class Position {
        private int x;
        private int y;
    
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        public int getX() {
            return x;
        }
        
        public void setX(int x) {
            this.x = x;
        }
        
        public int getY() {
            return y;
        }
        
        public void setY(int y) {
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            Position position = (Position)o;
            return x == position.x && y == position.y;
        }
    }
    
}
