package connect4.ui;

import connect4.engine.ConnectFourEngine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Player;

public interface UserInterface {
	public void setEngine(ConnectFourEngine e);
	public void show();
	public void start();
	abstract void updateBoard();
	abstract boolean put(int columnNumber) throws InvalidColumnIndexException;
	abstract void gameOver(Player winner);
	abstract Player whosTurn();
}
