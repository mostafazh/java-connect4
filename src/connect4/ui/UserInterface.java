package connect4.ui;

import connect4.engine.Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Player;

public interface UserInterface {
	public void setEngine(Engine e);
	public void updateBoard();
	public boolean put(int columnNumber) throws InvalidColumnIndexException;
	public void gameOver(Player winner);
	public Player whosTurn();
	public void show();
	public void start();
}