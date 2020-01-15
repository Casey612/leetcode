package exec.Tree;

import java.util.*;

/**
 * all the link sum max
 * not a single line
 * but links
 */
public class BinaryTreeMaximumLinkSum {

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Result result = findMaxPath(root);
        return result.getMax();
    }

    private Result findMaxPath(TreeNode root) {
        int cur = root.val;
        if (root.left == null && root.right == null) {
            return Result.create(cur, true);
        } else if (root.left != null && root.right != null) {
            //左右子节点都存在
            Result leftPart = findMaxPath(root.left);
            int leftMax = leftPart.getMax();
            Result rightPart = findMaxPath(root.right);
            int rightMax = rightPart.getMax();

            if (!leftPart.isLinked() && !rightPart.isLinked()) {
                //子节点不具有联通性
                if (cur >= leftMax && cur >= rightMax) {
                    return Result.create(cur, true);
                } else {
                    return Result.create(Math.max(leftMax, rightMax), false);
                }
            } else if (leftPart.isLinked() && rightPart.isLinked()) {
                //两个子节点都具有联通性
                int[] array = new int[]{cur, cur + leftMax, cur + rightMax};
                Arrays.sort(array);
                int temp = array[2];
                if (leftMax > temp || rightMax > temp) {
                    return Result.create(Math.max(leftMax, rightMax), false);
                } else {
                    return Result.create(temp, true);
                }
            } else {
                //只有一个子节点具有联通性
                Result notLinked = leftPart.isLinked() ? rightPart : leftPart;
                Result linked = leftPart.isLinked() ? leftPart : rightPart;

                int[] array = new int[]{cur, cur + linked.getMax()};
                Arrays.sort(array);
                int temp = array[1];
                if (notLinked.getMax() > temp && notLinked.getMax() > linked.getMax()) {
                    return Result.create(notLinked.getMax(), false);
                } else if (linked.getMax() >= notLinked.getMax() && linked.getMax() > temp){
                    return Result.create(linked.getMax(), false);
                } else {
                    return Result.create(temp, true);
                }
            }
        } else {
            //只有一个子节点
            TreeNode node = root.left == null ? root.right : root.left;
            Result sub = findMaxPath(node);
            int subMax = sub.getMax();
            if (sub.isLinked()) {
                int sum = cur + subMax;
                if (sum >= cur && sum >= subMax) {
                    return Result.create(sum, true);
                } else if (cur >= sum && cur >= subMax) {
                    return Result.create(cur, true);
                } else {
                    return Result.create(subMax, false);
                }
            } else {
                if (cur > subMax) {
                    return Result.create(cur, true);
                } else {
                    return Result.create(subMax, false);
                }
            }

        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(-6);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(9);
        root.left = n1;
        root.right = n2;
        n2.left = n3;
        n2.right = n4;
        BinaryTreeMaximumLinkSum summer = new BinaryTreeMaximumLinkSum();
        System.out.println(summer.maxPathSum(root));
    }

    private static class Result {
        int max;
        boolean linked;

        private Result() {
        }

        public static Result create(int max, boolean linked) {
            Result result = new Result();
            result.max = max;
            result.linked = linked;
            return result;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public void setLinked(boolean linked) {
            this.linked = linked;
        }

        public int getMax() {
            return max;
        }

        public boolean isLinked() {
            return linked;
        }
    }

}
