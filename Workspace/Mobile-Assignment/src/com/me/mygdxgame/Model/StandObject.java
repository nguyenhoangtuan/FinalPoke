package com.me.mygdxgame.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StandObject extends ObjectGame{

	protected String fileName;
	protected Texture textObject;
	protected boolean moveable;
	
	public StandObject(Vector2 vec2, float width, float height, String fileName, boolean moveable) {
		super(vec2, width, height);
		this.fileName = fileName;
		
		textObject = new Texture(fileName);
		this.bounds.x = vec2.x;
		this.bounds.y = vec2.y;
		bounds.height = height;
		bounds.width = width;
		this.moveable = moveable;
		
	}
	
	public void drawObject(SpriteBatch batch) {
		batch.begin();
		batch.draw(textObject, vec2.x, vec2.y, 0, 0, width, height, 1, 1, 0, 0, 0, textObject.getWidth(), textObject.getHeight(), false, false);
		batch.end();
		
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
