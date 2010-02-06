package connect4.ui;

import connect4.engine.Connect4Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;

public class AbstractUserInterface implements UserInterface{

	private Connect4Engine e;

	@Override
	public void setEngine(Connect4Engine e) {
		this.e = e;
	}
	
	public Connect4Engine getEngine() {
		return e;
	}
	
	@Override
	public void gameOver(Player winner) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean put(int columnNumber) throws InvalidColumnIndexException {
		boolean putIsDone = e.put(new Move(columnNumber));
		if (putIsDone) {
			Player p = e.isGameOver();
			updateBoard();
			if (p != null) {
				gameOver(p);
			}
		}
		return putIsDone;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
	}

	@Override
	public Player whosTurn() {
		return e.getPlayerInTurn();
	}
}
