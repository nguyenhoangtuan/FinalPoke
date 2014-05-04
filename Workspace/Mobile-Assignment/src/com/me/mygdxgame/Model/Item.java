package com.me.mygdxgame.Model;

public class Item {
	public static final int MAX_BALL = 10;
	public static final int MAX_INVENTORY = 10;
	public static final int MAX_INVENTORY_ATURN = 1;
	public final String RESTORE_HEALTH = "resHealth";
	public final String RESTORE_MANA = "resMana";
	public final String INCREASE_DAM = "incDamage";
	public final String INCREASE_DEF = "incDef";
	public final String INCREASE_BOTH = "incBoth";
	public final String REBORN = "reborn";
	public final String DECREASE_OPDEF = "decDef";
	protected String itemName;
	protected String description;
	protected float price;
	protected int weight;
	protected static int totalNoItem;
	protected Monster monster;
	
	public Item (String itemName, String description, int weight, Monster monster, float price) {
		this.itemName = itemName;
		this.description = description;
		this.weight = weight;
		this.monster = monster;
		this.price = price;
		totalNoItem++;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getTotalNoItem() {
		return totalNoItem;
	}

}
