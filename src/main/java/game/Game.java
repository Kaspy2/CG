package game;

import java.util.Scanner;

public class Game{
    
	private int numOfPlayers = 0;
	
	public void setNumOfPlayers(){
		while(numOfPlayers < 2 || 8 < numOfPlayers){
			System.out.println("Input number of players (2-8): ");
			Scanner sc = new Scanner(System.in);
			numOfPlayers = sc.nextInt();
			sc.close();
		}
	}
	
	public int getNumOfPlayers(){return numOfPlayers;}
	
	public static void main(String[] args){
		Game game = new Game();
		game.setNumOfPlayers();
	}

}