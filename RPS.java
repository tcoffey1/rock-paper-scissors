package rockPaperScissors;
//Tadhg Coffey	
//10/12/13  CS111B  Project 2

/*
* This program sets up a generic game of Rock Paper Scissors
* The computer generates a random number to represent its turn
* It contains methods to be used by other classes
* This file represents the game itself. getters, setters, 
*/

import java.util.Random;
import java.util.Scanner;

public class RPS {

	public final int ROCK = 1;
	public final int PAPER = 2;
	public final int SCISSORS = 3;	
	
	//constants representing possible outcomes
	public final int YOU_TIE = 1;
	public final int YOU_WIN = 2;
	public final int YOU_LOOSE = 3;	
	
	private int numComputerWins = 0;
	private int numUserWins = 0;
	private int numTies = 0;	
	
	private int userMove;
	private int computerMove;
	
	private int userBet;
	private int balance;

	
	// constructor; initialize instance data variables above with 
	// theUserMove and theComputerMove (both of which come from the driver class
	public RPS (int theUserMove, int theComputerMove, int theUserBet) {
		userMove = theUserMove;
		computerMove = theComputerMove;
		userBet = theUserBet;
	}

	// Alternative Constructor with no parameters
	// Used by the GUI with no betting option
	public RPS () {
	}
	
	// Alternative Constructor with no parameters
	// Used by the GUI with betting option
	public RPS (int theUserBet) {
		userBet = theUserBet;
	}
	
	// method to generate computer's random move
	public int generateComputerPlay(){
		Random generator = new Random();
		int computerMove = generator.nextInt(3) + 1;
		return computerMove;
	}
	
	//determine winner or tie and return result
	public int findWinner(int newUserMove, int newComputerMove){
		if ((newUserMove == ROCK && newComputerMove == ROCK) 
			|| (newUserMove == PAPER && newComputerMove == PAPER) 
			|| (newUserMove == SCISSORS && newComputerMove == SCISSORS)){
			numTies++;
			if (numComputerWins ==0 && numUserWins == 0 && numTies == 1){
				balance = userBet;
			}else {
				balance = balance;
			}
			return YOU_TIE;
		}else if ((newUserMove == ROCK && newComputerMove == SCISSORS)
			|| (newUserMove == PAPER && newComputerMove == ROCK) 
			|| (newUserMove == SCISSORS && newComputerMove == PAPER)){
			numUserWins++;
			balance = balance + userBet;
			return YOU_WIN;
		}else {
			numComputerWins++;
			balance = balance - userBet;
			return YOU_LOOSE;
		}	
	}
	
	//Console file uses: getUserMove 
	//GUI file uses: getNumComputerWins; getNumUserWins; getNumTies
	public int getNumTies() {
		return numTies;
	}	
	public int getNumUserWins(){
		return numUserWins;
	}		
	public int getNumComputerWins(){
		return numComputerWins;
	}	
	public int getUserMove(){
		return userMove;
	}
	public int getComputerMove() {
		return computerMove;
	}	
	public int getUserBet() {
		return userBet;
	}
	public int getBalance() {
		return balance;
	}	
	
	public String toString() {
		String s = "Tied: " + numTies 
				+ "\t  Your Wins: " + numUserWins 
				+ "\tYour Loses: " + numComputerWins + "\n";
		return s;
	}

	//setters: none of these seem to be needed 
	//Used to send the bet to this 
	public void setUserBet(int newUserBet) {
		userBet = newUserBet;
	}
}