package exec.Tree;

import java.util.*;

/**
 * #652
 */
public class FindDuplicateSubtree {


    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            return result;
        }

        //add all node to travelList
        HashMap<String, Integer> hashMap = new HashMap<>();
        findDuplicateHelp(root, hashMap, result);
        return result;
    }

    private String findDuplicateHelp(TreeNode node, HashMap<String, Integer> map, List<TreeNode> result) {
        StringBuilder sb = new StringBuilder();
        if (node == null) {
            return "";
        }
        sb.append(node.val);
        sb.append('#');
        if (node.left != null) {
            String left = findDuplicateHelp(node.left, map, result);
            sb.append(left);
        }
        sb.append('#');
        if (node.right != null) {
            String right = findDuplicateHelp(node.right, map, result);
            sb.append(right);
        }
        String travelStr = sb.toString();
        if (map.containsKey(travelStr)) {
            int temp = map.get(travelStr);
            if (temp == 1) {
                result.add(node);
            }
            temp++;
            map.put(travelStr, temp);
        } else {
            map.put(travelStr, 1);
        }
        return travelStr;
    }

    /**
     * 方法1，时间复杂度过高
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            return result;
        }

        //add all node to travelList
        List<TreeNode> travelList = new ArrayList<>();
        travelTree(root, travelList);
        boolean[] flags = new boolean[travelList.size()];
        Set<TreeNode> set = new HashSet<>(travelList.size());
        Arrays.fill(flags, true);
        for (int i = 0; i < travelList.size(); i++) {
            if (flags[i]) {
                TreeNode cur = travelList.get(i);
                for (int j = i + 1; j < travelList.size(); j++) {
                    if (flags[j]) {
                        TreeNode other = travelList.get(j);
                        if (other.val == cur.val) {
                            if (isDuplicate(cur, other)) {
                                flags[j] = false;
                                resultAdd(cur, travelList, flags, set);
                            }
                        }
                    }
                }
            }
        }
        result.addAll(set);
        return result;
    }

    private void resultAdd(TreeNode root, List<TreeNode> travelList, boolean[] flags, Set<TreeNode> set) {
        if (root != null && !set.contains(root)) {
            set.add(root);
            int index = travelList.indexOf(root);
            flags[index] = false;
            resultAdd(root.left, travelList, flags, set);
            resultAdd(root.right, travelList, flags, set);
        }
    }

    /**
     * two nodes are duplicate to each other or not
     * @param node1 node1
     * @param node2 node2
     * @return if is duplicate, return true;
     *         if not, return false.
     */
    private boolean isDuplicate(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            //both of them are null
            return true;
        } else if (node1 == null || node2 == null) {
            //one of them is null
            return false;
        }
        //both are not null
        if (node1 == node2) {
            //equals itself is not duplicate
            return false;
        }
        if (node1.val == node2.val) {
            return isDuplicate(node1.left, node2.left) && isDuplicate(node1.right, node2.right);
        } else {
            return false;
        }
    }

    private void travelTree(TreeNode root, List<TreeNode> result) {
        if (root != null) {
            result.add(root);
            if (root.left != null){
                travelTree(root.left, result);
            }
            if (root.right != null) {
                travelTree(root.right, result);
            }
        }
    }
}
