package connect4.engine.test;

import connect4.engine.ConnectFourEngine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;
import junit.framework.TestCase;

public class EngineTest extends TestCase {

	private ConnectFourEngine e;
	private Player p1;
	private Player p2;

	@Override
	protected void setUp() {
		p1 = new Player(1);
		p2 = new Player(2);
		e = ConnectFourEngine.reInitInstance(p1, p2);
	}

	public void testIsGameOver() {
		int[] drawMoves = { 0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 0, 1, 2,
				3, 4, 5, 6, 1, 0, 3, 2, 5, 4, 4, 5, 2, 6, 3, 1, 0, 3, 2, 1, 0,
				4, 5, 6, 6 };
		for (int i = 0; i < drawMoves.length; i++) {
			try {
				e.put(new Move(drawMoves[i]));
			} catch (InvalidColumnIndexException e1) {
				fail();
			}
		}
		assertEquals(3,e.isGameOver().getInt());

		setUp();
		int[] p1ToWinMoves = { 0, 0, 1, 1, 2, 2, 3, 3 };
		for (int i = 0; i < p1ToWinMoves.length; i++) {
			try {
				e.put(new Move(p1ToWinMoves[i]));
			} catch (InvalidColumnIndexException e1) {
				fail();
			}
		}
		assertEquals(1,e.isGameOver().getInt());

		setUp();
		int[] p2ToWinMoves = { 0, 1, 1, 2, 2, 3, 3, 4 };
		for (int i = 0; i < p2ToWinMoves.length; i++) {
			try {
				e.put(new Move(p2ToWinMoves[i]));
			} catch (InvalidColumnIndexException e1) {
				fail();
			}
		}
		assertEquals(2,e.isGameOver().getInt());
	}

	/**
	 * Tests the <code>true</code> part of the put.
	 */
	public void testPut1() {
		// TODO unit test the ConnectFourBoard class better than doing all the
		// tests here!!!

		Player inTurn = e.getPlayerInTurn();
		try {
			assertTrue("couldn't put a disc in the first column, its full", e
					.put(new Move(0)));
			assertEquals("didn't put the right player number.", e.getBoard()
					.get2dArray()[e.getRowsNumber() - 1][0], inTurn.getInt());
			assertNotSame(inTurn, e.getPlayerInTurn());
		} catch (InvalidColumnIndexException e) {
			fail("trying to put in an invalid column number");
		}
		try {
			assertTrue("the previous \"put\" was not successful.", e.getBoard()
					.isFullAt(e.getRowsNumber() - 1, 0));
		} catch (InvalidColumnIndexException e) {
			fail("trying to check in an invalid column number and/or row number");
		}
	}

	/**
	 * Tests the <code>false</code> part of the put.
	 */
	public void testPut2() {
		Player inTurn = e.getPlayerInTurn();

		// Fill a complete column (number 0)
		for (int i = 0; i < e.getRowsNumber(); i++) {
			try {
				assertTrue(e.put(new Move(0)));
				inTurn = e.getPlayerInTurn();
			} catch (InvalidColumnIndexException e1) {
				fail("trying to put in an invalid column number");
			}
		}

		// Try to put in the completely filled column, it shouldn't put and shouldn't
		// switch the turn.
		try {
			assertFalse(e.put(new Move(0)));
			assertEquals(inTurn, e.getPlayerInTurn());
		} catch (InvalidColumnIndexException e) {
			fail("trying to put in an invalid column number");
		}
	}
}
