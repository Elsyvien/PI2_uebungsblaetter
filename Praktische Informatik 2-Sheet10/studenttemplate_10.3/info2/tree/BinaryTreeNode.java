package info2.tree;


// ----------------------------------------------------------------
// Exercise 3 (a)
// ----------------------------------------------------------------

public class BinaryTreeNode<K extends Comparable<K>, V> {
    public V value;
    public K key;

    public BinaryTreeNode<K, V> left;
    public BinaryTreeNode<K, V> right;
    
    public BinaryTreeNode(final K key, final V value) {
        this.value = value;
        this.key = key;
        this.left = null;
        this.right = null; //  Explizit auf Null setzen
    }
    
    public BinaryTreeNode(
        final K key,
        final V value,
        final BinaryTreeNode<K, V> left,
        final BinaryTreeNode<K, V> right
    ) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.key = key;
    }
    
}