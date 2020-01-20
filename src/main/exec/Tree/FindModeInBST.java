package exec.Tree;

import java.util.*;

/**
 * #501
 */
public class FindModeInBST {

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }
        //count num
        Map<Integer, Integer> map = new HashMap<>();
        travel(root, map);
        int maxTimes = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxTimes = Math.max(maxTimes, entry.getValue());
        }
        List<Integer> resultContainer = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxTimes) {
                resultContainer.add(entry.getKey());
            }
        }
        return resultContainer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void travel(TreeNode root, Map<Integer, Integer> map) {
        int cur = root.val;
        if (map.containsKey(cur)) {
            int times = map.get(cur);
            times++;
            map.put(cur, times);
        } else {
            map.put(cur, 1);
        }
        if (root.left != null) {
            travel(root.left, map);
        }
        if (root.right != null) {
            travel(root.right, map);
        }
    }

}
