package exec;

import java.util.*;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (i + 1);
        }
        return process(array, k);
    }

    private List<List<Integer>> process(int[] array, int k) {
        List<List<Integer>> result = new LinkedList<>();
        if (k == 1) {
            for (int n : array) {
                List<Integer> list = new LinkedList<>();
                list.add(n);
                result.add(list);
            }
        } else {
            for (int i = 0; i <= (array.length - k); i++) {
                int cur = array[i];
                int[] sub = Arrays.copyOfRange(array, i + 1, array.length);
                List<List<Integer>> subs = process(sub, k - 1);
                for (List<Integer> l : subs) {
                    l.add(0, cur);
                    result.add(l);
                }
            }
        }
        return result;
    }

}
