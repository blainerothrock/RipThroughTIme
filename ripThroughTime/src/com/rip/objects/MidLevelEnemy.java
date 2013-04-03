package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;

public abstract class MidLevelEnemy extends Enemy {

	public MidLevelEnemy(int x, int y, float width, float height,
			Texture texture, int SPEED) {
		super(x, y, width, height, texture, SPEED, 45);

		this.health = 45;
		this.damage = 10;
	}
	
	public MidLevelEnemy(int x, int y, float width, float height, int SPEED) {
		super(x, y, width, height, SPEED, 45);
		
		this.health = 45;
		this.damage = 10;
	}

}
