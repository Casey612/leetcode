package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/22/19
 */
public class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        result.add(each);
        List<List<Integer>> temp = result;
        int size = 1;
        while (size < nums.length) {
            List<List<Integer>> subListWithSize = findSubsetWithSize(temp, nums);
            result.addAll(subListWithSize);
            size++;
            temp = subListWithSize;
        }
        return result;
    }

    private List<List<Integer>> findSubsetWithSize(List<List<Integer>> temp, int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> each : temp) {
            int index = findStart(each, nums);
            while (index < nums.length) {
                List<Integer> sub = new ArrayList<>();
                sub.addAll(each);
                sub.add(nums[index]);
                result.add(sub);
                index++;
                while (index < nums.length && nums[index] == nums[index - 1]) {
                    index++;
                }
            }
        }
        return result;
    }

    private int findStart(List<Integer> each, int[] nums) {
        if(each.size() == 0){
            return 0;
        }
        int index = each.size() - 1, size = 1;
        int flag = each.get(index);
        index--;
        while (index >= 0) {
            if (each.get(index) == flag) {
                size++;
                index--;
            } else {
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == flag){
                size--;
                while(size > 0){
                    size--;
                    i++;
                }
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Subsets2 subsets2 = new Subsets2();
        System.out.println(subsets2.subsetsWithDup(new int[]{1, 2, 2}));
    }

}
