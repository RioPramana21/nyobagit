import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import classes.Game;

public class Main {
	Scanner in = new Scanner(System.in);
	Random rand = new Random();
	
	Vector<Game> gameList = new Vector<Game>();
	
	public void cls() {
		for (int i = 0; i < 21; ++i) {
			System.out.println();
		}
	}
	
	public String randomCustID() {
		String id = "CU";
		Integer randomNumber;
		for (int i = 0; i < 4; ++i) {
			do {
				randomNumber = rand.nextInt(10);
			}while(randomNumber < 0);
			id.concat(randomNumber.toString());
		}
		
		return id;
	}
	
	public void homePage() {
		int menu = 0;
		do {
			cls();
			System.out.println("JH GAME SHOP");
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("1. Add new game");
			System.out.println("2. View all game");
			System.out.println("3. Sell a game");
			System.out.println("4. Exit");
			System.out.println("===================================================================");
			System.out.print("Your Choice: ");
			menu = in.nextInt(); in.nextLine();
			switch(menu) {
				case 1:
					add();
					break;
				case 2:
					view();
					if (gameList.size() != 0) {
						System.out.println("Press enter to continue...");
						in.nextLine();
					}
					break;
				case 3:
					sell();
					break;
				case 4:
					cls();
					System.out.println("Goodbye.....");
					break;
			}
		}while(menu != 4);
	}
	
	public void add() {
		cls();
		if (gameList.size() == 35) {
			System.out.println("JH Game Shop is already full...");
			System.out.println("Press enter to continue...");
			in.nextLine();
			return;
		}
		String name;
		String condition;
		int price;
		int len;
		do {
			System.out.print("Input game's name [5 - 25 characters]: ");
			name = in.nextLine();
			len = name.length();
			if (len < 5 || len > 25) System.out.println("Name must be between 5 - 25 characters");
		}while(len < 5 || len > 25);
		do {
			System.out.print("Input game's condition [New or Used (case sensitive)]: ");
			condition = in.nextLine();
		}while(!(condition.equals("New") || condition.equals("Used")));
		do {
			System.out.print("Input game's price [5000 - 850000]: ");
			price = in.nextInt(); in.nextLine();
			if (price < 5000 || price > 850000) System.out.println("Input must be valid number between 5000 - 850000");
		}while(price < 5000 || price > 850000);
		gameList.add(new Game(name, condition, price));
		System.out.println("Game has been added to the inventory successfully");
		System.out.println("Press enter to continue...");
		in.nextLine();
	}
	
	public void view() {
		cls();
		if (gameList.size() == 0) {
			System.out.println("JH Game Shop is still empty...");
			System.out.println("Press enter to continue...");
			in.nextLine();
			return;
		}
		
		Collections.sort(gameList);
		
		int index = 0;
		System.out.println("All games in JH Game Shop");
		System.out.println("========================================================================");
		System.out.println("|No |Game's Name |Game's Condition |Game's Price |");
		for (Game game : gameList) {
			index++;
			System.out.println(index + " |" + game.getName() + " |" + game.getCondition() + " |" + game.getPrice() + " |");
		}
		System.out.println("========================================================================");
	}
	
	public void sell() {
		view();
		if (gameList.size() != 0) {
			String customer = randomCustID();
			int selected = 0;
			double sellPrice = 0;
			do {
				System.out.print("Input the index number of game that you want to sell [1 - " + gameList.size() + "]: ");
				selected = in.nextInt(); in.nextLine();
				if (selected < 1 || selected > gameList.size()) System.out.println("Input must be valid number between 1 - " + gameList.size());
			}while(selected < 1 || selected > gameList.size());
			Game sold = gameList.get(selected-1);
			if (sold.getCondition().equals("New")) sellPrice = sold.getPrice() * 3.0 + 25000;
			else sellPrice = (double)sold.getPrice() * 1.5 + 15000;
			gameList.remove(selected-1);
			System.out.println(sold.getName() + " has been sold to the customer with the id of " + customer + " for Rp." + sellPrice);
			System.out.println("Press enter to continue...");
			in.nextLine();
		}
	}

	public Main() {
		homePage();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
