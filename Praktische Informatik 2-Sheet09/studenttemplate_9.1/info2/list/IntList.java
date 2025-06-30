package info2.list;

public class IntList {

    public static final int INVALID = Integer.MIN_VALUE;
    
    private IntListNode front; 
    
    public IntList() {
        this.front = null;
    }
    /**
     * Returns true if the list is empty.
     */
    public boolean isEmpty() {
        return this.front == null;
    }
    
    /**
     * Inserts a value at the front of list.
     * @param value The value that is to be inserted. 
     */
    public void addFront(final int value) {
        this.front = new IntListNode(value, this.front);
    }
    
    /**
     * Recursive helper method that traverses through a list inserts a new
     * node at the very end.
     * @param ptr Current list node.
     * @param value The value that is to be inserted.
     */
    private static void addBackHelper(final IntListNode ptr, final int value) {
        if (ptr.hasNext()) {
            addBackHelper(ptr.next, value);
        } else {
            ptr.next = new IntListNode(value);
        }
    }
    
    /**
     * Inserts a value at the end of the list.
     * @param value The value that is to be inserted.
     */
    public void addBack(final int value) {
        if (this.isEmpty()) {
            this.addFront(value);
        } else {
            addBackHelper(this.front, value);
        }
    }
    
    /**
     * Calculates (iteratively) the number of element within
     * the list.
     * @return Number of list elements/nodes.
     */
    public int size() {
        int ctr = 0;
        
        IntListNode ptr = this.front;
        
        while (ptr != null) {
            ctr++;
            ptr = ptr.next;
        }
        
        return ctr;
    }
    
    /**
     * Recursive helper method for get.
     * @param ptr Current list node.
     * @param i Index of the element that is to be returned.
     * @return Value at index i or INVALID.
     */
    private static int getHelper(final IntListNode ptr, int i) {
        if (ptr == null) {
            return INVALID;
        } else if (i == 0) {
            return ptr.value;
        }
        return getHelper(ptr.next, i-1);
    }
    /**
     * Recursive variant of the get method. Returns the i-th element
     * within the list or INVALID if the i-th element does not exist.
     * @param i Index of the element that is to be returned.
     * @return Value at index i or INVALID.
     */
    public int get(final int i) {
        return getHelper(this.front, i);
    }
    
    /**
     * Own implementation of the toString-method. This method will be
     * used automatically whenever an instance of IntList is, e.g., 
     * concatenated with a string.
     */
    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();
        IntListNode ptr = this.front;
        
        while (ptr != null) {
            out.append(ptr.value);
            if (ptr.hasNext()) {
                out.append(", ");
            }
            ptr = ptr.next;
        }
        
