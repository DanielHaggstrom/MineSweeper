public class Game <T extends API>{
	private Board board;
	private T user;
	private boolean finished;
	private boolean win;

	Game(Board board, T user){
		this.board = board;
		this.user = user;
		user.showBoard();
		Cell first = user.firstCell();
		board.distributeMines(first.getPositionX(), first.getPositionY());
		board.getCell(first.getPositionX(), first.getPositionY()).reveal();
		this.finished = false;
		this.win = false;
	}

	public void play(){
		while (!finished){
			user.showBoard();
			user.showTrueBoard();//debug
			Game.checkStatus(board);
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
			}
		}
	}
	public boolean winner(){
		return this.win;
	}

	public static void checkStatus(Board board){
		int hidden = 0;
		int flagged = 0;
		int shown = 0;
		int mines = 0;
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				Cell cell = board.getCells()[i][j];
				if (cell.isMine()){
					mines++;
				}
				switch (cell.getStatus()){
					case 0:
						hidden++;
						break;
					case 1:
						flagged++;
						break;
					case 2:
						shown++;
						break;
				}
			}
		}
		System.out.println("Hidden "+ hidden +" | Flagged: "+ flagged +" | Shown: "+ shown +" | Mines: " + mines);
	}
}
