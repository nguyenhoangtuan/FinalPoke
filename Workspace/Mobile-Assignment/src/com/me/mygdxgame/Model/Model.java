package com.me.mygdxgame.Model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.MyGdxGame;

public final class Model {
	
	private static Model model = new Model();
	
	ArrayList<Monster> allMonster = new ArrayList<Monster>();
	ArrayList<Monster> playerMonsters = new ArrayList<Monster>();
	ArrayList<Monster> battlePlayerMonster = new ArrayList<Monster>();
	
	ArrayList<Skills> listSkills = new ArrayList<Skills>();
	
	ArrayList<BoundsObject> listBoud = new ArrayList<BoundsObject>();
	ArrayList<StandObject> standObjectList = new ArrayList<StandObject>();
	ArrayList<BattleArea> listBattleAre = new ArrayList<BattleArea>();
	ArrayList<Player> listNPC = new ArrayList<Player>();
	
	ArrayList<BoundsObject> listBoud2 = new ArrayList<BoundsObject>();
	ArrayList<StandObject> standObjectList2 = new ArrayList<StandObject>();
	ArrayList<BattleArea> listBattleAre2 = new ArrayList<BattleArea>();
	ArrayList<Player> listNPC2 = new ArrayList<Player>();
	
	ArrayList<BoundsObject> listBoud3 = new ArrayList<BoundsObject>();
	ArrayList<StandObject> standObjectList3 = new ArrayList<StandObject>();
	ArrayList<BattleArea> listBattleAre3 = new ArrayList<BattleArea>();
	ArrayList<Player> listNPC3 = new ArrayList<Player>();
	
	private Player mainPlayer;
	private float scoreTotal = 0;
	private int maxNumberOfMonsterInBattle = 2;
	private int numberStage = 1;
	
	public Model() {
		mainPlayer = new Player("Cloud", 40f, MyGdxGame.DEFAULT_FRAME_SPEED, 0, 15, 15, new Vector2(225,900),
				"data/mainCharacterFrontSide.png","data/mainCharacterAnimation.png");
		
		Player npc = new Player("Tuan",40f, MyGdxGame.DEFAULT_FRAME_SPEED, 0, 15, 15, new Vector2( 300, 150 ), 
				"data/player3.png","data/animation.png");
		addNpcToNpcList(npc);
		
		
		// create map : 
	

		StandObject map = new StandObject(new Vector2(0 * 40, 0 * 40), 1200,
				1200, "data/Map/sands.jpg", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(0 * 40, 0 * 40), 66, 1200,
				"data/Map/doctraimap.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(67, 0 * 40), 1071, 145,
				"data/Map/ngangduoimap.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(1134, 0), 66, 661,
				"data/Map/docphaimap1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(735, 145), 400, 54,
				"data/Map/duoimo.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(120, 300), 266, 155,
				"data/Map/ba1.png", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(820, 825), 266, 155,
				"data/Map/ba1.png", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(66, 1006), 67, 194,
				"data/Map/trenriu.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(133, 1102), 495, 98,
				"data/Map/trenlang.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(628, 1135), 573, 65,
				"data/Map/ngangtrenmap1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(1168, 659), 32, 540,
				"data/Map/docphaimap2.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(396, 981), 233, 125,
				"data/Map/gocphailang.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(479, 241), 288, 448,
				"data/Map/duongdi.png", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(561, 659), 68, 322,
				"data/Map/phailang.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(225, 975), 80, 72,
				"data/Map/house1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(429, 875), 96, 56,
				"data/Map/house2.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(104, 788), 228, 116,
				"data/Map/ho.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(66, 655), 364, 61,
				"data/Map/duoilang.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(801, 239), 211, 103,
				"data/Map/mo.png", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(901, 551), 234, 108,
				"data/Map/trenmo.png", false);
		addStandObjectToList(map);

		// small things
		map = new StandObject(new Vector2(27 * 40, 26 * 40), 28, 50,
				"data/Map/portal1.png", true);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(3 * 40, 24 * 40), 20, 20,
				"data/Map/riu.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(24 * 40, 11 * 40), 64, 58,
				"data/Map/rom1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(5 * 40, 13 * 40), 29, 31,
				"data/Map/xuong1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(8 * 40, 5 * 40), 29, 31,
				"data/Map/xuong1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(18 * 40, 11 * 40), 29, 31,
				"data/Map/xuong1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(17 * 40, 26 * 40), 29, 31,
				"data/Map/xuong1.png", false);
		addStandObjectToList(map);
		map = new StandObject(new Vector2(27 * 40, 19 * 40), 29, 31,
				"data/Map/xuong1.png", false);
		addStandObjectToList(map);

