package exec;

public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        int m = board.length, n = board[0].length;
        boolean[][] flags = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = word.charAt(0);
                if (board[i][j] == c) {
                    boolean re = process(board, flags, word, i, j, 1);
                    if (re) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean process(char[][] board, boolean[][] flags, String word, int i, int j, int wordIndex) {
        int m = flags.length, n = flags[0].length;
        flags[i][j] = true;
        if (wordIndex >= word.length()) {
            return true;
        }
        char c = word.charAt(wordIndex);
        boolean flag = false;
        if (!flag && j > 0 && !flags[i][j - 1]) {
            //left
            if(board[i][j - 1] == c){
                flag = process(board, flags, word, i, j - 1, wordIndex + 1);
            }
        }
        if (!flag && j < (n - 1) && !flags[i][j + 1]) {
            //right
            if(board[i][j + 1] == c){
                flag = process(board, flags, word, i, j + 1, wordIndex + 1);
            }
        }
        if (!flag && i > 0 && !flags[i - 1][j]) {
            if(board[i - 1][j] == c){
                flag = process(board, flags, word, i - 1, j, wordIndex + 1);
            }
        }
        if (!flag && i < (m - 1) && !flags[i + 1][j]) {
            if(board[i + 1][j] == c){
                flag = process(board, flags, word, i + 1, j, wordIndex + 1);
            }
        }
        flags[i][j] = false;
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        }, "abcd"));
    }


}
