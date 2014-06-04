package rockPaperScissors;
//Tadhg Coffey	
//10/12/13  CS111B  Project 2

/*
* This GUI program creates an instance of the game Rock Paper Scissors
* user clicks button to play
* method called so that computer generates a random number to represent its turn
* Images and Stats are updated and displayed in window
* THIS IS A DRIVER PROGRAM FOR RPS.JAVA
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RPSGUIGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties/balance
	private JLabel statusC, statusU, statusT, statusB;

	// images and labels to display the computer and user's moves and the outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;
	
	// the game object
	private RPS game;
	
	private int newComputerMove = 0;
	private int newUserMove = 0;
	
	private static int newUserBet;
	private int newBalance;
	private static int betYesOrNo;
	
	public RPSGUIGame() {

		// initializes the window
		super("Rock, Paper, Scissors, Go!");
		setSize(350, 300);
		
		//assign a reference to the content pane
		Container contentPane = getContentPane();
		
		// set the layout manager
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		setResizable(false);

		// creates the game object
		//call to the constructor in RPS.java
		game = new RPS(newUserBet);
		
		// creates the labels for displaying the computer and user's move;
		// the images for the moves and the outcome of a match-up 
		//will be displayed in a single panel
		rockImage = new ImageIcon("rock.jpg");
		paperImage = new ImageIcon("paper.jpg");
		scissorsImage = new ImageIcon("scissors.jpg");

		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.cyan);
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		userPlay.setForeground(Color.cyan);
		
		outcome = new JLabel();
		outcome.setHorizontalTextPosition(SwingConstants.CENTER);
		outcome.setForeground(Color.pink);
		
		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.black);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(compPlay, BorderLayout.WEST);
		imageOutcomePanel.add(userPlay, BorderLayout.EAST);
		imageOutcomePanel.add(outcome, BorderLayout.SOUTH);
		
		// creates the labels for the status of the game (number of wins, losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel("Computer Wins: " + game.getNumComputerWins());
		statusU = new JLabel("User Wins: " + game.getNumUserWins());
		statusT = new JLabel("Ties: " + game.getNumTies());
		statusB = new JLabel("Balance: " + game.getBalance());
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);
		statusB.setForeground(Color.white);
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusC);
		statusPanel.add(statusU);
		statusPanel.add(statusT);
		
		//Only if user answered yes to betting, Balance is added to window
		if (betYesOrNo == JOptionPane.YES_OPTION){
		statusPanel.add(statusB);
		}
		
		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.black);
		gamePanel.add(imageOutcomePanel);
		gamePanel.add(statusPanel);
		
		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.black);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	//determines which button was clicked and updates the game accordingly
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			//set text under user's image
			userPlay.setText("Your move");	  
			
			//when the user clicks a Button,updates to corresponding picture
			//converts input to 1 for "rock", 2 for "paper", 3 for "scissors"
			if(e.getSource()==rockButton) {
				userPlay.setIcon(rockImage);
				newUserMove = game.ROCK;    
						
			} else if(e.getSource()==paperButton) {
				userPlay.setIcon(paperImage);
				newUserMove = game.PAPER;    
				
			} else if(e.getSource()==scissorsButton) {
				userPlay.setIcon(scissorsImage);
				newUserMove = game.SCISSORS;   	
			}
	
			//generate computer move and store return value,
			//call to update image and set text under image
			//by calling the generateComputerPlay() method in the RSP.java file
			//and by calling the updateComputerMoveDisplay method
			newComputerMove = game.generateComputerPlay();
			updateComputerMoveDisplay(newComputerMove);			
			compPlay.setText("Computer's move");	
			
			//call to find out who the winner is
			//update and display winner and stats
			updateStatusDisplay(newUserMove, newComputerMove);	
					
		}

		//Method to updated the computer's image only
		public void updateComputerMoveDisplay(int newComputerMove){
			if(newComputerMove==game.ROCK) {
				compPlay.setIcon(rockImage);		
			} else if(newComputerMove==game.PAPER) {
				compPlay.setIcon(paperImage);		
			} else if(newComputerMove==game.SCISSORS) {
				compPlay.setIcon(scissorsImage);
			}	
		}
	
		//Method to update status: calls the findWinner method the RSP.java file
		//Updates result of winner; updates on-going stats & balance
		public void updateStatusDisplay(int newUserMove, int newComputerMove){

			int outcomeStatus = game.findWinner(newUserMove, newComputerMove);	
			
			if(outcomeStatus==game.YOU_TIE) {
				outcome.setText("It's a Tie!");	
				statusT.setText("Ties: " + game.getNumTies());
				
			} else if(outcomeStatus==game.YOU_WIN) {
				if (newUserMove == game.ROCK && newComputerMove == game.SCISSORS){
					outcome.setText("You Win! Rock smashes Scissors");
				}else if(newUserMove == game.PAPER && newComputerMove == game.ROCK){
					outcome.setText("You Win! Paper covers Rock");
				}else if(newUserMove == game.SCISSORS && newComputerMove == game.PAPER){
					outcome.setText("You Win! Scissors shreds Paper");				
				}
				statusU.setText("User Wins: " + game.getNumUserWins());		
				
			} else if(outcomeStatus==game.YOU_LOOSE) {
				if (newComputerMove == game.ROCK && newUserMove == game.SCISSORS){
					outcome.setText("Computer Wins! Rock smashes Scissors");
				}else if(newComputerMove == game.PAPER && newUserMove == game.ROCK){
					outcome.setText("Computer Wins! Paper covers Rock");
				}else if(newComputerMove == game.SCISSORS && newUserMove == game.PAPER){
					outcome.setText("Computer Wins! Scissors shreds Paper");	
				}	
				statusC.setText("Computer Wins: " + game.getNumComputerWins());	
			}	
			statusB.setText("Balance: " + game.getBalance());
		}
		
	}	

	public static void main(String args[]) {
		
		//Ask user if they would like to place a bet.
		betYesOrNo = JOptionPane.showConfirmDialog(null, "Would you like to place a bet?");
		
		//Only if user answered yes, ask how much?
		if (betYesOrNo == JOptionPane.YES_OPTION){
			
			String userInputString = JOptionPane.showInputDialog("How much per round?");
			newUserBet = Integer.parseInt(userInputString);
		}
		
		// create an object of the class
		RPSGUIGame frame = new RPSGUIGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}