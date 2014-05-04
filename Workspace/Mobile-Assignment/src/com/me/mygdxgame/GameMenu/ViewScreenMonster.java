package com.me.mygdxgame.GameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Model.Monster;
import com.me.mygdxgame.Model.Skills;

public class ViewScreenMonster implements Screen {

	Monster current;
	TextButton back;
	Stage stage;
	SpriteBatch batch;
	private OrthographicCamera camera;
	TextButtonStyle style;
	MyGdxGame game;
	Screen s;
	private Skin skin;
	
	Texture bg1;
	
	TextButton hp, mp, exp, skill, atk , matk, def, mdef, level;
	float stateTime = 0;

	public ViewScreenMonster(MyGdxGame game, Monster m, Screen s) {
		this.current = m;
		float width = Gdx.graphics.getWidth() / 2;
		float height = Gdx.graphics.getHeight() / 2;
		this.game = game;
		this.s = s;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		addSkin();
		style = new TextButtonStyle();
		style.font = skin.getFont("consoleButtonFont");
		style.fontColor = Color.WHITE;
		style.up = skin.getDrawable("unpressedButton");
		style.down = skin.getDrawable("pressedButton");
	
		
		bg1 = new Texture("data/bg1.jpg");

	}
	public void createButton() {

		back = new TextButton("BACK", style);
		back.setWidth(100);
		back.setHeight(100);
		back.setX(Gdx.graphics.getWidth() / 2 - back.getWidth() / 2 - 300);
		back.setY(Gdx.graphics.getHeight() - back.getHeight());

		back.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(s);

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		stage.addActor(back);

		level = new TextButton("Level : " + current.getLevel(), style);
		level.setWidth(200);
		level.setHeight(100);
		level.setX(Gdx.graphics.getWidth() / 2 - level.getWidth() / 2 - 100);
		level.setY(Gdx.graphics.getHeight() - level.getHeight());
		stage.addActor(level);

		hp = new TextButton("HP : " + current.getCurrentHP() + " / "
				+ current.getHp(), style);
		hp.setWidth(100);
		hp.setHeight(150);
		hp.setX(Gdx.graphics.getWidth() / 2 - hp.getWidth() / 2 - 300);
		hp.setY(Gdx.graphics.getHeight() / 2 - hp.getHeight() / 2 + 100);
		stage.addActor(hp);

		mp = new TextButton("MP : " + current.getCurrentMP() + " / "
				+ current.getMp(), style);
		mp.setWidth(100);
		mp.setHeight(150);
		mp.setX(Gdx.graphics.getWidth() / 2 - mp.getWidth() / 2 - 300);
		mp.setY(Gdx.graphics.getHeight() / 2 - mp.getHeight() / 2 + 0);
		stage.addActor(mp);

		exp = new TextButton("Exp : " + current.getExp() + " / 100", style);
		exp.setWidth(100);
		exp.setHeight(150);
		exp.setX(Gdx.graphics.getWidth() / 2 - exp.getWidth() / 2 - 300);
		exp.setY(Gdx.graphics.getHeight() / 2 - exp.getHeight() / 2 - 100);
		stage.addActor(exp);

		atk = new TextButton("Atk : " + current.getAtkPhysic(), style);
		atk.setWidth(100);
		atk.setHeight(150);
		atk.setX(Gdx.graphics.getWidth() / 2 - atk.getWidth() / 2 + 300);
		atk.setY(Gdx.graphics.getHeight() / 2 - atk.getHeight() / 2 + 150);
		stage.addActor(atk);

		matk = new TextButton("Matk : " + current.getAtkMagic(), style);
		matk.setWidth(100);
		matk.setHeight(150);
		matk.setX(Gdx.graphics.getWidth() / 2 - matk.getWidth() / 2 + 300);
		matk.setY(Gdx.graphics.getHeight() / 2 - matk.getHeight() / 2 + 50);
		stage.addActor(matk);

		def = new TextButton("Defend : " + current.getDefendPhysic() + "%",
				style);
		def.setWidth(100);
		def.setHeight(150);
		def.setX(Gdx.graphics.getWidth() / 2 - def.getWidth() / 2 + 300);
		def.setY(Gdx.graphics.getHeight() / 2 - def.getHeight() / 2 - 50);
		stage.addActor(def);

		mdef = new TextButton("MaDefend : " + current.getDefendMagic() + "%",
				style);
		mdef.setWidth(100);
		mdef.setHeight(150);
		mdef.setX(Gdx.graphics.getWidth() / 2 - mdef.getWidth() / 2 + 300);
		mdef.setY(Gdx.graphics.getHeight() / 2 - mdef.getHeight() / 2 - 150);
		stage.addActor(mdef);
		int i = 0;
		for (Skills ski : current.getSkillsAvalable()) {

			TextButton skil = new TextButton(ski.getName() + " = "
					+ ski.getPercentAtk() + "% " + ski.getSkillType(), style);
			skil.setWidth(100);
			skil.setHeight(100);
			skil.setX(Gdx.graphics.getWidth() / 2 - skil.getWidth() / 2 - 150
					+ i);
			skil.setY(Gdx.graphics.getHeight() / 2 - skil.getHeight() / 2 - 250);
			stage.addActor(skil);
			i += 200;
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

		current.drawMonsterManage(batch, 300, 150, stateTime);
		current.drawMonsterEnemy(batch, 300, 200, stateTime);
		current.drawMonsterMine(batch, 300, 250, stateTime);

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
		// TODO Auto-generated method stub

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
	
	private void addSkin() {
		skin = new Skin() ;
		skin.add("pressedButton", new Texture(Gdx.files.internal("data/pressedButton.png")));
		skin.add("unpressedButton", new Texture(Gdx.files.internal("data/unpressedButton.png")));
		skin.add("consoleButtonFont",
				new BitmapFont(Gdx.files.internal("data/menuFont.fnt"), false));
	}

}
