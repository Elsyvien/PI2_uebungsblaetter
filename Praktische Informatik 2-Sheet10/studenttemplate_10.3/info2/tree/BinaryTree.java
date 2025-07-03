package info2.tree;

public class BinaryTree<K extends Comparable<K>, V> {
    public BinaryTreeNode<K, V> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(BinaryTreeNode<K, V> root) {
        this.root = root;
    }

    public void insertSorted(final K key, final V value) {
        root = insertSortedHelper(root, key, value);
    }
    // This method inserts a key-value pair into the binary tree in sorted order.
    private BinaryTreeNode<K, V> insertSortedHelper(BinaryTreeNode<K, V> node, final K key, final V value) {
        if(node == null) {
            return new BinaryTreeNode<>(key, value); // Create a new node if the current node is null
        }
        int comparison = key.compareTo(node.key); // Compare the key with the current node's key
        if (comparison < 0) { // Check if the key is less than the current node's key
            node.left = insertSortedHelper(node.left, key, value); // Insert in the left subtree
        } else if (comparison > 0) { // Check if the key is greater than the current node's key
            node.right = insertSortedHelper(node.right, key, value); // Insert in the right subtree
        } else { // If the key is equal, update the value
            node.value = value;
        }
        return node;
    }
    public void removeSorted(final K key) {
        root = removeSortedHelper(root, key);
    }
    private BinaryTreeNode<K, V> removeSortedHelper(BinaryTreeNode<K, V> node, final K key) {
        if (node == null) {
            return null; // If the node is null, return null
        }
       int comparison = key.compareTo(node.key);
       if (comparison < 0) {
           node.left = removeSortedHelper(node.left, key);
           return node;
       }
       else if (comparison > 0) {
           node.right = removeSortedHelper(node.right, key);
           return node;
       } else { // No Children
           if (node.left == null && node.right == null) {
               return null;
           } // One Child (left)
           if (node.left == null) {
               return node.left;
           } // One Child (right)
           if (node.left == null) {
               return node.right;
           } // Two Children
           BinaryTreeNode<K, V> minNode = findMin(node.right); // Find the minimum node in the right subtree
           node.key = minNode.key;
           node.value = minNode.value; // Replace the current node's key and value with the minimum node's key and value
           return node;
       }
    }
    private BinaryTreeNode<K, V> findMin(BinaryTreeNode<K, V> node) {
        if (node == null)
            return null; // If the node is null, return null

        while (node.left != null) // Traverse to the leftmost node
            node = node.left;

        return node; // Return the leftmost node, which is the minimum
    }
    public int height() {
        return heightHelper(root);
    }
    private int heightHelper(BinaryTreeNode<K, V> node) {
        if (node == null)
            return -1; // Return -1 for null nodes to count edges

        int leftHeight = heightHelper(node.left); // Recursively calculate the height of the left subtree
        int rightHeight = heightHelper(node.right); // Recursively calculate the height of the right subtree
        return Math.max(leftHeight, rightHeight) + 1; // Return the maximum height plus one for the current node
    }
    public boolean isBalanced() {
        return isBalancedHelper(root);
    }
    private boolean isBalancedHelper(BinaryTreeNode<K, V> node) {
        if (node == null)
            return true;

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;
        return isBalancedHelper(node.left) && isBalancedHelper(node.right);
    }
    public V get(final K key) {
        return getHelper(root, key);
    }
    private V getHelper(BinaryTreeNode<K, V> node, final K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return getHelper(node.left, key);
        } else {
            return getHelper(node.right, key);
        }
    }
    public void traverseDepthFirstPreOrder(Consumer<V> consumer) {
        traverseDepthFirstPreOrderHelper(root, consumer);
    }

    private void traverseDepthFirstPreOrderHelper(BinaryTreeNode<K, V> node, Consumer<V> consumer) {
        if (node == null) {
            return;
        }
        consumer.consume(node.value);
        traverseDepthFirstPreOrderHelper(node.left, consumer);
        traverseDepthFirstPreOrderHelper(node.right, consumer);
    }
}


