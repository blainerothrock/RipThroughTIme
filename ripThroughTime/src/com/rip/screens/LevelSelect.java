package com.rip.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.rip.RipGame;

public class LevelSelect implements Screen {
	
	RipGame game;
	Stage stage;
	BitmapFont black;
	BitmapFont white;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton level1_1Button;
	TextButton level1_2Button;

	public LevelSelect(RipGame game) {
		this.game = game;
		Gdx.app.log(RipGame.LOG, "LevelSelect Screen Started");
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
			stage.draw();
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		if (stage == null) {
			stage = new Stage(width, height, true);
			Gdx.app.log(RipGame.LOG, "new stage started");
		}
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		level1_1Button = new TextButton("Level1_1", style);
		level1_1Button.setWidth(300);
		level1_1Button.setHeight(75);
		level1_1Button.setX(Gdx.graphics.getWidth() / 2 + 125);
		level1_1Button.setY(Gdx.graphics.getHeight() /2 - level1_1Button.getHeight() / 2 + 50);
		
		level1_2Button = new TextButton("Level1_2", style);
		level1_2Button.setWidth(300);
		level1_2Button.setHeight(75);
		level1_2Button.setX(Gdx.graphics.getWidth() / 2 + 125);
		level1_2Button.setY(Gdx.graphics.getHeight() /2 - level1_2Button.getHeight() / 2 - 50);
		
		level1_1Button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RipGame.LOG, "Start Game: pushed");
//				maintheme.stop();
//				selectPlay.play();
				
				game.setScreen(new GameScreen(game, "level1_1"));
			}
		});
		
		level1_2Button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RipGame.LOG, "Start Game: pushed");
//				maintheme.stop();
//				selectPlay.play();
				
				game.setScreen(new GameScreen(game, "level1_2"));
			}
		});
		
		stage.addActor(level1_1Button);
		stage.addActor(level1_2Button);
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/button.pack"); //need to create our own button graphic!
		skin = new Skin();
		skin.addRegions(atlas);
		white = new BitmapFont(Gdx.files.internal("data/arcadeFontWhite32.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("data/arcadeFontBlack32.fnt"),false);
		
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
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		white.dispose();
		black.dispose();
		stage.dispose();
		
	}

}
