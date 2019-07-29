package exec.NumberProcess;

/**
 * @author: yuki
 * @date: 2018/9/30
 */
public class ReverseNum {

    public static int reverse(int x) {

        boolean flag = x < 0;
        if(flag){
            x *= -1;
        }
        long result = 0;
        while(x > 0){
            int mod = x  % 10;
            result *= 10;
            if(result > Integer.MAX_VALUE){
                return 0;
            }
            result += mod;
            x /= 10;
        }
        if(flag){
            result *= -1;
            if(result < Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

}
