package exec.Tree;

import java.util.*;

/**
 * #508
 */
public class MostFrequentSubtreeSum {


    /**
     * key: sum, value: times
     */
    private Map<Integer, Integer> map = new HashMap<>();

    //最少出现1次
    private int maxTimes = 1;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }
        sumHelper(root);
        List<Integer> resultContainer = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxTimes) {
                resultContainer.add(entry.getKey());
            }
        }
        return resultContainer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int sumHelper(TreeNode root) {
        int left = 0, right = 0;
        if (root.left != null) {
            left = sumHelper(root.left);
        }
        if (root.right != null) {
            right = sumHelper(root.right);
        }
        int sum = left + right + root.val;
        if (map.containsKey(sum)) {
            int times = map.get(sum) + 1;
            map.put(sum, times);
            maxTimes = Math.max(maxTimes, times);
        } else {
            map.put(sum, 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(-3);
        root.left = n1;
        root.right = n2;
        MostFrequentSubtreeSum summer = new MostFrequentSubtreeSum();
        System.out.println(Arrays.toString(summer.findFrequentTreeSum(root)));
    }


}
