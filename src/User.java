import gameCore.API;
import gameCore.Board;
import gameCore.Cell;
import gameCore.NoSuchCellException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User implements API {

	private Board board;

	User(Board board){
		this.board = board;
	}

	public void showBoard() {
		int size = this.board.getSize();

		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.print("-");
			System.out.println();
			for (int j = 0; j < size; j++) {
				Cell cell = board.getCell(i, j);
				if (cell.getStatus() == 0) {
					System.out.print("| ");
				}
				else if (cell.getStatus() == 1) {
					System.out.print("|!");
				}
				else if (cell.getStatus() == 2){
					System.out.print("|"+ cell.getSurroundingMines());
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

	public void showTrueBoard() {
		int size = this.board.getSize();
		System.out.println();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.print("-");
			System.out.println();
			for (int j = 0; j < size; j++) {
				Cell cell = board.getCells()[i][j];
				if (cell.isMine()) {
					System.out.print("|x");
				}
				else {
					System.out.print("|" + cell.getSurroundingMines());
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
					System.out.println("Invalid Selection.");
				}
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid Selection.");
			}
		}
		return answer;
	}

	public Cell selectCell() {
		int x = this.getCoordinate("x");
		int y = this.getCoordinate("y");
		return this.board.getCell(x, y);
	}

	public Cell firstCell() {
		return this.selectCell();
	}
	
	/**
	 * Gets a coordinate of a cell, selected by the user to perform an action on it.
	 * @param s String, the name of the coordinate.
	 * @return An integer.
	 */
	public int getCoordinate(String s) {
		boolean correct = false;
		int answer = -1;
		while(!correct) {
			System.out.println("Input "+ s +"-coordinate.");
			try {
				Scanner sc = new Scanner(System.in);
				int coordinate = sc.nextInt();
				try {
					Board.checkCell(coordinate, 0, this.board.getSize());
					correct = true;
					answer = coordinate;
				}
				catch (NoSuchCellException ex){
					System.out.println("Invalid Selection.");
				}
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid Selection.");
			}
		}
		return answer;
	}
	
	public int action() {
		String[] menu = new String[4];
		menu[0] = "Select an action:";
		menu[1] = "Reveal a cell.";
		menu[2] = "Flag a cell.";
		menu[3] = "Unflag a cell";

		int answer = -1;
		
		switch(User.menu(menu)) {
		case 1:
			{
				answer = 2;
				break;
			}
		case 2:
			{
				answer = 1;
				break;
			}
		case 3:
			{
				answer = 0;
				break;
			}
		}
		return answer;
	}
	
}
