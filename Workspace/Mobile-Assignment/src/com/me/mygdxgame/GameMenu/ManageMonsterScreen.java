package com.me.mygdxgame.GameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Data.DataBase;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;
import com.me.mygdxgame.Model.Model;
import com.me.mygdxgame.Model.Monster;

@SuppressWarnings("unused")
public class ManageMonsterScreen implements Screen {

	Model model = Model.getModel();
	private MyGdxGame game;
	private Screen s, screen;
	private Stage stage;

	private SpriteBatch batch;
	private float stateTime = 0;
	private OrthographicCamera camera;
	
	private MusicManager musicManager;
	private SoundManager soundManager;
	
	private TextButtonStyle style;
	private TextButton btnUp;
	private TextButton btnDown;
	private TextButton btnLeft;
	private TextButton btnRight;
	private TextButton done;
	private TextButton change;
	private TextButton remove;
	private TextButton setting;
	private TextButton view;
	private TextButton save;
	private TextButton pause;
	
	private Texture arrow , bg1;
	private Skin skin;
	BitmapFont bmf = new BitmapFont();
	Monster current;

	public ManageMonsterScreen(MyGdxGame game, Screen s, MusicManager musicManager, SoundManager soundManager) {
		this.game = game;
		this.s = s;
		this.musicManager = musicManager;
		this.soundManager = soundManager;
		screen = this;
		
		float width = Gdx.graphics.getWidth() / 2;
		float height = Gdx.graphics.getHeight() / 2;

		
		arrow = new Texture("data/arrow.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();
		bmf.setColor(Color.BLACK);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		current = model.getBattlePlayerMonster().get(0);
		
		bmf.scale(0.1f);
		bg1 = new Texture("data/bg1.jpg");
		init();
		
	}
	
	public void init() {
		int i = 0;
		for (Monster mos : model.getPlayerMonsters()) {
			mos.setLocation(new Vector2(100 + i, 200));
			i += 50;
		}

		i = 0;
		for (Monster mos : model.getBattlePlayerMonster()) {
			mos.setLocation(new Vector2(200 + i, 120));
			i += 50;
		}

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(bg1, 0, 0);
		batch.end();
		
		
		stage.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();

		for (Monster mos : model.getPlayerMonsters()) {
			mos.drawMonsterManage(batch, mos.getLocation().x,
					mos.getLocation().y, stateTime);
		}

		for (Monster mos : model.getBattlePlayerMonster()) {
			mos.drawMonsterManage(batch, mos.getLocation().x,
					mos.getLocation().y, stateTime);
		}
		
		batch.begin();
		batch.draw(arrow, current.getLocation().x + 5 , current.getLocation().y + 35,
				0, 0, 20, 20, 1, 1,
				0, 0, 0, arrow.getWidth() , arrow.getHeight() , false, false);
		batch.end();
		
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

		createButton();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
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

	public void createButton() {
		addSkin();

		style = new TextButtonStyle();
		style.font = skin.getFont("consoleButtonFont");
		style.fontColor = Color.WHITE;
		style.up = skin.getDrawable("unpressedButton");
		style.down = skin.getDrawable("pressedButton");

		
		pause = new TextButton("Pause", style);
		pause.setWidth(100);
		pause.setHeight(100);
		pause.setX(Gdx.graphics.getWidth() / 2 - pause.getWidth() / 2  -500);
		pause.setY(Gdx.graphics.getHeight() / 2 - pause.getHeight() / 2 - 0);

		pause.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new PauseScreen(s ,game));
//				game.pause();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		
		
		stage.addActor(pause);
		
		
		save = new TextButton("Save", style);
		save.setWidth(100);
		save.setHeight(100);
		save.setX(Gdx.graphics.getWidth() / 2 - save.getWidth() / 2  -500);
		save.setY(Gdx.graphics.getHeight() / 2 - save.getHeight() / 2 - 100);
		save.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				DataBase n = new DataBase();
				n.saveAlldata();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		stage.addActor(save);
		
		setting = new TextButton("Setting", style);
		setting.setWidth(100);
		setting.setHeight(100);
		setting.setX(Gdx.graphics.getWidth() / 2 - setting.getWidth() / 2  -500);
		setting.setY(Gdx.graphics.getHeight() / 2 - setting.getHeight() / 2 + save.getHeight());
		setting.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				game.setScreen(new Setting(game, musicManager, soundManager, ManageMonsterScreen.this));
				return super.touchDown(event, x, y, pointer, button);
			}
			
			
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				
			}
		});
		stage.addActor(setting);
		
		view = new TextButton("View", style);
		view.setWidth(100);
		view.setHeight(100);
		view.setX(Gdx.graphics.getWidth() / 2 - view.getWidth() / 2  -500);
		view.setY(Gdx.graphics.getHeight() / 2 - view.getHeight() / 2 - 200);

		view.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new ViewScreenMonster(game, current, screen));
