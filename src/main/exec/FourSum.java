package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/6/19
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Set<List<Integer>> set = new HashSet<>();
        int index = 0;
        Arrays.sort(nums);
        while (index < nums.length) {
            int cur = nums[index];
            int[] array = getNewArray(nums, index);
            List<List<Integer>> threeSumResult = threeSum(array, target - cur);
            for (List<Integer> list : threeSumResult) {
                List<Integer> l = new ArrayList<>();
                l.add(cur);
                l.addAll(list);
                l.sort(Integer::compareTo);
                set.add(l);
            }
            while (index < nums.length && cur == nums[index]) {
                index++;
            }
        }
        result.addAll(set);
        return result;
    }

    private static int[] getNewArray(int[] nums, int index) {
        int resultIndex = 0;
        int[] result = new int[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i != index) {
                result[resultIndex] = nums[i];
                resultIndex++;
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums, int targetSum) {
        System.out.println(Arrays.toString(nums) + ": " + targetSum);
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            System.out.println("left: " + left + ", right:" + right);
            int sum = nums[left] + nums[right];
            int target = targetSum - sum;
            List<Integer> targetIndexes = findTargetIndex(nums, target);
            //能够凑到targetSum
            for (int index : targetIndexes) {
                //检查是否重复元素
                if (index > left && index < right) {
                    //找到解
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[left]);
                    answer.add(target);
                    answer.add(nums[right]);
                    System.out.println(Arrays.toString(answer.toArray()));
                    result.add(answer);
                    break;
                }
            }
            if (right > left) {
                int cur = nums[right];
                while (left < right && nums[right] == cur) {
                    right--;
                }
            }
            if (right <= left) {
                //right reset
                right = nums.length - 1;
                int cur = nums[left];
                while (nums[left] == cur && left < right) {
                    left++;
                }
            }
        }
        return result;
    }

    private static List<Integer> findTargetIndex(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{1, 0, -1, 0, -2, 2};
//        System.out.println(fourSum(array, 0));
//        System.out.println(fourSum(new int[]{0, 4, -5, 2, -2, 4, 2, -1, 4}, 12));
//        System.out.println(fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
//        System.out.println(fourSum(new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9));
        System.out.println(fourSum(new int[]{2,0,3,0,1,2,4}, 7));
    }

}
