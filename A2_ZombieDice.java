import java.util.*; 

// Joseph Cheevers 2905225 + Jennifer Lebron 2894184 = JJ and the Zombies

class A2_ZombieDice {
	public static void main (String[]args) {
		
		Scanner in = new Scanner(System.in);
		Random diceColorInt = new Random();
		
		//Array for players names
		String [] playerNames;	 
		
	  	//Settings
		int noOfPlayers = 0;
		int turn = 0; 			// set players turn	
		int lives = 0;			// no of players playing
		int userOption = 0;		
		int shotgun = 0; 		//shotguns per turn
		int curbrains = 0; 		// brains counted per roll
		
		// Count footprints
		int rollRedFootprint = 0;
		int rollGreenFootprint = 0;
		int rollYellowFootprint = 0;
		
		// dice pool
		int noGreenDice = 6;
		int noYellowDice = 4;
		int noRedDice = 3;
		
		boolean nextPlayer = false; // change player
		
		displayWelcomeMessage();
		
		System.out.print("How Many Players: ");
		noOfPlayers = in.nextInt();
		in.nextLine();// clean out scanner

		int [] playerScores = new int[noOfPlayers]; // score array
		
		lives = noOfPlayers;
		playerNames = new String[noOfPlayers];

		// Take in all player names
		for(int i = 0; i < playerNames.length; i++){
			int playerNumber = i+1;
			System.out.print("Enter Player " + playerNumber + " Name: ");
			playerNames[i] = in.nextLine();
		}
		
		//Main Game Loop
		while(lives != 0){
			displayStart(turn, playerNames);

			System.out.println("");
			System.out.println("");
			
			//player action prompted
			System.out.println("Choose an option: ");
			System.out.println("1 - Take Dice, 2 - Bank, 3 - See Leaderboard, 4 - Exit"); 
			userOption = in.nextInt();
			in.nextLine();// clean out scanner
			System.out.println("");
			
			// Dice roll and play turn
			if(userOption == 1)
			{
				int randomDiceColor = 0;
				int setDiceColor = 0;
				int x = 0;
				String myDice = "";
				String displayDiceColor = "";
				String [] threeDiceToThrow;
				threeDiceToThrow = new String[3];							
				
				//Generate 3 Dice to throw
				while(x < threeDiceToThrow.length){
					if(rollGreenFootprint > 0){
						threeDiceToThrow[x] = generateDice(0);
						System.out.print("          [ Green ReRoll ]    ");	//Green
						rollGreenFootprint--; 								// take away footprints (Green)
						x++;												// add to throw dice array
					}
					else if(rollRedFootprint > 0){
						threeDiceToThrow[x] = generateDice(1);
						System.out.print("          [ Red ReRoll ]    ");	//Red
						rollRedFootprint--;									// take away footprints (red) 
						x++;												// add to throw dice array
					}
					else if(rollYellowFootprint > 0){
						threeDiceToThrow[x] = generateDice(2);
						System.out.print("          [ Yellow ReRoll ]    ");//Yellow
						rollYellowFootprint--;								// take away footprints (Yellow) 	
						x++;												// add to throw dice array
					}
					else{
						randomDiceColor = diceColorInt.nextInt(3);  		// pick random dice
						
						// check if dice is in play
						if(randomDiceColor == 0 && noGreenDice > 0){
							setDiceColor = 0;								// set dice color to green
							x++;											// add to throw dice array
						}
						else if(randomDiceColor == 1 && noRedDice > 0){
							setDiceColor = 1;								// set dice color to Red
							x++;											// add to throw dice array
						}
						else if(randomDiceColor == 2 && noYellowDice > 0){
							setDiceColor = 2;								// set dice color to Yellow
							x++;											// add to throw dice array
						}
						else if (noGreenDice == 0 && noRedDice == 0 && noYellowDice == 0 ){
							break;  										//stop loop
						}
												
						if(x > 0){
							threeDiceToThrow[x-1] = generateDice(setDiceColor);					// Dice Roll Result
							System.out.print("          [ "+ threeDiceToThrow[x-1] + " ]    ");	//random Color
						}
					}
				}
		
				System.out.println("");
				System.out.println("");
				System.out.println("");

				throwDicePrompt();	
				printCup();	
				
				// loop for dice results
				for(int i = 0; i < threeDiceToThrow.length; i++){
					if(threeDiceToThrow[i] == "Green"){
						myDice = throwDice(0);
						displayDiceColor = "Green ";
						
						if(myDice == "Brain"){
							noGreenDice--;								// remove dice from play
							curbrains++;								// add to current brains
						}
						else if(myDice == "Footprint"){
							rollGreenFootprint++;						// add to reroll dice for next throw
						}
						else if(myDice == "Shotgun"){
							noGreenDice--;								//remove dice from play
							shotgun++;									//add to current shotgun 
						}						
					}
					else if(threeDiceToThrow[i] == "Red"){
						myDice = throwDice(1);							
						displayDiceColor = "Red ";
						
						if(myDice == "Brain"){
							noRedDice--;								//remove dice from play
							curbrains++;								// add to current brains
						}
						else if(myDice == "Footprint"){
							rollRedFootprint++;							// add to reroll dice for next throw
						}
						else if(myDice == "Shotgun"){
							noRedDice--;								//remove dice from play
							shotgun++;									//add to current shotgun 
						}
					}
					else if(threeDiceToThrow[i] == "Yellow"){
						myDice = throwDice(2);
						displayDiceColor = "Yellow ";
						
						if(myDice == "Brain"){
							noYellowDice--;								//remove dice from play
							curbrains++;								// add to current brains
						}
						else if(myDice == "Footprint"){
							rollYellowFootprint++;						// add to reroll dice for next throw
						}
						else if(myDice == "Shotgun"){
							noYellowDice--;								//remove dice from play
							shotgun++;
						}
					}
					System.out.println("                                [ " + displayDiceColor + myDice + " ]");
				} 
				
				displayRollResults(shotgun, curbrains); // displays results of roll
				
				// change player 
				if(checkShotgun(shotgun)){
				 	shotgun = 0;
					nextPlayer = true;
				}
			}
			else if (userOption == 2){ 	//Bank Brains
				shotgun = 0;
				nextPlayer = true;
			
				displayTurnResults(playerScores, curbrains, turn);  // displays results at end of turn

				boolean finalround = checkFinalRound(playerScores, turn); //checks if score is 13 or higher

				if (finalround == true){ // score is <=13
					displayWinner(playerScores, noOfPlayers, playerNames);  // prints out winner
					lives = 0;  // ends game
				}
			}
			else if (userOption == 3){ // displays leaderboard
				System.out.println("Leaderboard: ");
				printScores(playerNames, playerScores);
			}
			else if (userOption == 4){ // user quits
				System.out.println("Are you sure?");
				System.out.println("1 - Cancel  |  0 - Quit");
				int quit = in.nextInt();

				if (quit == 0){
					lives = 0;
				}
			}
			
			// start turn for next player
			if(nextPlayer){
				nextPlayer = false;
				
				// reset Dice in cup
				noGreenDice = 6;
				noYellowDice = 4;
				noRedDice = 3;
				
				// reset footprints for next player
				rollRedFootprint = 0;
				rollGreenFootprint = 0;
				rollYellowFootprint = 0;
				
				curbrains = 0; // reset unbanked brains
				turn++; // next Player
			}
			
			if(turn == noOfPlayers){
				turn = 0; // return to starting player
			}
		}
	}
	

