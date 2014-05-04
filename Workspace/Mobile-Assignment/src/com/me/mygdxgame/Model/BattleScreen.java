package com.me.mygdxgame.Model;

import java.util.ArrayList;
import java.util.Random;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.mygdx.HighScores.HighScoreInput;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;

@SuppressWarnings("unused")
public class BattleScreen implements Screen {

	ArrayList<TextButton> listButton = new ArrayList<TextButton>();

	Texture backgroudBattle, bg1 ,bg2 , bg3;

	Player player;

	MapGameUpdate mgu;
	MapGame mg;
	SpriteBatch batch;
	TextButtonStyle style;
	OrthographicCamera cam;

	Texture playerBattleTexture, playerPokemon, monsterPokemon , arrow ,background;

	float stateTime;

	Stage stage;

	int check = 0;
	float timecount;

	BitmapFont bmf = new BitmapFont();

	Skills defendSkill;
	Skills captureMonster;
	Skills temp;

	TextButton attack;
	TextButton defend;
	TextButton skillAttack;
	TextButton back;
	TextButton flee;
	TextButton capture;

	ArrayList<Monster> listMonster;
	ArrayList<Monster> listBattleMonster;

	Monster currentMyMonster;

	int checkMonster = 0;
	ArrayList<String> movement = new ArrayList<String>();
	String action = "";
	private int actionStatus = 0;
	
	private int attackStatus = 0;
	private int skillStatus = 0;
	private int captureStatus = 0;
	private int defendStatus = 0;
	
	private int actionStatusEnemy = 0;
	
	private int attackStatusEnemy = 0;
	private int skillStatusEnemy = 0;
	private int captureStatusEnemy = 0;
	private int defendStatusEnemy = 0;

	private float timer = 0f;
	int numberCount = 0;
	MyGdxGame game;
	Screen gs;
	Model model = Model.getModel();
	private int randomRandom  = 0;
	private MusicManager musicManager;
	private SoundManager soundManager;
	private ArrayList<Monster> catchMonster  = new  ArrayList<Monster>();
	private ArrayList<Monster> affectedMonster = new ArrayList<Monster>();
	
	private int checkAoeEnemy = 0;
	
private float stateTimeNew = 0;
	
	ArrayList<String> actionArrayEnemy;

	public BattleScreen( MyGdxGame game, Screen gs, ArrayList<Monster> monsArray, MusicManager musicManager, SoundManager soundManager) {
//		player = mg.getPlayer();
		this.game = game;
		this.gs = gs;
		this.musicManager = musicManager;
		this.soundManager = soundManager;
		// this.listMonster = listMonster;

		listMonster = new ArrayList<Monster>(monsArray);
		listBattleMonster = new ArrayList<Monster>();
//		listMonster = monsArray;
		bmf.setColor(Color.RED);

		
		cam = new OrthographicCamera();
		System.out.println("width "+Gdx.graphics.getWidth() / 2);
		System.out.println("height "+Gdx.graphics.getWidth() / 2);
		
		cam.setToOrtho(false, Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		cam.update();

		style = new TextButtonStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.BLACK;

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);

		stateTime = 0f;

		defendSkill = new Skills("aaaa", "data/effectskill.png", "ATK", 1, "Ice", 5, 3,
				0.15f, "enemy");
		
		captureMonster = new Skills("Capture" , "data/capture.png" , "ATK" , 1 , "Capture", 5, 4 , 0.2f , "enemy");
		int k = 0;
		int i = 0;
		if (listMonster.get(0).isBoss()) {
			k = 60;
		}
		for (Monster mons : listMonster) {
			mons.setIdInBattkeEnemy(i);
			mons.setLocation(new Vector2(150, 120 + k));
			mons.setOriginlocation(new Vector2(150, 120 + k));
			mons.prepareBattle();
			k += 60;
			i++;
		}
		k = 0;
		i = 0;
		for (Monster mons : model.getBattlePlayerMonster()) {
			mons.setIdInBattleMine(i);
			mons.setLocation(new Vector2(450, 120 + k));
			mons.setOriginlocation(new Vector2(450, 120 + k));
			mons.setDie(false);
			if (mons.getCurrentHP() <= 0) {
				mons.setCurrentHP(1);
				
			}
			
			k += 60;
			listBattleMonster.add(mons);
			i++;
		}
		arrow = new Texture("data/arrow.png");
		background = new Texture("data/battleback.jpg");
		currentMyMonster = listBattleMonster.get(0);
		
		bg1 =  new Texture("data/bg1.jpg");
		bg2 =  new Texture("data/bg2.jpg");
		bg3 =  new Texture("data/bg3.jpg");
	}

