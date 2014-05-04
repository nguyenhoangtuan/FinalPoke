package com.me.mygdxgame.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@SuppressWarnings("unused")
public class AnimationPlayer {

	private float timePerFrame;

	private String fileName;

	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 4;

	private Texture walkSheet;


	private Animation walkAnimationRight, walkAnimationLeft, walkAnimationUp,
			walkAnimationDown;
	private TextureRegion[] walkFramesRight, walkFramesLeft, walkFramesUp,
			walkFramesDown;


	public AnimationPlayer(float timePerFrame, String fileName) {
		this.fileName = fileName;
		this.timePerFrame = timePerFrame;

		walkSheet = new Texture(fileName);

		// create animation for player
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);


		walkFramesRight = new TextureRegion[3];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 2) {
					walkFramesRight[index++] = tmp[i][j];
				}

			}
		}
		walkAnimationRight = new Animation(0.25f, walkFramesRight);

		walkFramesLeft = new TextureRegion[3];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 1) {
					walkFramesLeft[index++] = tmp[i][j];
				}

			}
		}
		walkAnimationLeft = new Animation(0.25f, walkFramesLeft);

		walkFramesUp = new TextureRegion[3];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 3) {
					walkFramesUp[index++] = tmp[i][j];
				}

			}
		}
		walkAnimationUp = new Animation(0.25f, walkFramesUp);

		walkFramesDown = new TextureRegion[3];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 0) {
					walkFramesDown[index++] = tmp[i][j];
				}

			}
		}
		walkAnimationDown = new Animation(this.timePerFrame, walkFramesDown);

	}

	public Animation getWalkAnimationRight() {
		return walkAnimationRight;
	}

	public void setWalkAnimationRight(Animation walkAnimationRight) {
		this.walkAnimationRight = walkAnimationRight;
	}

	public Animation getWalkAnimationLeft() {
		return walkAnimationLeft;
	}

	public void setWalkAnimationLeft(Animation walkAnimationLeft) {
		this.walkAnimationLeft = walkAnimationLeft;
	}

	public Animation getWalkAnimationUp() {
		return walkAnimationUp;
	}

	public void setWalkAnimationUp(Animation walkAnimationUp) {
		this.walkAnimationUp = walkAnimationUp;
	}

	public Animation getWalkAnimationDown() {
		return walkAnimationDown;
	}

	public void setWalkAnimationDown(Animation walkAnimationDown) {
		this.walkAnimationDown = walkAnimationDown;
	}	
}
