import java.io.FileNotFoundException;
import java.util.Scanner;

public class execute {

	public static void printIntro()
	{
		System.out.println(" --------------------- Welcome to Fluxx Game --------------------- " );
		System.out.println("|                                                                 |");
		System.out.println("|   Project Made By :: Naqi Amine && Kooli Firass Mohammed        |");
		System.out.println("|                                                                 |");
		System.out.println(" -----------------------------------------------------------------");
		System.out.println("");
	}
	
	public static void loadingAnimation() throws  InterruptedException
	{
		System.out.print("\n@ The game is going to start in 3");
		Thread.sleep(1000);
		System.out.print(" 2 ");
		Thread.sleep(1000);
		System.out.print("1");
		Thread.sleep(1000);
		System.out.print(" .");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(" GO FLUX !! @\n");
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
				System.out.println("\n@ Before starting the game please insert the number of players (Between 2 and 6 players) @\n");
				System.out.print("The amount of players is going to be :: ");
				numberofplayers = scan.nextInt(); 
			}
			
			//loadingAnimation();
			
			game = new Game(numberofplayers);

			while(game.isGameRunning())
			{
				game.run(scan.nextInt());
			}
			
			scan.close();
		}
	}
}