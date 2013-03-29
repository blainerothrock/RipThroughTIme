package com.rip.levels;

import java.util.ArrayList;
import java.util.Random;

import renderers.LevelRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.rip.RipGame;
import com.rip.objects.BackgroundObject;
import com.rip.objects.Enemy;
import com.rip.objects.Player;

public class Level_1_2 extends Level {

	public RipGame game;
	Player player;
	LevelRenderer lr;
	ArrayList<Enemy> enemies;
//	private InputHandler in;
	
	Random r = new Random();
	
	Timer t = new Timer();
		
	Music leveltheme;
	
	BackgroundObject sk;
	Array<BackgroundObject> grounds = new Array<BackgroundObject>(5);
	Array<BackgroundObject> trees = new Array<BackgroundObject>(100);
	Array<BackgroundObject> bushes = new Array<BackgroundObject>(100);
	Array<BackgroundObject> volcanos = new Array<BackgroundObject>(100);
	Array<BackgroundObject> clouds = new Array<BackgroundObject>(100);
	Array<BackgroundObject> debris = new Array<BackgroundObject>(100);
	
	boolean checkPoint1, checkPoint2, checkPoint3, checkPoint4, levelComplete = false;
	boolean cp2Wave1, cp2Wave2 = false;
	boolean cp3Wave1, cp3Wave2 = false;
	boolean cp4Wave1, cp4Wave2 = false;
	boolean end = false;
	
	public Level_1_2(RipGame game) {
		super(game);
		this.player = new Player(250, 158);
		setIn(new InputHandler(this));
		Gdx.input.setInputProcessor(getIn());
		
		levelLength = 14000;
		levelName = "Level 1  2";
		levelHudColor = "black";
	}
	
	@Override
	public void handleCheckPoints(LevelRenderer lr) {
		if (getEnemies().isEmpty() && LevelRenderer.move == false && LevelRenderer.camPos < 11500) {
			LevelRenderer.move = true;
		}

		//CHECKPOINT 1//
		if (LevelRenderer.camPos >= 1500 && checkPoint1 == false) {
			Gdx.app.log(RipGame.LOG, "checkpoint1");
			LevelRenderer.move = false;
			spawnApe(1);
			checkPoint1 = true;
		}

		//CHECKPOINT 2//
		//wave 1
		if (LevelRenderer.camPos >= 4000 && checkPoint2 == false && cp2Wave1 == false) {
			Gdx.app.log(RipGame.LOG, "checkpoint2");
			LevelRenderer.move = false;
			spawnApe(3);
			cp2Wave1 = true;
		}

		//wave2
		if (getEnemies().isEmpty() && cp2Wave2 == false && cp2Wave1 == true) {
			LevelRenderer.move = false;
			spawnApe(2);
			cp2Wave2 = true;
			checkPoint2 = true;
		}

		//CHECKPOINT 3//
		//wave 1
		if (LevelRenderer.camPos >= 7000 && checkPoint3 == false && cp3Wave1 == false) {
			LevelRenderer.move = false;
			spawnApe(2);
			cp3Wave1 = true;
		}

		//wave 2
		if (getEnemies().isEmpty() && cp3Wave2 == false && cp3Wave1 == true) {
			LevelRenderer.move = false;
			spawnRaptor(1);
			cp3Wave2 = true;
			checkPoint3 = true;
		}

		//CHECKPOINT 4 -- FINAL//
		//wave1
		if (LevelRenderer.camPos >= 11000 && checkPoint4 == false && cp4Wave1 == false) {
			LevelRenderer.move = false;
			spawnApe(6);
			cp4Wave1 = true;
		}

		//wave2
		if (getEnemies().isEmpty() && cp4Wave2 == false && cp4Wave1 == true) {
			LevelRenderer.move = false;
			spawnRaptor(3);
			cp4Wave2 = true;
			checkPoint4 = true;
		}

		//END LEVEL//

		if (checkPoint4 == true && LevelRenderer.camPos >= 11500) {
			LevelRenderer.move = false;
			end = true;
			Gdx.app.log(RipGame.LOG, "End Level 1-1");
			
		}
	}
	
