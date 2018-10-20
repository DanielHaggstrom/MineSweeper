import java.util.InputMismatchException;
import java.util.Scanner;

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
	
	/**
	 * Show a menu with several options, the user must choose one. If the user fails to choose properly (not an integer, or too small or too big an integer), the menu reappears.
	 * @param s is an array of type String, s[0] contains the general information and the other elements are the options that can be chosen from.
	 * @return an integer, showing with option was chosen. The first one correspond with 1, and so on.
	 */
	public static int menu(String[] s) {
		boolean correct = false;
		int answer = 0;
		while (!correct) {
			System.out.println("--------");
			System.out.println(s[0]);
			for (int i = 1; i < s.length ; i++) {
				System.out.println(i +". "+ s[i]);
			}
			System.out.println();
			try {
				Scanner sc = new Scanner(System.in);
				answer = sc.nextInt();
				if ((answer > 0) && (answer < s.length)) {
					correct = true;
				}
				else {
					System.out.println("Unvalid Selection.");
				}
			}
			catch (InputMismatchException ex) {
				System.out.println("Unvalid Selection.");
			}
		}
		return answer;
	}
	

}
