package connect4.engine;

import javax.swing.text.StyleContext.SmallAttributeSet;

public class Engine implements GameEngine {
	private static Engine e = null;

	private ConnectFourBoard board;
	private Player p1, p2, inTurn;

	private Engine(Player p1, Player p2) {
		board = new ConnectFourBoard();

		this.p1 = p1;
		this.p2 = p2;
		inTurn = this.p1;
	}

	public static synchronized Engine getInstance() {
		return e;
	}

	public static synchronized Engine initInstance(Player p1, Player p2) {
		if (e == null)
			e = new Engine(p1, p2);
		return getInstance();
	}

	@Override
	public boolean put(Move m) throws InvalidColumnIndexException {
		if (board.put(inTurn.getInt(), m.getTo())) {
			nextTurn();
			return true;
		}else
			return false;
	}

	@Override
	public void nextTurn() {
		if (inTurn == p1)
			inTurn = p2;
		else
			inTurn = p1;
	}

	@Override
	public Player isGameOver() {
		if(board.isFull())
			return new Player(3);
		else{
			
		}
		return null;
	}

	@Override
	public boolean isValidMove(Move m) throws InvalidColumnIndexException {
		return !board.isFullAt(0, m.getTo()) && board.isValidColumn(m.getTo());
	}

	@Override
	public Player whosTurn() {
		return inTurn;
	}

	public String getBoard() {
		return board.toString();
	}
}
