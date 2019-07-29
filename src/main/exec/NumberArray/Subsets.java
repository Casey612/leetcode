package exec.NumberArray;

import java.util.*;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (nums == null || nums.length == 0) {
            return result;
        }
        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i <= length; i++) {
            List<List<Integer>> arrays = process(nums, 0, i);
            result.addAll(arrays);
        }
        return result;
    }

    private List<List<Integer>> process(int[] nums, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0) {
            result.add(new ArrayList<>());
        } else if (k == 1) {
            for (int i = start; i < nums.length; i++) {
                List<Integer> array = new ArrayList<>();
                array.add(nums[i]);
                result.add(array);
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];
                List<List<Integer>> subResult = process(nums, i + 1, k - 1);
                for (List<Integer> re : subResult) {
                    re.add(0, cur);
                    result.add(re);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                System.out.println(n + ":" + subset);
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subsets2(new int[]{1, 2, 3}));
    }

}
