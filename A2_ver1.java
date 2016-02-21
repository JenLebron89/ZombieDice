import java.util.*; 

// Joseph Cheevers 2905225 + Jennifer Lebron 2894184 = JJ and the Zombies

/*
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
	
Contents
	13 Dice (pool of dice) 
		6G 
		4Y 
		3R
	1 Cup 

Start
	Introduction: Rules
	No of players 
	Names of players 

Turn:
	display player name
	display player points

User Input
	See leaderboards
	Roll
		On generate dice: displays colours
		
	Display results
	
	Check & compare results

		Check shotguns
			store on each roll
			3 or higher: end turn & lose unbanked brains
			reset on each turn
		
		Check Brains
			store on each roll
			if 13 or higher: win game/final round
			bank brains: ends turn

		Check Runner
			Keep dice of that color in play and generate remaining dice
	
	Roll Again / Bank/ Last Round(if < 13 brains) / Tie(sudden Death)
Give Up


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

class A2_ver1
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

		boolean nextPlayer = false;
		
		displayWelcomeMessage();
		
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
			System.out.println("Choose an option: ");
			System.out.println("1 - Roll Dice, 2 - See Leaderboard, 3 - Exit"); 
			userOption = in.nextInt();
			System.out.println("");

			if(userOption == 1)
			{
				System.out.println("Play Game: ");
				int roll1 = randomInt.nextInt(6);
				System.out.println(redDice[roll1]);
				int roll2 = randomInt.nextInt(6);
				System.out.println(greenDice[roll2]);
				int roll3 = randomInt.nextInt(6);
				System.out.println(yellowDice[roll3]);
				System.out.println(" ");
			}
			else if (userOption == 2)
			{
				System.out.println("Leaderboard: ");
			}
			else if (userOption == 3)
			{
				lives = 0;
			}
			
			//Insert Game Here
			System.out.println("1 for Roll, 2 for bank "); 
			userOption = in.nextInt();
			
			if(userOption == 1)
			{
				System.out.println("Roll Again");
			}
			else if(userOption == 2)
			{
				nextPlayer = true;
			}
			
			if(turn == noOfPlayers)  
			{
				turn = 0;
			}
			
			if(nextPlayer)
			{
				nextPlayer = false;
				turn++;
			}
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

	public static void displayWelcomeMessage () {
		System.out.println(" "); 
		System.out.println("                    -- Welcome to  --");
		System.out.println(" ");
		System.out.println("    _____               _     _        ____  _   ");
		System.out.println("   |__  /___  _ __ ___ | |__ (_) ___  |  _ \\(_) ___ ___ ");
		System.out.println("     / // _ \\| '_ ` _ \\| '_ \\| |/ _ \\ | | | | |/ __/ _ \\");
		System.out.println("    / /| (_) | | | | | | |_) | |  __/ | |_| | | (_|  __/");
		System.out.println("   /____\\___/|_| |_| |_|_.__/|_|\\___| |____/|_|\\___\\___|");

		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println("         ,-. . . |  ,-. ,-. ");
		System.out.println("         |   | | |  |-' `-. ");
		System.out.println("         '   `-' `' `-' `-' ");
		System.out.println("      __________________________");
		System.out.println("");

		System.out.println("      Eat brains. Don't get shotgunned. ");
		System.out.println("");
		System.out.println("       - On your turn, roll 3 dice. Each one is a human victim.");
		System.out.println("         Red are toughest. Green are easiest, and yellow are medium tough.");
		System.out.println("");
		System.out.println("       - The dice have three symbols: Brain, Shotgun and Footprints.");
		System.out.println("");
		System.out.println("        * 3 Shotguns = turn is over and score nothing.");
		System.out.println("        * Footprints = re-roll that same dice.");
		System.out.println("        * Brains = points.");
		System.out.println("");
		System.out.println("       You can choose to stop and bank your brains, or continue. After you take new dice, you can’t stop... you must roll.");
    	System.out.println("");
    	System.out.println("       If you decide to stop, score 1 for each Brain you have. It’s the next player’s turn.");
    	System.out.println("");
    	System.out.println("       Play until someone reaches 13 Brains. Then finish the round. ");
    	System.out.println("       Whoever has the most Brains at the end of that round is the winner.");
    	System.out.println("       If there’s a tie, the leaders (only) play a tiebreaker round.");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
		System.out.println("        Now go eat some BRAAAAINS!");
		System.out.println("");




	}
}
