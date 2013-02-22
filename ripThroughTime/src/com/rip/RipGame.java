package com.rip;


import com.badlogic.gdx.Game;
import com.rip.screens.MainMenu;



public class RipGame extends Game {
	
	public static final String VERSION = "0.0.04 pre-alpha";
	public static final String LOG = "";
	
	public static final int WIDTH = 960;
	public static final int HEIGHT = 480;
	
	@Override
	public void create() {	
		setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
