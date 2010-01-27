package connect4.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ConnectFourBoard extends Board {

	int[][] board;
	private int rowsNumber;
	private int columnsNumber;
	private static Logger logger = Logger.getLogger(ConnectFourBoard.class
			.getName());

	public ConnectFourBoard() {
		rowsNumber = 6;
		columnsNumber = 7;
		board = new int[rowsNumber][columnsNumber];
	}
	
	public int getRowsNumber() {
		return rowsNumber;
	}

	public int getColumnsNumber() {
		return columnsNumber;
	}

	/**
	 * Check if the specific place(row,column) is full.
	 * 
	 * @param row
	 *            index of the row (zero based).
	 * @param column
	 *            index of the column (zero based).
	 * @return true, if the <code>to</code> is between 0 and 6 (inclusive).
	 */
	public boolean isFullAt(int row, int column)
			throws InvalidColumnIndexException {
		return board[row][column] != 0;
	}

	/**
	 * Check the validity of the given column number(index).
	 * 
	 * @param columnNumber
	 *            the column index to check its validity.
	 * @return true, if the <code>columnNumber</code> is between 0 and <code>columnsNumber</code>-1 (inclusive).
	 */
	public boolean isValidColumn(int columnNumber) {
		return columnNumber >= 0 && columnNumber <= columnsNumber-1;
	}
	
	/**
	 * Check the validity of the given row number(index).
	 * 
	 * @param rowNumber
	 *            the row index to check its validity.
	 * @return true, if the <code>rowNumber</code> is between 0 and <code>rowsNumber</code>-1 (inclusive).
	 */
	public boolean isValidRow(int rowNumber) {
		return rowNumber >= 0 && rowNumber <= rowsNumber-1;
	}

	/**
	 * Puts the given <code>playerNumber</code> in the bottom most empty place
	 * in the board.
	 * 
	 * @param playerNumber
	 *            the Player's Number (non zero)
	 * @param columnNumber
	 *            the column index to put in it.
	 * @return false if the <code>columnNumber</code> is full, true otherwise.
	 * @throws InvalidColumnIndexException
	 *             if the
	 */
	public boolean put(int playerNumber, int columnNumber)
			throws InvalidColumnIndexException {
		if (!isValidColumn(columnNumber))
			throw new InvalidColumnIndexException();
		else {
			// TODO Auto-generated method stub
			for (int i = rowsNumber - 1; i >= 0; i--) {
				if (board[i][columnNumber] == 0) {
					board[i][columnNumber] = playerNumber;
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * is the board full or not.
	 * 
	 * @return true if the board is completely full, false otherwise.
	 */
	public boolean isFull() {
		boolean b = true;
		for (int i = 0; i < columnsNumber; i++)
			try {
				b = b && isFullAt(0, i);
			} catch (InvalidColumnIndexException e) {}
		return b;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			b.append("|");
			for (int j = 0; j < board[0].length; j++)
				b.append(board[i][j]).append("|");
			b.append("\n");
		}
		logger.debug("printing the board");
		return b.toString();
	}

	public int get(int rowIndex, int columnIndex) throws InvalidColumnIndexException {
		if(isValidColumn(columnIndex) && isValidRow(rowIndex))
			return board[rowIndex][columnIndex];
		else
			throw new InvalidColumnIndexException();
	}

	// public static void main(String[] args) throws IOException {
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// ConnectFourBoard b = new ConnectFourBoard();
	// while (true) {
	// String line = br.readLine();
	// try {
	// b.put(1, Integer.parseInt(line));
	// } catch (NumberFormatException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvalidColumnIndexException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println(b);
	// }
	// }

}
