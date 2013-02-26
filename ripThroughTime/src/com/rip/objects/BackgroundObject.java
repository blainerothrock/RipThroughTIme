package com.rip.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;

public class BackgroundObject {

	float x;
	float y;

	int width;
	int height;

	Texture texture;

	Random r = new Random();

	Array<Texture> textures = new Array<Texture>();

	public BackgroundObject(Pixmap p, float x, float y) {
		this.x = x;
		this.y = y;

		texture = new Texture(p);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}


	public BackgroundObject(Array<Pixmap> array) {
		this.x = 0f;
		this.y = 0f;

		for (Pixmap p : array) {
			Texture texture = new Texture(p);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textures.add(texture);
		}

	}

	public BackgroundObject(Array<Pixmap> array, float x, float y) {
		this.x = x;
		this.y = y;

		for (Pixmap p : array) {
			Texture texture = new Texture(p);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			textures.add(texture);
		}

	}

	public int getArrayLength() {
		return textures.size;
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

	public void setTexture() {
		int t = r.nextInt(textures.size);
		texture = textures.get(t);
	}

	public Texture getTexture() {
		return texture;
	}

}
