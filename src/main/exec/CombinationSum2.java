package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/13/19
 */
public class CombinationSum2 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return result;
        } else {
            Arrays.sort(candidates);
            System.out.println(Arrays.toString(candidates) + ": " +target);
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
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int i = 0; i < candidates.length; i++) {
                        if (!map.containsKey(candidates[i])) {
                            map.put(candidates[i], i);
                        }
                    }
//                    System.out.println(map.get(num) + ":" + index);
                    if (map.get(num) == index) {
                        int[] subCandidates = Arrays.copyOfRange(candidates, index + 1, candidates.length);
                        List<List<Integer>> arrays = combinationSum2(subCandidates, target - num);
                        for (List<Integer> array : arrays) {
                            array.add(0, num);
                            result.add(array);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 6, 7, 1, 5}, 8));
        System.out.println(combinationSum2(new int[]{1,2,5,6,7,10}, 7));
//        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

}
