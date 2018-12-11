
public class MainTest {

	public static void main(String[] args) {
		// this main is for testing purposes

		int size = 3;
		int mines = 1;
		Board board = new Board(size, mines);
		//User user = new User(board);
		GraphUser user = new GraphUser(board);
		//Game<User> game = new Game<>(board, user);
		Game<GraphUser> game = new Game<>(board, user);
		game.play();
		if (game.winner()){
			System.out.println("Congratulations!");
		}
		else {
			System.out.println("Boo!");
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
