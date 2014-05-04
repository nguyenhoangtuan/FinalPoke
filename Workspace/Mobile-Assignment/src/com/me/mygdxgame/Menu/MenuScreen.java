package com.me.mygdxgame.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.me.mygdx.HighScores.HighScoreInput;
import com.me.mygdx.HighScores.HighScoresScreen;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.ChoosingCharacter.ChoosingCharacter;
import com.me.mygdxgame.Data.DataBase;
import com.me.mygdxgame.GameMenu.Setting;
import com.me.mygdxgame.GameScreen.GameScreenStage1;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.MusicManager.MyMusic;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Manager.SoundManager.MySound;
import com.me.mygdxgame.Model.Model;

public class MenuScreen implements Screen {

	private MyGdxGame game;
	private Texture menuTexture;
	private SpriteBatch batch;

	private TextButton helpButton;
	private TextButton loadButton;
	private TextButton playButton;
	private TextButtonStyle style;

	private Stage stage;
	private NinePatchDrawable dialogButtonUp;
	private NinePatchDrawable dialogButtonDown;
	private Model model = Model.getModel();
	
	private MusicManager musicManager;
	private SoundManager soundManager;
	
	public MenuScreen(MyGdxGame game, MusicManager musicManager, SoundManager soundManager) {
		this.game = game;
		this.musicManager = musicManager;
		this.soundManager = soundManager;
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
			// True = scale to the entire screen
			stage = new Stage(width, height, true);
		}

		stage.clear();

		setBackground();
		addMenuButton();

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		musicManager.play(MyMusic.MENU);
	}

	private void setBackground() {
		menuTexture = new Texture(Gdx.files.internal("data/menuBackground.png"));
		menuTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(menuTexture);
		Image actor = new Image(region);
		actor.setWidth(Gdx.graphics.getWidth());
		actor.setHeight(Gdx.graphics.getHeight());
		stage.addActor(actor);
	}

	private void addMenuButton() {
		style = new TextButtonStyle();

		this.dialogButtonDown = new NinePatchDrawable(new NinePatch(
				new Texture(Gdx.files.internal("data/pressedButton.png"))));
		this.dialogButtonUp = new NinePatchDrawable(new NinePatch(new Texture(
				Gdx.files.internal("data/unpressedButton.png"))));

		style.font = new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false);
		style.fontColor = Color.WHITE;
		style.up = dialogButtonUp;
		style.down = dialogButtonDown;

		playButton = new TextButton("New Game", style);
		playButton.setX(Gdx.graphics.getWidth() - playButton.getWidth());
		playButton.setY(Gdx.graphics.getHeight() - (playButton.getHeight() * 3));
		setListener(playButton);
		
		loadButton = new TextButton("Load", style);
		loadButton.setX(Gdx.graphics.getWidth() - loadButton.getWidth());
		loadButton.setY(Gdx.graphics.getHeight() - (loadButton.getHeight() * 4));
		setListener(loadButton);
		
		helpButton = new TextButton("Setting", style);
		helpButton.setX(Gdx.graphics.getWidth() - helpButton.getWidth());
		helpButton.setY(Gdx.graphics.getHeight() - (helpButton.getHeight() * 5));
		setListener(helpButton);
		
		stage.addActor(playButton);
		stage.addActor(helpButton);
		stage.addActor(loadButton);
	}
	
	private void setListener (final TextButton button) {
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				if (button.getText().toString().equals("New Game")) {
					soundManager.play(MySound.BUTTONCLICK);
					game.setScreen(new ChoosingCharacter(game, musicManager, soundManager));
					model.removeAllData();
					model.getPlayer().setVec2(new Vector2(225 ,930));
					model.startNewGame();
					model.setNumberStage(1);
					
				} else if (button.getText().toString().equals("Setting")) {
					soundManager.play(MySound.BUTTONCLICK);
					game.setScreen(new Setting(game, musicManager, soundManager, MenuScreen.this));
				} else if (button.getText().toString().equals("Load")){
					DataBase d = new DataBase();
					System.out.println("check Load");
					d.loadAlldata();
					game.setScreen(new GameScreenStage1(game, musicManager, soundManager));
					
					
//					soundManager.play(MySound.BUTTONCLICK);
					game.setScreen(new HighScoreInput(game, musicManager, soundManager));
				}
			}
		});
	}

	@Override
	public void hide() {
		dispose();
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
		menuTexture.dispose();
		stage.dispose();
	}

}