        return out.toString();
    }
        
    // ----------------------------------------------------------------
    // Exercise 1 (a)
    // ----------------------------------------------------------------
    
   
    public int find(final int value) {
        int index = 0;
        IntListNode ptr = this.front;

        while(ptr != null) {
            if(ptr.value == value) {
                return index;
            }
            index++;
            ptr = ptr.next;
        }
        return -1;
    }
    
    // ----------------------------------------------------------------
    // Exercise 1 (b)
    // ----------------------------------------------------------------
    
    public int min() {
        if(this.isEmpty()) {
            return INVALID;
        }

        int currentMin = this.front.value;
        IntListNode ptr = this.front.next;
        
        while(ptr != null) {
            if(currentMin > ptr.value) {
                currentMin = ptr.value;
            }
            ptr = ptr.next;
        }
        return currentMin;
    }
    
    // ----------------------------------------------------------------
    // Exercise 1 (c)
    // ----------------------------------------------------------------
    
    public int max() {
        if(this.isEmpty()) {
            return INVALID;
        }

        int currentMax = this.front.value;
        IntListNode ptr = this.front.next;
        
        while(ptr != null) {
            if(currentMax < ptr.value) {
                currentMax = ptr.value;
            }
            ptr = ptr.next;
        }
        return currentMax;
    }

    // ----------------------------------------------------------------
    // Exercise 1 (d)
    // ----------------------------------------------------------------

    public int[] asArray() {
        if (this.isEmpty()) {
            return new int[]{};
        }
        int size = this.size();
        int[] array = new int[size];
        IntListNode ptr = this.front;

        int i = 0;

        while(ptr != null) {
            array[i] = ptr.value; // Fill from the end
            i++;
            ptr = ptr.next;
        }

        return array;
    }    

    // ----------------------------------------------------------------
    // Exercise 1 (e)
    // ----------------------------------------------------------------
    
    public void remove(final int i) {
        if(i < 0 || front == null) return;

        if(i == 0){
            this.front = this.front.next;
            return;
        }
        IntListNode current = this.front;

        for(int index = 0; current != null && index < i -1; index++) {
            current = current.next;
        }

        if(current != null && current.next != null) {
            current.next = current.next.next;
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 1 (f)
    // ----------------------------------------------------------------

    public void reverse() {
        IntListNode current = this.front;
        IntListNode ptr = null;
        while (current != null){
            IntListNode next = current.next;
            current.next = ptr;
            ptr = current;
            current = next;
        }
        front = ptr;
    }



    public static IntList cons(final int ...values) {
        final IntList list = new IntList();
        for (int i = 0; i < values.length; i++) {
            list.addBack(values[i]);
        }
        return list;
    }
    
    // Helper method to compare arrays
    private static void assertEqualsList(final int[] a, final int[] b) {
        if (a.length != b.length) {
            System.out.println("FAIL: Array lengths differ - got " + a.length + ", expected " + b.length);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.out.println("FAIL: Arrays differ at index " + i + " - got " + a[i] + ", expected " + b[i]);
                return;
            }
        }
        System.out.println("Passed");
    }
    
    //Helper method to compare integers
    private static void assertEquals(int actual, int expected) {
        if (actual == expected) {
            System.out.println( + actual);
        } else {
            System.out.println("FAIL: got " + actual + ", expected " + expected);
        }
    }
    
    // Helper method for remove testing
    private static IntList remove(final IntList list, final int i) {
        list.remove(i);
        return list;
    }
    
    // Helper method for reverse testing
    private static IntList reverse(final IntList list) {
        list.reverse();
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println("=== IntList Test ===\n");
        
        System.out.println("Testing find() method:");
        System.out.print("cons(1,2,3,4).find(1): " );
        assertEquals(cons(1,2,3,4).find(1), 0);
        System.out.print("cons(1,2,3,4).find(2): ");
        assertEquals(cons(1,2,3,4).find(2), 1);
        System.out.print("cons(1,2,3,4).find(3): ");
        assertEquals(cons(1,2,3,4).find(3), 2);
        System.out.print("cons(1,2,3,4).find(4): ");
        assertEquals(cons(1,2,3,4).find(4), 3);
        System.out.print("cons(1,2,3,4).find(5): ");
        assertEquals(cons(1,2,3,4).find(5), -1);
        System.out.print("new IntList().find(0): ");
        assertEquals(new IntList().find(0), -1);
        System.out.println();
        
        
        System.out.println("Testing min() method:");
        System.out.print("cons(1,2,3,4).min(): ");
        assertEquals(cons(1,2,3,4).min(), 1);
        System.out.print("cons(4,3,2,1).min(): ");
        assertEquals(cons(4,3,2,1).min(), 1);
        System.out.print("cons(4,3,0,2,1).min(): ");
        assertEquals(cons(4,3,0,2,1).min(), 0);
        System.out.print("new IntList().min(): ");
        assertEquals(new IntList().min(), IntList.INVALID);
        System.out.println();
        
        
        System.out.println("Testing max() method:");
        System.out.print("cons(1,2,3,4).max(): ");
        assertEquals(cons(1,2,3,4).max(), 4);
        System.out.print("cons(4,3,2,1).max(): ");
        assertEquals(cons(4,3,2,1).max(), 4);
        System.out.print("cons(4,3,8,2,1).max(): ");
        assertEquals(cons(4,3,8,2,1).max(), 8);
        System.out.print("new IntList().max(): ");
        assertEquals(new IntList().max(), IntList.INVALID);
        System.out.println();
        
      
        System.out.println("Testing asArray() method:");
        System.out.print("cons().asArray() = int[]{} ");
        assertEqualsList(cons().asArray(), new int[]{});
        
        System.out.print("cons(1).asArray() = int[]{1}: ");
        assertEqualsList(cons(1).asArray(), new int[]{1});
        
        System.out.print("cons(2).asArray() = int[]{2}: ");
        assertEqualsList(cons(2).asArray(), new int[]{2});
        
        System.out.print("cons(1,2,3,4).asArray() = int[]{1,2,3,4}: ");
        assertEqualsList(cons(1,2,3,4).asArray(), new int[]{1,2,3,4});
        
        System.out.print("cons(1,2,3,4,0,0,0,0,0,0,0,0,0,0,0).asArray() = int[]{1,2,3,4,0,0,0,0,0,0,0,0,0,0,0}:");
        assertEqualsList(
            cons(1,2,3,4,0,0,0,0,0,0,0,0,0,0,0).asArray(), 
            new int[]{1,2,3,4,0,0,0,0,0,0,0,0,0,0,0}
        );
        System.out.println();
        
        
        System.out.println("Testing remove() method:");
        System.out.print("remove(cons(), 0).asArray() = int[]{}: ");
        assertEqualsList(remove(cons(), 0).asArray(), new int[]{});
        
        System.out.print("remove(cons(1), 0).asArray() = int[]{}: ");
        assertEqualsList(remove(cons(1), 0).asArray(), new int[]{});
        
        System.out.print("remove(cons(1,2,3,4), 0).asArray() = int[]{2,3,4}: ");
        assertEqualsList(remove(cons(1,2,3,4), 0).asArray(), new int[]{2, 3, 4});
        
        System.out.print("remove(cons(1,2,3,4), 1).asArray() = int[]{1,3,4}: ");
        assertEqualsList(remove(cons(1,2,3,4), 1).asArray(), new int[]{1, 3, 4});
        
        System.out.print("remove(cons(1,2,3,4), 2).asArray() = int[]{1,2,4}: ");
        assertEqualsList(remove(cons(1,2,3,4), 2).asArray(), new int[]{1, 2, 4});
        
        System.out.print("remove(cons(1,2,3,4), 3).asArray() = int[]{1,2,3}: ");
        assertEqualsList(remove(cons(1,2,3,4), 3).asArray(), new int[]{1, 2, 3});
        
        System.out.print("remove(cons(1,2,3,4), -1).asArray() = int[]{1,2,3,4}: ");
        assertEqualsList(remove(cons(1,2,3,4), -1).asArray(), new int[]{1, 2, 3, 4});
        
        System.out.print("remove(cons(1,2,3,4), 4).asArray() = int[]{1,2,3,4}: ");
        assertEqualsList(remove(cons(1,2,3,4), 4).asArray(), new int[]{1, 2, 3, 4});
        System.out.println();
        
        
        System.out.println("Testing reverse() method:");
        System.out.print("reverse(cons()).asArray() = int[]{}: ");
        assertEqualsList(reverse(cons()).asArray(), new int[]{});
        
        System.out.print("revesrse(cons(1)).asArray() = int[]{1}: ");
        assertEqualsList(reverse(cons(1)).asArray(), new int[]{1});
        
        System.out.print("reverse(cons(1,2)).asArray() = int[]{2,1}: ");
        assertEqualsList(reverse(cons(1,2)).asArray(), new int[]{2,1});
        
        System.out.print("reverse(cons(2,3,4)).asArray() = int[]{4,3,2}: ");
        assertEqualsList(reverse(cons(2,3,4)).asArray(), new int[]{4,3,2});
        
        System.out.print("reverse(cons(1,2,3,4)).asArray() = int[]{4,3,2,1}: ");
        assertEqualsList(reverse(cons(1,2,3,4)).asArray(), new int[]{4,3,2,1});
        
        System.out.print("reverse(reverse(cons(1,2,3,4))).asArray() = int[]{1,2,3,4}: ");
        assertEqualsList(reverse(reverse(cons(1,2,3,4))).asArray(), new int[]{1, 2, 3, 4});
        
       
    }
}
