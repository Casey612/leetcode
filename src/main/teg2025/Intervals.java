package teg2025;

import java.util.*;

public class Intervals {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        List<Integer> resultList = new ArrayList<>(times);
        for (int i = 0; i < times; i++) {
            int n = scanner.nextInt();
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int[] ary = new int[n];
            for (int idx = 0; idx < n; idx++) {
                ary[idx] = scanner.nextInt();
            }
            int result = getSubaryCnt(n, l, r, ary);
            resultList.add(result);
        }
        for (int i = 0; i < times; i++) {
            System.out.println(resultList.get(i));
        }
    }

    private static int getSubaryCnt(int n, int l, int r, int[] ary) {
        int[][] dp = new int[n][n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (1 >= l && 1 <= r) {
                dp[i][i] = 1;
                result++;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            int key = ary[j];
            int count = map.getOrDefault(key, 0) + 1;
            map.put(key, count);
            if (isGoodAry(l, r, map)) {
                dp[0][j] = 1;
                result++;
            }
        }

        for (int i = 1; i < n; i++) {
            int cur = ary[i - 1];
            int curCnt = map.get(cur) - 1;
            map.put(cur, curCnt);
            for (int j = n - 1; j >= i; j--) {
                if (j == n - 1) {

                }
                if(isGoodAry(l, r, map)) {
                    dp[i][j] = 1;
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean isGoodAry(int l, int r, Map<Integer, Integer> map) {
        for (Integer key : map.keySet()) {
            int count = map.get(key);
            if (count < l || count > r) {
                return false;
            }
        }
        return true;
    }


}
