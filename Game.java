package heritage.model;

import java.time.LocalDate;

public class Game {
	private int games_ID;
	private String name, publisher,SKU;
	private double price;
	private LocalDate publication_date;
	
	public Game(int games_ID, String name, String publisher, LocalDate publication_date, double price, String SKU) {
		super();
		this.games_ID = games_ID;
		this.name = name;
		this.publisher = publisher;
		this.SKU = SKU;
		this.price = price;
		this.publication_date = publication_date;
	}

	@Override
	public String toString() {
		return "games_ID : " + games_ID + ", name : " + name + 
				", publisher : " + publisher + ", publication_date : " 
				+ publication_date + ", price : " + price + ", SKU : " + SKU;
	}



}
