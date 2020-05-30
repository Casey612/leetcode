package exec.dynamicProgramming;

public class Stock1 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int result = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                result = Math.max(result, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return result;
    }

}
