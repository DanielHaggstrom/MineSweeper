import java.util.Random;

public class Board {
	
	// Attributes
	
	private Cell[][] cells;
	private int totalMines;// total mines must be lower than size^2 -1
	private int size; // size = 3 means there are 4 cells on the side
	
	
	// Constructor
	/**
	 * Constructs a new Board. The first cell chosen by the user should be safe.
	 * @param size is the length of the side (the board is a square). 3 means the side has 4 cells.
	 * @param totalMines int the maximum amount of mines.
	 * @param safeX int is the x coordinate of the first chosen cell.
	 * @param safeY int is the y coordinate of the first chosen cell.
	 */
	Board (int size, int totalMines, int safeX, int safeY) {
		// First, a sanity check that the number of mines makes sense
		if ((totalMines <= 0) || (totalMines >= size*size)) {
			System.out.println("totalMines is not valid");
			System.exit(1);
		}
		this.size = size;
		this.totalMines = totalMines;
		this.cells = new Cell[size][size];
		int[][] mines = Board.distributeMine(this.size, this.totalMines, safeX, safeY);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board.alreadyExists(mines, i, j)) {
					this.cells[i][j] = new Cell(true, i, j);
				}
				else {
					this.cells[i][j] = new Cell(false, i, j);
				}
			}
		}
		Board.updateSurroundingMines(mines, cells);
	}
	
	// Static Methods
	
	/**
	 * Places mines into random positions on the board.
	 * @param size int is the side length of the board.
	 * @param totalMines int is the amount of mines there should be.
	 * @param safeX int is the x coordinate of the first chosen cell.
	 * @param safeY int is the y coordinate of the first chosen cell.
	 * @return an array with the coordinates of the mines.
	 */
	private static int[][] distributeMine (int size, int totalMines, int safeX, int safeY) {
		// places all mines in random positions
		// Mines should not be able to exist outside the board
		int placedMines = 0;
		int[][] mines = new int[2][totalMines];
		// Now we initialize the values to size (which will never occur naturally). Otherwise the cell (0,0) would always be skipped
		for (int i = 0; i < mines[0].length; i++) {
			mines[0][i] = size;
			mines[1][i] = size;
		}
		while (placedMines < totalMines) {
			Random rnd = new Random();
			int x = rnd.nextInt(size);
			int y = rnd.nextInt(size);
			if (!Board.alreadyExists(mines, x, y) && ((x != safeX) || (y != safeY))) {
				mines[0][placedMines] = x;
				mines[1][placedMines] = y;
				placedMines++;
			}
		}
		return mines;
	
	}
	/**
	 * Given an 2xn array, returns true if a certain pair of number are already on it.
	 * @param array
	 * @param x
	 * @param y
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
	// Only to be used once.
	private static void updateSurroundingMines(int[][] mines, Cell[][] cells) {
		// for every mine on the board, check surrounding cells and add +1 to surroundingMines
		for (int i = 0; i < mines[0].length; i++) {
			int x = mines[0][i];
			int y = mines[1][i];
			for (int j = x - 1; j < x + 2; j++) {
				for (int k = y - 1; k < y + 2; k++) {
					if (Board.isCellValid(j, k, cells.length) && !((j == x) && (k == y))) {
						cells[j][k].addSurroundingMines();
					}
				}
			}
		}
		
	}
	/**
	 * Checks that a cell is within the board
	 * @param x
	 * @param y
	 * @param size
	 * @return True if and only if the cell exists in the board.
	 */
	private static boolean isCellValid(int x, int y, int size) {
		boolean answer = true;
		if ((x < 0) || (x > size -1) || (y < 0) || (y > size -1)) {
			answer = false;
		}
		return answer;
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
	
	
}
