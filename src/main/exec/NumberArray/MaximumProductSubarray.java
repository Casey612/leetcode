package exec.NumberArray;

public class MaximumProductSubarray {

    public int maxProduct1(int[] nums) {
        int result = nums[0], min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i], mx = max, mn = min;
            min = Math.min(cur, Math.min(mn * cur, mx * cur));
            max = Math.max(cur, Math.max(mn * cur, mx * cur));
            result = Math.max(result, max);
        }
        return result;
    }

    public int maxProduct(int[] nums) {
        int length = nums.length;
        int result = Integer.MIN_VALUE;
        int[][] metric = new int[length][length];
        for (int start = 0; start < length; start++) {
            for (int end = start; end < length; end++) {
                if (start == end) {
                    metric[start][end] = nums[start];
                }else if (start == (end - 1)) {
                    metric[start][end] = nums[start] * nums[end];
                } else {
                    metric[start][end] = metric[start][end - 1] * nums[end];
                }
                if (metric[start][end] > result) {
                    result = metric[start][end];
                }
            }
        }
        return result;
    }

}
