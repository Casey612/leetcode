package exec.dynamicProgramming;

import java.util.*;

public class NumbersWithRepeatedDigits {

    //stack over flow
    public int numDupDigitsAtMostN(int N) {
        int[] twoDigits = {11, 22, 33, 44, 55, 66, 77, 88, 99};
        if (N <= 10) {
            return 0;
        } else if (N <= 99) {
            int result = 0;
            for (int digits : twoDigits) {
                if (digits <= N) {
                    result++;
                }
            }
            return result;
        } else {
            return numDupDigitsAtMostN(N - 1) + (withRepeated(N) ? 1 : 0);
        }
    }

    private boolean withRepeated(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            if (map.containsKey(digit)) {
                return true;
            } else {
                map.put(digit, 1);
            }
            temp = temp / 10;
        }
        return false;
    }

    private static List<Integer> list = new LinkedList<>();

    static {
        list.addAll(Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99));
    }

    public int numDupDigitsAtMostN2(int N) {
        if (N <= 10) {
            return 0;
        } else {
            int index = 0;
            while (index < list.size() && list.get(index) < N) {
                index++;
            }
            if (index < list.size()) {
                //contian N
                if (list.get(index) > N) {
                    return index;
                } else if (list.get(index) == N) {
                    return index + 1;
                } else {
                    //list.get(index) < N
                    return 0;
                }
            } else {
                int cur = list.get(index - 1);
                for (int temp = cur + 1; temp <= N; temp++) {
                    if (withRepeated(temp)) {
                        list.add(temp);
                    }
                }
                return list.size();
            }
        }

    }


    public int numDupDigitsAtMostN3(int N) {
        return N - notDup(N);
    }

    private int notDup(int n) {
        String nStr = String.valueOf(n);
        int k = nStr.length();
        int result = 0;

        //位数比length小
        for (int i = 2; i <= k; i++) {
            result += 9 * A(k - i, 9);
        }
        //位数相同
        int[] used = new int[10];
        for (int i = 1; i <= k; i++) {
            int num = nStr.charAt(i - 1) - '0';
            //最高位不可从0开始
            for (int j = (i == 1 ? 1 : 0); j < num; j++) {
                if (used[j] != 0) {
                    continue;
                }
                result += A(k - i, 10 - 1 - (i - 1));
            }
            //第i位取N第相同
            used[num]++;
            if (used[num] > 1) {
                break;
            }
            if (i == k) {
                result++;
            }
        }

        return result;
    }

    private int A(int m, int n) {
        return f(n) / f(n - m);
    }

    private int f(int i) {
        if (i == 1 || i == 0) {
            return 1;
        }
        return i * f(i - 1);
    }


    public static void main(String[] args) {
        NumbersWithRepeatedDigits instance = new NumbersWithRepeatedDigits();
//        System.out.println(instance.numDupDigitsAtMostN3(10));
//        System.out.println(instance.numDupDigitsAtMostN3(20));
        System.out.println(instance.numDupDigitsAtMostN3(22));
//        System.out.println(instance.numDupDigitsAtMostN3(100));
//        System.out.println(instance.numDupDigitsAtMostN3(110));
//        System.out.println(instance.numDupDigitsAtMostN3(1000));
//        System.out.println(instance.numDupDigitsAtMostN3(45069));
    }
}
