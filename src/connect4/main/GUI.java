package connect4.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import connect4.engine.Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;
import connect4.ui.UserInterface;

public class GUI implements KeyListener,UserInterface {
	private static Logger logger = Logger.getLogger(GUI.class.getName());

	Engine e;
	JFrame frame;
	JLabel[][] board;
	BoardPanel boardPanel;

	public GUI() {
		boardPanel = new BoardPanel();
		frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(50, 50, 800, 600);
		boardPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().setBackground(Color.YELLOW);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void initBoard() {
		board = new JLabel[e.getRowsNumber()][e.getColumnsNumber()];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new JLabel("0");
				board[i][j].setForeground(Color.BLUE);
				// board[i][j].setBackground(Color.RED);
				board[i][j].setBounds( j * 55, i * 55, 50, 50);
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
		frame.addKeyListener(this);
	}

	@Override
	public void updateBoard() {
		int[][] a = e.getBoard2D();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setText(a[i][j]+"");
			}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setEngine(Engine e) {
		this.e = e;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
			int i = arg0.getKeyCode();
			i = i - 49;
			System.out.println(i);
			try {
				put(i);
				Player p = e.isGameOver();
				updateBoard();
				if(p != null){
					gameOver(p);
					frame.removeKeyListener(this);
				}
			} catch (InvalidColumnIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void gameOver(Player winner) {
		// TODO Auto-generated method stub
//		JOptionPane jop = new JOptionPane();
		if(winner.getInt() != 3)
		JOptionPane.showMessageDialog(frame,
			    "Game Over, Player "+ winner.getInt()+" wins.",
			    "Game Over",
			    JOptionPane.OK_OPTION);
		else
			JOptionPane.showMessageDialog(frame,
				    "Game Over, Draw.",
				    "Game Over",
				    JOptionPane.OK_OPTION);
//		jop.setOptionType(JOptionPane.OK_OPTION);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean put(int columnNumber) throws InvalidColumnIndexException {
		e.put(new Move(columnNumber));
		return false;
	}

	@Override
	public Player whosTurn() {
		// TODO Auto-generated method stub
		return null;
	}
}
