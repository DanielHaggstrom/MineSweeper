package gameCore;

public interface API {

	/**
	 * Shows the board as seen by the user.
	 */
	public void showBoard();

	/**
	 * Shows the debugging board.
	 */
	public void showTrueBoard();

	/**
	 * Gets the first cell chosen by the user.
	 * @return an array of integers. the 0 index contains the x-coordinate and the 1 index contains the y-coordinate.
	 */
	abstract public Cell firstCell();

	/**
	 * Obtain a cell selected by the user in order to perform an action on it.
	 * @return an array of integers. the 0 index contains the x-coordinate and the 1 index contains the y-coordinate. If the user wants to change the action, the return is -1,-1.
	 */
	public Cell selectCell();

	/**
	 * Performs an action in the cell
	 * @return An integer: 0 -> Unflag, 1 -> Flag, 2 -> Reveal
	 */
	public int action();

	/**
	 * Receives the round number.
	 * @param round integer.
	 */
	public void receiveRound(int round);
}
