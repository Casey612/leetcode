package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeAllPath {

    private List<List<TreeNode>> findPath(TreeNode root) {
        return findPath(root, new ArrayList<>());
    }

    private List<List<TreeNode>> findPath(TreeNode node, List<TreeNode> list) {
        List<List<TreeNode>> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        list.add(node);
        if (node.left == null && node.right == null) {
            //leaf node
            result.add(list);
            return result;
        } else {
            List<List<TreeNode>> leftPart = findPath(node.left, new ArrayList<>(list));
            List<List<TreeNode>> rightPart = findPath(node.right, new ArrayList<>(list));
            result.addAll(leftPart);
            result.addAll(rightPart);
            return result;
        }
    }

}
