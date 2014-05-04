package com.me.mygdxgame.Model;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.SoundManager;

public class MapGameUpdate {
	Model model = Model.getModel();
	MapGame mapGame;
	SpriteBatch batch;
	Player player;
	
	OrthographicCamera camera;
	MusicManager musicManager;
	SoundManager soundManager;
	Texture playerTexture, mapTexture, bg1 , bg2 ,bg3;
	float width, height;
	Vector2 check = new Vector2();
	float stateTime;
	ShapeRenderer sr = new ShapeRenderer();
	ShapeRenderer debugreder = new ShapeRenderer();
	com.me.mygdxgame.MyGdxGame gdxgame;
	Screen gs;
	BitmapFont bmf = new BitmapFont();
	
	
	
	public MapGameUpdate(MapGame game, com.me.mygdxgame.MyGdxGame ga, Screen gs, MusicManager musicManager, SoundManager soundManager) {
		this.mapGame = game;
		this.gdxgame = ga;
		this.musicManager = musicManager;
		this.soundManager = soundManager;
		this.gs = gs;
		width = Gdx.graphics.getWidth() / 3;
		height = Gdx.graphics.getHeight() / 3;

		player = model.getPlayer();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		sr.setColor(Color.BLACK);
		
		
		
	}

	public void render() {
		// clear renderer
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// get player
		
		// set camera follow the player
		camera.position.set(player.getVec2().x, player.getVec2().y, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	
		
		bmf.setColor(Color.BLACK);
		
		if (model.getNumberStage() == 1) {
			if(player.getBounds().overlaps(new Rectangle(445,722,100,100))){
				for (Monster mos : model.getPlayerMonsters()) {
					mos.prepareBattle();
				}
				for (Monster mos : model.getBattlePlayerMonster()) {
					mos.prepareBattle();
				}
			}
			
			if(player.getBounds().overlaps(new Rectangle(27*40,26*40,30,30))){
				ArrayList<Monster> monl = new ArrayList<Monster>();
				monl.add(model.findMonsterByName("KaraHan"));
				gdxgame.setScreen(new BattleScreen(gdxgame, gs, monl, musicManager, soundManager));
				model.setNumberStage(2);
				player.setVec2(new Vector2(508, 856));
			}
			
			
			for(StandObject so : model.getStandObjectList()) {
				so.drawObject(batch);
				so.canNotMove(player);
			}

			for (Player play : model.getListNPC()) {
				play.autoMoveNPC(1);
				play.drawPlayer(batch);
				play.update();
				play.cannotMove(player);
				
			}
			
			for(BoundsObject so : model.getListBoud()) {
				so.drawObjectTest(camera);
				so.canNotMove(player);
			}
			
			for (BattleArea ba : model.getListBattleAre()) {
				ba.activeArea(player, gdxgame, gs, musicManager, soundManager);
			}
		} else if (model.getNumberStage() == 2) {
			
			if(player.getBounds().overlaps(new Rectangle(864, 223,30,30))){
				model.setNumberStage(3);
				ArrayList<Monster> monl = new ArrayList<Monster>();
				monl.add(model.findMonsterByName("Bahamut"));
				gdxgame.setScreen(new BattleScreen(gdxgame, gs, monl, musicManager, soundManager));
				
				
				player.setVec2(new Vector2(484, 460));
			}
			
			if(player.getBounds().overlaps(new Rectangle(485,885,100,100))){
				for (Monster mos : model.getPlayerMonsters()) {
					mos.prepareBattle();
				}
				for (Monster mos : model.getBattlePlayerMonster()) {
					mos.prepareBattle();
				}
			}
			
			
			for(StandObject so : model.getStandObjectList2()) {
				so.drawObject(batch);
				so.canNotMove(player);
			}

			for (Player play : model.getListNPC2()) {
				play.autoMoveNPC(1);
				play.drawPlayer(batch);
				play.update();
				play.cannotMove(player);
				
			}
			
			
			for(BoundsObject so : model.getListBoud2()) {
				so.drawObjectTest(camera);
				so.canNotMove(player);
			}
			
			for (BattleArea ba : model.getListBattleAre2()) {
				ba.activeArea(player, gdxgame, gs, musicManager, soundManager);
			}
		} else if (model.getNumberStage() == 3) {
			
			if(player.getBounds().overlaps(new Rectangle(482,481,30,30))){
				for (Monster mos : model.getPlayerMonsters()) {
					mos.prepareBattle();
				}
				for (Monster mos : model.getBattlePlayerMonster()) {
					mos.prepareBattle();
				}
			}
			
			for(StandObject so : model.getStandObjectList3()) {
				so.drawObject(batch);
				so.canNotMove(player);
			}

			for (Player play : model.getListNPC3()) {
				play.autoMoveNPC(1);
				play.drawPlayer(batch);
				play.update();
				play.cannotMove(player);
				
			}
			
			for(BoundsObject so : model.getListBoud3()) {
				so.drawObjectTest(camera);
				so.canNotMove(player);
			}
			
			for (BattleArea ba : model.getListBattleAre3()) {
				ba.activeArea(player, gdxgame, gs, musicManager, soundManager);
			}
		}
		
		player.drawPlayer(batch);
		
				
	}

	public void dispose() {
		batch.dispose();
		playerTexture.dispose();
		mapGame.dispose();
	}

}
