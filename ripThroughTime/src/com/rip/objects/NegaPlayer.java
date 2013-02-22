package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;


public class NegaPlayer extends LowLevelEnemy {

	
	public NegaPlayer(int x, int y, float width, float height, int SPEED) {
		super(x, y, width, height, new Texture("data/nega-player.png"), SPEED);
	}
}