	@Override
	public void generateBackground() {
		Pixmap g = new Pixmap(Gdx.files.internal("data/ground.png"));
		int startX = -20;
		int startY = 0;
		while (startX < levelLength) {
			BackgroundObject gr = new BackgroundObject(g, startX, startY);
			grounds.add(gr);
			startX = startX + g.getWidth();
		}


		//sky -- doesn't ever move. 
		Pixmap s = new Pixmap(Gdx.files.internal("data/sky.png"));
		sk = new BackgroundObject(s,0,0);
		
		
		//random tree objects.
		Pixmap tree1 = new Pixmap(Gdx.files.internal("data/tree.png"));
		Pixmap tree2 = new Pixmap(Gdx.files.internal("data/tree2.png"));
		Pixmap tree3 = new Pixmap(Gdx.files.internal("level1_2/tree.png"));
		Pixmap tree4 = new Pixmap(Gdx.files.internal("level1_2/tree2.png"));
		Pixmap tree5 = new Pixmap(Gdx.files.internal("level1_2/tree3.png"));
		Pixmap tree6 = new Pixmap(Gdx.files.internal("level1_2/tree4.png"));
		Array<Pixmap> treesPix = new Array<Pixmap>();
		treesPix.add(tree1);
		treesPix.add(tree2);
		treesPix.add(tree3);
		treesPix.add(tree4);
		treesPix.add(tree5);
		treesPix.add(tree6);
		int ranPos = -100;
		while (ranPos < levelLength * (1-(.5/3))) {
			int randomX = r.nextInt(120-75) + 75;
			int randomY = r.nextInt(235-210) + 210;
			ranPos = ranPos + randomX;
			BackgroundObject t = new BackgroundObject(treesPix, ranPos, randomY);
			t.setTexture();
			trees.add(t);
		}

		    //random bush objects.
			Pixmap bush1 = new Pixmap(Gdx.files.internal("data/bush.png"));
			Pixmap bush2 = new Pixmap(Gdx.files.internal("data/bush2.png"));
			Pixmap flower1 = new Pixmap(Gdx.files.internal("level1_2/flower.png"));
			Pixmap flower2 = new Pixmap(Gdx.files.internal("level1_2/flower2.png"));
			Pixmap flower3 = new Pixmap(Gdx.files.internal("level1_2/flower3.png"));
			Pixmap flower4 = new Pixmap(Gdx.files.internal("level1_2/flower4.png"));
			Array<Pixmap> bushPix = new Array<Pixmap>();
			bushPix.add(bush1);
			bushPix.add(bush2);
			bushPix.add(flower1);
			bushPix.add(flower2);
			bushPix.add(flower3);
			bushPix.add(flower4);
			ranPos = -30;
			while (ranPos < levelLength) {
				int randomX = r.nextInt(60-20) + 20;
				int randomY = r.nextInt(200-180) + 180;
				ranPos = ranPos + randomX;
				BackgroundObject b = new BackgroundObject(bushPix, ranPos, randomY);
				b.setTexture();
				bushes.add(b);
			}

			//random volcano objects
			Pixmap volcano1 = new Pixmap(Gdx.files.internal("data/volcano.png"));
			Pixmap volcano2 = new Pixmap(Gdx.files.internal("data/volcanosmall.png"));
			Array<Pixmap> volcanoPix = new Array<Pixmap>();
			volcanoPix.add(volcano1);
			volcanoPix.add(volcano2);
			ranPos = -300;
			while (ranPos < levelLength * (1-(1.5/3))) {
				int randomX = r.nextInt(500-300) + 300;
				int randomY = r.nextInt(260-230) + 230;
				ranPos = ranPos + randomX;
				BackgroundObject v = new BackgroundObject(volcanoPix, ranPos, randomY);
				v.setTexture();
				volcanos.add(v);
			}

			//random cloud objects
			Pixmap cloud1 = new Pixmap(Gdx.files.internal("level1_2/cloud1.png"));
			Pixmap cloud2 = new Pixmap(Gdx.files.internal("level1_2/cloud2.png"));
			Pixmap cloud3 = new Pixmap(Gdx.files.internal("level1_2/cloud3.png"));
			Pixmap cloud4 = new Pixmap(Gdx.files.internal("level1_2/cloud4.png"));
			Array<Pixmap> cloudPix = new Array<Pixmap>();
			cloudPix.add(cloud1);
			cloudPix.add(cloud2);
			cloudPix.add(cloud3);
			cloudPix.add(cloud4);
			ranPos = -150;
			while (ranPos < levelLength * (1-(2.5/3))) {
				int randomX = r.nextInt(380-150) + 150;
				int randomY = r.nextInt(460-300) + 300;
				ranPos = ranPos + randomX;
				BackgroundObject c = new BackgroundObject(cloudPix, ranPos, randomY);
				c.setTexture();
				clouds.add(c);
			}

			//random debris objects
			Pixmap debris1 = new Pixmap(Gdx.files.internal("data/smallgrass.png"));
			Pixmap debris2 = new Pixmap(Gdx.files.internal("data/smallrock1.png"));
			Pixmap debris3 = new Pixmap(Gdx.files.internal("data/smallrock2.png"));
			Array<Pixmap> debrisPix = new Array<Pixmap>();
			debrisPix.add(debris1);
			debrisPix.add(debris2);
			debrisPix.add(debris3);
			ranPos = 0;
			while (ranPos < levelLength) {
				int randomX = r.nextInt(100-50) + 50;
				int randomY = r.nextInt(178);
				ranPos = ranPos + randomX;
				BackgroundObject d = new BackgroundObject(debrisPix, ranPos, randomY);
				d.setTexture();
				debris.add(d);
			}
	}
	
	@Override
	public void drawBackground(SpriteBatch batch) {
		batch.draw(sk.getTexture(), sk.getX(), sk.getY());

		//draw random clouds only in visible screen.
		for (BackgroundObject i : clouds) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		//draw random volcanos only in visible screen.
		for (BackgroundObject i : volcanos) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}

		}

		//draw random trees only in visible screen.
		for (BackgroundObject i : trees) {
			if (i.getX() > LevelRenderer.camPos - 130 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		//draw ground
		for (BackgroundObject i : grounds) {
			if (i.getX() > LevelRenderer.camPos - i.getTexture().getWidth() && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		//draw random bushes only in visible screen.
		for (BackgroundObject i : bushes) {
			if (i.getX() > LevelRenderer.camPos - 100 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		//draw random debris objects only in visible screen.
		for (BackgroundObject i : debris) {
			if (i.getX() > LevelRenderer.camPos - 20 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}	
		}
		
	}
	
	@Override
	public void parallax() {
		sk.setX(sk.getX() + 3f);

		for (BackgroundObject i : clouds) {
			i.setX(i.getX() + 2.5f);
		}

		for (BackgroundObject i : volcanos) {
			i.setX(i.getX() + 1.5f);
		}

		for (BackgroundObject i : trees) {
			i.setX(i.getX() + 0.5f);
		}
		
	}
}
