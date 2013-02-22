package com.rip.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Background {
	
	float x;
	float y;
	Texture texture;
	
	int width;
	int height;
	
	public Background(Pixmap bg) {
		this.x = 0f;
		this.y = 0f;
		this.texture = new Texture(bg);
//		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		this.height = texture.getHeight();
		this.width = texture.getWidth();
	}
	
	public Background(Pixmap bg, float x, float y) {
		this.x = x;
		this.y = y;
		this.texture = new Texture(bg);
//		texture.setWrap(TextureWrap.Repeat,TextureWrap.Repeat);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		this.height = texture.getHeight();
		this.width = texture.getWidth();
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	
	public Texture getTexture() {
		return texture;
	}

}
