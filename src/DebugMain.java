import gameCore.Board;

import java.util.Random;

public class DebugMain {
	public static void main(String[] args) {
		int size = 4;
		int rounds = 10000;
		int[][] mineCount = new int[size][size];
		for (int i = 0; i < rounds; i++) {
			int mines = 3;
			Board board = new Board(size, mines);
			User user = new User(board);
			Random rnd = new Random();
			int x = rnd.nextInt(size);
			int y = rnd.nextInt(size);
			gameCore.Game<User> game = new gameCore.Game<>(board, user, x, y);
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (board.getCell(j,k).trueDisplay()[0] == 4){
						mineCount[j][k]++;
					}
				}

			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.println("Cell "+ i +", " + j +" : "+ mineCount[i][j]);
			}
		}
	}
}
