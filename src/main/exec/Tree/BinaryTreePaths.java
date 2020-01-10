package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        return findPaths(root, "");
    }

    private List<String> findPaths(TreeNode node, String s) {
        List<String> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        //node is not null
        StringBuilder sb = new StringBuilder(s);
        if (sb.length() > 0) {
            sb.append("->");
        }
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            //leaf node
            result.add(sb.toString());
            return result;
        } else {
            List<String> leftPart = findPaths(node.left, sb.toString());
            List<String> rightPart = findPaths(node.right, sb.toString());
            result.addAll(leftPart);
            result.addAll(rightPart);
            return result;
        }
    }


}