		// Map 2
		StandObject map2 = new StandObject(new Vector2(0 * 40, 0 * 40), 1024,
				1024, "data/Map/map2.png", true);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(0 * 40, 0 * 40), 226, 1024,
				"data/Map/doctraimap2.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(227, 0), 154, 67,
				"data/Map/ngangduoimap2a.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(381, 0), 644, 194,
				"data/Map/ngangduoimap2b.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(227, 865), 47, 159,
				"data/Map/ngangtrenmap2a.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(273, 639), 176, 385,
				"data/Map/ngangtrenmap2b.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(449, 894), 576, 130,
				"data/Map/ngangtrenmap2c.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(591, 862), 434, 32,
				"data/Map/docphaimap2a.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(846, 705), 178, 159,
				"data/Map/docphaimap2b.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(576, 543), 449, 162,
				"data/Map/docphaimap2c.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(776, 418), 249, 125,
				"data/Map/docphaimap2d.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(591, 271), 434, 147,
				"data/Map/docphaimap2e.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(912, 193), 113, 78,
				"data/Map/docphaimap2f.png", false);
		standObjectList2.add(map2);

		// small things map2
		map2 = new StandObject(new Vector2(330, 405), 32, 40,
				"data/Map/diamond.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(497, 684), 80, 80,
				"data/Map/rock1.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(721, 752), 96, 80,
				"data/Map/rock2.png", false);
		standObjectList2.add(map2);
		map2 = new StandObject(new Vector2(353, 319), 177, 161,
				"data/Map/rock3.png", false);
		standObjectList2.add(map2);

		// Map3
		StandObject map3 = new StandObject(new Vector2(0 * 40, 0 * 40), 672,
				560, "data/Map/map3.png", true);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(0, 480), 672, 80,
				"data/Map/ngangtrenmap3.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(512, 433), 160, 48,
				"data/Map/docphaimap3a.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(481, 368), 192, 64,
				"data/Map/docphaimap3b.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(577, 304), 96, 64,
				"data/Map/docphaimap3c.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(497, 0), 176, 304,
				"data/Map/docphaimap3d.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(352, 192), 144, 64,
				"data/Map/giuamap3.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(367, 0), 128, 192,
				"data/Map/ngangduoimap3b.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(0, 0), 368, 112,
				"data/Map/ngangduoimap3a.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(0, 113), 320, 80,
				"data/Map/doctraimap3a.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(0, 193), 288, 176,
				"data/Map/doctraimap3b.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(0, 369), 320, 112,
				"data/Map/doctraimap3c.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(321, 416), 80, 64,
				"data/Map/ngangtrenmap3a.png", false);
		standObjectList3.add(map3);
		map3 = new StandObject(new Vector2(401, 448), 32, 32,
				"data/Map/ngangtrenmap3b.png", false);
		standObjectList3.add(map3);
		
		
		Skills normalattack =  new Skills("normal attack","data/normalskill.png", "normal", 100, "none", 4, 4, 0.2f, "enemy");
		addSkilsToSkillList(normalattack);
		
		Skills normalattack1 =  new Skills("Ice Claw","data/Skills/ice_claw.png", "atk", 150, "none", 5, 3, 0.3f, "enemy");
		addSkilsToSkillList(normalattack1);
		
		Skills normal1 =  new Skills("Ice Claw 1","data/Skills/ice_claw.png", "atk", 220, "none", 5, 3, 0.3f, "enemy");
		addSkilsToSkillList(normal1);
		
		Skills normalattack2 =  new Skills("Fire Claw","data/Skills/fire_claw.png", "atk", 160, "none", 5, 3, 0.3f, "enemy");
		addSkilsToSkillList(normalattack2);
		
		Skills normalattack2a =  new Skills("Fire Claw 2","data/Skills/fire_claw.png", "atk", 230, "none", 5, 3, 0.3f, "enemy");
		addSkilsToSkillList(normalattack2a);
		
		Skills normalattack3 =  new Skills("Ring Fire 1","data/Skills/fire1.png", "matk", 130, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack3);
		
		Skills normalattack3a =  new Skills("Ring Fire 2","data/Skills/fire1.png", "matk", 200, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack3a);
		
		Skills normalattack4 =  new Skills("Ice Shure","data/Skills/ice_phi_tieu.png", "atk", 140, "none", 5, 4, 0.3f, "enemy");
		addSkilsToSkillList(normalattack4);
		
		Skills normalattack4a =  new Skills("Ice Shure 2","data/Skills/ice_phi_tieu.png", "atk", 240, "none", 5, 4, 0.3f, "enemy");
		addSkilsToSkillList(normalattack4a);
		
		Skills normalattack5 =  new Skills("Ring Ice 1","data/Skills/ice1.png", "matk", 150, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack5);
		
		Skills normalattack5a =  new Skills("Ring Ice 2","data/Skills/ice1.png", "matk", 250, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack5a);
		
		Skills normalattack6 =  new Skills("Light 1","data/Skills/light1.png", "matk", 120, "none", 5, 1, 0.3f, "enemy");
		addSkilsToSkillList(normalattack6);
		
		Skills normalattack7 =  new Skills("Light 2","data/Skills/light2.png", "matk", 180, "none", 5, 1, 0.3f, "enemy");
		addSkilsToSkillList(normalattack7);
		
		Skills normalattack8 =  new Skills("Thunder 1","data/Skills/lighting1.png", "matk", 140, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack8);
		
		Skills normalattack8a =  new Skills("Thunder 2","data/Skills/lighting1.png", "matk", 240, "none", 5, 2, 0.3f, "enemy");
		addSkilsToSkillList(normalattack8a);
		
		Skills normalattack9 =  new Skills("Wind 1","data/Skills/wind1.png", "matk", 190, "none", 5, 6, 0.1f, "enemy");
		addSkilsToSkillList(normalattack9);
		
		Skills normalattack9a =  new Skills("Wind 2","data/Skills/wind1.png", "matk", 290, "none", 5, 6, 0.1f, "enemy");
		addSkilsToSkillList(normalattack9a);
		
		
		Skills normalattack10 =  new Skills("Heal 1","data/Skills/heal_1.png", "matk", 100, "none", 5, 6, 0.1f, "team");
		addSkilsToSkillList(normalattack10);
		
		Skills normalattack101 =  new Skills("Heal 2","data/Skills/heal_1.png", "matk", 150, "none", 5, 6, 0.1f, "team");
		addSkilsToSkillList(normalattack101);
		
		Skills normalattack102 =  new Skills("Heal 3","data/Skills/heal_1.png", "matk", 250, "none", 5, 6, 0.1f, "team");
		addSkilsToSkillList(normalattack102);
		
		
		
		Skills normalattack11 =  new Skills("Thunder Aoe 1","data/Skills/lighting1.png", "matk", 90, "none", 5, 6, 0.1f, "aoe_enemy");
		normalattack11.setNumberOfTarget(2);
		addSkilsToSkillList(normalattack11);
		
		Skills normalattack111 =  new Skills("Thunder Aoe 2","data/Skills/lighting1.png", "matk", 90, "none", 5, 6, 0.1f, "aoe_enemy");
		normalattack111.setNumberOfTarget(3);
		addSkilsToSkillList(normalattack111);
		
		
		Skills normalattack12 =  new Skills("Thunder Big 1","data/Skills/lighting1.png", "matk", 90, "none", 5, 6, 0.1f, "aoe_enemy");
		normalattack12.setNumberOfTarget(2);
		normalattack12.setScaleSkill(1);
		addSkilsToSkillList(normalattack12);
		
		Skills normalattack121 =  new Skills("Thunder Big 2","data/Skills/lighting1.png", "matk", 190, "none", 5, 6, 0.1f, "aoe_enemy");
		normalattack121.setNumberOfTarget(3);
		normalattack121.setScaleSkill(1);
		addSkilsToSkillList(normalattack121);
		
		
		Skills normalattack13 =  new Skills("Ice Aoe 1","data/Skills/ice1.png", "matk", 100, "none", 5, 2, 0.3f, "aoe_enemy");
		normalattack13.setNumberOfTarget(2);
		addSkilsToSkillList(normalattack13);
		
		Skills normalattack131 =  new Skills("Ice Aoe 2","data/Skills/ice1.png", "matk", 140, "none", 5, 2, 0.3f, "aoe_enemy");
		normalattack131.setNumberOfTarget(3);
		addSkilsToSkillList(normalattack131);
		
		
		Skills normalattack14 =  new Skills("Shuriken Aoe 1","data/Skills/ice_phi_tieu.png", "matk", 110, "none", 5, 4, 0.3f, "aoe_enemy");
		normalattack14.setNumberOfTarget(2);
		addSkilsToSkillList(normalattack14);
		
		Skills normalattack141 =  new Skills("Shuriken Aoe 2","data/Skills/ice_phi_tieu.png", "matk", 150, "none", 5, 4, 0.3f, "aoe_enemy");
		normalattack141.setNumberOfTarget(3);
		addSkilsToSkillList(normalattack141);
		
		
		
		ArrayList<Skills> pokemon = new ArrayList<Skills>();

		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Fire Claw"));
		
		Monster mons = new Monster("Pokemon", 80, 10, 1, 15,15, 40,
				60, "water", pokemon, 10, null
				, "data/monster1.png",
				"data/animation.png");
		mons.setExpGain(60);
		addMonsterToAllMonsterList(mons);
		
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Ice 1"));
		pokemon.add(findSkillByName("Heal 1"));
		Monster mons1 = new Monster("Slime Blue", 100, 10, 1, 5,40, 60,
				60, "water", pokemon, 10, null, "data/MonsterImage/slime1_monster.png",
				"data/MonsterImage/slime1_monster.png");
		mons1.setExpGain(30);
		addMonsterToAllMonsterList(mons1);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Heal 1"));
		Monster mons2 = new Monster("Slime Green", 100, 10, 1, 15,18, 50,
				60, "nature", pokemon, 10, null, "data/MonsterImage/slime2_monster.png",
				"data/MonsterImage/slime2_monster.png");
		mons2.setExpGain(40);
		addMonsterToAllMonsterList(mons2);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Wind 1"));
		pokemon.add(findSkillByName("Thunder 1"));
		Monster mons3 = new Monster("Mushroom", 120, 10, 1, 15,16, 80,
				50, "rock", pokemon, 10, null, "data/MonsterImage/mushroom1_monster.png",
				"data/MonsterImage/mushroom1_monster.png");
		mons3.setExpGain(40);
		addMonsterToAllMonsterList(mons3);
		
		// area 2: 
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Wind 1"));
		pokemon.add(findSkillByName("Thunder 1"));
		Monster monsn1 = new Monster("Bat", 150, 10, 1, 20,20, 100,
				90, "rock", pokemon, 10, null, "data/MonsterImage/bat1.png",
				"data/MonsterImage/bat1.png");
		monsn1.setExpGain(60);
		addMonsterToAllMonsterList(monsn1);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Wind 1"));
		pokemon.add(findSkillByName("Thunder 1"));
		Monster monsn2 = new Monster("Dark Ghost", 145, 10, 1, 15,16, 100,
				100, "rock", pokemon, 10, null, "data/MonsterImage/darkghost1.png",
				"data/MonsterImage/darkghost1.png");
		monsn2.setExpGain(50);
		addMonsterToAllMonsterList(monsn2);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Thunder 1"));
		pokemon.add(findSkillByName("Fire Claw"));
		Monster monsn3 = new Monster("Dark Skull", 145, 10, 1, 20,20, 90,
				100, "rock", pokemon, 10, null, "data/MonsterImage/darkghost2.png",
				"data/MonsterImage/darkghost2.png");
		monsn3.setExpGain(60);
		addMonsterToAllMonsterList(monsn3);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ice Shure"));
		pokemon.add(findSkillByName("Fire Claw"));
		Monster monsn4 = new Monster("Dark Knight", 120, 10, 1, 30,30, 120,
				60, "rock", pokemon, 10, null, "data/MonsterImage/darkknight1.png",
				"data/MonsterImage/darkknight1.png");
		monsn4.setExpGain(40);
		addMonsterToAllMonsterList(monsn4);
		
		
		// area 3:
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Wind 1"));
		pokemon.add(findSkillByName("Fire Claw 2"));
		Monster monsn5 = new Monster("Dark Knight", 180, 10, 1, 20,20, 140,
				90, "fire", pokemon, 10, null, "data/MonsterImage/darkknight2.png",
				"data/MonsterImage/darkknight2.png");
		monsn5.setExpGain(50);
		addMonsterToAllMonsterList(monsn5);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Fire 1"));
		pokemon.add(findSkillByName("Fire Claw 2"));
		Monster monsn6 = new Monster("Eye Float", 160, 20, 1, 20,20, 140,
				90, "fire", pokemon, 10, null, "data/MonsterImage/eye1.png",
				"data/MonsterImage/eye1.png");
		monsn6.setExpGain(50);
		addMonsterToAllMonsterList(monsn6);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ice Claw 1"));
		pokemon.add(findSkillByName("Thunder Aoe 1"));
		pokemon.add(findSkillByName("Heal 2"));
		Monster monsn7 = new Monster("Flame Float", 200, 40, 1, 20,20, 150,
				140, "fire", pokemon, 10, null, "data/MonsterImage/firemonster.png",
				"data/MonsterImage/firemonster.png");
		monsn7.setExpGain(50);
		addMonsterToAllMonsterList(monsn7);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Light 2"));
		pokemon.add(findSkillByName("Heal 2"));
		Monster monsn8 = new Monster("Solider", 180, 40, 1, 20,20, 120,
				130, "water", pokemon, 10, null, "data/MonsterImage/flagsolidier.png",
				"data/MonsterImage/flagsolidier.png");
		monsn8.setExpGain(50);
		addMonsterToAllMonsterList(monsn8);
		
		// aera 4 
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Fire 2"));
		pokemon.add(findSkillByName("Heal 2"));
		Monster monsn9 = new Monster("Red Flower", 220, 40, 1, 25,25, 80,
				150, "water", pokemon, 10, null, "data/MonsterImage/flower1.png",
				"data/MonsterImage/flower1.png");
		monsn9.setExpGain(50);
		addMonsterToAllMonsterList(monsn9);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Fire 2"));
		pokemon.add(findSkillByName("Ice Aoe 1"));
		pokemon.add(findSkillByName("Ice Claw 1"));
		pokemon.add(findSkillByName("Fire Claw 1"));
		Monster monsn10 = new Monster("BLue Flower", 220, 40, 1, 25,25, 80,
				150, "water", pokemon, 10, null, "data/MonsterImage/flower2.png",
				"data/MonsterImage/flower2.png");
		monsn10.setExpGain(50);
		addMonsterToAllMonsterList(monsn10);
		
		pokemon = new ArrayList<Skills>();
