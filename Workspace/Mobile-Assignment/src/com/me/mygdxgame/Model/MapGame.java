package com.me.mygdxgame.Model;

import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.MyGdxGame;

@SuppressWarnings("unused")
public class MapGame {
	public final static int cellWidth = 40;
	public final static int cellHeight = 40;

	private MyGdxGame game;

	private MapGameUpdate mapupdater;
	private int rows;
	private int coloms;
	Model m = Model.getModel();

	public MapGame( MyGdxGame game, int rows, int coloms) {
		this.game = game;
		this.coloms = coloms;
		this.rows = rows;
	}

	public void update() {
		m.getPlayer().update();
	}

	public void dispose() {

	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColoms() {
		return coloms;
	}

	public void setColoms(int coloms) {
		this.coloms = coloms;
	}

	public static int getCellwidth() {
		return cellWidth;
	}

	public static int getCellheight() {
		return cellHeight;
	}
}
