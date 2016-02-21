import java.util.*; 

	// dice array test

class gametest1 {
	public static void main (String[]args) {
		Scanner in = new Scanner(System.in);
		Random randomInt = new Random();
		
		
		//Arrays
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		String [] diceColor = {"green", "green", "green", "green", "green", "green", "yellow", "yellow", "yellow", "yellow", "red", "red", "red"};

		String diceresult1 = " ";
		String diceresult2 = " ";
		String diceresult3 = " ";
		
		

		int dice1 = 0;
		diceresult1 = rollColor(dice1, diceColor);

		int dice2 = 0;
		diceresult2 = rollColor(dice2, diceColor);

		int dice3 = 0;
		diceresult3 = rollColor(dice3, diceColor);

		System.out.println(" ");

		rollDice(diceresult1);
		rollDice(diceresult2);
		rollDice(diceresult3);




	}

	public static String rollColor (int dice, String[] diceColor) {
		boolean rollcolor = false;
		int x = 13;
		Random randomInt = new Random();
		String temp = " ";

		while (rollcolor == false){
			dice = randomInt.nextInt(x);
			// System.out.println(dice);
			if (diceColor[dice] == " ") {
				rollcolor = false;
			}
			else {
				System.out.println(diceColor[dice]);
				temp = diceColor[dice];
				diceColor[dice] = " ";
				rollcolor = true;
			}
		}
		return temp;
	}

	public static void rollDice (String diceresult){
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		Random randomInt = new Random();

		if (diceresult == "red"){

			int roll1 = randomInt.nextInt(6);
			System.out.println(redDice[roll1]);
			System.out.println(" ");
		}
		else if (diceresult == "green"){
			int roll2 = randomInt.nextInt(6);
			System.out.println(greenDice[roll2]);
			System.out.println(" ");
		}

		else if (diceresult == "yellow"){
			int roll3 = randomInt.nextInt(6);
			System.out.println(yellowDice[roll3]);
			System.out.println(" ");
		}
	
	}
}