	//         --------------------------------- METHODS ---------------------------------------------

	// player must press enter to throw dice
	public static void throwDicePrompt(){
		System.out.println("Press enter to throw dice...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
	
	// display at beginning of each turn
	public static void displayStart(int x, String [] myArray ){
		System.out.println("");
		System.out.println("   ************** "+ myArray[x]+"'s" + " turn **************");
		System.out.println("");
	}
	
	/*public static void PrintNames(String [] myArray){
		for(int i = 0; i < myArray.length; i++){
			int playerNumber = i+1;
			System.out.print("Player " + playerNumber + " Name: ");
			System.out.println(myArray[i]);		
		}
	}*/

	// display scores on leaderboard 
	public static void printScores(String [] myArray, int [] scoreArray){
		for(int i = 0; i < myArray.length; i++){
			int playerNumber = i+1;
			System.out.println(" ------------------------------");
			System.out.print("   " + myArray[i] + ": ");		
			System.out.println(scoreArray[i] + " brains.");
			System.out.println(" ------------------------------");
			}
	}
	
	// Check current shotgun status
	public static boolean checkShotgun(int curShotguns){
		if(curShotguns > 2){
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
		else{
			System.out.println("                Not Dead");
			return false;
		}
	}

	// intro and rules
	public static void displayWelcomeMessage (){
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
    	System.out.println("       The first one to score 13 brains WINS! ");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
		System.out.println("        Now go eat some BRAAAAINS!");
		System.out.println("");
	}
	
	// randomly generate color of dice
	public static String generateDice(int dc){
		String colorOfDice = "";
		
		if(dc == 0){
			colorOfDice = "Green";
		}
		else if(dc == 1){
			colorOfDice = "Red";
		}
		else if(dc == 2){
			colorOfDice = "Yellow";
		}
		
		return colorOfDice ;
	}
	
	// randomly generate results of dice depending on color inputed
	public static String throwDice(int diceColor){
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		Random randomInt = new Random();
		String diceResult = "";
		
		int roll = randomInt.nextInt(6);	
					
		if(diceColor == 0){//Green Dice
			diceResult = greenDice[roll];
		}
		else if(diceColor == 1){// Red Dice 
			diceResult = redDice[roll];
		}
		else if(diceColor == 2){//Yellow Dice
			diceResult = yellowDice[roll];
		}
		else{
			diceResult = "Something went wrong";
		}
		return diceResult;
	}

	// game over
	public static boolean checkFinalRound (int [] playerScores, int turn) {
			if (playerScores[turn] >= 13){
				System.out.println(" ");
				System.out.println("     ");
				System.out.println("     __  __  __ __ ___    __   _   _  ___ ___ ");
				System.out.println("    / _]/  \\|  V  | __|  /__\\ | \\ / || __| _ \\");
				System.out.println("   | [/\\ /\\ | \\_/ | _|  | \\/ |`\\ V /'| _|| v /");
				System.out.println("    \\__/_||_|_| |_|___|  \\__/   \\_/  |___|_|_\\");

				return true;
			}
			return false;
		}

	// show resoults of dice throw
	public static void displayRollResults (int shotgun, int curbrains) {
		System.out.println(" ");
		System.out.println(" -----------------------------------------------");
		System.out.print(" Shots to the face: " + shotgun);
		System.out.println("    |   Delicious Brains: " + curbrains);
		System.out.println(" -----------------------------------------------");
		System.out.println(" ");
	}
	
	// display the winner
	public static void displayWinner (int[] scores, int players, String[] playerNames ){
		int highscore = scores[0];
		int i = 0;
		int winner = 0;

		while (i <= (players-1) ){
			if (scores[i] > highscore) {
				highscore = scores[i];
				winner = i;
			}
			i++;
		}
		System.out.println("");
		System.out.println("");
		System.out.println("                             _        ");           
		System.out.println("                     __ __ _(_)_ _  _ _  ___ _ _"); 
		System.out.println("                    \\ V  V / | ' \\| ' \\/ -_) '_|");
		System.out.println("                     \\_/\\_/|_|_||_|_||_\\___|_|  ");         
		System.out.println("");
		System.out.println("                 --------------------------------------------");
		System.out.println("                              " + playerNames[winner] + "      Score: " + highscore + "   ");
		System.out.println("                 --------------------------------------------");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

	// show leaderboard
	public static void displayTurnResults (int[] playerScores, int curbrains, int turn){
				playerScores[turn] = playerScores[turn] + curbrains;
				
				System.out.println("  ------------------------------------------------");
				System.out.println("       You got " + curbrains + " brains this turn. ");
				System.out.println("           TOTAL BRAINS: " + playerScores[turn]);
				System.out.println("  ------------------------------------------------");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
	}

	// This is a cup
	public static void printCup () {
		System.out.println("");
		System.out.println("");
		System.out.println("        .  .. .    ...    .          ");   
		System.out.println("      DMMOMMMDMMMMMMMMMMMMMM .        ");    
		System.out.println("   .Z                    IM    M        ");  
		System.out.println("   8                    M       M       ");  
		System.out.println("  I                    M         M      ");  
		System.out.println(" .N                  .M          M.     ");  
		System.out.println("  M                   8         .D      ");  
		System.out.println("  \\                  |          .D      ");  
		System.out.println("    7  ..            7           ?      ");  
		System.out.println("      NMM..          I          D.      ");  
		System.out.println("            '''$M    .M       .M.        ");  
		System.out.println("                 '''MM M ..  M          ");  
		System.out.println("                        MMMM            ");   
		System.out.println("");
		System.out.println("");
	}
}
