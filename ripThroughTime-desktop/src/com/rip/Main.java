package com.rip;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ripThroughTime " + RipGame.VERSION;
		cfg.useGL20 = true;
		cfg.width = 960;
		cfg.height = 480;
		
		new LwjglApplication(new RipGame(), cfg);
	}
}
