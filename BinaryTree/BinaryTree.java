package BinaryTree;

public class BinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        if (root.left == null && root.right == null)
            return root;
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        if (root.left != null)
            root.left = invertTree(root.left);
        if (root.right != null)
            root.right = invertTree(root.right);
        return root;
    }
}
