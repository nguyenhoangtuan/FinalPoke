package com.me.mygdxgame.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@SuppressWarnings("unused")
public class AnimationObject {
	
	private int columAni;
	private int rowAni;
	private Texture walkSheet;
	private TextureRegion currentFrame;
	private Animation walkAnimation;
	private TextureRegion[] walkFrames;
	private float stateTime;
	private float timePerFrame;
	private String fileName;
	
	public AnimationObject(int columAni, int rowAni, float timePerFrame, String fileName) {
		this.fileName = fileName;
		this.columAni = columAni;
		this.rowAni = rowAni;
		this.timePerFrame = timePerFrame;

		walkSheet = new Texture(this.fileName);
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / columAni, walkSheet.getHeight() / rowAni);
		stateTime = 0f;
		walkFrames = new TextureRegion[this.rowAni * this.columAni];
		int index = 0;
		for (int i = 0; i < rowAni; i++) {
			for (int j = 0; j < columAni; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(this.timePerFrame, walkFrames);
		walkAnimation.setPlayMode(Animation.NORMAL);
	
	}

	public Animation getWalkAnimation() {
		return walkAnimation;
	}

	public void setWalkAnimation(Animation walkAnimation) {
		this.walkAnimation = walkAnimation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
