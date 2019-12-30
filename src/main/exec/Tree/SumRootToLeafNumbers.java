package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        List<String> nums = sumLeaf(root, "");
        int result = 0;
        for (String num : nums) {
            int temp = Integer.parseInt(num);
            result += temp;
        }
        return result;
    }

    private static List<String> sumLeaf(TreeNode root, String s) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        StringBuilder sb = new StringBuilder(s);
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(sb.toString());
            return result;
        }
        if (root.left != null) {
            List<String> leftPart = sumLeaf(root.left, sb.toString());
            result.addAll(leftPart);
        }
        if (root.right != null) {
            List<String> rightPart = sumLeaf(root.right, sb.toString());
            result.addAll(rightPart);
        }
        return result;
    }


    public static int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        List<Integer> nums = sumLeafNum(root, 0);
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }

    private static List<Integer> sumLeafNum(TreeNode root, int i) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int temp = i * 10 + root.val;
        if (root.left == null && root.right == null) {
            result.add(temp);
            return result;
        }
        if (root.left != null) {
            List<Integer> leftPart = sumLeafNum(root.left, temp);
            result.addAll(leftPart);
        }
        if (root.right != null) {
            List<Integer> rightPart = sumLeafNum(root.right, temp);
            result.addAll(rightPart);
        }
        return result;


    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(sumNumbers1(root));
    }

}
