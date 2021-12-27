package exec.dynamicProgramming;

import java.util.Arrays;

/**
 * # 354
 * @author yuki
 * Created on 2021-12-27
 */
public class MaxEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] < o2[0]) {
                return -1;
            } else if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[1] > o2[0]) {
                return -1;
            } else if (o1[1] < o2[1]) {
                return 1;
            } else {
                return 0;
            }
        });
        int[] dp = new int[envelopes.length];
        int result = 1;
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int[] cur = envelopes[i];
            int dpVal = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[j][0] < cur[0] && envelopes[j][1] < cur[1]) {
                    int temp = dp[j] + 1;
                    dpVal = Math.max(dpVal, temp);
                    result = Math.max(result, temp);
                }
            }
            dp[i] = dpVal;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxEnvelopes envelopes = new MaxEnvelopes();
        System.out.println(envelopes.maxEnvelopes(new int[][]{
                {5,4},{6,4},{6,7},{2,3}
        }));
    }

}