	@Override
	public void render(float delta) {
		// clear renderer
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();
		if (model.getNumberStage() == 1) {
			batch.begin();
			batch.draw(bg1, 0, 0);
			batch.end();
		} else if (model.getNumberStage() == 2) {
			batch.begin();
			batch.draw(bg2, 0, 0);
			batch.end();
		} else {
			batch.begin();
			batch.draw(bg3, 0, 0);
			batch.end();
		}
		
		
		
		for (Monster mons : listMonster) {
			if (!mons.isDie()) {
				if (mons.isBoss()) {
					mons.drawMonsterEnemy(batch, mons.getLocation().x - 25,
							mons.getLocation().y - 25, stateTime);
				} else {
				mons.drawMonsterEnemy(batch, mons.getLocation().x,
						mons.getLocation().y, stateTime);
				}
			}
		}

		for (Monster mons : listBattleMonster) {
			if (!mons.isDie()) {
				mons.drawMonsterMine(batch, mons.getLocation().x,
						mons.getLocation().y, stateTime);
				batch.begin();
				bmf.setColor(Color.GREEN);
				bmf.draw(batch, "HP : "+ mons.getCurrentHP() + "/" + mons.getHp(), mons.getOriginlocation().x + 30, mons.getOriginlocation().y + 20);
				bmf.setColor(Color.RED);
				batch.end();
			}
		
		}
		
		if (actionStatus == 0) {
			batch.begin();
			batch.draw(arrow, currentMyMonster.getOriginlocation().x + 5 , currentMyMonster.getOriginlocation().y + 35,
					0, 0, 20, 20, 1, 1,
					0, 0, 0, arrow.getWidth() , arrow.getHeight() , false, false);
			batch.end();
		}
		
		stateTimeNew += Gdx.graphics.getDeltaTime();
	
		
		action(stateTimeNew);
		actionForEnemy(stateTimeNew);
//		checkResult();
		
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

		createButton();

	}

	@Override
	public void show() {

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

	}
	
