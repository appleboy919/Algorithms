package Heap;

public class MaxHeap {
    class Node {
        int data;
        Node left, right;

        Node(int k) {
            data = k;
        }

        void setRight(Node r) {
            this.right = r;
        }

        void setLeft(Node l) {
            this.left = l;
        }
    }

    Node root;
    int size;

    MaxHeap() {
        root = null;
        size = 0;
    }

    Node insert(int x) {
        return null;
    }
}
