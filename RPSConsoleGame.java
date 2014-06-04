package rockPaperScissors;
//Tadhg Coffey	
//10/12/13  CS111B  Project 2

/*
* This program creates an instance of the game Rock Paper Scissors
* asks the user to enter r,p, or s
* method called so computer generates a random number to represent its turn
* Stats are updated and printed 
* THIS IS A DRIVER PROGRAM FOR RPS.JAVA
*/

import java.util.Scanner;

public class RPSConsoleGame {

	//can't figure out how to change playGame correctly to false
	//use system.exit(0); to escape instead
	private static boolean playGame = true;
	
	private static int newUserMove = 0;
	private static int newComputerMove = 0;
	private static int newUserBet = 0;
	
	// call to the constructor in RSP.java
	// create an instance of the game
	static RPS game = new RPS(newUserMove, newComputerMove, newUserBet);	
		
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Rock, Paper, Scissors!");
		System.out.println("Enter the bet amount or press 0 to play without betting");
		newUserBet = scan.nextInt();
		
		//send in newUserBet to RPS.java via a setter
		game.setUserBet(newUserBet);
				
		while (playGame){		
		
			System.out.println("To play, enter:"
					+ "\n 'r' to play ROCK" 
					+ "\n 'p' to play PAPER"
					+ "\n 's' to play SCISSORS" 
					+ "\n or any other character to quit.");
			Scanner scan2 = new Scanner(System.in);
			String inputString = scan2.nextLine();

			if (inputString.equals("r")){  
				newUserMove = game.ROCK;         //converts input to 1 for "rock"
				System.out.println("You played: ROCK");
			}else if (inputString.equals("p")){  
				newUserMove = game.PAPER;        //converts input to2 for "paper"
				System.out.println("You played: PAPER");
			}else if (inputString.equals("s")){  
				newUserMove = game.SCISSORS;   //converts input to 3 for "scissors"
				System.out.println("You played: SCISSORS");
			}else {
				//playGame = false;
				System.exit(0);
			}
	
			//Invoke the computerPlay method on rpsGame
			//This generates a move by the computer; print computer play
			newComputerMove = game.generateComputerPlay();
	
			//call the printResultsMethod to
			//print out what was generated as the computer's move
			printComputerPlayMethod (newComputerMove);
			
			//call to the printResultsMethod to determine and print winner
			printResultsMethod();

			//invoke to_string method to display stats of the game
			System.out.println(game);
			
			//display balance only if betting
			if (newUserBet > 0){
			System.out.println("Balance: " + game.getBalance() + "\n");
			}		
		}		
	}
	
	//Method to print out what was generated as the computer's move
	public static void printComputerPlayMethod (int newComputerMove){
				
		if (newComputerMove == game.ROCK){
			System.out.println("The computer played: ROCK");
		}
		if (newComputerMove == game.PAPER){
			System.out.println("The computer played: PAPER");
		}
		if (newComputerMove == game.SCISSORS){
			System.out.println("The computer played: SCISSORS");
		}
	}
		
	//find winner by calling the findWinner method in RPS.java
	//and using return to determine result of game; print out winner
	public static void printResultsMethod(){
		int result = game.findWinner(newUserMove, newComputerMove);	
		
		if (result == game.YOU_TIE){
			System.out.println("You Tied!");
		}
		if (result == game.YOU_WIN){
			System.out.println("You Win!");
		}					
		if (result == game.YOU_LOOSE){
			System.out.println("You Lose!");
		}		
	}	
}
