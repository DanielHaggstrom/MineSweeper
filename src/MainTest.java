import gameCore.Board;
import gameCore.Game;

public class MainTest {

	public static void main(String[] args) {
		// this main is for testing purposes

		int size = 8;
		int mines = 4;
		Board board = new Board(size, mines);
		//User user = new User(board);
		GraphUser user = new GraphUser(board);
		//gameCore.Game<User> game = new gameCore.Game<>(board, user);
		Game<GraphUser> game = new Game<>(board, user);
		game.play();
		if (game.winner()){
			System.out.println("Congratulations!");
		}
		else {
			System.out.println("Boo!");
		}
	}
}
