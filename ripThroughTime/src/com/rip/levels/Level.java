package com.rip.levels;

import renderers.LevelRender;

//import com.badlogic.gdx.Gdx;
import com.rip.RipGame;
import com.rip.objects.Player;

public abstract class Level {

	
	RipGame game;
	Player player;
	LevelRender lr;
		
	public Level(RipGame game, Player player) {
			this.game = game;
			this.player = player;
//			Gdx.input.setInputProcessor(new InputHandler(this));
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
	
}
	

