package com.rip.levels;

import java.util.ArrayList;
import java.util.Random;

import renderers.LevelRender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.rip.RipGame;
import com.rip.objects.Ape;
import com.rip.objects.Enemy;
import com.rip.objects.Player;
import com.rip.objects.Raptor;


public class Level_1_1 {
	
	public RipGame game;
	Player player;
	LevelRender lr;
	ArrayList<Enemy> enemies;
	private InputHandler in;
		
	Music leveltheme;
	
	public Level_1_1(RipGame game) {
			this.game = game;
			enemies = new ArrayList<Enemy>();
			this.player = new Player(250, 158);
			setIn(new InputHandler(this));
			Gdx.input.setInputProcessor(getIn());
			/*
			Raptor raptor_one = new Raptor(800, 50);
			Raptor raptor_two = new Raptor(500, 150);
			enemies.add(raptor_one);
			enemies.add(raptor_two);
			*/
			leveltheme = Gdx.audio.newMusic(Gdx.files.internal("data/Prehistoric Main.mp3"));
			leveltheme.setLooping(true);
	}
	
	public void checkPoint(int numOfEnemiesRap, int numOfEnemiesApe) {
		Random r = new Random();
		int rightside;
		int leftside;
		boolean lr;

		for (int i = 0; i < numOfEnemiesRap; i++) {
			lr = r.nextBoolean();
			if (lr) {
				rightside = LevelRender.camPos + RipGame.WIDTH;
				enemies.add(new Raptor(r.nextInt((rightside + RipGame.WIDTH) - (rightside + 50)) + (rightside + 50), r.nextInt(LevelRender.Y_LIMIT)));
			} else {
				leftside = LevelRender.camPos;
				enemies.add(new Raptor(r.nextInt((leftside - 50) - (leftside - RipGame.WIDTH)) + (leftside - RipGame.WIDTH), r.nextInt(LevelRender.Y_LIMIT)));
			}

		}

		for (int i = 0; i < numOfEnemiesApe; i++) {
			lr = r.nextBoolean();
			if (lr) {
				rightside = LevelRender.camPos + RipGame.WIDTH;
				enemies.add(new Ape(r.nextInt((rightside + RipGame.WIDTH) - (rightside + 50)) + (rightside + 50), r.nextInt(LevelRender.Y_LIMIT)));
			} else {
				leftside = LevelRender.camPos;
				enemies.add(new Ape(r.nextInt((leftside - 50) - (leftside - RipGame.WIDTH)) + (leftside - RipGame.WIDTH), r.nextInt(LevelRender.Y_LIMIT)));
			}

		}
	}
	
		
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}


	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}


	public Player getPlayer(){
		return player;
	}
	
	public void update() {
		player.update();
	}

	public void setRenderer(LevelRender lr) {
		this.lr = lr;
	}
	
	public LevelRender getRenderer() {
		return lr;
	}
	
	public void dispose() {
		
	}


	public Music getLeveltheme() {
		return leveltheme;
	}

	public InputHandler getIn() {
		return in;
	}

	public void setIn(InputHandler in) {
		this.in = in;
	}

	
	
}

