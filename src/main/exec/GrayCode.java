package exec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhe
 * @since 3/22/19
 */
public class GrayCode {

    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            result.add(0);
            return result;
        } else if (n == 1) {
            result.add(0);
            result.add(1);
            return result;
        } else {
            List<Integer> last = grayCode(n - 1);
            result.addAll(last);
            int index = last.size() - 1;
            int high = 1 << (n - 1);
            while (index >= 0) {
                int cur = last.get(index);
                int temp = cur | high;
                result.add(temp);
                index--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
    }

}
