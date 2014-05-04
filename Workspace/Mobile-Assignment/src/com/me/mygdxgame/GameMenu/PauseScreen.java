package com.me.mygdxgame.GameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.me.mygdxgame.MyGdxGame;

public class PauseScreen implements Screen{
	Screen s;
	Texture pause;
	SpriteBatch batch;
	
	public PauseScreen(Screen s, MyGdxGame g) {
		this.s = s;
		pause = new Texture("data/Pause/Pause.png");
		batch = new SpriteBatch();
		 Gdx.input.setInputProcessor(new GestureDetector(new MyGesture(g, s)));
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(pause, Gdx.graphics.getWidth() /2 - pause.getWidth() /2  ,  Gdx.graphics.getHeight() /2 - pause.getHeight() /2 ,
				0, 0, pause.getWidth(), pause.getHeight(), 1, 1,
				0, 0, 0, pause.getWidth() , pause.getHeight() , false, false);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
