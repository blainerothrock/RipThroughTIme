package com.rip.screens;

import tweens.SpriteTween;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rip.RipGame;

public class SplashScreen implements Screen {
	
	Texture splashTexture1;
	Texture splashTexture2;
	Sprite splashSprite1;
	Sprite splashSprite2;
	SpriteBatch batch;
	RipGame game;
	TweenManager manager;
	

	
	public SplashScreen(RipGame game) {
		this.game = game;
//		Gdx.app.log(RipGame.LOG, "SplashScreen created.");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		
		
		batch.begin();
			splashSprite1.draw(batch);
			splashSprite2.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		splashTexture1 = new Texture("data/hoosierGamesSplash.png");
		splashTexture1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashTexture2 = new Texture("data/saturdayKnightsSplash.png");
		splashTexture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite1 = new Sprite(splashTexture1);
		splashSprite1.setColor(1, 1, 1, 0);
		
		splashSprite2 = new Sprite(splashTexture2);
		splashSprite2.setColor(1, 1, 1, 0);
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
		manager = new TweenManager();
		
		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				Tween1Completed();
			}
		};
		
		Tween.to(splashSprite1, SpriteTween.ALPHA, 2f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	public void Tween1Completed() {
//		Gdx.app.log(RipGame.LOG, "Tween Complete!");
		
		TweenCallback cb2 = new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				Tween2Completed();				
			}
			
		};
		
		Tween.to(splashSprite2, SpriteTween.ALPHA, 2f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2f).setCallback(cb2).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	public void Tween2Completed() {
		game.setScreen(new MainMenu(game));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
