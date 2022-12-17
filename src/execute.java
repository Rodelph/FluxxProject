import java.io.FileNotFoundException;
import java.util.Scanner;

public class execute
{

	public static void printIntro()
	{
		System.out.println(Utility.ANSI_RED + " --------------------- Welcome to Fluxx Game --------------------- " );
		System.out.println("|                                                                 |");
		System.out.println("|   " + Utility.ANSI_RESET + "      Made By :: Naqi Amine && Kooli Firass Mohammed          " + Utility.ANSI_RED + "|");
		System.out.println("|                                                                 |");
		System.out.println(" -----------------------------------------------------------------" + Utility.ANSI_RESET);
		System.out.println("");
	}
	
	public static void loadingAnimation() throws  InterruptedException
	{
		System.out.print(Utility.ANSI_RED + "\n@ The game is going to start in " + Utility.ANSI_RESET + Utility.ANSI_RED_BACKGROUND + "3" + Utility.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" " + Utility.ANSI_YELLOW_BACKGROUND + "2" + Utility.ANSI_RESET + " ");
		Thread.sleep(1000);
		System.out.print(Utility.ANSI_CYAN_BACKGROUND  + "1"  + Utility.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" .");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(" " + Utility.ANSI_GREEN_BACKGROUND + "GO FLUX !!" + Utility.ANSI_RESET + Utility.ANSI_RED + " @\n" + Utility.ANSI_RESET);
		Thread.sleep(1000);
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException
	{	
		printIntro();
		
		Game game;
		int numberofplayers = 0;
		try(Scanner scan = new Scanner(System.in)) 
		{
			while (numberofplayers < 2 || numberofplayers > 6)
			{
				System.out.println(Utility.ANSI_CYAN + "\n@ Before starting the game please insert the number of players (Between 2 and 6 players) @\n" + Utility.ANSI_RESET);
				System.out.print(Utility.ANSI_RED_BACKGROUND + "The amount of players is going to be ::" + Utility.ANSI_RESET + " ");
				numberofplayers = scan.nextInt(); 
			}
			
			loadingAnimation();
			
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