package info2.list;

public class IntListNode {  
    
    public int value;
    public IntListNode next;
    
    public boolean hasNext() {
        return this.next != null;
    }

    public IntListNode(final int value) {
        this(value, null);
    }
    
    public IntListNode(final int value, final IntListNode next) {
        this.value = value;
        this.next = next;
    }
    
}
