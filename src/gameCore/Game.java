package gameCore;

public class Game <T extends API>{

	// Attributes
	private Board board;
	private T user;
	private boolean finished;
	private boolean win;
	private int round;

	/**
	 * Constructs a new game.
	 * @param board the board in which the game is played.
	 * @param user the user that will play the game. Must implement API.
	 */
	public Game(Board board, T user){
		this.board = board;
		this.user = user;
		Cell first = user.firstCell();
		board.distributeMines(first.getPositionX(), first.getPositionY());
		board.getCell(first.getPositionX(), first.getPositionY()).reveal();
		this.finished = false;
		this.win = false;
		this.round = 0;
		user.receiveRound(this.round);
	}

	/**
	 * Plays the game until the player either wins or loses.
	 */
	public void play(){
		user.showBoard();
		user.showTrueBoard();
		while (!finished){
			boolean isActionCorrect = false;
			while (!isActionCorrect){
				Cell selectedCell = user.selectCell();
				int action = user.action();
				if (selectedCell.isChangeValid(action)){
					switch (action){
						case 0:
							selectedCell.unflag();
							break;
						case 1:
							selectedCell.flag();
							break;
						case 2:
							selectedCell.reveal();
							break;
					}
					isActionCorrect = true;
					this.round++;
					user.receiveRound(this.round);
				}
				else {
					System.out.println("Incorrect Action");
				}
				if (board.isGameFinished()) {
					finished = true;
					if (board.playerWon()){
						this.win = true;
					}
				}
				user.showBoard();
				user.showTrueBoard();
			}
		}
	}

	/**
	 * Checks if the player won.
	 * @return true if and only if the player won.
	 */
	public boolean winner(){
		return this.win;
	}

	/**
	 * Getter for round number
	 * @return the round number.
	 */
	public int getRound(){
		return this.round;
	}

	public Game(Board board, T user, int x, int y){
		this.board = board;
		this.user = user;
		Cell first = board.getCell(x, y);
		board.distributeMines(first.getPositionX(), first.getPositionY());
		board.getCell(first.getPositionX(), first.getPositionY()).reveal();
		this.finished = false;
		this.win = false;
		this.round = 0;
		user.receiveRound(this.round);
	}
}
