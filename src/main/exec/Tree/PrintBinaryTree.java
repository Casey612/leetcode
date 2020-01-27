package exec.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #655. Print Binary Tree
 */
public class PrintBinaryTree {

    /**
     * @param root node
     * @return m * n metric
     * m -> height
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            //leaf node
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(root.val));
            result.add(list);
            return result;
        }
        List<List<String>> leftPart = printTree(root.left);
        List<List<String>> rightPart = printTree(root.right);
        int height = leftPart.size() > rightPart.size() ? leftPart.size() : rightPart.size();
        int leftWidth = leftPart.size() > 0 ? leftPart.get(0).size() : 0;
        int rightWidth = rightPart.size() > 0 ? rightPart.get(0).size() : 0;
        int width = Math.max(leftWidth, rightWidth);
        List<String> curRow = new ArrayList<>();
        addEmpty(curRow, width);
        curRow.add(String.valueOf(root.val));
        addEmpty(curRow, width);
        result.add(curRow);
        for (int i = 0; i < height; i++) {
            List<String> left = leftPart.size() > i ? leftPart.get(i) : createEmpty(width);
            List<String> right = rightPart.size() > i ? rightPart.get(i) : createEmpty(width);
            if (left.size() < width) {
                left = fillList(left, width);
            }
            if (right.size() < width) {
                right = fillList(right, width);
            }
            List<String> row = new ArrayList<>(left);
            row.add("");
            row.addAll(right);
            result.add(row);
        }
        return result;
    }

    private static List<String> fillList(List<String> list, int width) {
        while (list.size() < width) {
            list = fillListHelper(list);
        }
        return list;
    }

    private static List<String> fillListHelper(List<String> list) {
        List<String> result = new ArrayList<>();
        result.add("");
        for (String s : list) {
            result.add(s);
            result.add("");
        }
        return result;
    }

    private List<String> createEmpty(int width) {
        List<String> result = new ArrayList<>();
        addEmpty(result, width);
        return result;
    }

    private static void addEmpty(List<String> curRow, int width) {
        for (int i = 0; i < width; i++) {
            curRow.add("");
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("4");
        list.add("E");
        list.add("6");
        list = fillList(list, 7);
        System.out.println(list.toString());
    }

}
