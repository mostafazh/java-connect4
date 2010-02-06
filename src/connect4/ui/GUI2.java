package connect4.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import connect4.engine.ConnectFourEngine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;
/**
 * 
 * @author mostafa
 *
 *	the pictures are taken from <a href="http://www.dreamincode.net/forums/showtopic97190.htm">http://www.dreamincode.net/forums/showtopic97190.htm</a>
 */
public class GUI2 implements KeyListener, UserInterface, MouseListener {
	private static Logger logger = Logger.getLogger(GUI.class.getName());

	ConnectFourEngine e;
	JFrame frame;
	JLabel[][] board;
	JPanel boardPanel;

	public GUI2() {
		boardPanel = new JPanel(null);
		frame = new JFrame();
		frame.setLayout(null);
		boardPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initBoard() {
		board = new JLabel[e.getRowsNumber()][e.getColumnsNumber()];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new JLabel();
				board[i][j].setBounds(j * 50, i * 50, 50, 50);
				board[i][j].setIcon(new ImageIcon("empty.png"));
				frame.getContentPane().add(board[i][j]);
			}
	}

	@Override
	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void start() {
		initBoard();
		frame.setBounds(50, 50, 50 * e.getColumnsNumber(), 50 * e
				.getRowsNumber() + 30);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
	}

	@Override
	public void updateBoard() {
		int[][] a = e.getBoard().get2dArray();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				// board[i][j].setText(a[i][j]+"");
				if (a[i][j] == 0)
					board[i][j].setIcon(new ImageIcon("empty.png"));
				else if (a[i][j] == 1)
					board[i][j].setIcon(new ImageIcon("red.png"));
				else
					board[i][j].setIcon(new ImageIcon("yellow.png"));
			}
	}

	@Override
	public void setEngine(ConnectFourEngine e) {
		this.e = e;
	}

	@Override
	public void gameOver(Player winner) {
		String message = "Game Over, Draw.";
		if (winner.getInt() != 3)
			message = "Game Over, Player " + winner.getInt() + " wins.";
		JOptionPane.showMessageDialog(frame, message, "Game Over",
				JOptionPane.OK_OPTION);
	}

	@Override
	public boolean put(int columnNumber) throws InvalidColumnIndexException {
		boolean putIsDone = e.put(new Move(columnNumber));
		System.out.println(columnNumber);
		if(putIsDone){
			Player p = e.isGameOver();
			updateBoard();
			if (p != null) {
				gameOver(p);
				frame.removeMouseListener(this);
				frame.removeKeyListener(this);
			}
		}
		return putIsDone;
	}

	@Override
	public Player whosTurn() {
		return e.getPlayerInTurn();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent key) {
		int i = key.getKeyCode() - 49;
		try {
			put(i);
		} catch (InvalidColumnIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent ee) {
		try {
			put(ee.getX() / 50);
		} catch (InvalidColumnIndexException e1) {
			// TODO Auto-generated method stub
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
