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

class A2_ver9
{
	public static void main (String[]args)
	{
		Scanner in = new Scanner(System.in);
		Random diceColorInt = new Random();
		
		//Arrays
		String [] playerNames;	 
		
	  	//players settings
		int noOfPlayers = 0;
		int turn = 0;
		int lives = 0;
		int userOption = 0;
		
		int shotgun = 0; //shotguns per turn
		int curbrains = 0; // brains counted per roll
		
		boolean nextPlayer = false; // change player
		
		// Count footprints
		int rollRedFootprint = 0;
		int rollGreenFootprint = 0;
		int rollYellowFootprint = 0;
		
		// dice pool
		int noGreenDice = 6;
		int noYellowDice = 4;
		int noRedDice = 3;
		
		displayWelcomeMessage();
		
		System.out.print("How Many Players: ");
		noOfPlayers = in.nextInt();
		in.nextLine();// clean out scanner

		int [] playerScores = new int[noOfPlayers]; // score array
		
		lives = noOfPlayers;
		playerNames = new String[noOfPlayers];

		for(int i = 0; i < playerNames.length; i++) 
		{
			int playerNumber = i+1;
			System.out.print("Enter Player " + playerNumber + " Name: ");
			playerNames[i] = in.nextLine();
		}
		
		while(lives != 0)
		{
			DisplayStart(turn, playerNames);
			// System.out.println("      -- Your current score is: " + playerScores[turn] + " --"); 
			System.out.println("");
			System.out.println("");
			
			//player action prompted
			System.out.println("Choose an option: ");
			System.out.println("1 - Take Dice, 2 - Bank, 3 - See Leaderboard, 4 - Exit"); 
			userOption = in.nextInt();
			in.nextLine();// clean out scanner
			System.out.println("");
			

			if(userOption == 1)
			{
				int randomDiceColor = 0;
				int setDiceColor = 0;
				int x = 0;
				String myDice = "";
				String [] threeDiceToThrow;
				threeDiceToThrow = new String[3];							
						
				//Generate 3 Dice to throw
	
				//for(int i = 0; i < threeDiceToThrow.length; i++)				
				while(x < threeDiceToThrow.length)
				{
					if(rollGreenFootprint > 0)
					{
						threeDiceToThrow[x] = GenerateDice(0);
						System.out.println("Green ReRoll");//Green
						rollGreenFootprint--;
						x++;
					}
					else if(rollRedFootprint > 0)
					{
						threeDiceToThrow[x] = GenerateDice(1);
						System.out.println("Red ReRoll");//Red
						rollRedFootprint--;
						x++;
					}
					else if(rollYellowFootprint > 0)
					{
						threeDiceToThrow[x] = GenerateDice(2);
						System.out.println("Yellow ReRoll");//Yellow
						rollYellowFootprint--;
						x++;
					}
					else
					{
						// check dice is in play		
						randomDiceColor = diceColorInt.nextInt(3);
						
						if(randomDiceColor == 0 && noGreenDice > 0)
						{
							setDiceColor = 0;
							x++;
						}
						else if(randomDiceColor == 1 && noRedDice > 0)
						{
							setDiceColor = 1;
							x++;
						}
						else if(randomDiceColor == 2 && noYellowDice > 0)
						{
							setDiceColor = 2;
							x++;
						}
						else if (noGreenDice == 0 && noRedDice == 0 && noYellowDice == 0 )
						{
							break;
						}
						
						System.out.println("Pick a color "+ setDiceColor);
						
						if(x > 0)
						{
							threeDiceToThrow[x-1] = GenerateDice(setDiceColor);
							System.out.println(threeDiceToThrow[x-1] + " Dice");//random Color
						}
					}
				}
		
				System.out.println("");
				
				ThrowDice();		
				
				for(int i = 0; i < threeDiceToThrow.length; i++)
				{
					if(threeDiceToThrow[i] == "Green")//Green
					{
						myDice = ThrowDice(0);
						
						if(myDice == "Brain")
						{
							noGreenDice--;
							curbrains++;
						}
						else if(myDice == "Footprint")
						{
							//store dice for another turn
							rollGreenFootprint++;
						}
						else if(myDice == "Shotgun")
						{
							noGreenDice--;
							shotgun++;
						}						
					}
					else if(threeDiceToThrow[i] == "Red")//Red
					{
						myDice = ThrowDice(1);
						
						if(myDice == "Brain")
						{
							noRedDice--;
							curbrains++;
						}
						else if(myDice == "Footprint")
						{
							//store dice for another turn
							rollRedFootprint++;
						}
						else if(myDice == "Shotgun")
						{
							noRedDice--;
							shotgun++;
						}
					}
					else if(threeDiceToThrow[i] == "Yellow")// Yellow
					{
						myDice = ThrowDice(2);
						
						if(myDice == "Brain")
						{
							noYellowDice--;
							curbrains++;
						}
						else if(myDice == "Footprint")
						{
							//store dice for another turn
							rollYellowFootprint++;
						}
						else if(myDice == "Shotgun")
						{
							noYellowDice--;
							shotgun++;
						}
					}
					System.out.println(threeDiceToThrow[i]+" "+ myDice);
				} 
				
				System.out.println("G"+noGreenDice+"Y"+noYellowDice+"R"+noRedDice);// testing
				 
				// These are the the variables to be used    -- Method?
				System.out.println(" ");
				System.out.println(" -----------------------------------------------");
				System.out.print(" Shots to the face: " + shotgun);
				System.out.println("    |   Delicious Brains: " + curbrains);
				System.out.println(" -----------------------------------------------");

				System.out.println(" ");
				
				if(CheckShotgun(shotgun)) // change player 
				{
				 	shotgun = 0;
					nextPlayer = true;
				}
			}
			else if (userOption == 2)
			{
				//Bank Brains
				shotgun = 0;
				nextPlayer = true;
				playerScores[turn] = playerScores[turn] + curbrains;
				Boolean finalround = checkFinalRound(playerScores, turn);
				System.out.println(finalround); // test
				System.out.println("  ------------------------------------------------");
				System.out.println("       You got " + curbrains + " brains this turn. ");
				System.out.println("           TOTAL BRAINS: " + playerScores[turn]);
				System.out.println("  ------------------------------------------------");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");

				if (finalround == true){
					System.out.println("     ___ _ __  _  __  _     ___  __  _  _ __  _ __ ");
					System.out.println("    | __| |  \\| |/  \\| |   | _ \\/__\\| || |  \\| | _\\ ");
					System.out.println("    | _|| | | ' | /\\ | |_  | v / \\/ | \\/ | | ' | v |");
					System.out.println("    |_| |_|_|\\__|_||_|___| |_|_\\\\__/ \\__/|_|\\__|__/");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("");
				}


			}
			else if (userOption == 3)
			{
				System.out.println("Leaderboard: ");
				printScores(playerNames, playerScores);
			}
			else if (userOption == 4)
			{
				lives = 0;
			}
						
			if(nextPlayer)
			{
				nextPlayer = false;
				
				// reset Dice in cup
				noGreenDice = 6;
				noYellowDice = 4;
				noRedDice = 3;
				
				curbrains = 0; // reset unbanked brains
				turn++; // next Player
			}
			
			if(turn == noOfPlayers)  
			{
				turn = 0; // return to starting player
			}
		}
	}
	
