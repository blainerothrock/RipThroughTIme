package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;

public class Ape extends LowLevelEnemy {

	static Texture t = new Texture("data/ape.png");

	public Ape(int x, int y) {
		super(x, y,t.getWidth(), t.getHeight(), t, 3); 


		// TODO Auto-generated constructor stub
	}

}
