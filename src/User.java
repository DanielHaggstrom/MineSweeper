
public class User {
	
	
	
	// All functions are static
	
	public static void showBoard(Board board) {
		int size = board.getSize();
		System.out.println();
		
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.print("-");
			System.out.println();
			for (int j = 0; j < size; j++) {
				System.out.print("| ");
			}
			System.out.print("|");
			System.out.println();
		}
		for (int j = 0; j < size; j++) {
			System.out.print("--");
		}
		System.out.print("-");
		System.out.println();
	}
	
	public static void showTrueBoard(Board board) {
		int size = board.getSize();
		System.out.println();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.print("-");
			System.out.println();
			for (int j = 0; j < size; j++) {
				if (board.getCells()[i][j].isMine()) {
					System.out.print("|x");
				}
				else {
					System.out.print("|" + board.getCells()[i][j].getSurroundingMines());
				}
			}
			System.out.print("|");
			System.out.println();
		}
		for (int j = 0; j < size; j++) {
			System.out.print("--");
		}
		System.out.print("-");
		System.out.println();
	}
	
	

}
