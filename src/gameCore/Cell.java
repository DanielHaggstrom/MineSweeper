package gameCore;

import java.util.ArrayList;

public class Cell {
	
	// Attributes
	private boolean mine;
	private int status; // 0 -> hidden	1-> flagged	2 -> shown
	private int surroundingMines;
	private int positionX;
	private int positionY;
	private ArrayList<Cell> neighbours;
	
	// Constructor
	/**
	 * Constructs a new gameCore.Cell.
	 * @param positionX int, the position of the cell in the x-axis. Should be higher than or equal to 0 and lower than the size of the {@link Board}.
	 * @param positionY int, the position of the cell in the y-axis. Should be higher than or equal to 0 and lower than the size of the {@link Board}.
	 */
	Cell (int positionX, int positionY) {
		this.mine = false;
		this.positionX = positionX;
		this.positionY = positionY;
		this.status = 0;
		this.neighbours = new ArrayList<>();
	}


	
	// Methods
	/**
	 * Checks if the cell contains a mine.
	 * @return True if and only if the cell contains a mine.
	 */
	boolean isMine() {
		return mine;
	}

	/**
	 * Sets the value of mine
	 * @param mine
	 */
	void setMine(boolean mine) {
		this.mine = mine;
	}
	
	/**
	 * Indicates how many of the surrounding cells contain a mine.
	 * @return an integer amount of mines that are contiguous to the cell, including diagonals.
	 */
	int getSurroundingMines() {
		return surroundingMines;
	}

	/**
	 * Gets the row of the cell within the board.
	 * @return a positive integer. The first row produces 0, the second 1, and so on.
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Gets the column of the cell within the board.
	 * @return a positive integer. The first column produces 0, the second 1, and so on.
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Shows the status of the cell.
	 * 0 -> hidden	1-> flagged	2 -> shown
	 * @return  0 if hidden, 1 if flagged, and 2 if revealed.
	 */
	int getStatus() {
		return status;
	}

	/**
	 * Shows the information about the cell available to the user.
	 * @return an array of two integers. The first first one describes the cell's status,
	 * except if a revealed cell is also a mine. In that case, it is 4. The second number is -1,
	 * except if the cell is a revealed non-mine cell. Then it is the number of surrounding cells.
	 */
	public int[] display(){
		int[] answer = new int[2];
		answer[1] = -1;
		if (this.status == 0){
			answer[0] = 0;//hidden
		}
		if (this.status == 1){
			answer[0] = 1;//flagged
		}
		if (this.status == 2){
			if (this.mine){
				answer[0] = 4;;//revealed mine
			}
			else {
				answer[0] = 3;
				answer[1] = this.surroundingMines;//revealed cell
			}
		}
		return answer;
	}

	/**
	 * Reveals the cell, changing the status to 2. Only does this if the change is valid.
	 * @return true if and only if the change of status was successful.
	 */
	boolean reveal() {
		boolean answer = this.isChangeValid(2);
		if (answer) {
			this.status = 2;
			if (this.surroundingMines == 0){
				this.autoReveal();
			}
		}
		return answer;
	}

	/**
	 * Adds one cell to the list of this cell's neighbours.
	 * @param cell to be added.
	 */
	void addNeighbour(Cell cell){
		this.neighbours.add(cell);
	}

	/**
	 * Flags the cell, changing the status to 1. Only does this if the change is valid.
	 * @return true if and only if the change of status was successful.
	 */
	boolean flag() {
		boolean answer = this.isChangeValid(1);
		if (answer) {
			this.status = 1;
		}
		return answer;
	}

	/**
	 * Unflags the cell, changing the status to 0. Only does this if the change is valid.
	 * @return true if and only if the change of status was successful.
	 */
	boolean unflag() {
		boolean answer = this.isChangeValid(0);
		if (answer) {
			this.status = 0;
		}
		return answer;
	}

	/**
	 * Cheks if the intended change is valid for this cell.
	 * @param i an integer that represents the action. 0 -> Unflag, 1 -> Flag, 2 -> Reveal.
	 * @return true if and only if the change is valid.
	 */
	boolean isChangeValid(int i) {
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

	/**
	 * Gets the cell's neighbours.
	 * @return an ArrayList containing the cell's neighbours.
	 */
	public ArrayList<Cell> getNeighbours(){
		return this.neighbours;
	}

	/**
	 * Changes the cell's surrounding mines to match reality.
	 */
	void updateSurroundingMines(){
		int total = 0;
		for (int i = 0; i < this.getNeighbours().size(); i++) {
			Cell neighbour = this.getNeighbours().get(i);
			if (neighbour.isMine()){
				total++;
			}
		}
		this.surroundingMines = total;
	}

	/**
	 * Recursively auto reveals the neighbours of a cell with no surrounding mines.
	 */
	private void autoReveal(){
		for (int i = 0; i < this.neighbours.size(); i++) {
			Cell neighbour = this.neighbours.get(i);
			if (neighbour.getStatus() == 0){
				neighbour.reveal();
				if (neighbour.getSurroundingMines() == 0){
					neighbour.autoReveal();
				}
			}
		}
	}
}
