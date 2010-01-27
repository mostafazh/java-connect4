package connect4.engine;

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
		} else
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
		if (board.isFull())
			return new Player(3);
		else {
			if (wins(p1.getInt()))
				return p1;
			else if (wins(p2.getInt()))
				return p2;
		}
		return null;
	}

	private boolean wins(int playerNumber) {
		// TODO Auto-generated method stub
		Score[][] s = new Score[board.getRowsNumber()+1][board.getColumnsNumber()+2];
		for (int i =0;i<s.length;i++) {
			for(int j =0;j<s[0].length ;j++)
				s[i][j] = new Score();
		}
		for (int i = 1; i < s.length; i++)
			for (int j = 1; j < s[0].length - 1; j++) {
				try {
					if (board.get(i-1, j-1) == playerNumber) {
						Score current = s[i][j];
						Score upper = s[i-1][j];
						Score left = s[i][j-1];
						Score upperRight = s[i-1][j+1];
						Score upperleft = s[i-1][j-1];
						current.setBackslash(upperleft.getBackslash()+1);
						current.setForslash(upperRight.getForslash()+1);
						current.setHorizontal(left.getHorizontal()+1);
						current.setVertical(upper.getVertical()+1);
						if(current.getBackslash() >= 4 || current.getForslash() >= 4 ||current.getHorizontal() >= 4 || current.getVertical() >= 4 )
							return true;
					}
					else{
						s[i][j] = new Score();
					}
				} catch (InvalidColumnIndexException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return false;
	}

	@Override
	public boolean isValidMove(Move m) throws InvalidColumnIndexException {
		return !board.isFullAt(0, m.getTo()) && board.isValidColumn(m.getTo());
	}
	
	public int getRowsNumber() {
		return board.getRowsNumber();
	}

	public int getColumnsNumber() {
		return board.getColumnsNumber();
	}

	@Override
	public Player getPlayerInTurn() {
		return inTurn;
	}

	public int[][] getBoard2D() {
		return board.get2dArray();
	}
	
	public String getBoard() {
		return board.toString();
	}
}
