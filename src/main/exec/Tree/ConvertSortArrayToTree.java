package exec.Tree;

public class ConvertSortArrayToTree {
    
    
    public TreeNode sortedArrayToBST(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        System.out.println(start + " - " + end);
        int mid = (start + end + 1) / 2;
        TreeNode result = new TreeNode(nums[mid]);
        
        if (mid > start) {
            result.left = sortedArrayToBST(nums, start, mid - 1);
        }
        if (mid < end) {
            result.right = sortedArrayToBST(nums, mid + 1, end);
        }
        return result;
    }
    
    public static void main(String[] args) {
        ConvertSortArrayToTree converter = new ConvertSortArrayToTree();
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        
        
        TreeNode root = converter.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        traversal.showTreeInLeverOrder(root);
    }
    
}
