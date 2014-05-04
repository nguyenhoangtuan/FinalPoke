package com.me.mygdx.HighScores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Model.Model;
import com.me.mygdxgame.Model.Player;

@SuppressWarnings("unused")
public class HighScoresScreen implements Screen {
	//Main Properties
	private MusicManager musicManager;
	private SoundManager soundManager;
	private MyGdxGame game;
	//Properties for display background
	private Texture backgroundTexture;
	private TextureRegion backgroundRegion;
	private Image backgroundImage;
	//Styles of label and text button
	private LabelStyle labelStyle;
	private TextButtonStyle btnStyle;
	//Screen's elements
	private Label label;
	private TextButton textBtn;
	//Container
	private Skin skin;
	private SpriteBatch batch;
	private Stage stage;
	private Model model = Model.getModel();
	
	public HighScoresScreen(MyGdxGame game, MusicManager musicManager, SoundManager soundManager) {
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
			stage = new Stage(width, height, true);
		}
		
		stage.clear();
		
		displayBackground();
		displayLabel();
		displayTestButton();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		
		HighScoreManipulation highScore = new HighScoreManipulation(Player.playerName, model.getScoreTotal());
		highScore.updateHighScores();
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
		stage.dispose();
		batch.dispose();
		skin.dispose();
	}
	
	private void addSkin() {
		skin = new Skin();
		skin.add("labelFont", new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
		skin.add("unpressedButton", new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("pressedButton", new Texture(Gdx.files.internal("data/pressedButton.png")));
	}
	
	private LabelStyle defineLabelStyle() {
		labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("labelFont");
		labelStyle.fontColor = Color.RED;
		return labelStyle;
	}
	
	private TextButtonStyle defineBtnStyle() {
		btnStyle = new TextButtonStyle();
		btnStyle.font = skin.getFont("labelFont");
		btnStyle.fontColor = Color.WHITE;
		btnStyle.up = skin.getDrawable("unpressedButton");
		btnStyle.down = skin.getDrawable("pressedButton");
		return btnStyle;
	}
	
	private void displayBackground() {
		addSkin();
		
		backgroundTexture = new Texture(Gdx.files.internal("data/splashScreenBackground.png"));
		backgroundRegion = new TextureRegion(backgroundTexture);
		backgroundImage = new Image(backgroundRegion);
		stage.addActor(backgroundImage);
	}
	
	private void displayLabel() {
		label = new Label("Staus", defineLabelStyle());
		label.setX(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2);
		label.setY(Gdx.graphics.getHeight() / 2 - label.getHeight() / 2);
		stage.addActor(label);
	}
	
	private void displayTestButton() {
		textBtn = new TextButton("Test", defineBtnStyle());
		textBtn.setX(Gdx.graphics.getWidth() / 2 - textBtn.getWidth() / 2);
		textBtn.setY(Gdx.graphics.getHeight() / 2 - textBtn.getHeight() / 2 - label.getHeight());
		stage.addActor(textBtn);
	}
}
