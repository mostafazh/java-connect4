package connect4.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import connect4.engine.Engine;
import connect4.engine.InvalidColumnIndexException;
import connect4.engine.Move;
import connect4.engine.Player;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		
		logger.info("Game Started");
		
		Engine e = Engine.initInstance(p1, p2);
		while (e.isGameOver() == null) {
			System.out.println(e.whosTurn().getInt()+"'s turn");
			
			String line = br.readLine();
			try {
				e.put(new Move(Integer.parseInt(line)));
			} catch (InvalidColumnIndexException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println(e.getBoard());
			System.out.println("-------------------------------");
		}
		Player winner = e.isGameOver();
		System.out.println(winner.getInt()+" wins");
	}
}
