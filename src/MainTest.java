
public class MainTest {

	public static void main(String[] args) {
		// this main is for testing purposes
		
		Board board = new Board(4, 15);
		User.showBoard(board);
		User.showTrueBoard(board);
		
		String s1 = "menu";
		String s2 ="opcion1";
		String s3 ="opcion1";
		String s4 ="opcion1";
		String[] menu = new String[4];
		menu[0] = s1;
		menu[1] = s2;
		menu[2] = s3;
		menu[3] = s4;
		
		System.out.println("selected: " +User.menu(menu));
		

	}

}
