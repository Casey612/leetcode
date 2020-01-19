package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeseriallizeBinaryTree {

    private static final String COMMA_SPLITTER = ",";
    private static final String NULL_STR = "null";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append(NULL_STR);
            } else {
                sb.append(cur.val);
                queue.add(cur.left);
                queue.add(cur.right);
            }
            sb.append(COMMA_SPLITTER);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] array = data.split(COMMA_SPLITTER);
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int index = 1; index < array.length; index += 2) {
            TreeNode cur = queue.poll();
            String leftStr = array[index];
            String rightStr = (index + 1) < array.length ? array[index+1] : NULL_STR;
            if (!leftStr.equals(NULL_STR)) {
                TreeNode temp = new TreeNode(Integer.parseInt(leftStr));
                cur.left = temp;
                queue.add(temp);
            }
            if (!rightStr.equals(NULL_STR)) {
                TreeNode temp = new TreeNode(Integer.parseInt(rightStr));
                cur.right = temp;
                queue.add(temp);
            }
        }
        return root;
    }

}
