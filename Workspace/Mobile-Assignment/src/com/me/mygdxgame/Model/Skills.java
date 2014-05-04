package com.me.mygdxgame.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("unused")
public class Skills {
	private String name;
	private int levelSkill;

	private String skillType;
	private int percentAtk;
	private String element;
	private String fileName;
	private String target; // enemy or team
	
	private int columAni;
	private int rowAni;
	private Texture walkSheet;
	private TextureRegion currentFrame;
	private Animation walkAnimation;
	private TextureRegion[] walkFrames;
	private float stateTime;
	private float timePerFrame;
	
	private int scaleSkill;
	
	private int numberOfTarget;
	
	private boolean skillState = false;;
	private float timecount;
	
	public Skills(String name, String filename, String skillType,
			int percentAtk, String element, int colum, int rows, float timePerFrame , String target) {
	
		this.name = name;
		this.fileName = filename;
		this.skillType = skillType;
		this.percentAtk = percentAtk;
		this.element = element;
		this.columAni = colum;
		this.rowAni = rows;
		this.timePerFrame = timePerFrame;
		this.target = target;
		
		walkSheet = new Texture(fileName);
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / columAni, walkSheet.getHeight() / rowAni);
		stateTime = 0f;
		walkFrames = new TextureRegion[rowAni * columAni];
		int index = 0;
		for (int i = 0; i < rowAni; i++) {
			for (int j = 0; j < columAni; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(timePerFrame, walkFrames);
		walkAnimation.setPlayMode(Animation.NORMAL);
	}


	public void useSkill(SpriteBatch batch , Vector2 locationMonster , int width , int hight) {
		batch.begin();
		if (skillState) {
			timecount += Gdx.graphics.getDeltaTime();
			
			currentFrame = walkAnimation.getKeyFrame(timecount, true);
			batch.draw(currentFrame, locationMonster.x, locationMonster.y, width , hight);
			if (walkAnimation.getKeyFrameIndex(timecount) == (rowAni * columAni -1 )) {
				skillState = false;
				timecount = 0f;
			}
		}
		batch.end();
	}
	
	public void useSkill(SpriteBatch batch , Vector2 locationMonster , int width , int hight , float time) {
		batch.begin();
		if (skillState) {
			currentFrame = walkAnimation.getKeyFrame(time, true);
			batch.draw(currentFrame, locationMonster.x, locationMonster.y, width , hight);
			if (walkAnimation.getKeyFrameIndex(time) == (rowAni * columAni -1 )) {
				skillState = false;
				time = 0f;
			}
		}
		batch.end();
	}
	
	public boolean isSkillEnd() {
		
		return true;
	}

	

	public int getScaleSkill() {
		return scaleSkill;
	}


	public void setScaleSkill(int scaleSkill) {
		this.scaleSkill = scaleSkill;
	}


	public int getNumberOfTarget() {
		return numberOfTarget;
	}


	public void setNumberOfTarget(int numberOfTarget) {
		this.numberOfTarget = numberOfTarget;
	}


	public boolean isSkillState() {
		return skillState;
	}


	public void setSkillState(boolean skillState) {
		this.skillState = skillState;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevelSkill() {
		return levelSkill;
	}


	public void setLevelSkill(int levelSkill) {
		this.levelSkill = levelSkill;
	}


	public String getSkillType() {
		return skillType;
	}


	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}


	public int getPercentAtk() {
		return percentAtk;
	}


	public void setPercentAtk(int percentAtk) {
		this.percentAtk = percentAtk;
	}


	public String getElement() {
		return element;
	}


	public void setElement(String element) {
		this.element = element;
	}


	public Animation getWalkAnimation() {
		return walkAnimation;
	}


	public void setWalkAnimation(Animation walkAnimation) {
		this.walkAnimation = walkAnimation;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}
	
	
	
}
