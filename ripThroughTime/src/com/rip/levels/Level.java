package com.rip.levels;

import java.util.ArrayList;
import java.util.Random;

import renderers.LevelRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rip.RipGame;
import com.rip.objects.Enemy;
import com.rip.objects.Player;
//import com.badlogic.gdx.Gdx;

public abstract class Level {

	
	public RipGame game;
	Player player;
	LevelRenderer lr;
	ArrayList<Enemy> enemies;
	private InputHandler in;
	public int levelLength;
	
	Random r = new Random();
		
	public Level(RipGame game) {
			this.game = game;
			enemies = new ArrayList<Enemy>();
			this.player = new Player(250, 158);
			setIn(new InputHandler(this));
			Gdx.input.setInputProcessor(getIn());
	}
	
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public void setRenderer(LevelRenderer lr) {
		this.lr = lr;
	}
		
	public Player getPlayer(){
		return player;
	}
	
	public void update() {
		player.update();
	}

//	public Music getLeveltheme() {
//		return leveltheme;
//	}

	public InputHandler getIn() {
		return in;
	}

	public void setIn(InputHandler in) {
		this.in = in;
	}

	
	public void dispose() {
		
	}
	
	public void generateBackground() {
		
	}
	
	public void drawBackground(SpriteBatch batch) {
		
	}
	
	public void parallax() {
		
	}
	
}
	

