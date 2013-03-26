package renderers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rip.RipGame;
import com.rip.levels.Level;
import com.rip.levels.Level_1_2;
import com.rip.objects.Enemy;
import com.rip.objects.MovableEntity;
import com.rip.objects.MovableEntity.Directions;
import com.rip.objects.Player;
import com.rip.objects.Raptor;
import com.rip.screens.MainMenu;

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
	public float levelTime = 0;
	public int levelScore = 0;
	public OrthographicCamera cam;
	public static int camPos = 0;
	public static float delta;
	public ArrayList<Enemy> enemy_list;
	public ArrayList<MovableEntity> drawables;
	public Random r = new Random();
	float stateTime = 0f;
	public boolean move = true;
	
	//////////UNIVERSAL TEXTURES//////////
	Texture playerTexture;
	Texture timeFreezeOverlay = new Texture(Gdx.files.internal("data/timeFreezeOverlay.png"));
	Texture level_complete = new Texture(Gdx.files.internal("data/level_complete.png"));
	Texture timebaroutline = new Texture(Gdx.files.internal("data/timebaroutlineWhite.png"));
	Texture timebar = new Texture(Gdx.files.internal("data/timebar.png"));
	Texture healthbaroutline = new Texture(Gdx.files.internal("data/healthbaroutlineWhite.png"));
	Texture healthbar = new Texture(Gdx.files.internal("data/healthbar.png"));
	Texture pauseOverlay = new Texture(Gdx.files.internal("data/pauseOverlay.png"));
	Texture timeFreezeLine = new Texture(Gdx.files.internal("data/timeLine.png"));
	BitmapFont font = new BitmapFont(Gdx.files.internal("data/arcadeFontWhite18.fnt"),false);
	BitmapFont fontBig = new BitmapFont(Gdx.files.internal("data/arcadeFontWhite32.fnt"),false);
	
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
		drawables.addAll(enemy_list);
		
		cam.update();
		
		batch.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);
		
		batch.begin();
		
			level.drawBackground(batch);
		
			drawDrawables();
		
			drawHud();
		
			player.handleTime(this, level, game);
		
			player.handleMovement(this, level, game);
		
			drawables.clear();
		
		batch.end();
		
	}
	
	public void drawDrawables() {
		for (int i = 0; i < drawables.size(); i++) {
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
		}
	}
	
	public void drawHud() {
		font.draw(batch, "World  1   Level  1", camPos + 800, 470);
		if (player.getTimeFreeze() == false) {
			levelTime = (float)levelTime + delta;
		}
		font.draw(batch, "Time:     " + (int)levelTime, camPos + 800, 450);
		font.draw(batch, "Score:     " + levelScore, camPos + 800, 430);
		batch.draw(healthbar, camPos + 25, 450, player.getHealth()*2, 15);
		batch.draw(healthbaroutline, camPos + 25 - 3, 450 - 3, 206, 21);
		if (player.getTimeFreeze() == true) {
			batch.draw(timeFreezeOverlay, camPos, 0);
			batch.draw(timeFreezeLine, camPos + r.nextInt(960), 0);
			batch.draw(timeFreezeLine, camPos + r.nextInt(960), 0);
			batch.draw(timeFreezeLine, camPos + r.nextInt(960), 0);
			batch.draw(timeFreezeLine, camPos + r.nextInt(960), 0);
			batch.draw(timeFreezeLine, camPos + r.nextInt(960), 0);
		}
		batch.draw(timebar, camPos + 25, 425, player.getTime()*2, 15);
		batch.draw(timebaroutline, camPos + 25 - 3, 425 - 3, 206, 21);
	}
}
