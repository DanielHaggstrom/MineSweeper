import java.util.Random;

public class Board {
	
	// Attributes
	
	private Cell[][] cells;
	private int totalMines;
	private int size; // size = 3 means there are 4 cells on the side
	// total mines must be lower than size^2 -1
	
	// Constructor
	
	Board (int size, int totalMines) {
		// First, a sanity check that the number of mines makes sense
		if ((totalMines <= 0) || (totalMines >= size*size)) {
			System.out.println("totalMines is not valid");
			System.exit(1);
		}
		this.size = size;
		this.totalMines = totalMines;
		this.cells = new Cell[size][size];
		int[][] mines = Board.distributeMine(this.size, this.totalMines);
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
	
	
	// Dynamic Methods
	
	
	
	// Static Methods
	
	private static int[][] distributeMine (int size, int totalMines) {
		// places all mines in random positions
		// Mines should not be able to exist outside the board
		int placedMines = 0;
		int[][] mines = new int[2][totalMines];
		// Now we initialize the values to null. Otherwise the cell (0,0) would always be skipped
		for (int i = 0; i < mines[0].length; i++) {
			mines[0][i] = size;
			mines[1][i] = size;
		}
		while (placedMines < totalMines) {
			Random rnd = new Random();
			int x = rnd.nextInt(size);
			int y = rnd.nextInt(size);
			if (!Board.alreadyExists(mines, x, y)) {
				mines[0][placedMines] = x;
				mines[1][placedMines] = y;
				placedMines++;
			}
		}
		return mines;
	
	}
	
	private static boolean alreadyExists(int[][] array, int x, int y) {
		// given an 2xn array, returns true if a certain pair of number are already on it
		boolean answer = false;
		for (int i = 0; i < array[0].length; i++) {
			if ((array[0][i] == x) && (array[1][i] == y)) {
				answer = true;
				break;
			}
		}
		
		return answer;
		
	}
	
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
	
	private static boolean isCellValid(int x, int y, int size) {
		// checks that a cell is within the board
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
