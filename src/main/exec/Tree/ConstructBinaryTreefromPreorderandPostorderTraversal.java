package exec.Tree;

public class ConstructBinaryTreefromPreorderandPostorderTraversal {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        return constructFromPrePostHelp(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private TreeNode constructFromPrePostHelp(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        TreeNode result = new TreeNode(pre[preStart]);
        if (preStart + 1 <= preEnd) {
            int leftPartRootVal = pre[preStart + 1];
            int leftPostIndex = indexOf(post, leftPartRootVal);
            int leftSize = leftPostIndex - postStart + 1;
            TreeNode left = constructFromPrePostHelp(pre, preStart + 1, preStart + 1 + leftSize - 1,
                    post, postStart, leftPostIndex);

        }
        return result;
    }

    private int indexOf(int[] array, int v) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == v) {
                return index;
            }
        }
        return -1;
    }
}
