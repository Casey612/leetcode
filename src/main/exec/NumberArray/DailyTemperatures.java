package exec.NumberArray;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] result = new int[length];

        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            if (i == length - 1) {
                result[i] = 0;
            }
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }

}
