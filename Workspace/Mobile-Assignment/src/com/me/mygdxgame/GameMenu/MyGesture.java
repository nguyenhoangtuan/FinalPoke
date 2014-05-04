package com.me.mygdxgame.GameMenu;



import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.MyGdxGame;

public class MyGesture implements GestureListener {
	
	Screen gas;
	MyGdxGame game;
	
	public MyGesture(MyGdxGame game, Screen gas) {
		this.game = game;
		this.gas = gas;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
//		player.getVec2().y += 10;
		System.out.print("check");
		game.setScreen(gas);
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
	
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
