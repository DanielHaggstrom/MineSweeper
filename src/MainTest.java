
public class MainTest {

	public static void main(String[] args) {
		// this main is for testing purposes
		
		int size = 10;
		int mines = 6;
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

}
