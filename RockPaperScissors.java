package rockPaperScissors;
//********************************************************************
//  RockPaperScissors.java       Author: Lewis/Loftus/Cocking
//
//  Solution to Programming Project 3.14
//********************************************************************

import java.util.Scanner;

public class RockPaperScissors
{
   //-----------------------------------------------------------------
   //  Plays the Rock-Paper-Scissors game with the user.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      final int OPTIONS = 3;
      final int ROCK = 1, PAPER = 2, SCISSORS = 3;
      final int COMPUTER = 1, PLAYER = 2, TIE = 3;

      int computer, player, winner = 0;
      int wins = 0, losses = 0, ties = 0;
      String again;
      Scanner scan = new Scanner(System.in);

      do
      {
         computer = (int) (Math.random() * OPTIONS) + 1;

         System.out.println();
         System.out.print ("Enter your choice - 1 for Rock, 2 for " +
                              "Paper, and 3 for Scissors: ");
         player = scan.nextInt();

         System.out.print ("My choice was ");

         // Determine the winner
         switch (computer)
         {
            case ROCK:
               System.out.println ("Rock.");
               if (player == SCISSORS)
                  winner = COMPUTER;
               else
                  if (player == PAPER)
                     winner = PLAYER;
                  else
                     winner = TIE;
               break;

            case PAPER:
               System.out.println ("Paper.");
               if (player == ROCK)
                  winner = COMPUTER;
               else
                  if (player == SCISSORS)
                     winner = PLAYER;
                  else
                     winner = TIE;
               break;

            case SCISSORS:
               System.out.println ("Scissors.");
               if (player == PAPER)
                  winner = COMPUTER;
               else
                  if (player == ROCK)
                     winner = PLAYER;
                  else
                     winner = TIE;
         }

         // Print results and increment appropriate counter
         if (winner == COMPUTER)
         {
            System.out.println ("I win!");
            losses++;
         }
         else
            if (winner == PLAYER)
            {
               System.out.println ("You win!");
               wins++;
            }
            else
            {
               System.out.println ("We tied!");
               ties++;
            }

         System.out.println();
         System.out.print ("Play again (y/n)?: ");
         again = scan.nextLine();
      }
      while (again.equalsIgnoreCase ("y"));

      // Print final results
      System.out.println();
      System.out.println ("You won " + wins + " times.");
      System.out.println ("You lost " + losses + " times.");
      System.out.println ("We tied " + ties + " times.");
   }
}
