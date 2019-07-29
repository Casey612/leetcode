package exec.NumberArray;

import java.util.*;

/**
 * candidate can be used repeatably
 * @author yuzhe
 * @since 3/13/19
 */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return result;
        } else {
//            System.out.println(Arrays.toString(candidates) + ": " +target);
            Arrays.sort(candidates);
            for (int index = 0; index < candidates.length; index++) {
                int num = candidates[index];
                if (target < num) {
                    break;
                } else if (target == num) {
                    List<Integer> array = new ArrayList<>();
                    array.add(target);
                    result.add(array);
                    break;
                } else if (target > num) {
                    int times = target / num;
                    while (times > 0) {
                        int[] subCandidates = Arrays.copyOfRange(candidates, index + 1, candidates.length);
                        int newTarget = target - num * times;
                        if (newTarget == 0) {
                            int temp = times;
                            List<Integer> array = new ArrayList<>();
                            while (temp > 0) {
                                array.add(0, num);
                                temp--;
                            }
                            result.add(array);
                        } else {
                            List<List<Integer>> arrays = combinationSum(subCandidates, newTarget);
                            for (List<Integer> array : arrays) {
                                int temp = times;
                                while (temp > 0) {
                                    array.add(0, num);
                                    temp--;
                                }
                                result.add(array);
                            }
                        }
                        times--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
    }
}
