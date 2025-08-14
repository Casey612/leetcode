package exec.graph;

import java.util.*;

/**
 * # 133
 */
public class CloneGraph {


    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<>());
    }

    public Node cloneGraph(Node node, Map<Node, Node> visited) {
        if (node == null) {
            return null;
        }

        int val = node.val;
        List<Node> neighbors = node.neighbors;

        Node result = new Node(val);
        visited.put(node, result);

        if (neighbors == null || neighbors.isEmpty()) {
            return result;
        }
        for (Node neighbor : neighbors) {
            Node newNeighbor;
            if (!visited.containsKey(neighbor)) {
                newNeighbor = cloneGraph(neighbor, visited);
            } else {
                newNeighbor = visited.get(neighbor);
            }
            result.neighbors.add(newNeighbor);
        }
        return result;
    }


    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}


