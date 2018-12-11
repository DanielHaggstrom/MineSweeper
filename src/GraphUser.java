import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphUser extends JFrame implements API {
	// constants
	private static final int CELL_SIZE = 60;  // Cell width and height, in pixels
	private static final Color BGCOLOR_NOT_REVEALED = Color.GREEN;
	private static final Color FGCOLOR_NOT_REVEALED = Color.RED;    // flag
	private static final Color BGCOLOR_REVEALED = Color.DARK_GRAY;
	private static final Color FGCOLOR_REVEALED = Color.LIGHT_GRAY; // number of mines
	private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	private int COLS;

	private final int CANVAS_WIDTH = CELL_SIZE * COLS; // Game board width/height
	private final int CANVAS_HEIGHT = CELL_SIZE * COLS;

	private Board board;

	CellMouseListener listener;

	// buttons
	private JButton[][] btnCells;

	GraphUser(Board board){
		this.board = board;
		this.COLS = board.getSize();
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(COLS, COLS, 2, 2));
		this.btnCells  = new JButton[COLS][COLS];

		// Allocate a common instance of listener as the MouseEvent listener
		// for all the JButtons
		this.listener = new CellMouseListener();
		// Construct 10x10 JButtons and add to the content-pane
		for (int row = 0; row < COLS; row++) {
			for (int col = 0; col < COLS; col++) {
				btnCells[row][col] = new JButton();  // Allocate each JButton of the array
				cp.add(btnCells[row][col]);          // add to content-pane in GridLayout
				btnCells[row][col].addMouseListener(listener);
			}
		}
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
		int status = cell.getStatus();
		while (true){
			if (this.listener.isClick()){
				if (status == 0){
					return 2;
				}
			}
			else {
				if (status == 0){
					return 1;
				}
				if (status == 1){
					return 0;
				}
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
