package info2.tree;


/**
 * Consumers take an item of generic type V 
 * and apply any functionality to it,
 * that is specified in {@link #consume(Object)}.
 * @author Daniel O'Grady
 * @author Sebastian Otte
 *
 * @param <V> 
 */
public interface Consumer<V> {
	
	/**
	 * Core functionality of the consumer.
	 * Receives an item and manipulates it or
	 * does any other side-effect-based
	 * operation.
	 * @param item the item that should be consumed.
	 */
    public void consume(final V item);
    
}