	public static void ThrowDice()
	{
		System.out.println("Press enter to throw dice...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
	
	public static void DisplayStart(int x, String [] myArray ) 
	{
		System.out.println("");
		System.out.println("   ************** "+ myArray[x]+"'s" + " turn **************");
		System.out.println("");
	}
	
	public static void PrintNames(String [] myArray)
	{
		for(int i = 0; i < myArray.length; i++)
		{
			int playerNumber = i+1;
			System.out.print("Player " + playerNumber + " Name: ");
			System.out.println(myArray[i]);		
		}
	}

		public static void printScores(String [] myArray, int [] scoreArray)
	{
		for(int i = 0; i < myArray.length; i++)
		{
			int playerNumber = i+1;
			System.out.println(" ------------------------------");
			System.out.print("   " + myArray[i] + ": ");		
			System.out.println(scoreArray[i] + " brains.");
			System.out.println(" ------------------------------");
		}
	}
	
	// Check current shotgun status
	public static boolean CheckShotgun(int curShotguns) 
	{
		if(curShotguns > 2)
		{
			System.out.println("    YOU DIED AND LOST YOUR BRAINS. ");
			System.out.println("");
			System.out.println("              +   ");
			System.out.println("            .-\"-. ");
			System.out.println("           / RIP \\ ");
			System.out.println("           |     |  ");
			System.out.println("          \\\\     |// ");
			System.out.println("          ` \" \"\" \"   ");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			return true;
		}
		else
		{
			System.out.println("              Not Dead");
			return false;
		}
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
	
	public static String GenerateDice(int dc)
	{
		String colorOfDice = "";
		
		if(dc == 0)
		{
			colorOfDice = "Green";
		}
		else if(dc == 1)
		{
			colorOfDice = "Red";
		}
		else if(dc == 2)
		{
			colorOfDice = "Yellow";
		}
		
		return colorOfDice ;
	}
	
	public static String ThrowDice(int diceColor)
	{
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		Random randomInt = new Random();
		String diceResult = "";
		
		//int diceColor = randomInt.nextInt(3);
		int roll = randomInt.nextInt(6);	
					
		if(diceColor == 0)//Green Dice
		{
			//System.out.println("Green");
			diceResult = greenDice[roll];
		}
		else if(diceColor == 1)// Red Dice 
		{
			//System.out.println("Red");
			diceResult = redDice[roll];
		}
		
		else if(diceColor == 2)//Yellow Dice
		{
			//System.out.println("Yellow");
			diceResult = yellowDice[roll];
		}
		else
		{
			diceResult = "Something went wrong";
		}
		return diceResult;
	}

		public static boolean checkFinalRound (int [] playerScores, int turn) {
		if (playerScores[turn] >= 13) {
			return true;
		}

		return false;
	}
}
