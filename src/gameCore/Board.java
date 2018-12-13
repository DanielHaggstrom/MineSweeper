package gameCore;

import java.util.Random;

public class Board {
	
	// Attributes
	private Cell[][] cells;
	private int totalMines;// total mines must be lower than size^2 -1
	private int size; // size = 3 means there are 4 cells on the side
	
	
	// Constructor
	/**
	 * Constructs a new gameCore.Board.
	 * @param size is the length of the side (the board is a square). Size 3 means the side has 4 cells.
	 * @param totalMines int the amount of mines.
	 */
	public Board(int size, int totalMines) {
		// First, a sanity check that the number of mines makes sense
		if ((totalMines <= 0) || (totalMines >= size*size)) {
			System.out.println("totalMines is not valid");
			System.exit(1);
		}
		this.size = size;
		this.totalMines = totalMines;
		this.cells = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.cells[i][j] = new Cell(i, j);
			}
		}
	}

	// Methods - Getters

	public int getTotalMines() {
		return totalMines;
	}

	public int getSize() {
		return size;
	}

	public Cell[][] getCells() {
		return this.cells;
	}

	/**
	 * Checks if the game is over.
	 * @return True if the player revealed a mine or if there are no more blank cells. False otherwise.
	 */
	public boolean isGameFinished() {
		return this.playerLost() || this.playerWon();
	}
	
	/**
	 * Checks if a player revealed a cell with a mine.
	 * @return true if a player revealed a mined cell.
	 */
	private boolean playerLost() {
		boolean answer = false;
		for (int i = 0; i < size && !answer; i++) {
			for (int j = 0; j < size && !answer; j++) {
				if (this.cells[i][j].getStatus() == 2 && this.cells[i][j].isMine()) {
					answer = true;
				}
			}
		}
		return answer;
	}

	/**
	 * Checks if a player won.
	 * @return True if there are no more blank cells and the player did not lose.
	 */
	public boolean playerWon() {
		boolean answer = true;
		for (int i = 0; i < size && answer; i++) {
			for (int j = 0; j < size && answer; j++) {
				if (this.cells[i][j].getStatus() == 0) {
					answer = false;
				}
			}
		}
		return answer && !this.playerLost();
	}

	/**
	 * Gets the cell in the specified coordinates.
	 * @param x int the row of the cell.
	 * @param y int the column of the cell.
	 * @return A cell situated in row x, column y.
	 */
	public Cell getCell(int x, int y){
		return this.cells[x][y];
	}
	
	/**
	 * Places mines into random positions on the board.
	 * @param safeX int is the x coordinate of the first chosen cell.
	 * @param safeY int is the y coordinate of the first chosen cell.
	 */
	public void distributeMines (int safeX, int safeY) {
		// places all mines in random positions
		// Mines should not be able to exist outside the board
		int placedMines = 0;
		int[][] mines = new int[2][this.totalMines];
		// Now we initialize the values to -1 (which will never occur naturally). Otherwise the cell (0,0) would always be skipped
		for (int i = 0; i < mines[0].length; i++) {
			mines[0][i] = -1;
			mines[1][i] = -1;
		}
		while (placedMines < this.totalMines) {
			Random rnd = new Random();
			int x = rnd.nextInt(this.size);
			int y = rnd.nextInt(this.size);
			if (!Board.alreadyExists(mines, x, y) && ((x != safeX) || (y != safeY))) {
				mines[0][placedMines] = x;
				mines[1][placedMines] = y;
				placedMines++;
			}
		}
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (Board.alreadyExists(mines, i, j)) {
					this.cells[i][j].setMine(true);
				}
			}
		}
		// It is now a good time to do some housekeeping.
		this.updateNeighbours();
		this.updateSurroundingMines();
	}

	/**
	 * Updates all cells' surrounding mines.
	 */
	private void updateSurroundingMines() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.cells[i][j].updateSurroundingMines();
			}
		}
	}

	/**
	 * Updates all cell's neighbours. Should only be called once.
	 */
	public void updateNeighbours(){
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				Cell cell = this.cells[i][j];
				for (int x = i -1; x < i +2; x++) {
					for (int y = j -1; y < j +2; y++) {
						if (i != x || j != y){
							try {
								Board.checkCell(x, y, size);
								cell.addNeighbour(cells[x][y]);
							}
							catch (NoSuchCellException ex){

							}
						}
					}
				}
			}
		}
	}

	/**
	 * Given an 2xn array, returns true if a certain pair of number are already on it.
	 * @param array of integers containing the coordinates of mines.
	 * @param x is the x-coordinate to be checked.
	 * @param y is the y-coordinate to be checked.
	 * @return true if and only if the pair of integers already exist in the array.
	 */
	private static boolean alreadyExists(int[][] array, int x, int y) {
		boolean answer = false;
		for (int i = 0; i < array[0].length; i++) {
			if ((array[0][i] == x) && (array[1][i] == y)) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	/**
	 * Checks that a cell is within the board
	 * @param x
	 * @param y
	 * @param size
	 * @return True if and only if the cell exists in the board.
	 */
	public static void checkCell(int x, int y, int size) throws NoSuchCellException {
		boolean answer = true;
		if ((x < 0) || (x > size -1) || (y < 0) || (y > size -1)) {
			answer = false;
		}
		if (!answer){
			throw new NoSuchCellException();
		}
	}
}
