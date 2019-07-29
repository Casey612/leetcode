package exec.Metrics;

public class UniquePaths2 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        if(obstacleGrid[0][0] == 1){
            return 0;
        }
        int flag = 1;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 1) {
                obstacleGrid[0][i] = flag;
            } else {
                obstacleGrid[0][i] = 0;
                flag = 0;
            }
        }
        flag = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1) {
                obstacleGrid[i][0] = flag;
            } else {
                obstacleGrid[i][0] = 0;
                flag = 0;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        }));
    }

}
