package com.me.mygdxgame.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends MoveObject {
	
	private Texture playerTexture;
	private String fileName;
	private String fileAnimationName;
	private Animation walkUp;
	private Animation walkDown;
	private Animation walkRight;
	private Animation walkLeft;
	private float stateTime;
	private float timeMove;
	
	private float frameSpeed;
	
	public static String playerName;
	
	private TextureRegion currentFrameRight,currentFrameLeft,currentFrameUp,currentFrameDown;


	public Player(String playerName, float speed, float frameSpeed, float rote, float widht, float hight, Vector2 position , String fileName ,String fileAnimationName) {
		super(speed, rote, widht, hight, position);
		Player.playerName = playerName;
		this.fileName = fileName;
		this.fileAnimationName = fileAnimationName;
		
		playerTexture = new Texture(this.fileName);
		AnimationPlayer ap= new AnimationPlayer(frameSpeed, this.fileAnimationName);
		walkUp = ap.getWalkAnimationUp();
		walkDown = ap.getWalkAnimationDown();
		walkLeft = ap.getWalkAnimationLeft();
		walkRight = ap.getWalkAnimationRight();
	}

	public void update() {
		previousLocation.x = vec2.x;
		previousLocation.y = vec2.y;
		
		vec2.add(getVelocitySpeed().tmp().mul(Gdx.graphics.getDeltaTime() * speed));
		
		bounds.x= vec2.x;
		bounds.y= vec2.y;
	}
	
	public void drawPlayer(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime(); 
		currentFrameRight = walkRight.getKeyFrame(stateTime, true); 
		currentFrameLeft = walkLeft.getKeyFrame(stateTime, true); 
		currentFrameUp = walkUp.getKeyFrame(stateTime, true); 
		currentFrameDown = walkDown.getKeyFrame(stateTime, true); 

		// draw method 
		batch.begin();
		
		if (this.getVelocitySpeed().x == 1) {
			batch.draw(currentFrameRight, this.getVec2().x, this.getVec2().y, 0,
					0, 15, 15, 1, 1, 1);
		} else if (this.getVelocitySpeed().x == -1) {
			batch.draw(currentFrameLeft, this.getVec2().x, this.getVec2().y, 0,
					0, 15, 15, 1, 1, 1);
		} else if (this.getVelocitySpeed().y == 1) {
			batch.draw(currentFrameUp, this.getVec2().x, this.getVec2().y, 0,
					0, 15, 15, 1, 1, 1);
		} else if (this.getVelocitySpeed().y == -1) {
			batch.draw(currentFrameDown, this.getVec2().x, this.getVec2().y, 0,
					0, 15, 15, 1, 1, 1);
		} else {
			batch.draw(playerTexture, this.getVec2().x, this.getVec2().y,
					0, 0, this.getWidth(), this.getHeight(), 1, 1,
					this.getRote(), 0, 0, playerTexture.getWidth(),
					playerTexture.getHeight(), false, false);
		}

		batch.end();
	}
	
	public void autoMoveNPC (int timeMovement) {
		
		timeMove += Gdx.graphics.getDeltaTime();
		if (timeMove < timeMovement *1 ) {
			this.getVelocitySpeed().x = 1;
			this.getVelocitySpeed().y = 0;
		} else if (timeMove < timeMovement * 2 ){
			this.getVelocitySpeed().x = 0;
			this.getVelocitySpeed().y = 0;
		} else if (timeMove < timeMovement * 3 ) {
			this.getVelocitySpeed().x = -1;
			this.getVelocitySpeed().y = 0;
		} else if (timeMove < timeMovement * 4) {
			this.getVelocitySpeed().y = 0;
			this.getVelocitySpeed().x = 0;
		} else if (timeMove < timeMovement * 5) {
			this.getVelocitySpeed().y = -1;
			this.getVelocitySpeed().x = 0;
		} else if (timeMove < timeMovement * 6) {
			this.getVelocitySpeed().y = 0;
			this.getVelocitySpeed().x = 0;
		} else if (timeMove < timeMovement * 7) {
			this.getVelocitySpeed().y = 1;
			this.getVelocitySpeed().x = 0;
		} else if (timeMove < timeMovement * 8) {
			this.getVelocitySpeed().y = 0;
			this.getVelocitySpeed().x = 0;
		} else {
			this.getVelocitySpeed().y = 0;
			this.getVelocitySpeed().x = 0;
			timeMove = 0;
		}
		
	}
	
	public void cannotMove(Player mainPlayer) {
		if (mainPlayer.getBounds().overlaps(this.getBounds())) {
			mainPlayer.getVec2().x = mainPlayer.getPreviousLocation().x;
			mainPlayer.getVec2().y = mainPlayer.getPreviousLocation().y;
			this.getVec2().x = this.getPreviousLocation().x;
			this.getVec2().y= this.getPreviousLocation().y;
		}
		
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		Player.playerName = playerName;
	}

	public float getFrameSpeed() {
		return frameSpeed;
	}

	public void setFrameSpeed(float frameSpeed) {
		this.frameSpeed = frameSpeed;
	}
}
