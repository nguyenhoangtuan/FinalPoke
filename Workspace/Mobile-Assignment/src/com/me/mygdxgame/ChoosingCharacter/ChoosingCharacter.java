package com.me.mygdxgame.ChoosingCharacter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.GameScreen.GameScreenStage1;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Manager.SoundManager.MySound;

public class ChoosingCharacter implements Screen {
	private MyGdxGame game;

	// Character
	private String playerName;
	private TextureRegion characterRegion;
	private Texture mainCharacter;
	private Image characterImage;
	// Background
	private TextureRegion backgroundRegion;
	private Texture background;
	private Image backgroundImage;
	// Text Field
	private TextFieldStyle textFieldStyle;
	private TextField textField;

	private TextButton nextBtn;
	private TextButtonStyle btnStyle;
	private Skin skin;

	private Stage stage;
	private SpriteBatch batch;
	private MusicManager musicManager;
	private SoundManager soundManger;

	public ChoosingCharacter(MyGdxGame game, MusicManager musicManager, SoundManager soundManager) {
		this.game = game;
		this.musicManager = musicManager;
		this.soundManger = soundManager;

		addSkin();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act();

		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}

		stage.clear();

		displayBackground();
		displayCharacter();
		displayTextField();
		addButtonOnScreen();

		setElementListeners();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
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
		stage.dispose();
		batch.dispose();
		skin.dispose();
		game.dispose();
	}

	/**
	 * Diplay Character On The Screen
	 */
	private void displayCharacter() {
		// FrontSide
		mainCharacter = new Texture(
				Gdx.files.internal("data/mainCharacterFrontSide.png"));
		mainCharacter.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		characterRegion = new TextureRegion(mainCharacter);
		characterImage = new Image(characterRegion);
		characterImage.setSize(150, 150);
		characterImage.setX(Gdx.graphics.getWidth() / 2
				- characterImage.getWidth() / 2);
		characterImage.setY(Gdx.graphics.getHeight() / 2
				- characterImage.getHeight() / 2);
		stage.addActor(characterImage);

		// Set for later use
		float frontX = Gdx.graphics.getWidth() / 2 - characterImage.getWidth()
				/ 2;

		// LeftSide
		mainCharacter = new Texture(
				Gdx.files.internal("data/mainCharacterLeftSide.png"));
		mainCharacter.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		characterRegion = new TextureRegion(mainCharacter);
		characterImage = new Image(characterRegion);
		characterImage.setSize(150, 150);
		characterImage.setX(frontX - characterImage.getWidth());
		characterImage.setY(Gdx.graphics.getHeight() / 2
				- characterImage.getHeight() / 2);
		stage.addActor(characterImage);

		// RightSide
		mainCharacter = new Texture(
				Gdx.files.internal("data/mainCharacterRightSide.png"));
		mainCharacter.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		characterRegion = new TextureRegion(mainCharacter);
		characterImage = new Image(characterRegion);
		characterImage.setSize(150, 150);
		characterImage.setX(frontX + characterImage.getWidth());
		characterImage.setY(Gdx.graphics.getHeight() / 2
				- characterImage.getHeight() / 2);
		stage.addActor(characterImage);
	}

	/**
	 * Set background for the choosing-character screen
	 */
	private void displayBackground() {
		background = new Texture(
				Gdx.files.internal("data/choosingScreenBackground.jpg"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backgroundRegion = new TextureRegion(background);
		backgroundImage = new Image(backgroundRegion);

		stage.addActor(backgroundImage);
	}

	// Display text field on screen
	private void displayTextField() {
		// TextFieldStyle
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = skin.getFont("fontTextField");
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.background = skin.getDrawable("textField");
		// TextField
		textField = new TextField("", textFieldStyle);
		textField.setX(Gdx.graphics.getWidth() / 2 - textField.getWidth() / 2);
		textField.setY(Gdx.graphics.getHeight() / 2 - textField.getHeight() / 2
				+ textField.getHeight() * 4);
		textField.setMaxLength(10);
		textField.setRightAligned(true);
		// Add background for the textField
		Texture textureBackground = new Texture(
				Gdx.files.internal("data/outsideTextField.png"));
		TextureRegion textFieldRegion = new TextureRegion(textureBackground);
		Image textFieldImage = new Image(textFieldRegion);
		textFieldImage.setWidth(textField.getWidth() + 4);
		textFieldImage.setHeight(textField.getHeight() + 10);
		textFieldImage.setPosition(textField.getX(), textField.getY());
		stage.addActor(textFieldImage);
		stage.addActor(textField);
	}


	private void setElementListeners() {
		textField.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				textField.selectAll();
				return true;
			}
		});

		nextBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				soundManger.play(MySound.BUTTONCLICK);
				playerName = textField.getText().toString();
				if (playerName.equals("")) {
					playerName = "George";
				}
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
					game.setScreen(new GameScreenStage1(game, musicManager, soundManger));
			}
		});
	}

	private void addSkin() {
		skin = new Skin();
		skin.add("textField",
				new Texture(Gdx.files.internal("data/textField.png")));
		skin.add("textFieldCover",
				new Texture(Gdx.files.internal("data/outsideTextField.png")));
		skin.add("fontTextField",
				new BitmapFont(Gdx.files.internal("data/textFieldFont.fnt"),
						false));
		skin.add("fontTextButton",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
		skin.add("unpressedButton",
				new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("pressedButton",
				new Texture(Gdx.files.internal("data/pressedButton.png")));
	}

	// Display button on screen
	private void addButtonOnScreen() {
		// TextButtonStyle
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("fontTextButton");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedButton");
		btnStyle.down = skin.getDrawable("pressedButton");
		// TextButton
		nextBtn = new TextButton("Next", btnStyle);
		nextBtn.setX(Gdx.graphics.getWidth() - nextBtn.getWidth());
		nextBtn.setY(Gdx.graphics.getHeight() - nextBtn.getHeight() * 4);

		stage.addActor(nextBtn);
	}
}