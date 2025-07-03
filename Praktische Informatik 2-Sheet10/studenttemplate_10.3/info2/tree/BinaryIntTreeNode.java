package info2.tree;


// ----------------------------------------------------------------
// Exercise 3 (a)
// ----------------------------------------------------------------

public class BinaryIntTreeNode {
    public int value;
    
    public BinaryIntTreeNode left;
    public BinaryIntTreeNode right;
    
    public BinaryIntTreeNode(final int value) {
        this.value = value;
    }
    
    public BinaryIntTreeNode(
        final int value,
        final BinaryIntTreeNode left,
        final BinaryIntTreeNode right
    ) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
}