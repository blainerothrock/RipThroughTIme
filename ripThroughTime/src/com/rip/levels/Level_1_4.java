package com.rip.levels;

import java.util.ArrayList;
import java.util.Random;

import renderers.LevelRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rip.RipGame;
import com.rip.objects.BackgroundObject;
import com.rip.objects.Enemy;
import com.rip.objects.Player;

public class Level_1_4 extends Level {

	public RipGame game;
	Player player;
	LevelRenderer lr;
	ArrayList<Enemy> enemies;

	Random r = new Random();


	Array<BackgroundObject> bgBack = new Array<BackgroundObject>(100);
	Array<BackgroundObject> bgFront = new Array<BackgroundObject>(100);
	Array<BackgroundObject> bgMiddle = new Array<BackgroundObject>(100);
	Array<BackgroundObject> foreground = new Array<BackgroundObject>(100);
	Array<BackgroundObject> groundRocks = new Array<BackgroundObject>(100);
	Array<BackgroundObject> rocks = new Array<BackgroundObject>(100);
	Array<BackgroundObject> rocks2 = new Array<BackgroundObject>(100);
	Array<BackgroundObject> smallRocks = new Array<BackgroundObject>(100);
	
	boolean checkPoint1, checkPoint2, checkPoint3, checkPoint4, levelComplete = false;
	boolean cp1Wave1, cp1Wave2 = false;
	boolean cp2Wave1, cp2Wave2 = false;
	boolean cp3Wave1, cp3Wave2 = false;
	boolean cp4Wave1, cp4Wave2, cp4Wave3 = false;
	float spawnChance = 0;
	boolean spawnToggle = false;
	boolean randomSpawnToggle = false;


	public Level_1_4(RipGame game) {
		super(game);
		this.player = new Player(250, 158);
		setIn(new InputHandler(this));
		Gdx.input.setInputProcessor(getIn());
		
		levelLength = 12000;
		levelName = "Level 1   4";
		levelHudColor = "white";
	}

//	public LevelRenderer1_2 getRenderer() {
//		return lr;
//	}
	
	@Override
	public void handleCheckPoints(LevelRenderer lr) {
		if (getEnemies().isEmpty() && LevelRenderer.move == false && LevelRenderer.camPos < 11500) {
			LevelRenderer.move = true;
		}
		
		if (getEnemies().isEmpty()) {
			randomSpawnToggle = true;
		}  else {
			randomSpawnToggle = false;
		}
		
		if (LevelRenderer.camPos >= 1000 && !checkPoint1 && !cp1Wave1) {
			LevelRenderer.move = false;
			spawnApe(2);
			cp1Wave1 = true;
		} else if (getEnemies().isEmpty() && cp1Wave1 && !cp1Wave2) {
			LevelRenderer.move = false;
			spawnSuperApe(1);
			cp1Wave2 = true;
			checkPoint1 = true;
		} else if (LevelRenderer.camPos >= 4000 && !checkPoint2 && !cp2Wave1) {
			LevelRenderer.move = false;
			spawnSuperApe(1);
			cp2Wave1 = true;
		} else if (getEnemies().isEmpty() && cp2Wave1 && !cp2Wave2) {
			LevelRenderer.move = false;
			spawnApe(2);
			spawnSuperApe(1);
			cp2Wave2 = true;
			checkPoint2 = true;
		} else if (LevelRenderer.camPos >= 7000 && !checkPoint3 && !cp3Wave1) {
			LevelRenderer.move = false;
			spawnSuperApe(2);
			cp3Wave1 = true;
		} else if (getEnemies().size() <= 1 && cp3Wave1 && !cp3Wave2) {
			LevelRenderer.move = false;
			spawnSuperApe(1);
			spawnRaptor(1);
			cp3Wave2 = true;
			checkPoint3 = true;
		} else if (LevelRenderer.camPos >= 10000 && !checkPoint4 && !cp4Wave1) {
			LevelRenderer.move = false;
			spawnSuperApe(1);
			spawnApe(2);
			cp4Wave1 = true;
		} else if (getEnemies().isEmpty() && cp4Wave1 && !cp4Wave2) {
			LevelRenderer.move = false;
			spawnApe(3);
			spawnSuperApe(1);
			cp4Wave2 = true;
		} else if (getEnemies().size() <= 1 && cp4Wave2 && !cp4Wave3) {
			LevelRenderer.move = false;
			spawnSuperApe(2);
			cp4Wave3 = true;
			checkPoint4 = true;
			levelComplete = true;
		} else if (checkPoint1 && !levelComplete && randomSpawnToggle) {
			if (player.getHealth() > player.getTotalHealth() * .75) {
				randomSpawn(5, 3);
			} else if (player.getHealth() > player.getTotalHealth() * .25) {
				randomSpawn(12, 5);
			} else {
				randomSpawn(20, 7);
			}
		}
		
		if (levelComplete) {
			//end level.
			LevelRenderer.move = false;
			Gdx.app.log(RipGame.LOG, "Level 1_4 Complete.");
		}
		
	}
	
