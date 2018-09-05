
public class Cell {
	
	// Attributes
	
	private boolean mine;
	private int status; // 0 -> hidden	1-> flagged	2 -> shown
	private int surroundingMines;
	private int positionX;
	private int positionY;
	
	// Constructor
	
	Cell (boolean mine, int positionX, int positionY) {
		this.mine = mine;
		this.positionX = positionX;
		this.positionY = positionY;
		this.status = 0;
	}
	
	// Methods - Setters and Getters
	
	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getSurroundingMines() {
		return surroundingMines;
	}

	public void setSurroundingMines(int surroundingMines) {
		this.surroundingMines = surroundingMines;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void addSurroundingMines() {
		this.surroundingMines++;
	}
	
	
	// Methods
	

}
