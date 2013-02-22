package com.rip.screens;

import renderers.LevelRender;

import com.badlogic.gdx.Screen;
import com.rip.RipGame;
//import com.rip.levels.Level;
import com.rip.levels.Level_1_1;

public class GameScreen implements Screen {
	RipGame game;
	// abstract once more levels are available
	Level_1_1 level;
	LevelRender lr;
	
	public GameScreen(RipGame game) {
		this.game = game;
		this.level = new Level_1_1(game);
		this.lr = new LevelRender(level);
		
	}
	
	/*
	public GameScreen(RipGame game, int level_num) {
		this.game = game;
		switch (level_num) {
			case 1:
				this.level = new Level_1_1(this.game);
				break;
			default:
				break;
		}
		this.lr = new LevelRender(this.level);
	}
	*/

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		level.update();
		lr.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
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
		level.dispose();
	}

}
