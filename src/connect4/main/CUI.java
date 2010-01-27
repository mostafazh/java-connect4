package connect4.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import connect4.engine.Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;
import connect4.ui.UserInterface;

public class CUI implements UserInterface{

	Engine e;
	private BufferedReader br;
	
	@Override
	public void gameOver(Player winner) {
		// TODO Auto-generated method stub
		System.out.println(winner.getInt()+" wins");
	}
	
	@Override
	public void setEngine(Engine e) {
		this.e = e;
	}
	
	@Override
	public void show() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		while (e.isGameOver() == null) {
			System.out.println(e.getPlayerInTurn().getInt()+"'s turn");
			
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				put(Integer.parseInt(line));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidColumnIndexException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			updateBoard();
			System.out.println("-------------------------------");
		}

		gameOver(e.isGameOver());
	}

	@Override
	public boolean put(int columnNumber) throws InvalidColumnIndexException {
		try {
			e.put(new Move(columnNumber));
		} catch (InvalidColumnIndexException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateBoard() {
		System.out.println(e.getBoard());
	}

	@Override
	public Player whosTurn() {
		return e.getPlayerInTurn();
	}

}
