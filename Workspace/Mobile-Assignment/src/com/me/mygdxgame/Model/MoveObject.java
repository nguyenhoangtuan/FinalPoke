package com.me.mygdxgame.Model;

import com.badlogic.gdx.math.Vector2;

public class MoveObject extends ObjectGame{
	protected Vector2 velocitySpeed;
	protected float speed;
	protected float rote;
	protected Vector2 previousLocation = new Vector2();
	
	public MoveObject(float speed, float rote, float widht, float hight, Vector2 position) {
		super(position, widht, hight);
		this.rote = rote;
		this.speed = speed;
		velocitySpeed = new Vector2(0 ,0);
	}

	public Vector2 getVelocitySpeed() {
		return velocitySpeed;
	}

	public void setVelocitySpeed(Vector2 velocitySpeed) {
		this.velocitySpeed = velocitySpeed;
	}

	public float getSpeed() {
		return speed;
	}

	
	

	public Vector2 getPreviousLocation() {
		return previousLocation;
	}

	public void setPreviousLocation(Vector2 previousLocation) {
		this.previousLocation = previousLocation;
	}

	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRote() {
		return rote;
	}

	public void setRote(float rote) {
		this.rote = rote;
	}
	
	public void update() {
		
	}
	
}
