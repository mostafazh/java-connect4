package connect4.ui;

import connect4.engine.Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;

public class AbstractUserInterface implements UserInterface{

	private Engine e;

	@Override
	public void setEngine(Engine e) {
		this.e = e;
	}
	
	public Engine getEngine() {
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
