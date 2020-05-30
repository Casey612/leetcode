package exec.NumberProcess;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        int cur = n;
        Set<Integer> set = new HashSet<>();
        while (cur != 1) {
            int sum = 0;
            while (cur != 0) {
                int num = cur % 10;
                sum += num * num;
                cur /= 10;
            }
            cur = sum;
            if (cur != 1 && set.contains(cur)) {
                return false;
            }
            set.add(sum);
        }
        return true;
    }

    public static void main(String[] args) {
        HappyNumber number = new HappyNumber();
        System.out.println(number.isHappy(19));
    }

}
