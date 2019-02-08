import gameCore.API;
import gameCore.Board;
import gameCore.Cell;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphUser extends JFrame implements API {
	// constants & attributes
	private static int CELL_SIZE = 60;  // gameCore.Cell width and height, in pixel
	private static Color BGCOLOR_NOT_REVEALED;
	private static Color FGCOLOR_NOT_REVEALED;    // flag
	private static Color BGCOLOR_REVEALED;
	private static Color FGCOLOR_REVEALED; // number of mines
	private static Font FONT_NUMBERS;

	private static int COLS;

	private static int CANVAS_WIDTH; // gameCore.Game board width/height
	private static int CANVAS_HEIGHT;

	private static Board board;

	private static CellMouseListener listener;

	private static Container cp;

	// buttons
	private static JButton[][] btnCells;

	GraphUser(Board board){
		this.board = board;
		this.COLS = board.getSize();
		this.CANVAS_WIDTH = CELL_SIZE * COLS;
		this.CANVAS_HEIGHT = CELL_SIZE * COLS;
		this.BGCOLOR_NOT_REVEALED = Color.GREEN;
		this.FGCOLOR_NOT_REVEALED = Color.RED;
		this.BGCOLOR_REVEALED = Color.DARK_GRAY;
		this.FGCOLOR_REVEALED = Color.LIGHT_GRAY;
		this.FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
		this.cp = this.getContentPane();
		cp.setLayout(new GridLayout(COLS, COLS, 2, 2));
		this.btnCells  = new JButton[COLS][COLS];

		// Allocate a common instance of listener as the MouseEvent listener
		// for all the JButtons
		this.listener = new CellMouseListener();
		// Construct JButtons and add to the content-pane
		GraphUser.construcButtons();

		// Set the size of the content-pane and pack all the components
		//  under this container.
		cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // handle window-close button
		setTitle("Mineswepper");
		setVisible(true);   // show it

	}

	/**
	 * Shows the board as seen by the user.
	 */
	@Override
	public void showBoard() {//actually updates the board window
		setVisible(false);
		this.updateGraphUser();
		setVisible(true);
	}

	/**
	 * Shows the debugging board.
	 */
	@Override
	public void showTrueBoard() {//actually updates the debugging board window

	}

	/**
	 * Gets the first cell chosen by the user.
	 *
	 * @return an array of integers. the 0 index contains the x-coordinate and the 1 index contains the y-coordinate.
	 */
	@Override
	public Cell firstCell() {
		return this.selectCell();
	}

	/**
	 * Obtain a cell selected by the user in order to perform an action on it.
	 *
	 * @return an array of integers. the 0 index contains the x-coordinate and the 1 index contains the y-coordinate. If the user wants to change the action, the return is -1,-1.
	 */
	@Override
	public Cell selectCell() {
		while (!this.listener.getAuxButton()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.listener.setAuxButton(false);
		int x = this.listener.getAuxCell()[0];
		int y = this.listener.getAuxCell()[1];
		return this.board.getCell(x, y);
	}

	/**
	 * Performs an action in the cell
	 *
	 * @return An integer: 0 -> Unflag, 1 -> Flag, 2 -> Reveal
	 */
	@Override
	public int action() {
		int x = this.listener.getAuxCell()[0];
		int y = this.listener.getAuxCell()[1];
		Cell cell = this.board.getCell(x, y);
		int[] status = cell.display();
		while (true){
			if (this.listener.isClick()){
				if (status[0] == 0){// reveal a hidden cell
					return 2;
				}
			}
			else {
				if (status[0] == 0){// flag
					return 1;
				}
				if (status[0] == 1){// unflag
					return 0;
				}
			}
		}
	}

	/**
	 * Receives the round number.
	 *
	 * @param round integer.
	 */
	@Override
	public void receiveRound(int round) {
		/*TODO*/
	}

	private void updateGraphUser(){
		for (int i = 0; i < this.board.getSize(); i++) {
			for (int j = 0; j < this.board.getSize(); j++) {
				Cell cell = this.board.getCell(i, j);
				JButton button = this.btnCells[i][j];
				int[] display = cell.display();
				if (display[0] == 0){
					// hidden
					button.setForeground(FGCOLOR_NOT_REVEALED);
					button.setBackground(BGCOLOR_NOT_REVEALED);
					button.setFont(FONT_NUMBERS);
					button.setText("");       // display blank
				}
				if (display[0] == 1){
					//flagged
					button.setText("!");
				}
				if (display[0] == 2){
					button.setForeground(FGCOLOR_REVEALED);
					button.setBackground(BGCOLOR_REVEALED);
					button.setText("" + display[1]);
				}
				if (display[0] == 3){
					button.setForeground(FGCOLOR_NOT_REVEALED);
					button.setBackground(BGCOLOR_REVEALED);
					button.setText("X");
				}
			}
		}
	}

	private static void construcButtons(){
		for (int row = 0; row < COLS; row++) {
			for (int col = 0; col < COLS; col++) {
				btnCells[row][col] = new JButton();  // Allocate each JButton of the array
				cp.add(btnCells[row][col]);          // add to content-pane in GridLayout
				btnCells[row][col].addMouseListener(listener);
				btnCells[row][col].setEnabled(true);  // enable button
				btnCells[row][col].setForeground(FGCOLOR_NOT_REVEALED);
				btnCells[row][col].setBackground(BGCOLOR_NOT_REVEALED);
				btnCells[row][col].setFont(FONT_NUMBERS);
				btnCells[row][col].setText("");       // display blank
			}
		}
	}

	private class CellMouseListener extends MouseAdapter {
		private boolean auxButton;
		private int auxCell[];
		private boolean click;// true -> left click, false -> right click

		public boolean getAuxButton(){
			return auxButton;
		}

		public void setAuxButton(boolean b){
			this.auxButton = b;
		}

		public int[] getAuxCell() {
			return auxCell;
		}

		public boolean isClick() {
			return click;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			this.auxCell = new int[2];
			// Determine the (row, col) of the JButton that triggered the event

			// Get the source object that fired the Event
			JButton source = (JButton)e.getSource();
			// Scan all rows and columns, and match with the source object
			boolean found = false;
			for (int row = 0; row < COLS && !found; ++row) {
				for (int col = 0; col < COLS && !found; ++col) {
					if (source == btnCells[row][col]) {
						this.auxCell[0] = row;
						this.auxCell[1] = col;
						this.auxButton = true;
						System.out.println("row: "+ row +", col: "+ col);
						found = true;   // break both inner/outer loops
					}
				}
			}

			if (e.getButton() == MouseEvent.BUTTON1) {  // Left-button clicked
				this.click = true;

			} else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
				this.click = false;
			}
		}
	}

}
