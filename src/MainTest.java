
public class MainTest<T> {

	public static void main(String[] args) {
		// this main is for testing purposes
		
		int size = 10;
		int mines = 20;
		int[] first = User.firstCell(size);
		Board board = new Board(size, mines, first[0], first[1]);
		board.getCells()[first[0]][first[1]].reveal();
		boolean finished = false;
		while(!finished) {
			User.showBoard(board);
			User.showTrueBoard(board);
			User.action(board);
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
