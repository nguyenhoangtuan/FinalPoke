package com.me.mygdxgame.Model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster implements Cloneable {
	private String name;
	private boolean catchAble = true;
	private int hp;
	private int mp;

	private int level;
	private int defendMagic;
	private int defendPhysic;
	private int atkPhysic;
	private int atkMagic;
	private String element;

	private int exp;
	private int expGain; // from 0 to 100;

	// fire 20% atkphy 10% other 5% all def
	// water 20% atkmag 10% other 5% def
	// rock 15% def 10% other
	// nature 10% all
	// wind 15% atk 9% other

	private ArrayList<Skills> skills;
	private int involment;
	private ArrayList<Skills> skillsAvalable;

	private int currentHP;
	private int currentMP;
	private int currentLevel;
	private int currentatkPhysic;
	private int currentatkMagic;
	private int currentdefendMagic;
	private int currentdefendPhysic;

	private Texture monsterTexture;
	private String fileNameImage;
	private String fileNameAnimation;

	private AnimationObject animation;

	private AnimationPlayer ap;
	private float stateTime;
	private TextureRegion currentFrameRight, currentFrameLeft,
			currentFrameDown;

	private int idInBattleMine;
	private int idInBattkeEnemy;

	private Vector2 location;
	private Vector2 Originlocation;
	private boolean boss;

	private boolean die = false;

	public Monster(String name, int hp, int mp, int level, int defendMa,
			int defendPy, int atkPhysic, int atkMagic, String element,
			ArrayList<Skills> skills, int involment,
			ArrayList<Skills> skillsAvalable, String filename,
			String fileNameAnimation) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.level = level;
		this.defendMagic = defendMa;
		this.defendPhysic = defendPy;
		this.atkPhysic = atkPhysic;
		this.atkMagic = atkMagic;
		this.element = element;
		this.skills = skills;
		this.involment = involment;
		if (skillsAvalable == null) {
			this.skillsAvalable = new ArrayList<Skills>();
		} else {
			this.skillsAvalable = skillsAvalable;
		}
		this.fileNameImage = filename;
		this.monsterTexture = new Texture(filename);
		this.fileNameAnimation = fileNameAnimation;

		createAnimation();

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public void increaseLevelCheck() {
		if (this.exp >= 100) {
			levelUp();
			this.level = level + 1;
			prepareBattle();
			this.exp = 0;
		}
	}

	public void prepareBattle() {
		currentHP = hp;
		currentMP = mp;
		currentLevel = level;
		currentatkPhysic = atkPhysic;
		currentatkMagic = atkMagic;
		currentdefendMagic = defendMagic;
		currentdefendPhysic = defendPhysic;
	}

	public void levelUp() {
		if (this.element.equals("fire")) {
			hp = hp * 105 / 100;
			mp = mp * 105 / 100;
			atkPhysic = atkPhysic * 120 / 100;
			atkMagic = atkMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		} else if (this.element.equals("water")) {
			hp = hp * 105 / 100;
			mp = mp * 105 / 100;
			atkPhysic = atkPhysic * 110 / 100;
			atkMagic = atkMagic * 120 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		} else if (this.element.equals("rock")) {
			hp = hp * 120 / 100;
			mp = mp * 120 / 100;
			atkPhysic = atkPhysic * 110 / 100;
			atkMagic = atkMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		} else if (this.element.equals("nature")) {
			hp = hp * 110 / 100;
			mp = mp * 110 / 100;
			atkPhysic = atkPhysic * 110 / 100;
			atkMagic = atkMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		} else if (this.element.equals("wind")) {
			hp = hp * 125 / 100;
			mp = mp * 125 / 100;
			atkPhysic = atkPhysic * 110 / 100;
			atkMagic = atkMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		} else {
			hp = hp * 125 / 100;
			mp = mp * 125 / 100;
			atkPhysic = atkPhysic * 110 / 100;
			atkMagic = atkMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
			// defendMagic = defendMagic * 110 / 100;
		}
	}

	public void setLevelForMonster(int inte) {
		for (int i = 1; i < inte; i++) {
			this.exp = 100;
			increaseLevelCheck();
		}
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public void levelDownToOne() {

		for (int i = 1; i < level; i++) {

			if (this.element.equals("fire")) {
				hp = hp * 100 / 105;
				mp = mp * 100 / 105;
				atkPhysic = atkPhysic * 100 / 120;
				atkMagic = atkMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			} else if (this.element.equals("water")) {
				hp = hp * 100 / 105;
				mp = mp * 100 / 105;
				atkPhysic = atkPhysic * 100 / 110;
				atkMagic = atkMagic * 100 / 120;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			} else if (this.element.equals("rock")) {
				hp = hp * 100 / 120;
				mp = mp * 100 / 120;
				atkPhysic = atkPhysic * 100 / 110;
				atkMagic = atkMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			} else if (this.element.equals("nature")) {
				hp = hp * 100 / 110;
				mp = mp * 100 / 110;
				atkPhysic = atkPhysic * 100 / 110;
				atkMagic = atkMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			} else if (this.element.equals("wind")) {
				hp = hp * 100 / 125;
				mp = mp * 100 / 125;
				atkPhysic = atkPhysic * 100 / 110;
				atkMagic = atkMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			} else {
				hp = hp * 100 / 110;
				mp = mp * 100 / 110;
				atkPhysic = atkPhysic * 100 / 110;
				atkMagic = atkMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
				// defendMagic = defendMagic * 100 / 110;
			}
		}
	}

	public void randomSKill() {
		// skillsAvalable.removeAll(skillsAvalable);
		System.out.println("Size : aaaaaa" + skills.size());
		if (skills == null || skills.size() == 0) {
			return;
		} else {
			int size = skills.size();
			System.out.println("Size : " + size);
			Random rad = new Random();
			ArrayList<Integer> arrayInt = new ArrayList<Integer>();
			int randomNumber = rad.nextInt(size - 0 + 1) + 0;
			System.out.println("rasndom number " + randomNumber);
			for (int i = 0; i < randomNumber; i++) {
				int check = rad.nextInt(size - 1 - 0 + 1) + 0;
				if (arrayInt.contains(check)) {
					i--;
				} else {
					arrayInt.add(check);
				}
			}
			System.out.println("array sizer " + arrayInt.size());
			skillsAvalable = new ArrayList<Skills>();
			for (Integer inmte : arrayInt) {
				skillsAvalable.add(skills.get(inmte));
			}
		}
	}

	public boolean isCatchAble() {
		return catchAble;
	}

	public void setCatchAble(boolean catchAble) {
		this.catchAble = catchAble;
	}

	public boolean isDie() {
		return die;
	}

	public boolean checkIsDieByHp() {
		if (currentHP <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	
	
	@Override
	public String toString() {
		String skillString = "";
		for (Skills ski : skillsAvalable) {
			if (skillString.equals("")) {
				skillString = ski.getName();
			} else {
				skillString = skillString +"<->" +ski.getName();
			}
		}
		return name + "<->" + hp + "<->" + mp
				+ "<->" + level + "<->" + defendMagic
				+ "<->" + defendPhysic + "<->" + atkPhysic
				+ "<->" + atkMagic + "<->" + element + "<->"
				+ exp + "<->"
				+ currentHP + "<->"
				+ currentMP
				+ "<->" + fileNameImage + "<->"
				+ fileNameAnimation 
				+ "<->" + die + "<->"
				+ skillsAvalable.size() + "<->" + skillString;
	}

	public Vector2 getLocation() {
		return location;
	}

	public void setLocation(Vector2 location) {
		this.location = location;
	}

	public Vector2 getOriginlocation() {
		return Originlocation;
	}

	public void setOriginlocation(Vector2 originlocation) {
		Originlocation = originlocation;
	}

	public void createAnimation() {
		ap = new AnimationPlayer(0.25f, fileNameAnimation);
	}

	public void drawMonsterEnemy(SpriteBatch batch, float x, float y,
			float stateTime) {

		currentFrameRight = ap.getWalkAnimationRight().getKeyFrame(stateTime,
				true);
		if (this.isBoss()) {
			batch.begin();
			batch.draw(currentFrameRight, x, y, 0, 0, 100, 100, 1, 1, 1);
			batch.end();

		} else {
			batch.begin();
			batch.draw(currentFrameRight, x, y, 0, 0, 30, 30, 1, 1, 1);
			batch.end();
		}
	}

	public void drawMonsterManage(SpriteBatch batch, float x, float y,
			float stateTime) {

		currentFrameDown = ap.getWalkAnimationDown().getKeyFrame(stateTime,
				true);
		batch.begin();
		batch.draw(currentFrameDown, x, y, 0, 0, 30, 30, 1, 1, 1);
		batch.end();
	}

	public void drawMonsterMine(SpriteBatch batch, float x, float y,
			float stateTime) {

		currentFrameLeft = ap.getWalkAnimationLeft().getKeyFrame(stateTime,
				true);
		batch.begin();
		batch.draw(currentFrameLeft, x, y, 0, 0, 30, 30, 1, 1, 1);
		batch.end();
	}

	public void addSkill(Skills skill) {
		skillsAvalable.add(skill);
	}

	public int getIdInBattleMine() {
		return idInBattleMine;
	}

	public void setIdInBattleMine(int idInBattleMine) {
		this.idInBattleMine = idInBattleMine;
	}

	public int getIdInBattkeEnemy() {
		return idInBattkeEnemy;
	}

	public void setIdInBattkeEnemy(int idInBattkeEnemy) {
		this.idInBattkeEnemy = idInBattkeEnemy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAtkPhysic() {
		return atkPhysic;
	}

	public void setAtkPhysic(int atkPhysic) {
		this.atkPhysic = atkPhysic;
	}

	public int getAtkMagic() {
		return atkMagic;
	}

	public void setAtkMagic(int atkMagic) {
		this.atkMagic = atkMagic;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public ArrayList<Skills> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skills> skills) {
		this.skills = skills;
	}

	public int getInvolment() {
		return involment;
	}

	public void setInvolment(int involment) {
		this.involment = involment;
	}

	public ArrayList<Skills> getSkillsAvalable() {
		return skillsAvalable;
	}

	public void setSkillsAvalable(ArrayList<Skills> skillsAvalable) {
		this.skillsAvalable = skillsAvalable;
	}

	public Texture getMonsterTexture() {
		return monsterTexture;
	}

	public void setMonsterTexture(Texture monsterTexture) {
		this.monsterTexture = monsterTexture;
	}

	public String getFileNameImage() {
		return fileNameImage;
	}

	public void setFileNameImage(String fileNameImage) {
		this.fileNameImage = fileNameImage;
	}

	public AnimationObject getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationObject animation) {
		this.animation = animation;
	}

	public int getDefendMagic() {
		return defendMagic;
	}

	public void setDefendMagic(int defendMagic) {
		this.defendMagic = defendMagic;
	}

	public int getDefendPhysic() {
		return defendPhysic;
	}

	public void setDefendPhysic(int defendPhysic) {
		this.defendPhysic = defendPhysic;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		
		if (currentHP > hp) {
			currentHP = hp;
		}
		this.currentHP = currentHP;

	}

	public int getCurrentMP() {
		return currentMP;
	}

	public void setCurrentMP(int currentMP) {
		this.currentMP = currentMP;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getCurrentatkPhysic() {
		return currentatkPhysic;
	}

	public void setCurrentatkPhysic(int currentatkPhysic) {
		this.currentatkPhysic = currentatkPhysic;
	}

	public int getCurrentatkMagic() {
		return currentatkMagic;
	}

	public void setCurrentatkMagic(int currentatkMagic) {
		this.currentatkMagic = currentatkMagic;
	}

	public int getCurrentdefendMagic() {
		return currentdefendMagic;
	}

	public void setCurrentdefendMagic(int currentdefendMagic) {
		this.currentdefendMagic = currentdefendMagic;
	}

	public int getCurrentdefendPhysic() {
		return currentdefendPhysic;
	}

	public void setCurrentdefendPhysic(int currentdefendPhysic) {
		this.currentdefendPhysic = currentdefendPhysic;
	}

	public String getFileNameAnimation() {
		return fileNameAnimation;
	}

	public void setFileNameAnimation(String fileNameAnimation) {
		this.fileNameAnimation = fileNameAnimation;
	}

	public AnimationPlayer getAp() {
		return ap;
	}

	public void setAp(AnimationPlayer ap) {
		this.ap = ap;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public TextureRegion getCurrentFrameRight() {
		return currentFrameRight;
	}

	public void setCurrentFrameRight(TextureRegion currentFrameRight) {
		this.currentFrameRight = currentFrameRight;
	}

	public TextureRegion getCurrentFrameLeft() {
		return currentFrameLeft;
	}

	public void setCurrentFrameLeft(TextureRegion currentFrameLeft) {
		this.currentFrameLeft = currentFrameLeft;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpGain() {
		return expGain;
	}

	public void setExpGain(int expGain) {
		this.expGain = expGain;
	}

}
