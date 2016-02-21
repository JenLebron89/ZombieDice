import java.util.*; 

	// dice array test

class dicetest3 {
	public static void main (String[]args) {
		Scanner in = new Scanner(System.in);
		Random randomInt = new Random();
		
		
		//Arrays
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		String [] diceColor = {"green", "green", "green", "green", "green", "green", "yellow", "yellow", "yellow", "yellow", "red", "red", "red"};

		
		

		int dice1 = 0;
		rollColor(dice1, diceColor);

		int dice2 = 0;
		rollColor(dice2, diceColor);

		int dice3 = 0;
		rollColor(dice3, diceColor);




	}

	public static void rollColor (int dice, String[] diceColor) {
		boolean rollcolor = false;
		int x = 13;
		Random randomInt = new Random();

		while (rollcolor == false){
			dice = randomInt.nextInt(x);
			// System.out.println(dice);
				if (diceColor[dice] == " ") {
					rollcolor = false;
				}
				else {
					System.out.println(diceColor[dice]);
					diceColor[dice] = " ";
					rollcolor = true;
				}
		}


	}
}