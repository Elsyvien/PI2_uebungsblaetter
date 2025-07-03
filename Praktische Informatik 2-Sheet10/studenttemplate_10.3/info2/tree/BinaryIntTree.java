package info2.tree;


// ----------------------------------------------------------------
// Exercise 3 (b) -- general class structure
// ----------------------------------------------------------------

public class BinaryIntTree {
    
    private BinaryIntTreeNode root;
    
    public BinaryIntTree() {
        this.root = null;
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (c)
    // ----------------------------------------------------------------
    
    private BinaryIntTreeNode insertSortedHelper(
        final BinaryIntTreeNode node,
        final int value
    ) {
        if (node == null) {
            return new BinaryIntTreeNode(value);
        }
        
        if (value < node.value) {
            node.left = this.insertSortedHelper(node.left, value);
        } else if (value > node.value) {
            node.right = this.insertSortedHelper(node.right, value);
        }
        return node;
        
    }
    
    public void insertSorted(final int value) {
        this.root = insertSortedHelper(this.root, value);
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (d)
    // ----------------------------------------------------------------

    private BinaryIntTreeNode minNode(final BinaryIntTreeNode ptr) {
        if (ptr == null) { 
            return null;
        } else if (ptr.left == null) {
            return ptr;
        } else {
            return minNode(ptr.left);
        }
    }
    
    private BinaryIntTreeNode removeSortedHelper(
        final BinaryIntTreeNode ptr, final int value
    ) {
        if (ptr == null ) {
            return null;
        }
        
        if (value < ptr.value) {
            ptr.left = removeSortedHelper(ptr.left, value);
            return ptr;
        } else if (value > ptr.value) {
            ptr.right = removeSortedHelper(ptr.right, value);
            return ptr;
        } else {
            if ((ptr.left == null) && (ptr.right == null)) {
                //
                // Case 1:
                // - delete immediately!
                //
                return null;
            } else if (ptr.right == null) {
                //
                // Case 2 (a): 
                // - list-remove (lift left)
                //
                return ptr.left;
            } else if (ptr.left == null) {
                //
                // Case 2 (b): 
                // - list-remove (lift right)
                //
                return ptr.right;
            } else {
                //
                // Complex Case 3:
                // - search smallest value in right tree.
                // - delete in right tree (case 1 or 2)
                // - replace current no with new node containing
                //   right-min value.
                //
                final BinaryIntTreeNode minNode = minNode(ptr.right);
                final int minValue = minNode.value;
                ptr.right = removeSortedHelper(ptr.right, minValue);
                return new BinaryIntTreeNode(
                    minValue, ptr.left, ptr.right
                );
            }
        }
    }
    
    public void removeSorted(final int value) {
        this.root = removeSortedHelper(this.root, value);
    }
    
    // ----------------------------------------------------------------
    // Exercise 3 (e)
    // ----------------------------------------------------------------
    
    private int heightHelper(final BinaryIntTreeNode ptr) {
        if (ptr == null) {
            return 0;
        }
        return 1 + Math.max(
            this.heightHelper(ptr.left),
            this.heightHelper(ptr.right)
        );
    }
        
    public int height() {
        return this.heightHelper(this.root);
    }
    
    private int balanceFactor(final BinaryIntTreeNode ptr) {
        if (ptr == null) return 0;
        return heightHelper(ptr.right) - heightHelper(ptr.left); 
    }
    
    private boolean isBalancedHelper(final BinaryIntTreeNode ptr) {
        if (ptr == null) {
            return true;
        }
        return (
            isBalancedHelper(ptr.left) && 
            isBalancedHelper(ptr.right) &&
            (Math.abs(this.balanceFactor(ptr)) <= 1)
        );
    }
    
    public boolean isBalanced() {
        return this.isBalancedHelper(this.root);
    }
    // ----------------------------------------------------------------
    // Exercise 3 (f)
    // ----------------------------------------------------------------

    // ----------------------------------------------------------------
    // Exercise 3 (g)
    // ----------------------------------------------------------------

    private void visit(final BinaryIntTreeNode node) {
        System.out.println(node.value);
    }
    
    private void traverseDepthFirstPreOrderHelper(final BinaryIntTreeNode node) {
        if (node == null) {
            return;
        }
        visit(node);
        traverseDepthFirstPreOrderHelper(node.left);
        traverseDepthFirstPreOrderHelper(node.right);
    }
    
    public void traverseDepthFirstPreOrder() {
        traverseDepthFirstPreOrderHelper(this.root);
    }
    
    
    public static void main(String[] args) {
        BinaryIntTree tree = new BinaryIntTree();
        tree.insertSorted(16);
        System.out.println(tree.isBalanced());
        tree.insertSorted(8);
        System.out.println(tree.isBalanced());
        tree.insertSorted(32);
        System.out.println(tree.isBalanced());
        tree.insertSorted(4);
        System.out.println(tree.isBalanced());
        tree.insertSorted(6);
        System.out.println(tree.isBalanced());
        tree.insertSorted(12);
        System.out.println(tree.isBalanced());
        tree.insertSorted(18);
        System.out.println(tree.isBalanced());
        tree.insertSorted(48);
        System.out.println(tree.isBalanced());
        tree.insertSorted(20);
        
        tree.traverseDepthFirstPreOrder();
        System.out.println();
        
        
        
        
        tree.removeSorted(16);
        tree.traverseDepthFirstPreOrder();
        
    }
    
}
