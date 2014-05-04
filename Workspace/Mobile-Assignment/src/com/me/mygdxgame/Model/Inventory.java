package com.me.mygdxgame.Model;

@SuppressWarnings("unused")
public class Inventory extends Item {
	private String type;
	private int rangeOfAffect;
	private int percentage;
	private static int noInventory;
	
	public Inventory (String itemName, String description, String type, int weight, int percentage, Monster monster, float price) {
		super (itemName, description, weight, monster, price);
		this.type = type;
		this.percentage = percentage;
		Inventory.noInventory++;
		
		getItemsAffect();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRangeOfAffect() {
		return rangeOfAffect;
	}

	public void setRangeOfAffect(int rangeOfAffect) {
		this.rangeOfAffect = rangeOfAffect;
	}

	public static int getNoInventory() {
		return noInventory;
	}

	private void getItemsAffect() {
//		if (type.equals(super.DECREASE_OPDEF)) {
//			rangeOfAffect = super.monster.getDefend() - (super.monster.getDefend() * percentage / 100);
//			super.monster.setDefend(rangeOfAffect);
//		} else if (type.equals(super.INCREASE_BOTH)) {
//			super.monster.setAtkMagic(super.monster.getAtkMagic() + (super.monster.getAtkMagic() * percentage / 100));
//			super.monster.setAtkPhysic(super.monster.getAtkPhysic() + (super.monster.getAtkPhysic() * percentage / 100));
//			super.monster.setDefend(super.monster.getDefend() + (super.monster.getDefend() * percentage / 100));
//		} else if (type.equals(super.INCREASE_DAM)) {
//			super.monster.setAtkMagic(super.monster.getAtkMagic() + (super.monster.getAtkMagic() * percentage / 100));
//			super.monster.setAtkPhysic(super.monster.getAtkPhysic() + (super.monster.getAtkPhysic() * percentage / 100));
//		} else if (type.equals(super.INCREASE_DEF)) {
//			rangeOfAffect = super.monster.getDefend() + (super.monster.getDefend() * percentage / 100);
//			super.monster.setDefend(rangeOfAffect);
//		} else if (type.equals(super.REBORN)) {
//			super.monster.setCurrentHP(super.monster.getHp());
//			super.monster.setCurrentMP(super.monster.getMp());
//		} else if (type.equals(super.RESTORE_HEALTH)) {
//			super.monster.setCurrentHP(super.monster.getCurrentHP() + (super.monster.getCurrentHP() * percentage / 100));
//		} else if (type.equals(super.RESTORE_MANA)) {
//			super.monster.setCurrentMP(super.monster.getCurrentMP() + (super.monster.getCurrentMP() * percentage / 100));
//		}
	}
}
