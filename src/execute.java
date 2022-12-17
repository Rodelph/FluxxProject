import java.io.FileNotFoundException;
import java.util.Scanner;

public class execute
{

	public static void printIntro()
	{
		System.out.println(Game.ANSI_RED + " --------------------- Welcome to Fluxx Game --------------------- " );
		System.out.println("|                                                                 |");
		System.out.println("|   " + Game.ANSI_RESET + "      Made By :: Naqi Amine && Kooli Firass Mohammed          " + Game.ANSI_RED + "|");
		System.out.println("|                                                                 |");
		System.out.println(" -----------------------------------------------------------------" + Game.ANSI_RESET);
		System.out.println("");
	}
	
	public static void loadingAnimation() throws  InterruptedException
	{
		System.out.print(Game.ANSI_RED + "\n@ The game is going to start in " + Game.ANSI_RESET + Game.ANSI_RED_BACKGROUND + "3" + Game.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" " + Game.ANSI_YELLOW_BACKGROUND + "2" + Game.ANSI_RESET + " ");
		Thread.sleep(1000);
		System.out.print(Game.ANSI_CYAN_BACKGROUND  + "1"  + Game.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" .");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(" " + Game.ANSI_GREEN_BACKGROUND + "GO FLUX !!" + Game.ANSI_RESET + Game.ANSI_RED + " @\n" + Game.ANSI_RESET);
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
				System.out.println(Game.ANSI_CYAN + "\n@ Before starting the game please insert the number of players (Between 2 and 6 players) @\n" + Game.ANSI_RESET);
				System.out.print(Game.ANSI_RED_BACKGROUND + "The amount of players is going to be ::" + Game.ANSI_RESET + " ");
				numberofplayers = scan.nextInt(); 
			}
			
			//loadingAnimation();
			
			game = new Game(numberofplayers);

			while(game.isGameRunning())
			{
				game.run(scan);
			}
			
			scan.close();
			System.out.println(Game.ANSI_CYAN + "Game Over !!\nThanks for playing Fluxx." + Game.ANSI_RESET);
		}
	}
}