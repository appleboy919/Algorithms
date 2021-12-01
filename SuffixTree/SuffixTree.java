package SuffixTree;

import java.util.*;

public class SuffixTree {
    class Node {
        int idx;
        String data;
        List<Node> children;

        Node(int i, String s) {
            idx = i;
            data = s;
            children = new ArrayList<>();
        }
    }

    Node root;

    SuffixTree() {
        root = new Node(0, null);
    }

    Node createSuffixTree(String s) {
        return null;
    }
}
