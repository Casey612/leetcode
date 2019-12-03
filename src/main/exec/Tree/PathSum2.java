package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            //leaf node
            if (sum == root.val) {
                List<Integer> array = new ArrayList<>();
                array.add(sum);
                result.add(array);
            }
            return result;
        } else {
            //not leaf node
            int subSum = sum - root.val;
            List<List<Integer>> leftResult = pathSum(root.left, subSum);
            List<List<Integer>> rightResult = pathSum(root.right, subSum);
            
            result.addAll(leftResult);
            result.addAll(rightResult);
            result.forEach(array -> {
                array.add(0, root.val);
            });
            return result;
        }
    }
}
