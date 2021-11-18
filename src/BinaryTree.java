public class BinaryTree {
    private TreeNode root;

    private TreeNode addRecursive(TreeNode currentNode, int value) {
        // Exit condition, will interrupt recursive calls
        // We have reached a position where we can insert our new node
        if(currentNode == null) {
            return new TreeNode(value);
        }

        // Check to see if we need to go left
        if(value < currentNode.getValue()) {
            currentNode.setLeft(addRecursive(currentNode.getLeft(), value));
        }
        // No, not left. Let us check if we need to go right
        else if(value > currentNode.getValue()) {
            currentNode.setRight(addRecursive(currentNode.getRight(), value));
        }
        // Nope, not right either. We have found a value that was already in the tree
        else {
            return currentNode;
        }

        return currentNode;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private void printInorderRecursive(TreeNode currentNode) {
        // Exit condition
        if(currentNode == null) {
            return;
        }

        // First, go left
        printInorderRecursive(currentNode.getLeft());

        // Then take care of this node's value
        System.out.println(currentNode.getValue());

        // Then, go right
        printInorderRecursive(currentNode.getRight());
    }

    public void printInorder() {
        printInorderRecursive(root);
    }

    private void printPreOrderRecursive(TreeNode currentNode) {
        // Exit condition
        if(currentNode == null) {
            return;
        }

        // Then take care of this node's value
        System.out.println(currentNode.getValue());

        // First, go left
        printPreOrderRecursive(currentNode.getLeft());

        // Then, go right
        printPreOrderRecursive(currentNode.getRight());
    }

    public void printPreOder() {
        printPreOrderRecursive(root);
    }

    private void printPostOrderRecursive(TreeNode currentNode) {
        // Exit condition
        if(currentNode == null) {
            return;
        }

        // First, go left
        printPostOrderRecursive(currentNode.getLeft());

        // Then, go right
        printPostOrderRecursive(currentNode.getRight());

        // Then take care of this node's value
        System.out.println(currentNode.getValue());
    }

    public void printPostOder() {
        printPostOrderRecursive(root);
    }

    private boolean isInTreeRecursive(TreeNode currentNode, int value) {
        // Exit conditions
        if(currentNode == null) {
            return false;
        }
        if(value == currentNode.getValue()) {
            return true;
        }

        // To the left?
        if(value < currentNode.getValue()) {
            return isInTreeRecursive(currentNode.getLeft(), value);
        }
        else {  // To the right?
            return isInTreeRecursive(currentNode.getRight(), value);
        }
    }

    public boolean isInTree(int value) {
        return isInTreeRecursive(root, value);
    }
}
