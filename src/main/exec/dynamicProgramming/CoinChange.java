package exec.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        //贪心
        Arrays.sort(coins);
        int temp = amount;
        int result = 0;
        while (temp >= coins[0]) {
            int targetCoinIndex = coins.length - 1;
            while (coins[targetCoinIndex] > temp) {
                targetCoinIndex--;
            }
            result++;
            temp -= coins[targetCoinIndex];
        }
        return temp == 0 ? result : -1;
    }

    public int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Set<Integer> set = new HashSet<>();
        for (int coin : coins) {
            set.add(coin);
        }

        //初始化部分
        dp[0] = 0;
        for (int i = 1; i < coins[0] && i <= amount; i++) {
            dp[i] = -1;
        }
        for (int i = coins[0]; i <= amount; i++) {
            if (set.contains(i)) {
                dp[i] = 1;
            } else {
                int cur = Integer.MAX_VALUE;
                boolean allFail = true;
                for (int j = i / 2; j >= 1; j--) {
                    if (dp[j] != -1 && dp[i - j] != -1) {
                        allFail = false;
                        cur = Math.min(dp[j] + dp[i - j], cur);
                        if (cur == 2) {
                            break;
                        }
                    }
                }
                dp[i] = allFail ? -1 : cur;
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange changer = new CoinChange();
        System.out.println(changer.coinChange1(new int[]{2, 5, 1}, 11));
    }

}
