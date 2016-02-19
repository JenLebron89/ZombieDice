import java.util.*; 

// Joseph Cheevers 2905225

/*
Java-based Zombie Dice Game
Zombie Dice is a game published by Steve Jackson Games. It involves rolling special
die to determine what happens on each players turn. The goal of the game is to eat 13
brains before the other players.

Game Rules
On each turn, randomly generate 3 dice for the game. Each one is a human victim.
There are 3 different types of die in the game

1 Red are the toughest.
	(They have 3 Shotguns sides, 2 Footprints sides and 1 Brains side)
2 Green are easiest.
	(They have 1 Shotguns side, 2 Footprints sides and 3 Brains sides)
3 Yellow are medium tough.
	(They have 2 Shotguns sides, 2 Footprints sides and 2 Brains sides)

	
The dice have three symbols:
1 Brain
	You ate your victim’s brain.
2 Shotgun
	He fought back!
3 Footprints
	Your victim escaped.
	
	
If you rolled three Shotguns, your turn is over. Otherwise, you can choose to stop and
score, or continue. 
If you decide to stop, score 1 for each Brain you have, and end your turn. 

It’s the next player’s turn. If you choose to keep going, you reroll the die,
but you must keep track of your brains and shotguns.

If you are up to 3 Shotguns, your turn is over and you score nothing. 
Otherwise, you can stop and score, or take another roll…

Footprints are to be kept on the table, what this means is that if you roll a green
Footprint you will reroll that dice again. Depending on your luck this can mean that
you reroll between 0 – 3 dice in any 1 turn.


Rules Breakdown
1. 3 Shotguns = turn over
2. successful role = stop & score / continue
	A. stop = score 1 for  each brain (end turn) 
	

Deliverables:
For this assignment you are required to submit 2 pieces of work:
1. Java file containing your program (70%)
2. A flow chart to show your design of the program (20%)
3. A small report from each team member on the work distribution (10%)

Learning Outcomes
1. solve programming problems of modest complexity in a systematic, wellorganised way
2. specify precisely the syntax and semantics of a programming language construct
3. select an appropriate program construct (or datatype) to achieve a given task
4. document accurately the design of a program on-the-fly

Game System
Generating Dice 			- 5
Rolling Dice 				- 5
Adding scores 				- 5
Tracking shotguns 			- 5
User ending turn 			- 5
Game ending turn 			- 5
Continue playing 			- 5
Turn based system 			- 10
Ending game 				- 5

User Interface: 			(10%)
Game style layout 			- 5
Display current player	 	- 2
User feedback 				- 3

Coding style: 				(20%)
Indentation 				- 3
Comments 					- 4
Use of Methods 				- 3
Use of arrays 				- 10

Extra Features: 			(20%)
Using footprints correctly 	- 3
Taking players names 		- 4
3+ players 					- 3
Use of arrays 				- 10

in.nextLine() clean out sanner
*/

class A2
{
	public static void main (String[]args)
	{
		Scanner in = new Scanner(System.in);
		Random randomInt = new Random();
		
		//Arrays
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		String [] playerNames;
		int [] playerScores;  // changed playerScorres to playerScores
		
	  	//players settings
		int noOfPlayers = 0;
		int turn = 1;
		int lives = 0;
		int userOption = 0;
		
		System.out.print("How Many Players: ");
		noOfPlayers = in.nextInt();
		in.nextLine();// clean out scanner
		
		lives = noOfPlayers;
		playerNames = new String[noOfPlayers];

		for(int i = 0; i < playerNames.length; i++)
		{
			System.out.print("Enter Player " + (i+1) + " Name: ");
			playerNames[i] = in.nextLine();
		}
		
		while(lives != 0)
		{
			DisplayStart(turn, playerNames);
			
			//player action prompted
			System.out.print("Choose an option: ");
			System.out.print("1 - Display names, 2 - Exit, 3 - Roll dice   "); // Added this to test the options
			userOption = in.nextInt();
			System.out.println("");

			if(userOption == 1)
			{
				Names(playerNames);
			}
			else if (userOption == 2)
			{
				lives = 0;
			}
			else if (userOption == 3)
			{
				int roll1 = randomInt.nextInt(6);
				System.out.println(redDice[roll1]);
				int roll2 = randomInt.nextInt(6);
				System.out.println(greenDice[roll2]);
				int roll3 = randomInt.nextInt(6);
				System.out.println(yellowDice[roll3]);
			}
			else if (userOption == 4)
			{
				
			}
			
			if(turn == noOfPlayers)  
			{
				turn = 0;
			}
			turn++;
		}
	}
	
	public static void DisplayStart(int x, String [] myArray ) 
	{
		System.out.println("");
		System.out.println(" "+ myArray[x-1]+"'s" + " turn");
		System.out.println("");
	}
	
	public static void Names(String [] myArray)
	{
		for(int i = 0; i < myArray.length; i++)
		{
			System.out.print("Player " + (i+1) + " Name: ");
			System.out.println(myArray[i]);		
		}
	}
	
	public static void Scores()
	{
		// save scores here
	}
	
	public static void Dice()
	{
		
	}
	
	/*public static String chickDice(String s)
	{
		if(s == "Brain")
		{
			//You ate your victim’s brain.
		}
		else if (s == "Shotgun")
		{
			//He fought back!
		}
		else if(s == "Footprint")
		{
			//Your victim escaped.
		}
		return;
	}*/
}
