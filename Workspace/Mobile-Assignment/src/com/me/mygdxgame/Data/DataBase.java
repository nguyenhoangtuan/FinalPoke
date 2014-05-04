package com.me.mygdxgame.Data;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.Model.Model;
import com.me.mygdxgame.Model.Monster;
import com.me.mygdxgame.Model.Skills;

@SuppressWarnings("unused")
public class DataBase {
	public Preferences pref_1;
    public Preferences pref_2;
    
    Model model = Model.getModel();
    
    public DataBase() {
        pref_1 = Gdx.app.getPreferences("male-identity"); 
        pref_2 = Gdx.app.getPreferences("female-identity");
    }
    
    public void saveAlldata() {
    	int i = 1;
    	pref_1.putInteger("Size of monster battle",  model.getBattlePlayerMonster().size() );
    	for (Monster m : model.getBattlePlayerMonster()) {
    		pref_1.putString("Player Monster Battle " + i, m.toString());
    		i++;
    	}
    	
    	i = 1;
    	pref_2.putInteger("Size of monster pack", model.getPlayerMonsters().size());
    	for (Monster m : model.getPlayerMonsters()) {
    		pref_2.putString("Player Monster Pack " + i, m.toString());
    		i++;
    	}
    	
    	pref_1.putFloat("location x", model.getPlayer().getVec2().x);

    	pref_1.putFloat("location y", model.getPlayer().getVec2().y);
    	
    	pref_1.putInteger("stage", model.getNumberStage());
    	
    	pref_1.putInteger("max monster", model.getMaxNumberOfMonsterInBattle());
    	
    	
    	pref_1.putFloat("score", model.getScoreTotal());
    	
    	pref_2.flush();
    	pref_1.flush();
    }
    
    public void loadAlldata() {
    	int size = pref_1.getInteger("Size of monster battle");
    	System.out.println(size);
    	System.out.println("check");
    	for (int i = 1; i <= size; i++) {
    		String strMons = pref_1.getString("Player Monster Battle " + i);
    		System.out.println(strMons);
    		Monster m = createMonster(strMons);
    		model.addMonsterToBattle(m);
    	}
    	
    	size = pref_2.getInteger("Size of monster pack");
    	for (int i = 1; i <= size; i++) {
    		String strMons = pref_2.getString("Player Monster Pack " + i);
    		System.out.println(strMons);
    		Monster m = createMonster(strMons);
    		model.addMosterToPlayerMonster(m);
    	}
    	
    	model.getPlayer().setVec2(new Vector2(pref_1.getFloat("location x") , pref_1.getFloat("location y")) );
    	model.setNumberStage(pref_1.getInteger("stage"));
    	
    	model.setMaxNumberOfMonsterInBattle(pref_1.getInteger("max monster"));
    	
    	model.setScoreTotal(pref_1.getFloat("score"));
    }
    
    public Monster createMonster(String code) {
    	String tmp[] = code.split("<->");
    	String name = tmp[0];
    	int hp = Integer.parseInt(tmp[1]);
    	int mp = Integer.parseInt(tmp[2]);
    	int level = Integer.parseInt(tmp[3]);
    	int defendMa = Integer.parseInt(tmp[4]);
    	int defendPy = Integer.parseInt(tmp[5]);
    	int atkPhy = Integer.parseInt(tmp[6]);
    	int atkMag = Integer.parseInt(tmp[7]);
    	
//    	int defendMa = Integer.parseInt(tmp[1]);
    	
    	String element = tmp[8];
    	int exp = Integer.parseInt(tmp[9]);
    	int curHp = Integer.parseInt(tmp[10]);
    	int curMp = Integer.parseInt(tmp[11]);
    	String fileName = tmp[12];
    	String fileAnimation = tmp[13];
    	boolean die = Boolean.parseBoolean(tmp[14]);
    	int numberSkill = Integer.parseInt(tmp[15]);
    	ArrayList<Skills> skilslist = new ArrayList<Skills>();
    	for (int i = 0; i < numberSkill ; i++) {
    		skilslist.add(model.findSkillByName(tmp[16 + i]));
    	}
    	Monster mo = new Monster(name, hp, mp, level, defendMa, defendPy, atkPhy,
    			atkMag, element, skilslist, 10, skilslist, fileName, fileAnimation);
    	mo.setExp(exp);
    	mo.setCurrentHP(curHp);
    	mo.setCurrentMP(curMp);
    	mo.setDie(die);
    	
    	
    	return mo;
    }
    
    
    
}
