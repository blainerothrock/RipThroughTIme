package renderers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rip.RipGame;
import com.rip.levels.Level;
import com.rip.objects.Ape;
import com.rip.objects.Enemy;
import com.rip.objects.HealthPack;
import com.rip.objects.MovableEntity;
import com.rip.objects.Player;
import com.rip.objects.Raptor;

public class LevelRenderer {
	//////////UNIVERSAL VARIABLES//////////
	Level level;
	SpriteBatch batch;
	Music leveltheme;
	RipGame game;
	ShapeRenderer sr;
	Player player;
	int width, height;
	public final static int Y_LIMIT = 180;
	public static float levelTime = 0;
	public static int levelScore = 0;
	public static OrthographicCamera cam;
	public static int camPos = 0;
	public static float delta;
	public static ArrayList<Enemy> enemy_list;
	public static ArrayList<HealthPack> healthpacks = new ArrayList<HealthPack>();
	public static ArrayList<MovableEntity> drawables;
	public Random r = new Random();
	float stateTime = 0f;
	public static boolean move = true;
	
	public static Stage stage;
	public static boolean pause = false;

	//////////UNIVERSAL TEXTURES//////////
	Texture playerTexture;

	public LevelRenderer() {
		width = 960;
		height = 480;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);

		batch = new SpriteBatch();

		sr = new ShapeRenderer();

		drawables = new ArrayList<MovableEntity>();
	}

	public LevelRenderer(Level level) {
		this.level = level;
		level.setRenderer(this);

		width = 960;
		height = 480;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);

		batch = new SpriteBatch();

		sr = new ShapeRenderer();

		drawables = new ArrayList<MovableEntity>();
		game = level.game;

		level.generateBackground();
		
		
	}

	public void render() {
//		Gdx.app.log(RipGame.LOG, "rendering...");

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		delta = Gdx.graphics.getDeltaTime();
		stateTime += delta;

		player = level.getPlayer();
		enemy_list = level.getEnemies();
		drawables.add(player);
		drawables.addAll(healthpacks);
		drawables.addAll(enemy_list);
		
		//sort enemies by Y position for drawling.
		Collections.sort(drawables, new Comparator<MovableEntity>() {
			public int compare(MovableEntity a, MovableEntity b) {
				return a.getY() >= b.getY() ? -1 : 1;
			}
		});

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);

		batch.begin();
		sr.begin(ShapeType.Rectangle);

			level.drawBackground(batch);

			drawDrawables();

			if (!pause) {
				player.handleTime(this, level, game);

				player.handleMovement(this, level, game);
			}

			level.drawHud(batch, level.levelHudColor, this);

			level.handleCheckPoints(this);
			
//			if (level.isEnd()) {
//				level.getLeveltheme().stop();
//				batch.draw(level.level_complete, camPos, 0);
//			}

			drawables.clear();
			level.checkPause();

		batch.end();
		sr.end();

	}

	public void drawDrawables() {
		for (int i = 0; i < drawables.size(); i++) {
			MovableEntity me = drawables.get(i);
			sr.rect(me.hitableBox.x, me.hitableBox.y, me.hitableBox.width, me.hitableBox.height);
			if ((me instanceof Player) && player.getTimeFreeze() == false){
				batch.draw(me.getCurrentFrame(), me.getX(), me.getY());
				sr.rect(player.punchBoxLeft.x, player.punchBoxLeft.y, player.punchBoxLeft.width, player.punchBoxLeft.height);
				sr.rect(player.punchBoxRight.x, player.punchBoxRight.y, player.punchBoxRight.width, player.punchBoxRight.height);
			} else if (me instanceof Raptor){
				batch.draw(me.getCurrentFrame(), me.getX(), me.getY());
				((Raptor) me).setCurrentFrame(delta);
				if (((Raptor) me).attacking && 
							((Raptor) me).getRaptor_animation().isAnimationFinished(me.getStateTime())) {
					//Gdx.app.log(RipGame.LOG, "Attack End");
					//player.setHealth(player.getHealth() - ((Raptor) me).getDamage());
					((Raptor) me).attacking = false;
					me.setStateTime(0);
					switch (me.getDir()) {
					case DIR_LEFT:
						((Raptor) me).setRaptor_animation(((Raptor) me).getWalkAnimationLeft());
						break;
					case DIR_RIGHT:
						((Raptor) me).setRaptor_animation(((Raptor) me).getWalkAnimationRight());
						break;
					default:
						break;
					}
				}
			} else if (me instanceof Ape) {
				batch.draw(me.getCurrentFrame(), me.getX(), me.getY());
				((Ape) me).setCurrentFrame(delta);
				if (((Ape) me).attacking && 
							((Ape) me).getApe_animation().isAnimationFinished(me.getStateTime())) {
					//Gdx.app.log(RipGame.LOG, "Attack End");
					//player.setHealth(player.getHealth() - ((Ape) me).getDamage());
					((Ape) me).attacking = false;
					me.setStateTime(0);
					switch (me.getDir()) {
					case DIR_LEFT:
						((Ape) me).setApe_animation(((Ape) me).getWalkAnimationLeft());
						break;
					case DIR_RIGHT:
						((Ape) me).setApe_animation(((Ape) me).getWalkAnimationRight());
						break;
					default:
						break;
					}
				}
			} else if (me instanceof HealthPack) {
				batch.draw(me.getTexture(), me.getX(), me.getY());
				if (((HealthPack) me).collides(player)) {
					healthpacks.remove(me);
				}
				
			} else {
				batch.draw(me.getTexture(), me.getX(), me.getY());
			}
			
		}
			/*
			//Gdx.app.log(RipGame.LOG, "Drawables");
			MovableEntity me = drawables.get(i);
			if ((me instanceof Player) && player.getTimeFreeze() == false){
				batch.draw(me.getCurrentFrame(), me.getX(), me.getY());
			} else if (me instanceof Raptor){
				batch.draw(me.getCurrentFrame(), me.getX(), me.getY());
				((Raptor) me).setCurrentFrame(delta);
			} else {
				batch.draw(me.getTexture(), me.getX(), me.getY());
			}
			//sr.rect(me.hitableBox.x, me.hitableBox.y, me.hitableBox.width, me.hitableBox.height);
			//sr.rect(me.getX(), me.getY(), me.hitableBox.width, me.hitableBox.height);
		}*/
	}

	public void setLevel(Level level) {
		this.level = level;
		level.generateBackground();
	}
	
	
	public void dispose() {
		this.camPos = 0;
		
	}
}