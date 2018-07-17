import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	// Attributes
	
	private ArrayList<Cell> cells;
	private int totalMines;
	private int size; // size = 3 means there are 4 cells on the side
	
	// Constructor
	
	Board (int size, int totalMines) {
		this.size = size;
		this.totalMines = totalMines;
		this.cells = new ArrayList<Cell>();
		int[][] mines = Board.distributeMine(this.size, this.totalMines);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Board.alreadyExists(mines, i, j)) {
					this.cells.add(new Cell(true, i, j));
				}
				else {
					this.cells.add(new Cell(false, i, j));
				}
			}
		}
	}
	
	// Static Methods
	
	private static int[][] distributeMine (int size, int totalMines) {
		// places one mine in a random position as long as it is not already occupied
		int placedMines = 0;
		int[][] mines = new int[2][totalMines + 1];
		while (placedMines <= totalMines) {
			Random rnd = new Random();
			int x = rnd.nextInt(size + 1);
			int y = rnd.nextInt(size + 1);
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
			}
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
	
	public ArrayList<Cell> getCells() {
		return this.cells;
	}
	
}
