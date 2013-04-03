package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;

public abstract class LowLevelEnemy extends Enemy {

	public LowLevelEnemy(int x, int y, float width, float height,
			Texture texture, int SPEED) {
		super(x, y, width, height, texture, SPEED, 35);
		
		this.health = 35;
		this.damage = 5;
	}
	
	public LowLevelEnemy(int x, int y, float width, float height, int SPEED) {
		super(x, y, width, height, SPEED, 35);
		
		this.health = 35;
		this.damage = 5;
	}

}