	public void randomSpawn(int freq, int prob) {
		if (spawnChance >= freq && spawnToggle) {
			spawnChance = 0;
			if (r.nextInt(prob) == 1) {
				float ran2 = r.nextFloat();
				if (ran2 <= .25) {
					spawnApe(2);
				} else if ( ran2 <= .5 && ran2 > .25) {
					spawnRaptor(2);
				} else if (ran2 > .5 && ran2 <= .7) {
					spawnRaptor(1);
					spawnApe(1);
				} else if (ran2 > .7 && ran2 <= .85) {
					spawnSuperApe(1);
					spawnRaptor(1);
				} else {
					spawnApe(1);
					spawnRedRaptor(1);
				}
			} else {
				float ran2 = r.nextFloat();
				if (ran2 <= .3) {
					spawnApe(1);
				} else if (ran2 > .3 && ran2 <= .6) { 
					spawnRaptor(1);
				} else if (ran2 > .6 && ran2 <= .8) {
					spawnRedRaptor(1);
				} else {
					spawnSuperApe(1);
				}
			}
			spawnToggle = false;
		} else {
			spawnChance += r.nextFloat() * LevelRenderer.delta;
		}
		
		if (spawnChance >= freq && !spawnToggle) {
			spawnToggle = true;
		}
	}

	@Override
	public void generateBackground() {
		//////////background textures//////////
		Pixmap fg1 = new Pixmap(Gdx.files.internal("level1_4/foreground1.png"));
		Pixmap fg2 = new Pixmap(Gdx.files.internal("level1_4/foreground2.png"));
		Pixmap fg3 = new Pixmap(Gdx.files.internal("level1_4/foreground3.png"));
		Pixmap fg4 = new Pixmap(Gdx.files.internal("level1_4/foreground4.png"));
		Array<Pixmap> fgPix = new Array<Pixmap>();
		fgPix.add(fg1);
		fgPix.add(fg2);
		fgPix.add(fg3);
		fgPix.add(fg4);
		int ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = -20;
			int randomY = 0;
			BackgroundObject fg = new BackgroundObject(fgPix, ranPos, randomY);
			fg.setTexture();
			foreground.add(fg);
			ranPos += fg1.getWidth();
		}


