package exec;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class DivideTwoInteger {

    public static int divide(int dividend, int divisor) {
        boolean negative = false;
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            negative = true;
        }
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int result = 0;
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                result = Integer.MAX_VALUE;
            } else {
                result = -dividend;
            }
        } else {
            while (dividend <= divisor) {
                dividend -= divisor;
                result++;
            }
        }
        return negative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 3));
        System.out.println(divide(7, -3));
        System.out.println(divide(-2147483648, -1));
        System.out.println(divide(-2147483648, 2));
    }

}
