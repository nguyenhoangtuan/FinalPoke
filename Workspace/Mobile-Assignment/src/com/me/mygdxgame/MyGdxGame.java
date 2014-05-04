package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.me.mygdxgame.Menu.SplashScreen;

public class MyGdxGame extends Game {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "MyGame";
	public static final float DEFAULT_FRAME_SPEED = 0.25f;
	
	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
