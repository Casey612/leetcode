package exec.Metrics;

public class MinimumPath {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = grid[0][i] + (i == 0 ? 0 : array[i - 1]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int fromTop = array[j] + grid[i][j];
                int fromLeft;
                if (j == 0) {
                    fromLeft = Integer.MAX_VALUE;
                } else {
                    fromLeft = array[j - 1] + grid[i][j];
                }
                array[j] = Integer.min(fromLeft, fromTop);
            }
        }
        return array[n - 1];
    }

}