		//furthest back background
		Pixmap bgBack1 = new Pixmap(Gdx.files.internal("level1_4/back1.png"));
		Pixmap bgBack2 = new Pixmap(Gdx.files.internal("level1_4/back2.png"));
		Pixmap bgBack3 = new Pixmap(Gdx.files.internal("level1_4/back3.png"));
		Pixmap bgBack4 = new Pixmap(Gdx.files.internal("level1_4/back4.png"));
		Array<Pixmap> bgBackPix = new Array<Pixmap>();
		bgBackPix.add(bgBack1);
		bgBackPix.add(bgBack2);
		bgBackPix.add(bgBack3);
		bgBackPix.add(bgBack4);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = -20;
			int randomY = 300;
			BackgroundObject bgB = new BackgroundObject(bgBackPix, ranPos, randomY);
			bgB.setTexture();
			bgBack.add(bgB);
			ranPos += bgBack1.getWidth();
		}

		//middle background
		Pixmap bgMiddle1 = new Pixmap(Gdx.files.internal("level1_4/middle1.png"));
		Pixmap bgMiddle2 = new Pixmap(Gdx.files.internal("level1_4/middle2.png"));
		Pixmap bgMiddle3 = new Pixmap(Gdx.files.internal("level1_4/middle3.png"));
		Pixmap bgMiddle4 = new Pixmap(Gdx.files.internal("level1_4/middle4.png"));
		Array<Pixmap> bgMiddlePix = new Array<Pixmap>();
		bgMiddlePix.add(bgMiddle1);
		bgMiddlePix.add(bgMiddle2);
		bgMiddlePix.add(bgMiddle3);
		bgMiddlePix.add(bgMiddle4);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = -20;
			int randomY = 340;
			ranPos += bgMiddle1.getWidth();
			BackgroundObject bgM = new BackgroundObject(bgMiddlePix, ranPos, randomY);
			bgM.setTexture();
			bgMiddle.add(bgM);
		}

		///closest back background object
		Pixmap bgFront1 = new Pixmap(Gdx.files.internal("level1_4/front1.png"));
		Pixmap bgFront2 = new Pixmap(Gdx.files.internal("level1_4/front2.png"));
		Pixmap bgFront3 = new Pixmap(Gdx.files.internal("level1_4/front3.png"));
		Pixmap bgFront4 = new Pixmap(Gdx.files.internal("level1_4/front4.png"));
		Array<Pixmap> bgFrontPix = new Array<Pixmap>();
		bgFrontPix.add(bgFront1);
		bgFrontPix.add(bgFront2);
		bgFrontPix.add(bgFront3);
		bgFrontPix.add(bgFront4);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = -20;
			int randomY = 385;
			ranPos += bgFront1.getWidth();
			BackgroundObject bgF = new BackgroundObject(bgFrontPix, ranPos, randomY);
			bgF.setTexture();
			bgFront.add(bgF);
		}

		Pixmap groundRock1 = new Pixmap(Gdx.files.internal("level1_4/ground1_1.png"));
		Pixmap groundRock2 = new Pixmap(Gdx.files.internal("level1_4/ground1_2.png"));
		Pixmap groundRock3 = new Pixmap(Gdx.files.internal("level1_4/ground1_3.png"));
		Pixmap groundRock4 = new Pixmap(Gdx.files.internal("level1_4/ground2_1.png"));
		Pixmap groundRock5 = new Pixmap(Gdx.files.internal("level1_4/ground2_2.png"));
		Pixmap groundRock6 = new Pixmap(Gdx.files.internal("level1_4/ground2_3.png"));
		Pixmap groundRock7 = new Pixmap(Gdx.files.internal("level1_4/ground2_4.png"));
		Pixmap groundRock8 = new Pixmap(Gdx.files.internal("level1_4/ground2_5.png"));
		Pixmap groundRock9 = new Pixmap(Gdx.files.internal("level1_4/paint9.png"));
		Pixmap groundRock10 = new Pixmap(Gdx.files.internal("level1_4/paint6.png"));
		Pixmap groundRock11 = new Pixmap(Gdx.files.internal("level1_4/paint1.png"));
		Pixmap groundRock12 = new Pixmap(Gdx.files.internal("level1_4/rock1.png"));
		Pixmap groundRock13 = new Pixmap(Gdx.files.internal("level1_4/rock2.png"));
		Pixmap groundRock14 = new Pixmap(Gdx.files.internal("level1_4/rock4.png"));
		Pixmap groundRock15 = new Pixmap(Gdx.files.internal("level1_4/rock6.png"));
		Pixmap groundRock16 = new Pixmap(Gdx.files.internal("level1_4/paint2.png"));
		Pixmap groundRock17 = new Pixmap(Gdx.files.internal("level1_4/paint5.png"));
		Array<Pixmap> groundRockPix = new Array<Pixmap>();
		groundRockPix.add(groundRock1);
		groundRockPix.add(groundRock2);
		groundRockPix.add(groundRock3);
		groundRockPix.add(groundRock4);
		groundRockPix.add(groundRock5);
		groundRockPix.add(groundRock6);
		groundRockPix.add(groundRock7);
		groundRockPix.add(groundRock8);
		groundRockPix.add(groundRock9);
		groundRockPix.add(groundRock10);
		groundRockPix.add(groundRock11);
		groundRockPix.add(groundRock12);
		groundRockPix.add(groundRock13);
		groundRockPix.add(groundRock14);
		groundRockPix.add(groundRock15);
		groundRockPix.add(groundRock16);
		groundRockPix.add(groundRock17);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = r.nextInt(250 - 100) + 100;
			int randomY = 230;
			ranPos += randomX;
			BackgroundObject gR = new BackgroundObject(groundRockPix, ranPos, randomY);
			gR.setTexture();
			groundRocks.add(gR);
		}

		Pixmap rock1 = new Pixmap(Gdx.files.internal("level1_4/paint3.png"));
		Pixmap rock2 = new Pixmap(Gdx.files.internal("level1_4/paint4.png"));
		Pixmap rock3 = new Pixmap(Gdx.files.internal("level1_4/paint7.png"));
		Pixmap rock4 = new Pixmap(Gdx.files.internal("level1_4/paint8.png"));
		Pixmap rock5 = new Pixmap(Gdx.files.internal("level1_4/rock3.png"));
		Pixmap rock6 = new Pixmap(Gdx.files.internal("level1_4/rock5.png"));
		Pixmap rock7 = new Pixmap(Gdx.files.internal("level1_4/rock7.png"));
		Array<Pixmap> rocksPix = new Array<Pixmap>();
		rocksPix.add(rock1);
		rocksPix.add(rock2);
		rocksPix.add(rock3);
		rocksPix.add(rock4);
		rocksPix.add(rock5);
		rocksPix.add(rock6);
		rocksPix.add(rock7);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = r.nextInt(400 - 100) + 100;
			int randomY = 230;
			ranPos += randomX;
			BackgroundObject rR = new BackgroundObject(rocksPix, ranPos, randomY);
			rR.setTexture();
			rocks.add(rR);
		}

		Pixmap smallRock1 = new Pixmap(Gdx.files.internal("level1_4/cluster1.png"));
		Pixmap smallRock2 = new Pixmap(Gdx.files.internal("level1_4/cluster2.png"));
		Pixmap smallRock3 = new Pixmap(Gdx.files.internal("level1_4/cluster3.png"));
		Pixmap smallRock4 = new Pixmap(Gdx.files.internal("level1_4/cluster4.png"));
		Pixmap smallRock5 = new Pixmap(Gdx.files.internal("level1_4/cluster5.png"));
		Pixmap smallRock6 = new Pixmap(Gdx.files.internal("level1_4/cluster6.png"));
		Pixmap smallRock7 = new Pixmap(Gdx.files.internal("level1_4/cluster7.png"));
		Pixmap smallRock8 = new Pixmap(Gdx.files.internal("level1_4/cluster8.png"));
		Pixmap smallRock9 = new Pixmap(Gdx.files.internal("level1_4/cluster9.png"));
		Pixmap smallRock10 = new Pixmap(Gdx.files.internal("level1_4/cluster10.png"));
		Array<Pixmap> smallRockPix = new Array<Pixmap>();
		smallRockPix.add(smallRock1);
		smallRockPix.add(smallRock2);
		smallRockPix.add(smallRock3);
		smallRockPix.add(smallRock4);
		smallRockPix.add(smallRock5);
		smallRockPix.add(smallRock6);
		smallRockPix.add(smallRock7);
		smallRockPix.add(smallRock8);
		smallRockPix.add(smallRock9);
		smallRockPix.add(smallRock10);
		ranPos = -100;
		while (ranPos < levelLength) {
			int randomX = r.nextInt(150-100) + 100;
			int randomY = r.nextInt(210-190) + 190;
			ranPos += randomX;
			BackgroundObject sR = new BackgroundObject(smallRockPix, ranPos, randomY);
			sR.setTexture();
			smallRocks.add(sR);
		}



	}
	@Override
	public void drawBackground(SpriteBatch batch) {



		for (BackgroundObject i : bgBack) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : bgMiddle) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : rocks) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : groundRocks) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : bgFront) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : foreground) {
			if (i.getX() > LevelRenderer.camPos - 1000 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}

		for (BackgroundObject i : smallRocks) {
			if (i.getX() > LevelRenderer.camPos - 250 && i.getX() < LevelRenderer.camPos + RipGame.WIDTH + 20) {
				batch.draw(i.getTexture(), i.getX(), i.getY());
			}
		}
	}
	@Override 
	public void parallax() {
		for (BackgroundObject i : bgFront) {
			i.setX(i.getX() + 0.5f);
		}

		for (BackgroundObject i : groundRocks) {
			i.setX(i.getX() + 1.0f);
		}

		for (BackgroundObject i : rocks) {
			i.setX(i.getX() + 1.25f);
		}

		for (BackgroundObject i : bgMiddle) {
			i.setX(i.getX() + 1.5f);
		}

		for (BackgroundObject i : bgBack) {
			i.setX(i.getX() + 2.5f);
		}
	}
	
	public void dispose() {
		//leveltheme.dispose();
		super.dispose();
		
	}

}
