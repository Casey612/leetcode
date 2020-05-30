package exec.Metrics;

public class ClosedIsland {

    public int closedIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    result += dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 1) {
            return 1;
        }
        // grid[i][j] == 0 still island
        grid[i][j] = 1;
        int left = dfs(grid, i - 1, j);
        int right = dfs(grid, i + 1, j);
        int up = dfs(grid, i, j - 1);
        int down = dfs(grid, i, j + 1);
        return Math.min(Math.min(left, right), Math.min(up, down));
    }

}
