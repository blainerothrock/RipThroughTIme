package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;


public class Raptor extends LowLevelEnemy {

	
	public Raptor(int x, int y) {
		super(x, y, 224, 174, new Texture("data/raptor.png"), 3);
	}
}
