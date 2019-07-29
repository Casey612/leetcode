package exec.NumberProcess;

/**
 * @author yuzhe
 * @since 3/12/19
 */
public class Pow {

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0d) {
            return 0d;
        }
        int pow = n < 0 ? n : -n;
        double num = x < 0 ? -x : x;
        double result = 1;
        if (num != 1) {
            while (pow < 0) {
                result *= num;
                pow++;
            }
        }
        if (result > 100000) {
            return 0;
        }
        if (x < 0 && (n % 2) == 1) {
            result = -result;
        }
        if (n < 0) {
            result = 1d / result;
        }
        return result;
    }


    public static double myPow1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0d) {
            return 0d;
        }
        if (n == 1) {
            return x;
        }
        double result = 1;
        result = myPow1(x, n / 2);
        if (n % 2 == 0) {
            return result * result;
        } else {
            if (n > 0) {
                return result * result * x;
            } else {
                return result * result / x;
            }
        }
    }


    public static void main(String[] args) {
//        System.out.println(myPow(2, Integer.MIN_VALUE));
        System.out.println(myPow(-1, Integer.MIN_VALUE));
    }

}
