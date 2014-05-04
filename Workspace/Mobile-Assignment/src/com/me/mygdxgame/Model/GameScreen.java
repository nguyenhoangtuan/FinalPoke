//package com.me.mygdxgame.Model;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
//import com.me.mygdxgame.MyGdxGame;
//
//@SuppressWarnings("unused")
//public class GameScreen implements Screen {
//
//	private MyGdxGame game;
//	private MapGame mapGame;
//	private MapGameUpdate mapGameUpdate;
//
//	private TextButton btnUp;
//	private TextButton btnDown;
//	private TextButton btnLeft;
//	private TextButton btnRight;
//
//	private Skin skin;
//	private Stage stage;
//	private SpriteBatch batch;
//	private Player player;
//
//	public GameScreen(MyGdxGame game, String playerName) {
//		this.game = game;
//		mapGame = new MapGame(playerName, game, 30, 30);
//		mapGameUpdate = new MapGameUpdate(mapGame, game, this);
//		player = mapGame.getPlayer();
//		
//		
//	}
//
//	@Override
//	public void render(float delta) {
//		mapGame.update();
//		mapGameUpdate.render();
//		stage.act(delta);
//
//		batch.begin();
//		stage.draw();
//		batch.end();
//	}
//
//	@Override
//	public void resize(int width, int height) {
//		if (stage == null) {
//			stage = new Stage(width, height, true);
//		}
//		stage.clear();
//
//		setButtonConsole();
//
//		Gdx.input.setInputProcessor(stage);
//	}
//
//	@Override
//	public void show() {
//		batch = new SpriteBatch();
//	}
//
//	@Override
//	public void hide() {
//		dispose();
//	}
//
//	@Override
//	public void pause() {
//	}
//
//	@Override
//	public void resume() {
//
//	}
//
//	@Override
//	public void dispose() {
//		mapGame.dispose();
//	//	batch.dispose();
//		game.dispose();
//		skin.dispose();
//		stage.dispose();
//	}
//
//	public void setButtonConsole() {
//
//		TextButtonStyle style = new TextButtonStyle();
//		style.font = new BitmapFont();
//		style.fontColor = Color.WHITE;
//
//		btnUp = new TextButton("Up", style);
//		btnUp.setWidth(100);
//		btnUp.setHeight(100);
//		btnUp.setX(Gdx.graphics.getWidth() / 2 - btnUp.getWidth() / 2 + 300);
//		btnUp.setY(Gdx.graphics.getHeight() / 2 - btnUp.getHeight() / 2 - 50);
//
//		btnUp.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().y = 1;
//
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().y = 0;
//			}
//		});
//
//		stage.addActor(btnUp);
//
//		btnDown = new TextButton("Down", style);
//		btnDown.setWidth(100);
//		btnDown.setHeight(100);
//		btnDown.setX(Gdx.graphics.getWidth() / 2 - btnDown.getWidth() / 2 + 300);
//		btnDown.setY(Gdx.graphics.getHeight() / 2 - btnDown.getHeight() / 2
//				- 200);
//
//		btnDown.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().y = -1;
//
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().y = 0;
//			}
//		});
//
//		stage.addActor(btnDown);
//
//		btnLeft = new TextButton("Left", style);
//		btnLeft.setWidth(100);
//		btnLeft.setHeight(100);
//		btnLeft.setX(Gdx.graphics.getWidth() / 2 - btnLeft.getWidth() / 2 + 200);
//		btnLeft.setY(Gdx.graphics.getHeight() / 2 - btnLeft.getHeight() / 2
//				- 125);
//
//		btnLeft.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().x = -1;
//
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().x = 0;
//			}
//		});
//
//		stage.addActor(btnLeft);
//
//		btnRight = new TextButton("Right", style);
//		btnRight.setWidth(100);
//		btnRight.setHeight(100);
//		btnRight.setX(Gdx.graphics.getWidth() / 2 - btnRight.getWidth() / 2
//				+ 400);
//		btnRight.setY(Gdx.graphics.getHeight() / 2 - btnRight.getHeight() / 2
//				- 125);
//
//		btnRight.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().x = 1;
//
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y,
//					int pointer, int button) {
//				player.getVelocitySpeed().x = 0;
//			}
//		});
//
//		stage.addActor(btnRight);
//	}
//	
//	private void addSkins() {
//		skin = new Skin();
//	}
//}
