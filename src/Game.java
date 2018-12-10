public class Game <T extends API>{
	private Board board;
	private T user;
	private boolean finished;
	private boolean win;

	Game(int size, int totalMines, T user){
		this.board = new Board(size, totalMines);
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
			while (true){
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
					break;
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
}
