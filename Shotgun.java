import java.util.*; 

class Shotgun
{
	public static void main (String[]args)
	{
		Scanner in = new Scanner(System.in);
		
	  	//players settings
		int userOption = 0;
		int shotgun = 0;
		int lives = 3;
		int brains = 10;
		
		while(lives != 0)
		{
			System.out.print("Press 1 to play: ");
			userOption = in.nextInt();
			
			if(userOption == 1)
			{
				shotgun++;
			}
			else
			{
				lives = 0;
			}
						
			if(CheckShotgun(shotgun))
			{
				shotgun = 0;
				brains = 0;
			}
			
			System.out.println(shotgun);
			System.out.println(brains);
		}
	}
	
	public static boolean CheckShotgun(int curShotguns)
	{
		if(curShotguns > 2)
		{
			System.out.println("dead");
			return true;
		}
		else
		{
			System.out.println("Not Dead");
			return false;
		}
	}
}
