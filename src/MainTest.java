
public class MainTest<T extends API> {

	public static void main(String[] args) {
		// this main is for testing purposes
		
		int size = 10;
		int mines = 20;
		Board board = new Board(size, mines);
		User user = new User(board);// use generics
		Cell first = user.firstCell();
		board.distributeMines(first.getPositionX(), first.getPositionY());
		board.getCells()[first.getPositionX()][first.getPositionY()].reveal();
		boolean finished = false;
		while(!finished) {

			//TODO
			user.showBoard();
			user.showTrueBoard();
			Cell selectedCell = user.selectCell();
			int action = user.action();

			board.performAction();

			if (board.isGameFinished()) {
				finished = true;
			}
		}
		if (board.playerLost()) {
			System.out.println("You lost :(");
		}
		else {
			System.out.println("You won :)");
		}
	}


	public static void checkStatus(Board board){
		int hidden = 0;
		int flagged = 0;
		int shown = 0;
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				Cell cell = board.getCells()[i][j];
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
		System.out.println("Hidden "+ hidden +" | Flagged: "+ flagged +" | Shown: "+ shown);
	}
}
