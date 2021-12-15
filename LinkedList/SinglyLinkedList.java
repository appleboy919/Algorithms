package LinkedList;

public class SinglyLinkedList {
    class Node {
        int val;
        Node next;

        Node(int v) {
            val = v;
            next = null;
        }
    }

    Node head;
    int len;

    public SinglyLinkedList() {
        head = null;
    }

    public int get(int index) {
        Node cur = head;
        if (cur == null)
            return -1;
        int i = 0;
        while (cur != null) {
            if (i == index)
                return cur.val;
            i++;
            cur = cur.next;
        }
        return -1;
    }

    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if (head == null)
            head = newNode;
        else {
            newNode.next = head;
            head = newNode;
        }
        len++;
    }

    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (head == null)
            head = newNode;
        else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
        len++;
    }

    public void addAtIndex(int index, int val) {
        if (index > len)
            return;
        Node newNode = new Node(val);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            int i = 0;
            Node cur = head;
            while (i < index - 1) {
                cur = cur.next;
                i++;
            }
            newNode.next = cur.next;
            cur.next = newNode;
        }
        len++;
    }

    public void deleteAtIndex(int index) {
        if (index >= len)
            return;
        if (index == 0)
            head = head.next;
        else {
            int i = 0;
            Node cur = head;
            while (i < index - 1) {
                cur = cur.next;
                i++;
            }
            cur.next = cur.next.next;
        }
        len--;
    }
}
