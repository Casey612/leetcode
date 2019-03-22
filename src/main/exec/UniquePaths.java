package exec;

import java.math.BigInteger;

public class UniquePaths {

    public static int uniquePaths(int m, int n) {
        if(m == 0 || n == 0){
            return 0;
        }
        int right = n - 1;
        int down = m - 1;
        if(right == 0 || down == 0){
            return 1;
        }
        int total = right + down;
        return c(right > down ? down: right, total);
    }

    private static int c(int m, int n) {
        long num = 1;
        for(int i = 0; i < m; i++){
            num *= (n - i);
            num /= (i + 1);
        }
        return (int) num;
    }

    private static int c1(int m, int n) {
        BigInteger num = BigInteger.ONE;
        int limit = n - m;
        while(n > limit){
            num = num.multiply(BigInteger.valueOf(n));
            n--;
        }
        while(m > 1){
            num = num.divide(BigInteger.valueOf(m));
            m--;
        }
        return Integer.valueOf(num.toString());
    }


    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths(5, 5));
        System.out.println(c(12, 34));
        System.out.println(c(9, 18));
    }

}
