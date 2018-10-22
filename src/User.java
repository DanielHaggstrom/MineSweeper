import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
	
	
	
	// All functions are static
	/**
	 * Shows the board as seen by the user. Revealed cells show the surrounding mines, flagged cells show '!' and if a mine is hit, is shows 'x'. All other cells are blank.
	 * @param board
	 */
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
				if (board.getCells()[i][j].getStatus() == 0) {
					System.out.print("| ");
				}
				else if (board.getCells()[i][j].getStatus() == 1) {
					System.out.print("|!");
				}
				else {
					System.out.print("|"+ board.getCells()[i][j].getSurroundingMines());
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
	 * Shows the debugging board.
	 * @param board
	 */
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
	
	/**
	 * Obtain a cell selected by the user in order to perform an action on it.
	 * @param board Board, the board the user is playing on.
	 * @param action integer. 0 is to unflag, 1 is to flag, and 2 is to reveal.
	 * @return an array of integer. the 0 index contains the x-coordinate and the 1 index contains the y-coordinate.
	 */
	public static int[] selectCell(Board board, int action) {
		int[] answer = new int[2];
		boolean correct = false;
		while(!correct) {
			answer[0] = User.getCoordinate("x", board);
			answer[1] = User.getCoordinate("y", board);
			if (board.getCells()[answer[0]][answer[1]].isChangeValid(action)) {
				correct = true;
			}
			else {
				System.out.println("The cell selected cannot perform the action.");
			}
		}
		return answer;
	}
	
	/**
	 * Gets a coordinate of a cell, selected by the user to perform an action on it.
	 * @param s String, the name of the coordinate.
	 * @param board Board, the board the user is playing on.
	 * @return An integer.
	 */
	public static int getCoordinate(String s, Board board) {
		boolean correct = false;
		int answer = -1;
		while(!correct) {
			System.out.println("Input "+ s +"-coordinate.");
			try {
				Scanner sc = new Scanner(System.in);
				int y = sc.nextInt();
				if ((y >= 0) && (y < board.getSize())) {
					correct = true;
					answer = y;
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
