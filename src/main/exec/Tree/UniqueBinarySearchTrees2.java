package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees2 {
    
    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(n, 1, n);
    }
    
    private static List<TreeNode> generateTrees(int n, int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            return result;
        } else if (n == 1) {
            TreeNode root = new TreeNode(start);
            result.add(root);
            return result;
        } else {
            for (int i = 0; i < n; i++) {
                List<TreeNode> leftPart = generateTrees(i, start, start + i - 1);
                List<TreeNode> rightPart = generateTrees(n - i - 1, start + i + 1, end);
                if (leftPart.size() == 0) {
                    for (TreeNode right : rightPart) {
                        TreeNode root = new TreeNode(start);
                        root.right = right;
                        result.add(root);
                    }
                } else if (rightPart.size() == 0) {
                    for (TreeNode left : leftPart) {
                        TreeNode root = new TreeNode(end);
                        root.left = left;
                        result.add(root);
                    }
                } else {
                    for (TreeNode left : leftPart) {
                        for (TreeNode right : rightPart) {
                            TreeNode root = new TreeNode(start + i);
                            root.left = left;
                            root.right = right;
                            result.add(root);
                        }
                    }
                }
            }
            return result;
        }
    }
    
    public static void main(String[] args) {
        List<TreeNode> result = generateTrees(3);
        System.out.println(result.size());
    }
    
}
