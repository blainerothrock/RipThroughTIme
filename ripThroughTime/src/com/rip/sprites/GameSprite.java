package com.rip.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameSprite {
	
	public boolean active;
	public boolean visible;
	public float x = 0;
	public float y = 0;
	public int width = 0;
	public int height = 0;
	
	public TextureRegion skin;
	public Rectangle body;
	
	protected Game _game;
	
	public GameSprite (Game game, float x, float y) {
		_game = game;
		this.x = x;
		this.y = y;
		active = true;
		visible = true;
		skin = null;
	}

}
