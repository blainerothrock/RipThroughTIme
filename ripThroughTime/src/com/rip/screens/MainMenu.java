package com.rip.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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


public class MainMenu implements Screen {

	RipGame game;
	Stage stage;
	BitmapFont black;
	BitmapFont white;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton startButton;
	TextButton optionsButton;
	TextButton creditsButton;

	Music maintheme;
	Music selectPlay;

	public MainMenu(RipGame game) {
		this.game = game;

		maintheme = Gdx.audio.newMusic(Gdx.files.internal("data/Main Menu.mp3"));
		maintheme.setLooping(true);
		maintheme.play();

		selectPlay = Gdx.audio.newMusic(Gdx.files.internal("data/Main Menu Select.mp3"));
		selectPlay.setLooping(false);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		//maintheme.play();

		batch.begin();
			white.draw(batch, "Rip Through Time", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new Stage(width, height, true);
		}
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;

		startButton = new TextButton("Start Game", style);
		startButton.setWidth(300);
		startButton.setHeight(75);
		startButton.setX(Gdx.graphics.getWidth() / 2 + 125);
		startButton.setY(Gdx.graphics.getHeight() /2 - startButton.getHeight() / 2 + 50);

		optionsButton = new TextButton("Options", style);
		optionsButton.setWidth(300);
		optionsButton.setHeight(75);
		optionsButton.setX(Gdx.graphics.getWidth()/2 + 125);
		optionsButton.setY(Gdx.graphics.getHeight()/2 - optionsButton.getHeight() / 2 - 50);

		creditsButton = new TextButton("Credits", style);
		creditsButton.setWidth(300);
		creditsButton.setHeight(75);
		creditsButton.setX(Gdx.graphics.getWidth() / 2 + 125);
		creditsButton.setY(Gdx.graphics.getHeight() / 2 - startButton.getHeight() / 2 - 150);

		startButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RipGame.LOG, "Start Game: pushed");
				maintheme.stop();
				selectPlay.play();

				game.setScreen(new LevelSelect(game));
			}
		});

		optionsButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RipGame.LOG, "Options: pushed");
			}
		});

		creditsButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RipGame.LOG, "Credits: pushed");
			}
		});

		stage.addActor(startButton);
		stage.addActor(optionsButton);
		stage.addActor(creditsButton);
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
		dispose();
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

		selectPlay.dispose();
		maintheme.dispose();
	}

}