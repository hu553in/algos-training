import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {

    private final Map<Character, Node> nodes = new HashMap<>();

    public void insert(String word) {
        if (nodes.get(word.charAt(0)) == null) {
            nodes.put(word.charAt(0), newSubtree(0, word));
        } else {
            var node = searchNode(word);
            if (node != null) {
                if (node.depth < word.length() - 1) {
                    int nextDepth = node.depth + 1;
                    node.nodes.put(word.charAt(nextDepth), newSubtree(nextDepth, word.substring(nextDepth)));
                } else {
                    node.terminal = true;
                }
            }
        }
    }

    public boolean search(String word) {
        Node node = searchNode(word);
        return node != null && node.depth == word.length() - 1 && node.terminal;
    }

    public boolean startsWith(String prefix) {
        Node node = searchNode(prefix);
        return node != null && node.depth >= prefix.length() - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Node> entry : nodes.entrySet()) {
            toStringHelper(entry.getValue(), new StringBuilder().append(entry.getKey()), sb);
        }
        return sb.toString();
    }

    private void toStringHelper(Node node, StringBuilder prefix, StringBuilder result) {
        if (node.terminal) {
            result.append(prefix).append('\n');
        }
        for (Map.Entry<Character, Node> entry : node.nodes.entrySet()) {
            prefix.append(entry.getKey());
            toStringHelper(entry.getValue(), prefix, result);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private Node newSubtree(int depth, String word) {
        var node = new Node(depth);
        if (word.length() > 1) {
            node.nodes.put(word.charAt(1), newSubtree(depth + 1, word.substring(1)));
        } else {
            node.terminal = true;
        }
        return node;
    }

    private Node searchNode(String word) {
        Node node = nodes.get(word.charAt(0));
        if (node == null) {
            return null;
        }
        var depth = 0;
        while (depth < word.length() - 1 && node != null && node.nodes.containsKey(word.charAt(depth + 1))) {
            node = node.nodes.get(word.charAt(++depth));
        }
        return node;
    }

    @EqualsAndHashCode
    private static class Node {
        final int depth;
        boolean terminal;
        final Map<Character, Node> nodes = new HashMap<>();

        Node(int depth) {
            this.depth = depth;
        }
    }
}
