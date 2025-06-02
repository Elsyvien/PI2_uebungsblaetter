package info2.pendigit.gui;


/**
 * This listener interface provides methods to catch events
 * raised by the PenBoardPanel.
 * 
 * @author Sebastian Otte
 */
public interface PenBoardListener {
	/**
	 * This event is raised for each new data point drawn on the pen board.
	 * 
	 * @param x Current pen x position.
	 * @param y Current pen y position.
	 * @param pendown Is true when the current point is the first point of a stroke (onset event).
	 */
    public void onPenDraw(final double x, final double y, final boolean pendown);
	/**
	 * This event is raised after a certain writing pause. It indicates that writing of the current
	 * char/digit has ended.
	 */
    public void onFinished();
}