//				DataBase n = new DataBase();
//				n.loadAlldata();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(view);
		
		done = new TextButton("Done", style);
		done.setWidth(100);
		done.setHeight(100);
		done.setX(Gdx.graphics.getWidth() / 2 - done.getWidth() / 2 - 400);
		done.setY(Gdx.graphics.getHeight() / 2 - done.getHeight() / 2 - 200);

		done.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(s);
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(done);
		
		
		change  = new TextButton("Change", style);
		change.setWidth(100);
		change.setHeight(100);
		change.setX(Gdx.graphics.getWidth() / 2 - change.getWidth() / 2 - 300);
		change.setY(Gdx.graphics.getHeight() / 2 - change.getHeight() / 2 - 200);

		change.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(model.checkMonsterAtPackOrBattle(current).equals("battle")) {
					if ( model.getBattlePlayerMonster().size() != 1) {
						model.removeMonsterFromBattleToPlayerMonster(current);
						init();
					}
					
				} else {
					if (model.getBattlePlayerMonster().size() < model.getMaxNumberOfMonsterInBattle()) {
						model.addMonsterFromPlayerToBattleMonster(current);
						init();
					}
					
				}
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(change);
		
		
		
		remove  = new TextButton("Remove", style);
		remove.setWidth(100);
		remove.setHeight(100);
		remove.setX(Gdx.graphics.getWidth() / 2 - remove.getWidth() / 2 - 200);
		remove.setY(Gdx.graphics.getHeight() / 2 - remove.getHeight() / 2 - 200);

		remove.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(model.checkMonsterAtPackOrBattle(current).equals("battle")) {
					System.out.print("SIze : " + model.getBattlePlayerMonster().size());
					if (model.getBattlePlayerMonster().size() != 1) {
						model.removeMonsterFromBattleMonster(current);
						current = model.getBattlePlayerMonster().get(0);
					}
					
				} else {
					model.removeMosterfromPlayerMonster(current);
					current = model.getBattlePlayerMonster().get(0);
				}
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(remove);
		
		
		btnUp = new TextButton("Up", style);
		btnUp.setWidth(100);
		btnUp.setHeight(100);
		btnUp.setX(Gdx.graphics.getWidth() / 2 - btnUp.getWidth() / 2 + 300);
		btnUp.setY(Gdx.graphics.getHeight() / 2 - btnUp.getHeight() / 2 - 50);

		btnUp.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(model.checkMonsterAtPackOrBattle(current).equals("battle") && !model.getPlayerMonsters().isEmpty()) {
					current = model.getPlayerMonsters().get(0);
					
				}
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(btnUp);

		btnDown = new TextButton("Down", style);
		btnDown.setWidth(100);
		btnDown.setHeight(100);
		btnDown.setX(Gdx.graphics.getWidth() / 2 - btnDown.getWidth() / 2 + 300);
		btnDown.setY(Gdx.graphics.getHeight() / 2 - btnDown.getHeight() / 2
				- 200);

		btnDown.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(model.checkMonsterAtPackOrBattle(current).equals("pack")) {
					current = model.getBattlePlayerMonster().get(0);
				}
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(btnDown);

		btnLeft = new TextButton("Left", style);
		btnLeft.setWidth(100);
		btnLeft.setHeight(100);
		btnLeft.setX(Gdx.graphics.getWidth() / 2 - btnLeft.getWidth() / 2 + 200);
		btnLeft.setY(Gdx.graphics.getHeight() / 2 - btnLeft.getHeight() / 2
				- 125);

		btnLeft.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (model.checkMonsterAtPackOrBattle(current).equals("battle")) {
					int size = model.getBattlePlayerMonster().size();
					int count = 0;
					for (Monster mo : model.getBattlePlayerMonster()) {
						if (current.equals(mo) && count!= 0) {
							current = model.getBattlePlayerMonster().get(count -1);
							return true;
						}
						count++;
					}
				} else {
					int size = model.getPlayerMonsters().size();
					int count = 0;
					for (Monster mo : model.getPlayerMonsters()) {
						if (current.equals(mo) && count!= 0) {
							current = model.getPlayerMonsters().get(count -1);
							return true;
						}
						count++;
					}
				}
				
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(btnLeft);

		btnRight = new TextButton("Right", style);
		btnRight.setWidth(100);
		btnRight.setHeight(100);
		btnRight.setX(Gdx.graphics.getWidth() / 2 - btnRight.getWidth() / 2
				+ 400);
		btnRight.setY(Gdx.graphics.getHeight() / 2 - btnRight.getHeight() / 2
				- 125);

		btnRight.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (model.checkMonsterAtPackOrBattle(current).equals("battle")) {
					int size = model.getBattlePlayerMonster().size();
					int count = 0;
					for (Monster mo : model.getBattlePlayerMonster()) {
						if (current.equals(mo) && count + 1 < size) {
							current = model.getBattlePlayerMonster().get(count +1);
							return true;
						}
						count++;
					}
				} else {
					int size = model.getPlayerMonsters().size();
					int count = 0;
					for (Monster mo : model.getPlayerMonsters()) {
						if (current.equals(mo) && count + 1 < size) {
							current = model.getPlayerMonsters().get(count +1);
							return true;
						}
						count++;
					}
				}
				
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		stage.addActor(btnRight);

	}
	
	private void addSkin() {
		skin = new Skin() ;
		skin.add("pressedButton", new Texture(Gdx.files.internal("data/pressedButton.png")));
		skin.add("unpressedButton", new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("consoleButtonFont",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
	}
}
