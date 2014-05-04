package com.me.mygdxgame.Model;

public class Pokeball extends Item {

	private String typePokeball;
	private int percentage;
	private static int noPokeball;

	public Pokeball(String itemName, String description, int weight,
			Monster monster, float price, String typePokeball, int percentage) {
		super(itemName, description, weight, monster, price);

		this.typePokeball = typePokeball;
		this.percentage = percentage;
		Pokeball.noPokeball++;

	}

	public String getTypePokeball() {
		return typePokeball;
	}

	public void setTypePokeball(String typePokeball) {
		this.typePokeball = typePokeball;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public static int getNoPokeball() {
		return noPokeball;
	}

}
