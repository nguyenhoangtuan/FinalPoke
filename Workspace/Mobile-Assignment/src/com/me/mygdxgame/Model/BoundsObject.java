package com.me.mygdxgame.Model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class BoundsObject extends ObjectGame{

	private boolean moveable;
	ShapeRenderer sr = new ShapeRenderer();
	
	public BoundsObject(Vector2 vec2, float width, float height, boolean move) {
		super(vec2, width, height);
		this.moveable = move;
		this.bounds.x = vec2.x;
		this.bounds.y = vec2.y;
		bounds.height = height;
		bounds.width = width;
		sr.setColor(Color.BLUE);
	}
	
	public void drawObjectTest(OrthographicCamera camera) {
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Rectangle);
		sr.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		sr.end();
	}
	
	public void canNotMove(Player mainPlayer) {
		if(!moveable) {
			if (mainPlayer.getBounds().overlaps(this.getBounds())) {
				mainPlayer.getVec2().x = mainPlayer.getPreviousLocation().x;
				mainPlayer.getVec2().y = mainPlayer.getPreviousLocation().y;
			}
		}
		
	}

}
