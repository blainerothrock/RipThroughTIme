package com.rip.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;

public class HealthPack extends MovableEntity{
	protected int health_increase = 10;
	protected static float height = 35;
	protected static float width = 34;
	static Texture texture = new Texture("data/healthpack.png");
	Sound pickup = Gdx.audio.newSound(Gdx.files.internal("data/Health PickUp.wav"));
	
	public HealthPack(int x, int y) {
		super(x, y, width, height, 0, texture);

	}
	
	public boolean collides(Player p){
		if (Intersector.overlapRectangles(this.bounds, p.bounds)) {
			p.setHealth(p.getHealth() + this.health_increase);
			pickup.play();
			return true;
		} else {
			return false;
		}
	}


	
	
	
	
	
	

}
