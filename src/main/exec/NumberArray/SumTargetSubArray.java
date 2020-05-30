package exec.NumberArray;

import java.util.ArrayList;
import java.util.List;

public class SumTargetSubArray {

    public List<List<Integer>> getSubArray(int[] array, int target){
        return getSubArray(array, 0, target);
    }

    private List<List<Integer>> getSubArray(int[] array, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (start >= array.length) {
            return null;
        }
        int cur = array[start];
        if (cur == target) {
            List<Integer> ele = new ArrayList<>();
            ele.add(cur);
            result.add(ele);
            return result;
        }
        //1. 不包含当前元素
        if (start + 1 < array.length) {
            List<List<Integer>> notContain = getSubArray(array, start + 1, target);
            if (notContain != null && !notContain.isEmpty()) {
                result.addAll(notContain);
            }

        }
        //2. 包含当前元素
        if (target > array[start]) {
            List<List<Integer>> lefts = getSubArray(array, start + 1, target - cur);
            if (lefts != null && !lefts.isEmpty()) {
                for (List<Integer> left : lefts) {
                    List<Integer> ele = new ArrayList<>();
                    ele.add(cur);
                    ele.addAll(left);
                    result.add(ele);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SumTargetSubArray sumer = new SumTargetSubArray();
        System.out.println(sumer.getSubArray(new int[]{1, 2, 3, 4, 5, 6}, 6));
    }

}