	public void createButtonForNoSkill() {
		
			action = "";
			
			

			skillAttack = new TextButton("Skill", style);
			skillAttack.setWidth(100);
			skillAttack.setHeight(100);
			skillAttack.setX(Gdx.graphics.getWidth() / 2
					- skillAttack.getWidth() / 2 - 100);
			skillAttack.setY(Gdx.graphics.getHeight() / 2
					- skillAttack.getHeight() / 2 - 200);

			skillAttack.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					action = currentMyMonster.getIdInBattleMine() + "-";
					removeButtonLevel1();

					createButtonForChooseSkill(currentMyMonster);
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
				}
			});

			stage.addActor(skillAttack);

			attack = new TextButton("Attack", style);
			attack.setWidth(100);
			attack.setHeight(100);
			attack.setX(Gdx.graphics.getWidth() / 2 - attack.getWidth() / 2
					- 300);
			attack.setY(Gdx.graphics.getHeight() / 2 - attack.getHeight() / 2
					- 200);

			attack.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// a.setSkillState(true);
					removeButtonLevel1();
					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "attack" + "-";
					createButtonForChoosingMonster();
					// action = "";

					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(attack);

			defend = new TextButton("Defend", style);
			defend.setWidth(100);
			defend.setHeight(100);
			defend.setX(Gdx.graphics.getWidth() / 2 - defend.getWidth() / 2
					+ 300);
			defend.setY(Gdx.graphics.getHeight() / 2 - defend.getHeight() / 2
					- 200);

			defend.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					
					removeButtonLevel1();

					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "defend";
					movement.add(action);

					createButton();
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(defend);

			capture = new TextButton("Capture", style);
			capture.setWidth(100);
			capture.setHeight(100);
			capture.setX(Gdx.graphics.getWidth() / 2 - capture.getWidth() / 2
					+ 100);
			capture.setY(Gdx.graphics.getHeight() / 2 - capture.getHeight() / 2
					- 200);

			capture.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "capture" + "-";
					removeButtonLevel1();
					createButtonForChoosingMonster();

					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

				}
			});

			stage.addActor(capture);
		
		
		
		Gdx.input.setInputProcessor(stage);
		
	}

	public void createButton() {
		numberCount = 0 ;
		for	(Monster mos : listBattleMonster) {
			if (!mos.isDie()) {
				numberCount++;
			}
		}
		
		if (checkMonster == listBattleMonster.size()) {
			actionStatus = 1;
			
		}
		if (checkMonster < listBattleMonster.size() && !listBattleMonster.get(checkMonster).isDie()) {
			action = "";
			
			currentMyMonster = listBattleMonster.get(checkMonster);

			skillAttack = new TextButton("Skill", style);
			skillAttack.setWidth(100);
			skillAttack.setHeight(100);
			skillAttack.setX(Gdx.graphics.getWidth() / 2
					- skillAttack.getWidth() / 2 - 100);
			skillAttack.setY(Gdx.graphics.getHeight() / 2
					- skillAttack.getHeight() / 2 - 200);

			skillAttack.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					action = currentMyMonster.getIdInBattleMine() + "-";
					removeButtonLevel1();

					createButtonForChooseSkill(currentMyMonster);
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
				}
			});

			stage.addActor(skillAttack);

			attack = new TextButton("Attack", style);
			attack.setWidth(100);
			attack.setHeight(100);
			attack.setX(Gdx.graphics.getWidth() / 2 - attack.getWidth() / 2
					- 300);
			attack.setY(Gdx.graphics.getHeight() / 2 - attack.getHeight() / 2
					- 200);

			attack.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// a.setSkillState(true);
					removeButtonLevel1();
					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "attack" + "-";
					createButtonForChoosingMonster();
					// action = "";

					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(attack);

			defend = new TextButton("Defend", style);
			defend.setWidth(100);
			defend.setHeight(100);
			defend.setX(Gdx.graphics.getWidth() / 2 - defend.getWidth() / 2
					+ 300);
			defend.setY(Gdx.graphics.getHeight() / 2 - defend.getHeight() / 2
					- 200);

			defend.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					
					removeButtonLevel1();

					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "defend";
					movement.add(action);

					createButton();
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(defend);

			capture = new TextButton("Capture", style);
			capture.setWidth(100);
			capture.setHeight(100);
			capture.setX(Gdx.graphics.getWidth() / 2 - capture.getWidth() / 2
					+ 100);
			capture.setY(Gdx.graphics.getHeight() / 2 - capture.getHeight() / 2
					- 200);

			capture.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					action = currentMyMonster.getIdInBattleMine() + "-"
							+ "capture" + "-";
					removeButtonLevel1();
					createButtonForChoosingMonster();

					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

				}
			});

			stage.addActor(capture);
		} 
		
		checkMonster++;
		if (checkMonster -1 < listBattleMonster.size() && listBattleMonster.get(checkMonster - 1).isDie()) {
			createButton();
		}
		Gdx.input.setInputProcessor(stage);
	}

	public void removeButtonLevel1() {
		attack.remove();
		skillAttack.remove();
		defend.remove();
		capture.remove();

	}

	public void createButtonForChoosingMonster() {
		int i = -300;
		for (final Monster mon : listMonster) {
			if (!mon.isDie()) {
				TextButton monster = new TextButton(mon.getName(), style);
				addButtonToArray(monster);
				monster.setWidth(100);
				monster.setHeight(100);
				monster.setX(Gdx.graphics.getWidth() / 2 - monster.getWidth() / 2
						+ i);
				monster.setY(Gdx.graphics.getHeight() / 2 - monster.getHeight() / 2
						- 200);

				monster.addListener(new InputListener() {
					public boolean touchDown(InputEvent event, float x, float y,
							int pointer, int button) {
						removeButtonInArray();

						action = action + mon.getIdInBattkeEnemy();
						movement.add(action);
						createButton();
						return true;
					}
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
					}
				});

				stage.addActor(monster);
				i += 200;
			}
			
		}
	}

	public void createButtonForChoosingMonsterTeam() {
		int i = -300;
		for (final Monster mon : listBattleMonster) {
			if (!mon.isDie()) {
			TextButton monster = new TextButton(mon.getName(), style);
			addButtonToArray(monster);
			monster.setWidth(100);
			monster.setHeight(100);
			monster.setX(Gdx.graphics.getWidth() / 2 - monster.getWidth() / 2
					+ i);
			monster.setY(Gdx.graphics.getHeight() / 2 - monster.getHeight() / 2
					- 200);

			monster.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					removeButtonInArray();
					
					action = action + mon.getIdInBattleMine();
					movement.add(action);
					System.out.println(action);
					createButton();
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(monster);
			i += 200;
			}
		}
	}

	public void createButtonForChooseSkill(Monster mons) {
		int i = -300;
		if (mons.getSkillsAvalable() == null
				|| mons.getSkillsAvalable().size() == 0) {
			final TextButton back = new TextButton("No Skill Avalable", style);
			back.setWidth(100);
			back.setHeight(100);
			back.setX(Gdx.graphics.getWidth() / 2 - back.getWidth() / 2 + 100);
			back.setY(Gdx.graphics.getHeight() / 2 - back.getHeight() / 2 - 200);

			back.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// a.setSkillState(true);

					back.remove();
					createButtonForNoSkill();

					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// check = 0;
				}
			});

			stage.addActor(back);

		} else {
			for (final Skills ski : mons.getSkillsAvalable()) {
				TextButton skil = new TextButton(ski.getName(), style);
				addButtonToArray(skil);
				skil.setWidth(100);
				skil.setHeight(100);
				skil.setX(Gdx.graphics.getWidth() / 2 - skil.getWidth() / 2 + i);
				skil.setY(Gdx.graphics.getHeight() / 2 - skil.getHeight() / 2
						- 200);

				skil.addListener(new InputListener() {
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						removeButtonInArray();
						if (ski.getTarget().equals("enemy")) {
							action = action + "skill-" + ski.getName() + "-";
							createButtonForChoosingMonster();
							
						} else if (ski.getTarget().equals("team")) {
							action = action + "skill-" + ski.getName() + "-";
							createButtonForChoosingMonsterTeam();
							
						} else if (ski.getTarget().equals("aoe_enemy")) {
							action = action + "skill-" + ski.getName() + "-";
//							createButtonForChoosingMonsterTeam();
							movement.add(action);
							createButton();
						}
						return true;
					}

					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {

						// check = 0;
					}
				});

				stage.addActor(skil);
				i += 200;
			}

		}

	}

	public void addButtonToArray(TextButton tb) {
		listButton.add(tb);
	}

	public void removeButtonInArray() {
		for (TextButton but : listButton) {
			but.remove();
		}
	}

	public void action(float stateTi) {
		if (actionStatus == 1) {
			timer += Gdx.graphics.getDeltaTime();
			for (String str : movement) {
				String tmp[] = str.split("-");
				if (tmp[1].equals("attack")) {
					if (attackStatus == 0) {
						listBattleMonster
								.get(Integer.parseInt(tmp[0]))
								.getLocation()
								.lerp( new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().x + 40 , listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().y),
										Gdx.graphics.getDeltaTime());
						model.findSkillByName("normal attack").setSkillState(true);
						model.findSkillByName("normal attack").useSkill(batch,  new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x - 15 , listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().y -10) , 60 , 60 , stateTi);
				
						int damage = 0;
						damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() *( 100 -listMonster.get(Integer.parseInt(tmp[2])).getDefendPhysic() ) /100;
						batch.begin();
						bmf.draw(batch, damage+"", listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x  , listMonster.get(Integer.parseInt(tmp[2])).getLocation().y + 40);
						batch.end();
						if (timer >= 5) {
							attackStatus = 1;
						}
					} else if (attackStatus == 1){
						listBattleMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
				} else if (tmp[1].equals("defend")) {
					if (defendStatus == 0) {
						
						defendSkill.setSkillState(true);
						defendSkill.useSkill(batch,  new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getLocation().x - 15 , listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getLocation().y -10) , 60 , 60 , stateTi);
						if (timer >= 5) {
							defendStatus = 1;
						}
					} else if (defendStatus == 1){
						listBattleMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
					
					
				} else if (tmp[1].equals("skill")) {
					temp = model.findSkillByName(tmp[2]);
					if (skillStatus == 0) {
						if (temp.getTarget().equals("enemy")) {
							listBattleMonster.get(Integer.parseInt(tmp[0])).getLocation()
									.lerp( new Vector2 (listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x + 160 , listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y), Gdx.graphics.getDeltaTime());
							temp.setSkillState(true);
							temp.useSkill(batch,  new Vector2 (listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x - 15 , listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y -10) , 60 , 60 ,stateTi);
							int damage = 0;
							if (temp.getSkillType().equals("atk")) {
								temp.getPercentAtk();
								listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic();
								listMonster.get(Integer.parseInt(tmp[3])).getDefendPhysic();
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  *( 100 -listMonster.get(Integer.parseInt(tmp[3])).getDefendPhysic() ) /100;
								batch.begin();
								bmf.draw(batch, damage+"", listMonster.get(Integer.parseInt(tmp[3]))
										.getLocation().x  , listMonster.get(Integer.parseInt(tmp[3])).getLocation().y + 40);
								batch.end();
							} else {
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  *( 100 -listMonster.get(Integer.parseInt(tmp[3])).getDefendMagic() ) /100;
								batch.begin();
								bmf.draw(batch, damage+"", listMonster.get(Integer.parseInt(tmp[3]))
										.getLocation().x  , listMonster.get(Integer.parseInt(tmp[3])).getLocation().y + 40);
								batch.end();
							}
							
							
							
						} else if (temp.getTarget().equals("team")) {
							listBattleMonster.get(Integer.parseInt(tmp[0])).getLocation()
									.lerp( new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getOriginlocation().x - 160 , listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getOriginlocation().y), Gdx.graphics.getDeltaTime());
							temp.setSkillState(true);
							temp.useSkill(batch,  new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x - 15 , listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y -10) , 60 , 60 ,stateTi);
							
							int damage = 0;
							if (temp.getSkillType().equals("atk")) {
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  ;
								batch.begin();
								bmf.setColor(Color.GREEN);
								bmf.draw(batch, damage+"", listBattleMonster.get(Integer.parseInt(tmp[3]))
										.getLocation().x  , listBattleMonster.get(Integer.parseInt(tmp[3])).getLocation().y + 40);
								batch.end();
								bmf.setColor(Color.RED);
							} else {
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  ;
								batch.begin();
								bmf.setColor(Color.GREEN);
								bmf.draw(batch, damage+"", listBattleMonster.get(Integer.parseInt(tmp[3]))
										.getLocation().x  , listBattleMonster.get(Integer.parseInt(tmp[3])).getLocation().y + 40);
								batch.end();
								bmf.setColor(Color.RED);
							}
						} else if (temp.getTarget().equals("aoe_enemy")) {
							if (checkAoeEnemy == 0) {
								caculateAffectedEnemy(temp.getNumberOfTarget());
							}
							
							listBattleMonster.get(Integer.parseInt(tmp[0])).getLocation()
								.lerp( new Vector2 (affectedMonster.get(0)
								.getLocation().x + 160 , affectedMonster.get(0)
								.getLocation().y), Gdx.graphics.getDeltaTime());
							
							for (Monster mon : affectedMonster) {
								if (temp.getScaleSkill() == 1) {
									temp.setSkillState(true);
									temp.useSkill(batch,  new Vector2 (listMonster.get(0)
										.getOriginlocation().x - 100 , listMonster.get(0)
										.getOriginlocation().y -10) , 300 , 300, stateTi);
								} else {
									temp.setSkillState(true);
									temp.useSkill(batch,  new Vector2 (mon
										.getLocation().x - 15 , mon
										.getLocation().y -10) , 60 , 60, stateTi);
								}
								int damage = 0;
								if (temp.getSkillType().equals("atk")) {
									damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() *
											temp.getPercentAtk() /100  *( 100 -mon.getDefendPhysic() ) /100;
									batch.begin();
									bmf.draw(batch, damage+"", mon
											.getLocation().x  , mon.getLocation().y + 40);
									batch.end();
								} else if (temp.getSkillType().equals("matk")) {
									damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() *
											temp.getPercentAtk() /100  *( 100 -mon.getDefendMagic() ) /100;
									batch.begin();
									bmf.draw(batch, damage+"", mon
											.getLocation().x  , mon.getLocation().y + 40);
									batch.end();
								}
							}
							
						
						}
						
				
						if (timer >= 5) {
							skillStatus = 1;
						}
					} else if (skillStatus == 1){
						listBattleMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
				} else if (tmp[1].equals("capture")) {
					if (captureStatus == 0) {
						listBattleMonster
								.get(Integer.parseInt(tmp[0]))
								.getLocation()
								.lerp( new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().x + 100 , listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().y), Gdx.graphics.getDeltaTime());
						captureMonster.setSkillState(true);
						captureMonster.useSkill(batch,  new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x - 15 , listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().y -10) , 60 , 60 ,stateTi);
				
						if (timer >= 5) {
							captureStatus = 1;
						}
					} else if (captureStatus == 1){
						listBattleMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
					
				}
				
			}
			
			if (timer >= 10) {
				actionStatus = 0;
				attackStatus = 0;
				skillStatus = 0;
				defendStatus = 0;
				captureStatus = 0;
				numberCount = 0;
				timer = 0;
				checkMonster = 0;
				checkAoeEnemy = 0;
				caculateActionLogic(movement);
				movement.removeAll(movement);
				checkResult();
				enemyTurn();
				if (actionArrayEnemy.size() != 0) {
					for (String str : actionArrayEnemy) {
						System.out.println(str);
					}
				}
			}
		}
	}
	
	public void caculateActionLogic (ArrayList<String> act) {
		for (String str : act) {
			String tmp[] = str.split("-");
			if (tmp[1].equals("attack")) {
					int damage = 0;
					damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * (100 - listMonster.get(Integer.parseInt(tmp[2])).getDefendPhysic() ) /100;	
					listMonster.get(Integer.parseInt(tmp[2])).setCurrentHP(listMonster.get(Integer.parseInt(tmp[2])).getCurrentHP() - damage);
					System.out.println(listMonster.get(Integer.parseInt(tmp[2])).getCurrentHP());
				
			} else if (tmp[1].equals("defend")) {
				
			} else if (tmp[1].equals("skill")) {
				temp = model.findSkillByName(tmp[2]);
					if (temp.getTarget().equals("enemy")) {
						int damage = 0;
						if (temp.getSkillType().equals("atk")) {
							damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  *( 100 -listMonster.get(Integer.parseInt(tmp[3])).getDefendPhysic() ) /100;
							listMonster.get(Integer.parseInt(tmp[3])).setCurrentHP(listMonster.get(Integer.parseInt(tmp[3])).getCurrentHP() - damage);
						} else {
							damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  *( 100 -listMonster.get(Integer.parseInt(tmp[3])).getDefendMagic() ) /100;
							listMonster.get(Integer.parseInt(tmp[3])).setCurrentHP(listMonster.get(Integer.parseInt(tmp[3])).getCurrentHP() - damage);
						}
						
					} else if (temp.getTarget().equals("team")) {
						int damage = 0;
						if (temp.getSkillType().equals("atk")) {
							damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  ;
							listBattleMonster.get(Integer.parseInt(tmp[3])).setCurrentHP(listBattleMonster.get(Integer.parseInt(tmp[3])).getCurrentHP() + damage);
//							listMonster.get(Integer.parseInt(tmp[3])).setCurrentHP(listMonster.get(Integer.parseInt(tmp[3])).getCurrentHP() - damage);
						} else {
							damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  ;
							listBattleMonster.get(Integer.parseInt(tmp[3])).setCurrentHP(listBattleMonster.get(Integer.parseInt(tmp[3])).getCurrentHP() + damage);
						}
					} else if (temp.getTarget().equals("aoe_enemy")) { 
						int damage = 0;
						System.out.println("check1");
						if (temp.getSkillType().equals("atk")) {
							System.out.println("check2");
//							caculateAffectedEnemy(temp.getNumberOfTarget());
							for (Monster mo :affectedMonster) {
								System.out.println("check3");
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  *( 100 -mo.getDefendPhysic() ) /100;
								mo.setCurrentHP(mo.getCurrentHP() - damage);
								
							}
						} else {
							for (Monster mo :affectedMonster) {
								System.out.println("check3");
								damage = listBattleMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  *( 100 -mo.getDefendMagic() ) /100;
								mo.setCurrentHP(mo.getCurrentHP() - damage);
								
							}
						}
						affectedMonster.removeAll(affectedMonster);
					}
					
					
			} else if (tmp[1].equals("capture")) {
				Random rad = new Random();
				int randomRandom = rad.nextInt(100 - 0 + 1) + 0;
				if (listMonster.get(Integer.parseInt(tmp[2])).isCatchAble()) {
					if (randomRandom < 30 ) {
						listMonster.get(Integer.parseInt(tmp[2])).setCurrentHP(0);
						catchMonster.add(listMonster.get(Integer.parseInt(tmp[2])));
					} else {
						
					}
				}
			}
		}
	}
	
	public void caculateAffectedEnemy(int target) {
		ArrayList<Monster> enemyMonster = new ArrayList<Monster>();
		for (Monster mon : listMonster) {
			if (!mon.isDie()) {
				enemyMonster.add(mon);
			}		
		}
		affectedEnemy(target, enemyMonster);
	}
	
	public void caculateAffectedTeam(int target) {
		ArrayList<Monster> enemyMonster = new ArrayList<Monster>();
		for (Monster mon : listBattleMonster) {
			if (!mon.isDie()) {
				enemyMonster.add(mon);
			}		
		}
		affectedEnemy(target, enemyMonster);
	}
	
	public void affectedEnemy (int target, ArrayList<Monster> array) {
		Random rad = new Random();
		int size = array.size();
		ArrayList<Integer> enemyInteger = new ArrayList<Integer>();
		ArrayList<Monster> enemyMonster = new ArrayList<Monster>();
		if (target < size) {	
			for (int i = 0; i < target ; i ++) {
				int rand = rad.nextInt(size- 1 - 0 + 1) + 0;
				if (enemyInteger.contains(rand)) {
					i --;
				} else {
					enemyInteger.add(rand);
				}
			}
			
			for (Integer in :enemyInteger) {
				enemyMonster.add(array.get(in));
			}
			affectedMonster = enemyMonster;
		} else {
			affectedMonster = array;
		}
		
		checkAoeEnemy = 1;
	}
	
	public void caculateActionLogicForEnemy (ArrayList<String> act) {
		for (String str : act) {
			String tmp[] = str.split("-");
			if (tmp[1].equals("attack")) {
					int damage = 0;
					damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * (100- listBattleMonster.get(Integer.parseInt(tmp[2])).getDefendPhysic() ) /100;	
					listBattleMonster.get(Integer.parseInt(tmp[2])).setCurrentHP(listBattleMonster.get(Integer.parseInt(tmp[2])).getCurrentHP() - damage);
					System.out.println(listBattleMonster.get(Integer.parseInt(tmp[2])).getCurrentHP());
				
			} else if (tmp[1].equals("defend")) {
				
			} else if (tmp[1].equals("skill")) {
				temp = model.findSkillByName(tmp[2]);
					if (temp.getTarget().equals("enemy")) {
						
					} else if (temp.getTarget().equals("team")) {
						
					} else if (temp.getTarget().equals("aoe_enemy")) {
						int damage = 0;
						if (temp.getSkillType().equals("atk")) {
							for (Monster mo :affectedMonster) {
								damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * temp.getPercentAtk() /100  *( 100 -mo.getDefendPhysic() ) /100;
								mo.setCurrentHP(mo.getCurrentHP() - damage);
								
							}
						} else {
							for (Monster mo :affectedMonster) {
								damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() * temp.getPercentAtk() /100  *( 100 -mo.getDefendMagic() ) /100;
								mo.setCurrentHP(mo.getCurrentHP() - damage);
								
							}
						}
						affectedMonster.removeAll(affectedMonster);
					}
					
					
					
			} else if (tmp[1].equals("capture")) {
				
			}
		}
	}
	
	
	public void checkResult() {
		
		for (Monster mos : listMonster) {
			if (mos.getCurrentHP() <= 0) {
				mos.setDie(true);
			}
		}
		
		int count = 0;
		for (Monster mos : listMonster) {
			if (mos.isDie()) {
				count++;
			}
		}
		if (count == listMonster.size()) {
			int expGain = 0;
			for (Monster mos : listMonster) {
				expGain += mos.getExpGain();
			}
			expGain = expGain/listBattleMonster.size();
			for (Monster mos : listBattleMonster) {
				System.out.println("Name : " +mos.getName());
				mos.setExp(mos.getExp() + expGain +25 );
				mos.increaseLevelCheck();
			}
			for (Monster mos : catchMonster) {
//				mos.setLevel(1);
				
				mos.randomSKill();
				mos.levelDownToOne();
				mos.setLevel(1);
				mos.prepareBattle();
				try {
					if (model.getPlayerMonsters().size() + model.getBattlePlayerMonster().size() < 8) {
						model.addMosterToPlayerMonster((Monster)mos.clone());
					}
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			model.setScoreTotal(model.getScoreTotal() + 100);
			game.setScreen(gs);
		}
		
		for (Monster mos : listBattleMonster) {
			if (mos.getCurrentHP() <= 0) {
				mos.setDie(true);
			}
		}
		
		 count = 0;
		for (Monster mos : listBattleMonster) {
			if (mos.isDie()) {
				count++;
			}
		}
		if (count == listBattleMonster.size()) {
			HighScoreInput upload = new HighScoreInput(game, musicManager, soundManager);
			game.setScreen(upload);
		}

	}
	
	public void enemyTurn() {
		Random ran =  new Random();
		actionArrayEnemy = new ArrayList<String>();
		int i = 0;
		String actionEnemy = ""; 
		for (Monster mos :listMonster) {
			if (!mos.isDie()) {
				actionEnemy = mos.getIdInBattkeEnemy() + "-";
				int randomNum;
				if (mos.isBoss()) {
					 randomNum = ran.nextInt(3 - 0 + 1) + 0;  // 0 == attack 1== skill 2= defend
				} else {
					 randomNum = ran.nextInt(2 - 0 + 1) + 0;
				}
				 
				
				if (randomNum == 0 ||randomNum == 1 ||randomNum == 2  ) {
					actionEnemy = actionEnemy + "attack" + "-";
					ArrayList <Integer> array = new ArrayList<Integer>();
					for (Monster mosMine :listBattleMonster) {
						if (!mosMine.isDie()) {
							array.add(mosMine.getIdInBattleMine());
						}
					}
					int randomNum2 = ran.nextInt(array.size()-1 - 0 + 1) + 0;
					actionEnemy = actionEnemy + array.get(randomNum2);
					actionArrayEnemy.add(actionEnemy);
				} else if (randomNum == 3) {
					for (Skills ski: mos.getSkillsAvalable()) {
						if (ski.getTarget().equals("aoe_enemy")) {
							actionEnemy = actionEnemy + "skill-"+ ski.getName();
							System.out.println(actionEnemy);
						}
					}
					
					actionArrayEnemy.add(actionEnemy);
				}
			}
		}
		actionStatusEnemy = 1;
	}
	
	
	public void actionForEnemy(float stateTi) {
		if (actionStatusEnemy == 1) {
			timer += Gdx.graphics.getDeltaTime();
			for (String str : actionArrayEnemy) {
				String tmp[] = str.split("-");
				if (tmp[1].equals("attack")) {
					if (attackStatus == 0) {
						listMonster
								.get(Integer.parseInt(tmp[0]))
								.getLocation()
								.lerp( new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().x - 40 , listBattleMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().y),
										Gdx.graphics.getDeltaTime());
						model.findSkillByName("normal attack");
						model.findSkillByName("normal attack").setSkillState(true);
						model.findSkillByName("normal attack").useSkill(batch,  new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x - 15 , listBattleMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().y -10) , 60 , 60 , stateTi);
				
						int damage = 0;
						damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() * (100- listBattleMonster.get(Integer.parseInt(tmp[2])).getDefendPhysic() ) /100;
						batch.begin();
						bmf.draw(batch, damage+"", listBattleMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x  , listBattleMonster.get(Integer.parseInt(tmp[2])).getLocation().y + 40);
						batch.end();
						if (timer >= 5) {
							attackStatus = 1;
						}
					} else if (attackStatus == 1){
						listMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
				} else if (tmp[1].equals("defend")) {
					//checkMonster = 0;
				} else if (tmp[1].equals("skill")) {
					temp = model.findSkillByName(tmp[2]);
					if (skillStatus == 0) {
						if (temp.getTarget().equals("enemy")) {
							listBattleMonster.get(Integer.parseInt(tmp[0])).getLocation()
									.lerp( new Vector2 (listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x + 160 , listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y), Gdx.graphics.getDeltaTime());
							temp.setSkillState(true);
							temp.useSkill(batch,  new Vector2 (listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x - 15 , listMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y -10) , 60 , 60 );
						} else if (temp.getTarget().equals("team")) {
							listBattleMonster.get(Integer.parseInt(tmp[0])).getLocation()
									.lerp( new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x + 160 , listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y), Gdx.graphics.getDeltaTime());
							temp.setSkillState(true);
							temp.useSkill(batch,  new Vector2 (listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().x - 15 , listBattleMonster.get(Integer.parseInt(tmp[3]))
									.getLocation().y -10) , 60 , 60 );
						} else if (temp.getTarget().equals("aoe_enemy")) {
							System.out.println("check aoe");
							if (checkAoeEnemy == 0) {
								caculateAffectedTeam(temp.getNumberOfTarget());
							}
							
							listMonster.get(Integer.parseInt(tmp[0])).getLocation()
								.lerp( new Vector2 (affectedMonster.get(0)
								.getLocation().x - 160 , affectedMonster.get(0)
								.getLocation().y), Gdx.graphics.getDeltaTime());
							
							for (Monster mon : affectedMonster) {
								if (temp.getScaleSkill() == 1) {
									temp.setSkillState(true);
									temp.useSkill(batch,  new Vector2 (listBattleMonster.get(0)
										.getOriginlocation().x + 100 , listBattleMonster.get(0)
										.getOriginlocation().y +10) , 300 , 300, stateTi);
								} else {
									temp.setSkillState(true);
									temp.useSkill(batch,  new Vector2 (mon
										.getLocation().x - 15 , mon
										.getLocation().y -10) , 60 , 60, stateTi);
								}
								int damage = 0;
								if (temp.getSkillType().equals("atk")) {
									damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkPhysic() *
											temp.getPercentAtk() /100  *( 100 -mon.getDefendPhysic() ) /100;
									batch.begin();
									bmf.draw(batch, damage+"", mon
											.getLocation().x  , mon.getLocation().y + 40);
									batch.end();
								} else if (temp.getSkillType().equals("matk")) {
									damage = listMonster.get(Integer.parseInt(tmp[0])).getAtkMagic() *
											temp.getPercentAtk() /100  *( 100 -mon.getDefendMagic() ) /100;
									batch.begin();
									bmf.draw(batch, damage+"", mon
											.getLocation().x  , mon.getLocation().y + 40);
									batch.end();
								}
							}			
						}
						if (timer >= 5) {
							skillStatus = 1;
						}
					} else if (skillStatus == 1){
						listMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
				} else if (tmp[1].equals("capture")) {
					if (captureStatus == 0) {
						listBattleMonster
								.get(Integer.parseInt(tmp[0]))
								.getLocation()
								.lerp( new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().x + 100 , listMonster.get(Integer.parseInt(tmp[2]))
										.getLocation().y), Gdx.graphics.getDeltaTime());
						captureMonster.setSkillState(true);
						captureMonster.useSkill(batch,  new Vector2 (listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().x - 15 , listMonster.get(Integer.parseInt(tmp[2]))
								.getLocation().y -10) , 60 , 60 );
				
						if (timer >= 5) {
							captureStatus = 1;
						}
					} else if (captureStatus == 1){
						listBattleMonster
						.get(Integer.parseInt(tmp[0]))
						.getLocation()
						.lerp(listBattleMonster.get(Integer.parseInt(tmp[0]))
								.getOriginlocation(),
								Gdx.graphics.getDeltaTime());
					}
					
				}
				
			}
			
			if (timer >= 10) {
				actionStatusEnemy = 0;
				attackStatus = 0;
				skillStatus = 0;
				defendStatus = 0;
				captureStatus = 0;
				timer = 0;
				checkMonster = 0;
				caculateActionLogicForEnemy(actionArrayEnemy);
				actionArrayEnemy.removeAll(actionArrayEnemy);
				checkResult();
				createButton();
			}

		}
	}
}