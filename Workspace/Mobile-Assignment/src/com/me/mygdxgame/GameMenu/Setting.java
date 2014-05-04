package com.me.mygdxgame.GameMenu;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.MusicManager.MyMusic;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Manager.SoundManager.MySound;
import com.me.mygdxgame.Menu.MenuScreen;

public class Setting implements Screen {

	private MyGdxGame game;

	private Stage stage;
	private SpriteBatch batch;

	private Texture titleTexture;
	private TextureRegion titleRegion;
	private Image titleImage;

	private MusicManager musicManager;
	private SoundManager soundManager;
	private CheckBox musicCheckBox;
	private CheckBox soundCheckBox;
	private Slider volumeSlider;
	private Skin skin;
	
	private LabelStyle labelStyle;
	private TextButtonStyle textButtonStyle;
	private Label label;
	private TextButton textBtn;
	private Screen screen;
	
	public Setting(MyGdxGame game, MusicManager musicManger, SoundManager soundManger, Screen screen) {
		this.game = game;
		this.musicManager = musicManger;
		this.soundManager = soundManger;
		this.screen = screen;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
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

		utility();

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
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

	/**
	 * Utility method
	 * Used to call the other method
	 */
	private void utility() {
		addSkins();

		displayTitle();
		displaySoundCheckBox();
		displayMusicCheckBox();
		displayVolumeSlider();
		displayBackBtn();
		
	}

	/**
	 * Display title image on screen
	 */
	private void displayTitle() {
		titleTexture = new Texture(
				Gdx.files.internal("data/OptionMenu/optionTitle.png"));
		titleRegion = new TextureRegion(titleTexture);
		titleImage = new Image(titleRegion);
		titleImage
				.setX(Gdx.graphics.getWidth() / 2 - titleImage.getWidth() / 2);
		titleImage.setY(Gdx.graphics.getHeight() - titleImage.getHeight());
		stage.addActor(titleImage);
	}

	/**
	 * Display sound checkbox
	 * to either enable or disable music
	 */
	private void displaySoundCheckBox() {
		soundCheckBox = new CheckBox("Sound", defineCheckBox());
		soundCheckBox.setChecked(isEnabled("sound"));
		soundCheckBox.setX(Gdx.graphics.getWidth() / 2 - soundCheckBox.getWidth() / 2 - titleImage.getWidth() / 2);
		soundCheckBox.setY(Gdx.graphics.getHeight() - soundCheckBox.getHeight() - titleImage.getHeight());
		soundCheckBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = soundCheckBox.isChecked();
				setEnabled(enabled, "sound");
				soundManager.setEnabled(enabled);
				soundManager.play(MySound.BUTTONCLICK);
			}
		});
		stage.addActor(soundCheckBox);
	}

	/**
	 * Display Music checkbox
	 * to either enable or disable music
	 */
	private void displayMusicCheckBox() {
		musicCheckBox = new CheckBox("Music", defineCheckBox());
		musicCheckBox.setChecked(isEnabled("music"));
		musicCheckBox.setX(Gdx.graphics.getWidth() / 2 - musicCheckBox.getWidth() / 2 - titleImage.getWidth() / 2);
		musicCheckBox.setY(Gdx.graphics.getHeight() - musicCheckBox.getHeight() - (titleImage.getHeight() + soundCheckBox.getHeight()));
		musicCheckBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = musicCheckBox.isChecked();
				setEnabled(enabled, "music");
				musicManager.setEnabled(enabled);
				soundManager.play(MySound.BUTTONCLICK);
				
				if (enabled) musicManager.play(MyMusic.MENU);
			}
		});
		stage.addActor(musicCheckBox);
	}
	
	/**
	 * Display Volume slider on screen
	 */
	private void displayVolumeSlider() {
		SliderStyle sliderStyle = new SliderStyle();
		sliderStyle.background = skin.getDrawable("sliderBackground");
		sliderStyle.knob = skin.getDrawable("knob");
		volumeSlider = new Slider(0f, 1f, 0.1f, false, sliderStyle);
		volumeSlider.setWidth(500);
		volumeSlider.setValue(getVolume());
		volumeSlider.setX(Gdx.graphics.getWidth() / 2 - volumeSlider.getWidth() / 2 - titleImage.getHeight());
		volumeSlider.setY(Gdx.graphics.getHeight() - volumeSlider.getHeight() - (titleImage.getHeight() + soundCheckBox.getHeight() + musicCheckBox.getHeight()));
		volumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				float value = ((Slider) actor).getValue();
				setVolume(value);
				musicManager.setVolume(value);
				soundManager.setVolume(value);
				updateVolumeLabel();
			}
		});
		stage.addActor(volumeSlider);
		
		labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("titleFont");
		labelStyle.fontColor = Color.BLACK;
		label = new Label("",labelStyle);
		label.setX(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2 - volumeSlider.getWidth() / 2);
		label.setY(Gdx.graphics.getHeight() - volumeSlider.getHeight() - (titleImage.getHeight() + soundCheckBox.getHeight() + musicCheckBox.getHeight()));
		stage.addActor(label);
		updateVolumeLabel();
	}
	
	private void displayBackBtn() {
		textBtn = new TextButton("Back", defineTextButton());
		textBtn.setX(Gdx.graphics.getWidth() / 2 - textBtn.getWidth() / 2);
		textBtn.setY(Gdx.graphics.getHeight() - textBtn.getHeight() - (titleImage.getHeight() + soundCheckBox.getHeight() + musicCheckBox.getHeight() + volumeSlider.getHeight()));
		textBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				if (screen instanceof MenuScreen) {
					game.setScreen(new MenuScreen(game, musicManager, soundManager));
				} else {
					game.setScreen(screen);
				}
			}
		});
		stage.addActor(textBtn);
	}

	/**
	 * Define/Initialize style of a text button
	 * @return
	 */
	private TextButtonStyle defineTextButton() {
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont("titleFont");
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.up = skin.getDrawable("unpressedMenuButton");
		textButtonStyle.down = skin.getDrawable("pressedMenuButton");
		
		return textButtonStyle;
	}
	
	/**
	 * Define/Initialize style of a checkbox
	 * @return
	 */
	private CheckBoxStyle defineCheckBox() {
		CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = skin.getFont("elementFont");
		checkBoxStyle.fontColor = Color.RED;
		checkBoxStyle.checkboxOn = skin.getDrawable("checkedCheckBox");
		checkBoxStyle.checkboxOff = skin.getDrawable("uncheckedCheckBox");

		return checkBoxStyle;
	}

	/**
	 * Check whether music and sounds is enabled or disabled
	 * @param type
	 * @return
	 */
	private boolean isEnabled(String type) {
		String gameName = "Pokemon";
		String music = "music.enabled";
		String sound = "sound.enabled";

		if (music.contains(type)) {
			return Gdx.app.getPreferences(gameName).getBoolean(music, true);
		} else {
			return Gdx.app.getPreferences(gameName).getBoolean(sound, true);
		}
	}

	/**
	 * Get Current Volume
	 * @return
	 */
	private float getVolume() {
		return Gdx.app.getPreferences("Pokemon").getFloat("volume", 0.5f);
	}
	
	/**
	 * Set music and sound enabled/disabled
	 * If (music is enable) play music or sounds
	 * Else stop sounds and musics
	 * @param enabled
	 * @param type
	 */
	private void setEnabled(boolean enabled, String type) {
		String gameName = "Pokemon";
		String music = "music.enabled";
		String sound = "sound.enabled";

		if (music.contains(type)) {
			Gdx.app.getPreferences(gameName).putBoolean(music, enabled);
			Gdx.app.getPreferences(gameName).flush();
		} else {
			Gdx.app.getPreferences(gameName).putBoolean(sound, enabled);
			Gdx.app.getPreferences(gameName).flush();
		}
	}
	
	  
	/**
	 * Set volume when silder is slided
	 * @param volume
	 */
	private void setVolume(float volume) {
		Gdx.app.getPreferences( "Pokemon" ).putFloat("volume", volume);
		Gdx.app.getPreferences( "Pokemon" ).flush();
	}

	/**
	 * Add textures and font as a skin
	 */
	private void addSkins() {
		skin = new Skin();

		skin.add(
				"checkedCheckBox",
				new Texture(Gdx.files
						.internal("data/OptionMenu/checkbox_checked.png")));
		skin.add(
				"uncheckedCheckBox",
				new Texture(Gdx.files
						.internal("data/OptionMenu/checkbox_unchecked.png")));
		skin.add("unpressedMenuButton", new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("pressedMenuButton", new Texture(Gdx.files.internal("data/pressedButton.png")));
		skin.add(
				"titleImage",
				new Texture(Gdx.files
						.internal("data/OptionMenu/optionTitle.png")));
		skin.add("sliderBackground", new Texture(Gdx.files.internal("data/OptionMenu/sliderBackground.png")));
		skin.add("knob", new Texture(Gdx.files.internal("data/OptionMenu/knob.png")));
		skin.add("titleFont",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
		skin.add("elementFont",
				new BitmapFont(Gdx.files.internal("data/textFieldFont.fnt"),
						false));
	}
	

    /**
     * Updates the volume label next to the slider.
     */
    private void updateVolumeLabel()
    {
        float volume = ( getVolume() * 100 );
        label.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }

}
