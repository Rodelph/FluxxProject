import java.io.FileNotFoundException;
import java.util.Scanner;

public class execute
{
	public static void main(String[] args) throws FileNotFoundException, InterruptedException
	{	
		Utility.printIntro();
		
		Game game;
		int numberofplayers = 0;
		try(Scanner scan = new Scanner(System.in)) 
		{
			while (numberofplayers < 2 || numberofplayers > 6)
			{
				System.out.println(Utility.ANSI_CYAN + "\n@ Before starting the game please insert the number of players (Between 2 and 6 players) @\n" + Utility.ANSI_RESET);
				System.out.print(Utility.ANSI_RED_BACKGROUND + "The number of players is going to be ::" + Utility.ANSI_RESET + " ");
				numberofplayers = scan.nextInt(); 
			}
			
			Utility.loadingAnimation();
			
			game = new Game(numberofplayers);
			
			while(game.isGameRunning())
			{
				game.run(scan);
			}
			
			scan.close();
			System.out.println(Utility.ANSI_CYAN + "Game Over !!\nThanks for playing Fluxx." + Utility.ANSI_RESET);
		}
	}
}