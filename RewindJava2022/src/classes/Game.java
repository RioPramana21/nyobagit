package classes;

public class Game implements Comparable<Game>{
	
	protected String name;
	protected String condition;
	protected int price;

	public Game(String name, String condition, int price) {
		this.name = name;
		this.condition = condition;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int compareTo(Game game) {
		return this.getName().compareTo(game.getName());
	}
	
}