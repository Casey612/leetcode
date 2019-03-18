package exec;

public class Sqrt {

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int result = 1;
        for (long i = 1; i <= (x / 2); i++) {
            if (i * i <= x) {
                result = (int) i;
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395600));
    }

}
