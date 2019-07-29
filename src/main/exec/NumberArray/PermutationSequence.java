package exec.NumberArray;

import java.util.*;

public class PermutationSequence {

    public static String getPermutation(int n, int k) {
        if (n < 1 || n > 9) {
            return null;
        }
        List<Integer> array = new LinkedList<>();
        int[] mul = new int[n];
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            array.add(i);
            sum *= i;
            mul[i - 1] = sum;
        }
        return gen(array, mul, k, n);
    }

    private static String gen(List<Integer> array, int[] mul, int k, int n) {
//        System.out.println(array.toString() + ", " + k + ", " + n);
        if (n == 1) {
            return array.get(0).toString();
        } else {
            k--;
            int index = k / mul[n - 2];
            int mod = k % mul[n - 2];
            StringBuilder sb = new StringBuilder();
            sb.append(array.get(index));
            array.remove(index);
            String sub = gen(array, mul, mod + 1, n - 1);
            sb.append(sub);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(1, 1));
        System.out.println(getPermutation(2, 1));
        System.out.println(getPermutation(2, 2));
        System.out.println(getPermutation(3, 1));
        System.out.println(getPermutation(3, 2));
        System.out.println(getPermutation(3, 3));
        System.out.println(getPermutation(3, 4));
        System.out.println(getPermutation(3, 5));
        System.out.println(getPermutation(3, 6));
    }

}
