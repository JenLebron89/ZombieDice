import java.util.*; 

	// dice array test

class dicetest1 {
	public static void main (String[]args) {
		Scanner in = new Scanner(System.in);
		Random randomInt = new Random();
		
		//Arrays
		String [] redDice = {"Shotgun", "Shotgun", "Shotgun","Footprint","Footprint","Brain"};
		String [] greenDice = {"Shotgun","Footprint","Footprint","Brain","Brain","Brain"};
		String [] yellowDice = {"Shotgun","Shotgun","Footprint","Footprint","Brain","Brain"};
		
		String [] diceColor = {"green", "green", "green", "green", "green", "green", "yellow", "yellow", "yellow", "yellow", "red", "red", "red"};

		int dice1 = randomInt.nextInt(13);
		System.out.println(diceColor[dice1]);

		int dice2 = randomInt.nextInt(12);
		System.out.println(diceColor[dice2]);

		int dice3 = randomInt.nextInt(11);
		System.out.println(diceColor[dice3]);



	}
}