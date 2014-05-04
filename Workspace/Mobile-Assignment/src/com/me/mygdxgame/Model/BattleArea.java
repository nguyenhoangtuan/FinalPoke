package com.me.mygdxgame.Model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.MusicManager;
import com.me.mygdxgame.Manager.MusicManager.MyMusic;
import com.me.mygdxgame.Manager.SoundManager;

public class BattleArea extends ObjectGame {
	private int maxEnemy;
	private int step;
	private Vector2 check = new Vector2(0, 0);
	private int levelMonster;
	private ArrayList<Monster> monsterEnemy = new ArrayList<Monster>();
	private MusicManager musicManager;
	private SoundManager soundManager;
	ArrayList<String> name = new ArrayList<String>();
	
	public BattleArea(Vector2 vec2, float width, float height,
			ArrayList<String> name, int maxEnemy, int stepToMeetMonster , int levelMonster) {
		
		super(vec2, width, height);
		this.levelMonster = levelMonster;
		if (maxEnemy > 4) {
			this.maxEnemy = 4;
		} else {
			this.maxEnemy = maxEnemy;
		}
		this.step = stepToMeetMonster;
		this.name = name;

	}

	public void activeArea(Player player, MyGdxGame gdxgame, Screen screen, MusicManager musicManager, SoundManager soundManager) {
		this.setMusicManager(musicManager);
		this.setSoundManager(soundManager);
		
		if (player.getBounds().overlaps(this.getBounds())) {
			if (player.getVelocitySpeed().x == -1) {
				check.x -= player.getVelocitySpeed().x;
			}
			if (player.getVelocitySpeed().x == 1) {
				check.x += player.getVelocitySpeed().x;
			}
			if (player.getVelocitySpeed().y == -1) {
				check.y -= player.getVelocitySpeed().y;
			}
			if (player.getVelocitySpeed().y == 1) {
				check.y += player.getVelocitySpeed().y;
			}

			if (check.x == step || check.y == step) {

				if (gdxgame != null) {
					check.x = 0;
					check.y = 0;
					gdxgame.setScreen(new BattleScreen(gdxgame, screen, chooseRandomMonster(), musicManager, soundManager));
					musicManager.play(MyMusic.BATTLE);
				}

			}
		}

	}

	public ArrayList<Monster> chooseRandomMonster() {
		Model model = Model.getModel();
		monsterEnemy.removeAll(monsterEnemy);
		
		Random rand = new Random();
		ArrayList<Integer> array = new ArrayList<Integer>();
		int ranMax = rand.nextInt(maxEnemy - 1 + 1 ) + 1;
		for (int i = 0; i < ranMax; i++) {
			int randomNumber = rand.nextInt(name.size()-1 - 0 + 1) + 0;
			array.add(randomNumber);
		}
		
		for (Integer inte : array) {
			try {
				System.out.println(name.get(inte));
				System.out.println(model.findMonsterByName(name.get(inte)).getName());
				Monster m = (Monster) (model.findMonsterByName(name.get(inte)).clone());
				m.setLevelForMonster(levelMonster);
				monsterEnemy.add(m);
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Size : " +monsterEnemy.size());

		return monsterEnemy;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}

	public void setMusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

}
