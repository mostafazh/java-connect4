package connect4.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ConnectFourBoard extends Board {

	int[][] board;
	private static Logger logger = Logger.getLogger(ConnectFourBoard.class.getName());
	
	public ConnectFourBoard() {
		board = new int[6][7];
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
	 * Check the validity of a column number(index).
	 * 
	 * @param to
	 *            the column index to check its validity.
	 * @return true, if the <code>to</code> is between 0 and 6 (inclusive).
	 */
	public boolean isValidColumn(int to) {
		return to >= 0 && to <= 6;
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
			for (int i = 5; i >= 0; i--) {
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
		for (int i = 0; i < 7; i++)
			try {
				b = b && isFullAt(0, i);
			} catch (InvalidColumnIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
