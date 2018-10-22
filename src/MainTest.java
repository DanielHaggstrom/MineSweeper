
public class MainTest {

	public static void main(String[] args) {
		// this main is for testing purposes
		
		Board board = new Board(4, 3, 1, 1);
		
		while(true) {
			User.showBoard(board);
			User.showTrueBoard(board);
			
			String[] menu = new String[4];
			menu[0] = "Select an accion:";
			menu[1] = "Reveal a cell.";
			menu[2] = "Flag a cell.";
			menu[3] = "Unflag a cell";
			
			switch(User.menu(menu)) {
			case 1:
				int[] cell = User.selectCell(board, 2);
				board.getCells()[cell[0]][cell[1]].reveal();
				break;
			case 2:
				//
				break;
			case 3:
				//
				break;
			}
			
		}
		

	}

}