//		pokemon.add(findSkillByName("Ring Fire 2"));
//		pokemon.add(findSkillByName("Ice Aoe 1"));
		pokemon.add(findSkillByName("Ice Claw 1"));
		pokemon.add(findSkillByName("Fire Claw 1"));
		Monster monsn11 = new Monster("Ghost Water", 260, 40, 1, 25,25, 170,
				120, "water", pokemon, 10, null, "data/MonsterImage/ghost1.png",
				"data/MonsterImage/ghost1.png");
		monsn11.setExpGain(50);
		addMonsterToAllMonsterList(monsn11);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Fire 2"));
		pokemon.add(findSkillByName("Ice Aoe 2"));
		pokemon.add(findSkillByName("Ice Claw 1"));
		pokemon.add(findSkillByName("Fire Claw 1"));
		Monster monsn12 = new Monster("Girl Flower", 220, 40, 1, 25,25, 80,
				150, "water", pokemon, 10, null, "data/MonsterImage/girlmonster1.png",
				"data/MonsterImage/girlmonster1.png");
		monsn12.setExpGain(50);
		addMonsterToAllMonsterList(monsn12);
		
		// area 5
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Fire 2"));
		pokemon.add(findSkillByName("Thunder 2"));
		pokemon.add(findSkillByName("Heal 3"));
		
		Monster monsn13 = new Monster("BLue Girl", 300, 40, 1, 10,10, 180,
				180, "water", pokemon, 10, null, "data/MonsterImage/girlmonster2.png",
				"data/MonsterImage/girlmonster2.png");
		monsn13.setExpGain(50);
		addMonsterToAllMonsterList(monsn13);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Wind 2"));
		pokemon.add(findSkillByName("Thunder 2"));
		pokemon.add(findSkillByName("Shuriken Aoe 2"));
		
		Monster monsn14 = new Monster("Night Knight", 320, 40, 1, 20,20, 200,
				200, "nature", pokemon, 10, null, "data/MonsterImage/knight1.png",
				"data/MonsterImage/knight1.png");
		monsn14.setExpGain(50);
		addMonsterToAllMonsterList(monsn14);
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Thunder Big 2"));
		pokemon.add(findSkillByName("Thunder 2"));
		pokemon.add(findSkillByName("Shuriken Aoe 2"));
		
		Monster monsn15 = new Monster("Night Night", 320, 40, 1, 25,25, 200,
				150, "nature", pokemon, 10, null, "data/MonsterImage/knight2.png",
				"data/MonsterImage/knight2.png");
		monsn15.setExpGain(50);
		addMonsterToAllMonsterList(monsn15);
		
		
		
		
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Ice 2"));
		pokemon.add(findSkillByName("Ice Aoe 2"));
		pokemon.add(findSkillByName("Heal 3"));
		
		Monster monsn17 = new Monster("Rat", 400, 40, 1, 25,25, 220,
				170, "nature", pokemon, 10, null, "data/MonsterImage/rat1.png",
				"data/MonsterImage/rat1.png");
		monsn17.setExpGain(50);
		addMonsterToAllMonsterList(monsn17);
		
		
		
		
		
		// are 6
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Heal 2"));
		pokemon.add(findSkillByName("Ice Aoe 2"));
		pokemon.add(findSkillByName("Heal 3"));
		
		Monster monsn19 = new Monster("Rat", 400, 40, 1, 25,25, 220,
				170, "nature", pokemon, 10, null, "data/MonsterImage/rat1.png",
				"data/MonsterImage/rat1.png");
		monsn19.setExpGain(50);
		addMonsterToAllMonsterList(monsn19);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ring Ice 2"));
		pokemon.add(findSkillByName("Ice Aoe 2"));
		pokemon.add(findSkillByName("Shuriken Aoe 2"));
		
		Monster monsn18 = new Monster("Spider", 400, 40, 1, 30,30, 250,
				250, "nature", pokemon, 10, null, "data/MonsterImage/scorpion2.png",
				"data/MonsterImage/scorpion2.png");
		monsn18.setExpGain(50);
		addMonsterToAllMonsterList(monsn18);
		
		// boss:
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Thunder Aoe 1"));
		Monster mons4 = new Monster("KaraHan", 600, 10, 1, 20,20, 200,
				200, "rock", pokemon, 10, pokemon, "data/MonsterImage/boss2.png",
				"data/MonsterImage/boss2.png");
		mons4.setExpGain(100);
		mons4.setBoss(true);
		mons4.setCatchAble(false);
		addMonsterToAllMonsterList(mons4);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Ice Aoe 1"));
		Monster mons5 = new Monster("Bahamut", 12500, 100, 1, 20,20, 1500,
				1500, "rock", pokemon, 10, pokemon, "data/MonsterImage/boss3.png",
				"data/MonsterImage/boss3.png");
		mons5.setExpGain(100);
		mons5.setBoss(true);
		mons5.setCatchAble(false);
		addMonsterToAllMonsterList(mons5);
		
		pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Shuriken Aoe 2"));
		Monster mons6 = new Monster("Lava", 100500, 100, 1, 30,30, 3000,
				2400, "rock", pokemon, 10, pokemon, "data/MonsterImage/lava.png",
				"data/MonsterImage/lava.png");
		mons6.setExpGain(400);
		mons6.setBoss(true);                                         
		mons6.setCatchAble(false);
		addMonsterToAllMonsterList(mons6);
		
		
		
		// create battle area :
		ArrayList<String> name  = new ArrayList<String>();
		name.add("Pokemon");
		name.add("Slime Green");
		name.add("Slime Blue");
		name.add("Mushroom");
		BattleArea ba = new BattleArea(new Vector2(125, 300), 250, 120, name,
				2, 60, 1);
		addBattleArea(ba);
		
		ArrayList<String> strname1  = new ArrayList<String>();
		strname1.add("Bat");
		strname1.add("Dark Ghost");
		strname1.add("Dark Skull");
		strname1.add("Dark Knight");
		BattleArea bar1 = new BattleArea(new Vector2(825, 825), 250, 120, strname1,
				2, 80, 10);
		listBattleAre.add(bar1);
		
		BattleArea bar11 = new BattleArea(new Vector2(801, 239), 211, 103, strname1,
				3, 80, 10);
		listBattleAre.add(bar11);
		
		

		
		
		ArrayList<String> strname2  = new ArrayList<String>();
		strname2.add("Eye Float");
		strname2.add("Flame Float");
		strname2.add("Solider");
		strname2.add("Red Flower");
		BattleArea bar2 = new BattleArea(new Vector2(500, 198), 71, 168, strname2,
				4, 80, 11);
		listBattleAre2.add(bar2);
		
		BattleArea ba22 = new BattleArea(new Vector2(231, 309), 71, 168, strname2,
				4, 80, 12);
		listBattleAre2.add(ba22);
		
		ArrayList<String> strname3  = new ArrayList<String>();
		strname3.add("BLue Flower");
		strname3.add("Ghost Water");
		strname3.add("Girl Flower");
		strname3.add("BLue Girl");
		BattleArea bar3 = new BattleArea(new Vector2(420, 491), 94, 182, strname3,
				4, 80, 14);
		listBattleAre2.add(bar3);
		BattleArea bar33 = new BattleArea(new Vector2(620, 560), 165, 79, strname3,
				4, 80, 14);
		listBattleAre2.add(bar33);
		
		BattleArea bar34 = new BattleArea(new Vector2(482, 774), 202, 85, strname3,
				4, 80, 14);
		listBattleAre2.add(bar34);
		
		
		
		ArrayList<String> strname4  = new ArrayList<String>();
		strname4.add("Night Knight");
		strname4.add("Night Night");
		strname4.add("Rat");
