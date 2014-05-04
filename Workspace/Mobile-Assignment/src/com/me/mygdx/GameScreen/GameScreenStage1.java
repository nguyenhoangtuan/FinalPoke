/*package com.me.mygdx.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Model.MapGame;
import com.me.mygdxgame.Model.MapGameUpdate;
import com.me.mygdxgame.Model.Player;

@SuppressWarnings("unused")
public class GameScreenStage1 implements Screen {

	private MyGdxGame game;
	private MapGame mapGame;
	private MapGameUpdate mapGameUpdate;

	private TextButtonStyle btnStyle;
	private TextButton btnUp;
	private TextButton btnDown;
	private TextButton btnLeft;
	private TextButton btnRight;
	private TextButton circularBtn;
	private TextButton menuBtn;

	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	private Player player;
	private float currentSpeed;

	public GameScreenStage1(MyGdxGame game, String playerName) {
		this.game = game;
		mapGame = new MapGame(playerName, game, 30, 30);
		mapGameUpdate = new MapGameUpdate(mapGame, game, this);
		player = mapGame.getPlayer();
		currentSpeed = mapGame.getPlayer().getSpeed();
		System.out.println("check construcot");
	}

	@Override
	public void render(float delta) {
		mapGame.update();
		mapGameUpdate.render();
		stage.act(delta);

		batch.begin();
		stage.draw();
		batch.end();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();
		System.out.println("check Reseize");
		displayButtonConsole();
		addButtonListener();

		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		
	}

	@Override
	public void hide() {
		System.out.println("check hide");
//		dispose();
	}

	@Override
	public void pause() {
		System.out.println("check pause");
	}

	@Override
	public void resume() {
		System.out.println("check Resume");
	}

	@Override
	public void dispose() {
		System.out.println("check dispose");
//		mapGame.dispose();
//		batch.dispose();
	}

	*//**
	 * Display Button Console
	 *//*
	public void displayButtonConsole() {
		//Add texture into skin
		addSkins();

		//Circular Button
		//Accept Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedCircularButton");
		btnStyle.down = skin.getDrawable("pressedCircularButton");
		circularBtn = new TextButton("A", btnStyle);
		circularBtn
				.setX(Gdx.graphics.getWidth() - (circularBtn.getWidth() * 2));
		circularBtn.setY(Gdx.graphics.getHeight()
				- (circularBtn.getHeight() * 9));
		stage.addActor(circularBtn);
		//Cancel Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedCircularButton");
		btnStyle.down = skin.getDrawable("pressedCircularButton");
		circularBtn = new TextButton("B", btnStyle);
		circularBtn
				.setX(Gdx.graphics.getWidth() - (circularBtn.getWidth() * 3));
		circularBtn.setY(Gdx.graphics.getHeight()
				- (circularBtn.getHeight() * 10));
		stage.addActor(circularBtn);

		//Console - Down Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedDownButton");
		btnStyle.down = skin.getDrawable("pressedDownButton");
		btnDown = new TextButton("", btnStyle);
		btnDown.setX(Gdx.graphics.getWidth() - (btnDown.getWidth() * 18));
		btnDown.setY(Gdx.graphics.getHeight() - (btnDown.getHeight() * 11));
		stage.addActor(btnDown);
		//Console - Up Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedUpButton");
		btnStyle.down = skin.getDrawable("pressedUpButton");
		btnUp = new TextButton("", btnStyle);
		btnUp.setX(Gdx.graphics.getWidth() - (btnUp.getWidth() * 18));
		btnUp.setY(Gdx.graphics.getHeight() - (btnUp.getHeight() * 9));
		stage.addActor(btnUp);
		//Console - Left Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedLeftButton");
		btnStyle.down = skin.getDrawable("pressedLeftButton");
		btnLeft = new TextButton("", btnStyle);
		btnLeft.setX(Gdx.graphics.getWidth() - (btnLeft.getWidth() * 19));
		btnLeft.setY(Gdx.graphics.getHeight() - (btnLeft.getHeight() * 10));
		stage.addActor(btnLeft);
		//Console - Right Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedRightButton");
		btnStyle.down = skin.getDrawable("pressedRightButton");
		btnRight = new TextButton("", btnStyle);
		btnRight.setX(Gdx.graphics.getWidth() - (btnRight.getWidth() * 17));
		btnRight.setY(Gdx.graphics.getHeight() - (btnRight.getHeight() * 10));
		stage.addActor(btnRight);
		//Console - Menu Button
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("consoleButtonFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedMenuButton");
		btnStyle.down = skin.getDrawable("pressedMenuButton");
		menuBtn = new TextButton("Menu", btnStyle);
		menuBtn.setX(Gdx.graphics.getWidth() - menuBtn.getWidth());
		menuBtn.setY(Gdx.graphics.getHeight() - menuBtn.getHeight());
		stage.addActor(menuBtn);
		System.out.println("check addbutton");
	}
	
	*//**
	 * Add listener for button
	 *//*
	private void addButtonListener () {
		buttonListenerManipulation(btnDown);
		buttonListenerManipulation(btnLeft);
		buttonListenerManipulation(btnRight);
		buttonListenerManipulation(btnUp);
		buttonListenerManipulation(circularBtn);
		buttonListenerManipulation(menuBtn);
	}

	*//**
	 * Manipulate Button Action
	 * @param button
	 *//*
	private void buttonListenerManipulation (final TextButton button) {
		if (!button.getText().toString().equals("Menu")
				&& !button.getText().toString().equals("A")
				&& !button.getText().toString().equals("B")) {

			button.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int buttons) {
					
					if (btnDown.isOver()) {
						player.getVelocitySpeed().y = -1;
					} else if (btnUp.isOver()) {
						player.getVelocitySpeed().y = 1;
					} else if (btnLeft.isOver()) {
						player.getVelocitySpeed().x = -1;
					} else if (btnRight.isOver()) {
						player.getVelocitySpeed().x = 1;
					}
					
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					player.getVelocitySpeed().y = 0;
					player.getVelocitySpeed().x = 0;
				}
			});
		} else {
			button.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int buttons) {
					if (button.getText().toString().equals("Menu")) {
						Gdx.app.log("Check", "Menu");
					} else if (button.getText().toString().equals("A")) {
						Gdx.app.log("Check", "A");
					} else if (button.getText().toString().equals("B")) {
						if (btnDown.isOver()) {
							player.setFrameSpeed(0.025f);
							player.setSpeed(100f);
						} else if (btnUp.isOver()) {
							player.setFrameSpeed(0.025f);
							player.setSpeed(100f);
						} else if (btnLeft.isOver()) {
							player.setFrameSpeed(0.025f);
							player.setSpeed(60f);
						} else if (btnRight.isOver()) {
							player.setFrameSpeed(0.025f);
							player.setSpeed(60f);
						}
					}
					return true;
				}
				
				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int buttons) {
					if (button.getText().toString().equals("B")) {
						if (btnDown.isOver()) {
							player.setFrameSpeed(MyGdxGame.DEFAULT_FRAME_SPEED);
							player.setSpeed(currentSpeed);
						} else if (btnUp.isOver()) {
							player.setFrameSpeed(MyGdxGame.DEFAULT_FRAME_SPEED);
							player.setSpeed(currentSpeed);
						} else if (btnLeft.isOver()) {
							player.setFrameSpeed(MyGdxGame.DEFAULT_FRAME_SPEED);
							player.setSpeed(currentSpeed);
						} else if (btnRight.isOver()) {
							player.setFrameSpeed(MyGdxGame.DEFAULT_FRAME_SPEED);
							player.setSpeed(currentSpeed);
						}
					}
				}
				
			});
		}
	}

	*//**
	 * Add textures and font as a skin
	 *//*
	private void addSkins() {
		skin = new Skin();
		skin.add(
				"unpressedCircularButton",
				new Texture(Gdx.files
						.internal("data/unpressedCircularButton.png")));
		skin.add(
				"pressedCircularButton",
				new Texture(Gdx.files
						.internal("data/pressedCircularButton.png")));
		skin.add("unpressedUpButton",
				new Texture(Gdx.files.internal("data/unpressedUpButton.png")));
		skin.add("pressedUpButton",
				new Texture(Gdx.files.internal("data/pressedUpButton.png")));
		skin.add("unpressedDownButton",
				new Texture(Gdx.files.internal("data/unpressedDownButton.png")));
		skin.add("pressedDownButton",
				new Texture(Gdx.files.internal("data/pressedDownButton.png")));
		skin.add("unpressedLeftButton",
				new Texture(Gdx.files.internal("data/unpressedLeftButton.png")));
		skin.add("pressedLeftButton",
				new Texture(Gdx.files.internal("data/pressedLeftButton.png")));
		skin.add(
				"unpressedRightButton",
				new Texture(Gdx.files.internal("data/unpressedRightButton.png")));
		skin.add("pressedRightButton",
				new Texture(Gdx.files.internal("data/pressedRightButton.png")));
		skin.add("unpressedMenuButton",
				new Texture(Gdx.files.internal("data/unpressedMenuButton.png")));
		skin.add("pressedMenuButton",
				new Texture(Gdx.files.internal("data/pressedMenuButton.png")));
		skin.add("consoleButtonFont",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
	}
}
*/