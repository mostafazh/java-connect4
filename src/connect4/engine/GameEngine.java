package connect4.engine;


public interface GameEngine {
	public Player isGameOver();
	public boolean isValidMove(Move m) throws InvalidColumnIndexException;
	public Player whosTurn();
	boolean put(Move m) throws InvalidColumnIndexException;
	void nextTurn();
}
