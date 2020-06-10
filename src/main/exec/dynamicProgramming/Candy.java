package exec.dynamicProgramming;

public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int length = ratings.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = 1;
            rank(ratings, dp, 0, i);
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            result += dp[i];
        }
        return result;
    }

    private int[] rank(int[] ratings, int[] dp, int start, int end) {
        for (int i = end; i > start; i--) {
            if (ratings[i - 1] < ratings[i]) {
                if (dp[i - 1] >= dp[i]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    break;
                }
            } else if (ratings[i - 1] > ratings[i]) {
                //ratings[i - 1] > ratings[i]
                if (dp[i - 1] <= dp[i]) {
                    dp[i - 1] = dp[i] + 1;
                } else {
                    break;
                }
            }  //nothing
        }
        return dp;
    }

    public static void main(String[] args) {
        Candy candy  = new Candy();
        System.out.println(candy.candy(new int[]{1, 0, 2}));
        System.out.println(candy.candy(new int[]{1, 2, 2}));
    }

}
