package exec.NumberArray;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    int result = 1;

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            travel(nums[i], set);
        }
        return result;
    }

    private int travel(int num, Set<Integer> set) {
        if (set.isEmpty() || !set.contains(num)) {
            return 0;
        }
        set.remove(num);
        int left = travel(num - 1, set);
        int right = travel(num + 1, set);
        result = Math.max(result, left + right + 1);
        return left + right + 1;
    }

    public static void main(String[] args){
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        System.out.println(longestConsecutive.longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1}));
        longestConsecutive.result = 1;
        System.out.println(longestConsecutive.longestConsecutive(new int[] {100,4,200,1,3,2}));
        longestConsecutive.result = 1;
        System.out.println(longestConsecutive.longestConsecutive(new int[] {1,0,1,2}));
    }

}
