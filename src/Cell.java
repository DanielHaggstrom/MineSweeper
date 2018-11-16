
public class Cell {
	
	// Attributes
	
	private boolean mine;
	private int status; // 0 -> hidden	1-> flagged	2 -> shown
	private int surroundingMines;
	private int positionX;
	private int positionY;
	
	// Constructor
	/**
	 * Constructs a new Cell.
	 * @param mine boolean, true if the new cell should contain a mine, false otherwise.
	 * @param positionX int, the position of the cell in the x-axis. Should be higher than or equal to 0 and lower than the size of the {@link Board}.
	 * @param positionY int, the position of the cell in the y-axis. Should be higher than or equal to 0 and lower than the size of the {@link Board}.
	 */
	Cell (boolean mine, int positionX, int positionY) {
		this.mine = mine;
		this.positionX = positionX;
		this.positionY = positionY;
		this.status = 0;
	}
	
	// Methods
	
	/**
	 * Checks if the cell contains a mine.
	 * @return True if and only if the cell contains a mine.
	 */
	public boolean isMine() {
		return mine;
	}
	/* there should be no reason to use this method other than debugging.
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	*/
	
	/**
	 * Indicates how many of the surrounding cells contain a mine.
	 * @return an integer amount of mines that are contiguous to the cell, including diagonals.
	 */
	public int getSurroundingMines() {
		return surroundingMines;
	}
	/* there should be no reason to use this method other than debugging.
	public void setSurroundingMines(int surroundingMines) {
		this.surroundingMines = surroundingMines;
	}
	*/
	

	public int getPositionX() {
		return positionX;
	}
	/* there should be no reason to use this method other than debugging.
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	*/

	public int getPositionY() {
		return positionY;
	}
	/* there should be no reason to use this method other than debugging.
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	*/
	/**
	 * Shows the status of the cell.
	 * @return  0 if hidden, 1 if flagged, and 2 if revealed.
	 */
	public int getStatus() {
		return status;
	}
	/* there should be no reason to use this method other than debugging.
	public void setStatus(int status) {
		this.status = status;
	}
	*/
	/**
	 * Adds 1 to the number of surrounding mines.
	 */
	public void addSurroundingMines() {
		this.surroundingMines++;
	}
	
	public boolean reveal() {
		boolean answer = this.isChangeValid(2);
		if (answer) {
			this.status = 2;
		}
		return answer;
	}
	
	public boolean flag() {
		boolean answer = this.isChangeValid(1);
		if (answer) {
			this.status = 1;
		}
		return answer;
	}
	
	public boolean unflag() {
		boolean answer = this.isChangeValid(0);
		if (answer) {
			this.status = 0;
		}
		return answer;
	}
	
	public boolean isChangeValid(int i) {
		boolean answer = false;
		switch (this.status) {
		case 0:
			if ((i == 1) || (i == 2)) {// a blank cell can be flagged or revealed
				answer = true;
			}
			break;
		case 1:
			if (i == 0) {// a flagged cell can only be unflagged
				answer = true;
			}
			break;
		default:// a revealed cell cannot be interacted with
			break;
		}
		return answer;
	}

}