//		BattleArea bar4 = new BattleArea(new Vector2(100,100), 200, 200, strname4, 4, 80, 10);
//		listBattleAre3.add(bar4);
		
		BattleArea ba31 = new BattleArea(new Vector2(400, 417), 80, 32, strname4,
				4, 80, 20);
		BattleArea ba32 = new BattleArea(new Vector2(320, 369), 160, 48, strname4,
				4, 80, 20);
		BattleArea ba33 = new BattleArea(new Vector2(320, 337), 257, 31, strname4,
				4, 80, 20);
		BattleArea ba34 = new BattleArea(new Vector2(357, 259), 138, 74, strname4,
				4, 80, 20);
		BattleArea ba35 = new BattleArea(new Vector2(291, 193), 60, 116, strname4,
				4, 80, 20);

		listBattleAre3.add(ba31);
		listBattleAre3.add(ba32);
		listBattleAre3.add(ba33);
		listBattleAre3.add(ba34);
		listBattleAre3.add(ba35);
		
		
		
		
		// boss area
		
//		ArrayList<String> name2 = new ArrayList<String>();
//		name2.add("KaraHan");
//		BattleArea ba2 = new BattleArea(new Vector2(400,400), 100, 100, name2, 1, 80, 1);
//		addBattleArea(ba2);
//		
//		ArrayList<String> name3 = new ArrayList<String>();
//		name3.add("Bahamut");
//		BattleArea ba3 = new BattleArea(new Vector2(400,400), 100, 100, name3, 1, 80, 1);
//		listBattleAre2.add(ba3);
		
		ArrayList<String> name4 = new ArrayList<String>();
		name4.add("Lava");
		BattleArea ba4 = new BattleArea(new Vector2(327,118), 100, 100, name4, 1, 80, 1);
		listBattleAre3.add(ba4);
		
		
	}
	
	public void removeAllData() {
//		playerMonsters = new ArrayList<Monster>();
//		ArrayList<Monster> battlePlayerMonster = new ArrayList<Monster>();
		playerMonsters.removeAll(playerMonsters);
		battlePlayerMonster.removeAll(battlePlayerMonster);
		
	}
	
	public void startNewGame () {
		ArrayList<Skills> pokemon = new ArrayList<Skills>();
		pokemon.add(findSkillByName("Fire Claw"));
//		pokemon.add(findSkillByName("Thunder Aoe 1"));

		for (int i = 0; i < 2; i++) {
			Monster mons = new Monster("Pokemon" + i, 100, 10, 1, 15,15, 80,
					80, "fire", pokemon, 10, pokemon, "data/monster1.png",
					"data/animation.png");
			mons.prepareBattle();
			mons.setExpGain(60);
			addMonsterToBattle(mons);
		}
		
	}
	
	public void addNpcToNpcList (Player e) {
		listNPC.add(e);
	}
	
	public void removeNpc(Player e) {
		listNPC.remove(e);
	}
	
	public float getScoreTotal() {
		return scoreTotal;
	}



	public void setScoreTotal(float scoreTotal) {
		this.scoreTotal = scoreTotal;
		if (scoreTotal == 3000) {
			this.setMaxNumberOfMonsterInBattle(getMaxNumberOfMonsterInBattle() + 1); 
		}
		if (scoreTotal == 10000) {
			this.setMaxNumberOfMonsterInBattle(getMaxNumberOfMonsterInBattle() + 1); 
		}
	}

	public Player getPlayer() {
		return mainPlayer;
	}

	public void setPlayer(Player player) {
		this.mainPlayer = player;
	}

	public int getMaxNumberOfMonsterInBattle() {
		return maxNumberOfMonsterInBattle;
	}



	public void setMaxNumberOfMonsterInBattle(int maxNumberOfMonsterInBattle) {
		if (maxNumberOfMonsterInBattle > 4) {
			this.maxNumberOfMonsterInBattle = 4;
		} else {
		this.maxNumberOfMonsterInBattle = maxNumberOfMonsterInBattle;
		}
	}



	public String checkMonsterAtPackOrBattle(Monster mo) {
		for (Monster mos : battlePlayerMonster) {
			if (mo.equals(mos)) {
				return "battle";
			}
		}
		
		for (Monster mos  :playerMonsters ) {
			if (mo.equals(mos)) {
				return "pack";
			}
		}
		
		return null;
	}
	
	
	public ArrayList<BoundsObject> getListBoud2() {
		return listBoud2;
	}



	public void setListBoud2(ArrayList<BoundsObject> listBoud2) {
		this.listBoud2 = listBoud2;
	}



	public ArrayList<StandObject> getStandObjectList2() {
		return standObjectList2;
	}



	public void setStandObjectList2(ArrayList<StandObject> standObjectList2) {
		this.standObjectList2 = standObjectList2;
	}



	public ArrayList<BattleArea> getListBattleAre2() {
		return listBattleAre2;
	}



	public void setListBattleAre2(ArrayList<BattleArea> listBattleAre2) {
		this.listBattleAre2 = listBattleAre2;
	}



	public void addMonsterFromPlayerToBattleMonster (Monster mo) {
		addMonsterToBattle(mo);
		removeMosterfromPlayerMonster(mo);
	}
	
	public void removeMonsterFromBattleToPlayerMonster(Monster mo) {
		addMosterToPlayerMonster(mo);
		removeMonsterFromBattleMonster(mo);
	}
	
	public void removeMonsterFromBattleMonster(Monster mo) {
		battlePlayerMonster.remove(mo);
	}
	
	public void addMosterToPlayerMonster(Monster mos) {
		playerMonsters.add(mos);
	}
	
	public void removeMosterfromPlayerMonster(Monster mo) {
		playerMonsters.remove(mo);
	}
	
	public Monster findMonsterByName (String name) {
		for (Monster mons : allMonster) {
			if (mons.getName().equals(name)) {
				return mons;
			}
		}
		return null;
	}
	
	public void addBattleArea (BattleArea ba) {
		listBattleAre.add(ba);
	}
	public ArrayList<BattleArea> getListBattleAre() {
		return listBattleAre;
	}



	public void setListBattleAre(ArrayList<BattleArea> listBattleAre) {
		this.listBattleAre = listBattleAre;
	}

	public void addMonsterToAllMonsterList(Monster mos) {
		allMonster.add(mos);
	}

	public ArrayList<Monster> getAllMonster() {
		return allMonster;
	}

	public void setAllMonster(ArrayList<Monster> allMonster) {
		this.allMonster = allMonster;
	}

	public ArrayList<Monster> getBattlePlayerMonster() {
		return battlePlayerMonster;
	}

	public void setBattlePlayerMonster(ArrayList<Monster> battlePlayerMonster) {
		this.battlePlayerMonster = battlePlayerMonster;
	}

	public void addMonsterToBattle (Monster mos) {
		battlePlayerMonster.add(mos);
	}
	
	public void addObjectToBoundList(BoundsObject bo) {
		listBoud.add(bo);
	}

	public Skills findSkillByName (String name) {
		for (Skills ski : listSkills) {
			if (name.equals(ski.getName())) {
				return ski;
			}
		}
		return null;
	}
	public void addSkilsToSkillList(Skills s) {
		listSkills.add(s);
	}
	
	public ArrayList<StandObject> getStandObjectList() {
		return standObjectList;
	}


	public void setStandObjectList(ArrayList<StandObject> standObjectList) {
		this.standObjectList = standObjectList;
	}
	
	public void addStandObjectToList(StandObject so) {
		standObjectList.add(so);
	}
	
	public void removeStandObjectFromList(StandObject so) {
		standObjectList.remove(so);
	}


	public static Model getModel() {
		return model;
	}


	public static void setModel(Model model) {
		Model.model = model;
	}


	public ArrayList<Monster> getPlayerMonsters() {
		return playerMonsters;
	}


	public void setPlayerMonsters(ArrayList<Monster> playerMonsters) {
		this.playerMonsters = playerMonsters;
	}


	public ArrayList<Skills> getListSkills() {
		return listSkills;
	}


	public void setListSkills(ArrayList<Skills> listSkills) {
		this.listSkills = listSkills;
	}


	public ArrayList<BoundsObject> getListBoud() {
		return listBoud;
	}


	public void setListBoud(ArrayList<BoundsObject> listBoud) {
		this.listBoud = listBoud;
	}



	public int getNumberStage() {
		return numberStage;
	}



	public void setNumberStage(int numberStage) {
		this.numberStage = numberStage;
	}



	public ArrayList<Player> getListNPC() {
		return listNPC;
	}



	public void setListNPC(ArrayList<Player> listNPC) {
		this.listNPC = listNPC;
	}

	public ArrayList<Player> getListNPC2() {
		return listNPC2;
	}

	public void setListNPC2(ArrayList<Player> listNPC2) {
		this.listNPC2 = listNPC2;
	}

	public ArrayList<BoundsObject> getListBoud3() {
		return listBoud3;
	}

	public void setListBoud3(ArrayList<BoundsObject> listBoud3) {
		this.listBoud3 = listBoud3;
	}

	public ArrayList<StandObject> getStandObjectList3() {
		return standObjectList3;
	}

	public void setStandObjectList3(ArrayList<StandObject> standObjectList3) {
		this.standObjectList3 = standObjectList3;
	}

	public ArrayList<BattleArea> getListBattleAre3() {
		return listBattleAre3;
	}

	public void setListBattleAre3(ArrayList<BattleArea> listBattleAre3) {
		this.listBattleAre3 = listBattleAre3;
	}

	public ArrayList<Player> getListNPC3() {
		return listNPC3;
	}

	public void setListNPC3(ArrayList<Player> listNPC3) {
		this.listNPC3 = listNPC3;
	}
	
	
	
}
