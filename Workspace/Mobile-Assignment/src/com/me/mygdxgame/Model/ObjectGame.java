package com.me.mygdxgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ObjectGame {
	protected Vector2 vec2;
	protected Rectangle bounds;
	protected float width;
	protected float height;
	
	public ObjectGame(Vector2 vec2, float width, float height) {
		this.vec2 = vec2;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(vec2.x , vec2.y, width , height); 
	}

	public Vector2 getVec2() {
		return vec2;
	}
	
	public void setVec2(Vector2 vec2) {
		this.vec2 = vec2;
//		this.bounds.setX(vec2.x);
//		this.bounds.setY(vec2.y);
//		System.out.println(bounds.x);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	
}
