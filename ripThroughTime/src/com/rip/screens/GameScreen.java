package com.rip.screens;

import renderers.LevelRenderer1_1;
import renderers.LevelRenderer;

import com.badlogic.gdx.Screen;
import com.rip.RipGame;
import com.rip.levels.Level;
import com.rip.levels.Level_1_1;
import com.rip.levels.Level_1_2;
//import com.rip.levels.Level;

public class GameScreen implements Screen {
	RipGame game;
	// abstract once more levels are available
	Level_1_1 level1_1;
	Level level1_2;
	Level level;
	LevelRenderer1_1 lr1_1;
	LevelRenderer lr1_2;
	
	public GameScreen(RipGame game, String l) {
		this.game = game;
		
		if (l == "level1_1") {
			this.level1_1 = new Level_1_1(game);
			this.lr1_1 = new LevelRenderer1_1(level1_1);
			this.level = level1_1;
		} else if (l == "level1_2") {
			this.level = new Level_1_2(game);
			this.lr1_2 = new LevelRenderer(level);
		}
		
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
		if (lr1_1 != null) {
			lr1_1.render();
		} else if (lr1_2 != null) {
			lr1_2.render();
		}
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
