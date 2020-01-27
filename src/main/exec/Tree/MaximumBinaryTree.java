package exec.Tree;

/**
 * #654
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructHelper(nums, 0, nums.length - 1);
    }

    private TreeNode constructHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int val = nums[start], maxIndex = start;
        for (int index = start; index <= end; index++) {
            if (val < nums[index]) {
                val = nums[index];
                maxIndex = index;
            }
        }
        TreeNode result = new TreeNode(val);
        result.left = constructHelper(nums, start, maxIndex - 1);
        result.right = constructHelper(nums, maxIndex + 1, end);
        return result;
    }

}
