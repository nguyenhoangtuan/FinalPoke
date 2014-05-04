package com.me.mygdx.HighScores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
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
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Menu.MenuScreen;
import com.me.mygdxgame.Model.Model;

public class HighScoreInput implements Screen {

	private MyGdxGame game;
	private MusicManager musicManager;
	private SoundManager soundManager;

	private Texture backgroundTexture;
	private TextureRegion backgroundRegion;
	private Image backgroundImage;

	private TextFieldStyle textFieldStyle;
	private TextButtonStyle textButtonStyle;

	private TextButton submitBtn;
	private TextButton scoreBtn;
	private TextField textField;

	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;

	public HighScoreInput(MyGdxGame game, MusicManager musicManager,
			SoundManager soundManager) {
		this.game = game;
		this.musicManager = musicManager;
		this.soundManager = soundManager;
	}

	private TextFieldStyle setTextFieldStyle() {
		textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = skin.getFont("textFieldFont");
		textFieldStyle.fontColor = Color.WHITE;
		textFieldStyle.background = skin.getDrawable("textFieldBackground");
		return textFieldStyle;
	}

	private TextButtonStyle setTextButtonStyle() {
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont("commonFont");
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.up = skin.getDrawable("unpressedButton");
		textButtonStyle.down = skin.getDrawable("pressedButton");
		return textButtonStyle;
	}

	private void addSkin() {
		skin = new Skin();

		skin.add("commonFont",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
		skin.add("textFieldFont",
				new BitmapFont(Gdx.files.internal("data/textFieldFont.fnt"),
						false));
		skin.add("textFieldBackground",
				new Texture(Gdx.files.internal("data/textField.png")));
		skin.add("unpressedButton",
				new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("pressedButton",
				new Texture(Gdx.files.internal("data/pressedButton.png")));
	}

	private void utility() {
		addSkin();

		displayTextField();
		displaySubmitBtn();
		addBtnListener();
	}

	private void displayTextField() {
		textField = new TextField("", setTextFieldStyle());
		textField.setWidth(500);
		textField.setHeight(150);
		textField.setX(Gdx.graphics.getWidth() / 2 - textField.getWidth() / 2);
		textField
				.setY(Gdx.graphics.getHeight() / 2 - textField.getHeight() / 2);
		stage.addActor(textField);
	}

	private void displaySubmitBtn() {
		submitBtn = new TextButton("Submit", setTextButtonStyle());
		submitBtn.setWidth(250);
		submitBtn.setHeight(150);
		submitBtn.setX(Gdx.graphics.getWidth() / 2 - submitBtn.getWidth() / 2);
		submitBtn.setY(Gdx.graphics.getHeight() / 2 - submitBtn.getHeight() / 2
				+ textField.getHeight());
		stage.addActor(submitBtn);
		
		scoreBtn = new TextButton("High Scores", setTextButtonStyle());
		scoreBtn.setWidth(250);
		scoreBtn.setHeight(150);
		scoreBtn.setX(Gdx.graphics.getWidth() / 2 - scoreBtn.getWidth() / 2);
		scoreBtn.setY(Gdx.graphics.getHeight() / 2 - scoreBtn.getHeight() / 2
				+ (scoreBtn.getHeight() + submitBtn.getHeight()));
		stage.addActor(scoreBtn);
	}
	
	private void displayBackground() {
		backgroundTexture = new Texture(Gdx.files.internal("data/background"));
		backgroundRegion = new TextureRegion(backgroundTexture);
		backgroundImage = new Image(backgroundRegion);
		stage.addActor(backgroundImage);
	}

	private void addBtnListener() {
		submitBtn.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				HighScoreManipulation upload = new HighScoreManipulation(textField.getText().toString(), Model.getModel().getScoreTotal());
				upload.updateHighScores();
				game.setScreen(new MenuScreen(game, musicManager, soundManager));
				
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
		scoreBtn.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				HighScoreManipulation upload = new HighScoreManipulation(textField.getText().toString(), 0.0);
				upload.getHighScores();
				return super.touchDown(event, x, y, pointer, button);
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
			}
		});
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act();

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

		utility();

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
		game.dispose();
		batch.dispose();
		skin.dispose();
		stage.dispose();
	}

}
