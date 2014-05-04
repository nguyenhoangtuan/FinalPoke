package com.me.mygdxgame.Menu;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Manager.MusicManager.MyMusic;

public class SplashScreen implements Screen{
	
	private MyGdxGame game;
	private Texture splashTexture; // an image
	private Sprite splashSprite; // more access to the image manipulation (fade in, fade out and so on)
	private SpriteBatch batch; // bound images - speed up game
	private MusicManager musicManager = new MusicManager();
	private SoundManager soundManager = new SoundManager();
	TweenManager manager;
	
	public SplashScreen(MyGdxGame myGdxGame) {
		this.game = myGdxGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); // Each time we go through, it would
											// set the screen black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // Doing a clearing

		manager.update(delta);
		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		splashTexture = new Texture(Gdx.files.internal("data/splashScreenBackground.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1,1,1,0);
		
		splashSprite.setX(Gdx.graphics.getWidth() - (splashSprite.getWidth()));
		splashSprite.setY(Gdx.graphics.getHeight() - (splashSprite.getHeight()));
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
		manager = new TweenManager();
		
		TweenCallback callBack = new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};
		
		Tween.to(splashSprite, SpriteTween.ALPHA, 0f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2.5f).setCallback(callBack).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
		
		
		musicManager.play(MyMusic.SPLASHSCREEN);
	}
	
	private void tweenCompleted() {
		game.setScreen(new MenuScreen(game, musicManager, soundManager));
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		splashTexture.dispose();
	}

}