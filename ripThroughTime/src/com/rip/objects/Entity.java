package com.rip.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	protected int x;
	protected int y;
	protected float width;
	protected float height;
	protected Rectangle bounds;
	Texture texture;
	public Rectangle hitableBox;
	
	public Entity(int x, int y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(this.x, this.y, width, height);
		
		hitableBox = new Rectangle(this.x, this.y + (height/2), width, (height/2));
		
	}
	
	public Entity(int x, int y, float width, float height, Texture texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(this.x, this.y, width, height);
		this.texture = texture;
		
		hitableBox = new Rectangle(this.x, this.y + (height/2), width, (height / 2));
		
	}
	
	
	public Rectangle getHitableBox() {
		return hitableBox;
	}

	public void setHitableBox(Rectangle hitableBox) {
		this.hitableBox = hitableBox;
	}

	public void setTexture(Texture t) {
		this.texture = t;
	}

	public Texture getTexture() {
		return texture;
	}

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
		this.bounds.x = x;
		this.hitableBox.x = x;
		
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
		this.bounds.y = y;
		this.hitableBox.y = y + (height / 2);
	}



	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	
}
