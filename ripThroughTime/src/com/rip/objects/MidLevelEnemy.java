package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;

public abstract class MidLevelEnemy extends Enemy {

	public MidLevelEnemy(int x, int y, float width, float height,
			Texture texture, int SPEED, float health) {
		super(x, y, width, height, texture, SPEED, health);

		this.health = 55;
		this.damage = 15;
	}
	
	public MidLevelEnemy(int x, int y, float width, float height, int SPEED, float health) {
		super(x, y, width, height, SPEED, health);
		
		this.health = 55;
		this.damage = 15;
	}

}
