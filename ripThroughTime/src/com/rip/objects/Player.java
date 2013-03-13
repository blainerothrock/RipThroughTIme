package com.rip.objects;



import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.rip.RipGame;

public class Player extends MovableEntity {

	Texture punch;
	float health;
	float punch_damage;
	float kick_damage;
	float time;
	boolean timeFreeze;
	
	boolean ATTACK_ANIMATION = false;
	
	//The player class has several animations
	////At any given time, the renderer can only 
	////access the current frame from the current
	////animation
	protected Animation player_animation;
	protected TextureRegion currentFrame;
	protected float stateTime = 0f;
	
	//Walk Animation
	private static final int WALK_COLS = 7;
	private static final int WALK_ROWS = 1;
	
	protected Animation walkAnimationRight;
	protected Animation walkAnimationLeft;
	protected Texture walkSheet;
	protected TextureRegion[] walkFramesRight;
	protected TextureRegion[] walkFramesLeft;
	protected TextureRegion currentwalkFrame;
	
    float walkTime = 0f;
    
	//Kick Animation
    private static final int KICK_COLS = 7;
	private static final int KICK_ROWS = 1;
	
	protected Animation kickAnimationRight;
	protected Animation kickAnimationLeft;
	protected Texture kickSheet;
	protected TextureRegion[] kickFramesRight;
	protected TextureRegion[] kickFramesLeft;
	protected TextureRegion currentkickFrame;
	
    float kickTime = 0f;
	
	//Hit Animation
	
	//Punch Animation
	private static final int PUNCH_COLS = 5;
	private static final int PUNCH_ROWS = 1;
	
	protected Animation punchAnimationRight;
	protected Animation punchAnimationLeft;
	protected Texture punchSheet;
	protected TextureRegion[] punchFramesRight;
	protected TextureRegion[] punchFramesLeft;
	protected TextureRegion currentpunchFrame;
	
    float punchTime = 0f;
    

    
	
	Random rand = new Random();
	
	// Remove as soon as sprite sheets are available
	protected final Texture PUNCH_RIGHT = new Texture("data/RIP_PUNCH_RIGHT.png");
	protected final Texture PUNCH_LEFT = new Texture("data/RIP_PUNCH_LEFT.png");
	protected final static Texture RIGHT = new Texture("data/RIP_RIGHT.png");
	protected final Texture LEFT = new Texture("data/RIP_LEFT.png");
	
	Sound[] punch_sounds = {Gdx.audio.newSound(Gdx.files.internal("data/Punches_01.wav")),
	                        Gdx.audio.newSound(Gdx.files.internal("data/Punches_02.wav")),
	                        Gdx.audio.newSound(Gdx.files.internal("data/Punches_03.wav"))};
	
	//collision objects.
//	public Intersector in = new Intersector();
//	public Rectangle hitableBox = new Rectangle(this.getX() + this.getHeight() / 2,this.getY() + this.getWidth() / 2,this.getWidth() / 2,this.getHeight()/2);
	public Rectangle punchBoxRight = new Rectangle(this.getX() + (this.width / 2), (this.getY() + (this.height / 2)), (this.width / 2), (this.height / 2) - 30);
	public Rectangle punchBoxLeft =  new Rectangle(this.getX(), (this.getY() + (this.height / 2)), (this.width / 2), (this.height / 2) - 30);
	
	//public Rectangle punchBoxRight = new Rectangle(this.getX() + 60, this.getY() + (this.height / 2), 65, (this.height / 2));
	//public Rectangle punchBoxLeft =  new Rectangle(this.getX() + 10, this.getY() + (this.height / 2), 50, (this.height / 2));
	
	
	public Player(int x, int y, float width, float height, int SPEED, Texture text) {
		super(x, y, width, height, SPEED, text);
		this.health = 100;
		this.punch_damage = 10;
		this.kick_damage = 15;
		CreateAnimations();

	}
	
	public Player(int x, int y) {
		super(x, y, 128, 163, 3, RIGHT);
		this.health = 100;
		this.punch_damage = 10;
		this.kick_damage = 15;
		CreateAnimations();
		
	}
	
	public void CreateAnimations() {
		int index;
		
		//Initiate Walk Animation
		TextureRegion temp;
		walkSheet = new Texture(Gdx.files.internal("data/rip_walk.png"));
		TextureRegion[][] tmpwRight = TextureRegion.split(walkSheet, walkSheet.getWidth() / WALK_COLS, walkSheet.getHeight() / WALK_ROWS);
		TextureRegion[][] tmpwLeft = TextureRegion.split(walkSheet, walkSheet.getWidth() / WALK_COLS, walkSheet.getHeight() / WALK_ROWS);
		walkFramesRight = new TextureRegion[WALK_COLS * WALK_ROWS];
		walkFramesLeft = new TextureRegion[WALK_COLS * WALK_ROWS];
		index = 0;
		for (int i = 0; i < WALK_ROWS; i++) {
			for (int j = 0; j < WALK_COLS; j++) {
				temp = tmpwRight[i][j];
				walkFramesRight[index] = temp;
				//walkFramesLeft[index] = temp;
				index++;
			}
		}
		
		
		index = 0;
		for (int i = 0; i < WALK_ROWS; i++) {
			for (int j = 0; j < WALK_COLS; j++) {
				temp = tmpwLeft[i][j];
				walkFramesLeft[index] = temp;
				walkFramesLeft[index].flip(true, false);
				index++;
			}
		}
		/*
		for (TextureRegion region : walkFramesLeft) {
			region.flip(true, false);
		}
		*/
		walkAnimationRight = new Animation(0.1f, walkFramesRight);
		walkAnimationLeft = new Animation(0.1f, walkFramesLeft);
		
		//Initiate Kick Animation
		kickSheet = new Texture(Gdx.files.internal("data/rip_kick.png"));
		TextureRegion[][] tmpkRight = TextureRegion.split(kickSheet, kickSheet.getWidth() / KICK_COLS, kickSheet.getHeight() / KICK_ROWS);
		TextureRegion[][] tmpkLeft = TextureRegion.split(kickSheet, kickSheet.getWidth() / KICK_COLS, kickSheet.getHeight() / KICK_ROWS);
		kickFramesRight = new TextureRegion[KICK_COLS * KICK_ROWS];
		kickFramesLeft = new TextureRegion[KICK_COLS * KICK_ROWS];
		index = 0;
		for (int i = 0; i < KICK_ROWS; i++) {
			for (int j = 0; j < KICK_COLS; j++) {
				kickFramesRight[index++] = tmpkRight[i][j];
			}
		}
		
		index = 0;
		for (int i = 0; i < KICK_ROWS; i++) {
			for (int j = 0; j < KICK_COLS; j++) {
				kickFramesLeft[index] = tmpkLeft[i][j];
				kickFramesLeft[index].flip(true, false);
				index++;
			}
		}
		kickAnimationRight = new Animation(0.05f, kickFramesRight);
		kickAnimationLeft = new Animation(0.05f, kickFramesLeft);
		
		
		//Initiate Hit Animation
		
		//Initiate Punch Animation
		punchSheet = new Texture(Gdx.files.internal("data/rip_punch.png"));
		TextureRegion[][] tmppRight = TextureRegion.split(punchSheet, punchSheet.getWidth() / PUNCH_COLS, punchSheet.getHeight() / PUNCH_ROWS);
		TextureRegion[][] tmppLeft = TextureRegion.split(punchSheet, punchSheet.getWidth() / PUNCH_COLS, punchSheet.getHeight() / PUNCH_ROWS);
		punchFramesRight = new TextureRegion[PUNCH_COLS * PUNCH_ROWS];
		punchFramesLeft = new TextureRegion[PUNCH_COLS * PUNCH_ROWS];
		index = 0;
		for (int i = 0; i < PUNCH_ROWS; i++) {
			for (int j = 0; j < PUNCH_COLS; j++) {
				punchFramesRight[index++] = tmppRight[i][j];
			}
		}
		
		index = 0;
		for (int i = 0; i < PUNCH_ROWS; i++) {
			for (int j = 0; j < PUNCH_COLS; j++) {
				punchFramesLeft[index] = tmppLeft[i][j];
				punchFramesLeft[index].flip(true, false);
				index++;
			}
		}
		punchAnimationRight = new Animation(0.05f, punchFramesRight);
		punchAnimationLeft = new Animation(0.05f, punchFramesLeft);
		
		
		//Set player_animation
		player_animation = walkAnimationRight;
		currentFrame = player_animation.getKeyFrame(stateTime, true);
		
	}
	
	
	
	public float getHealth() {
		return health;
	}



	public void setHealth(float health) {
		this.health = health;
	}

	public boolean getTimeFreeze() {
		return timeFreeze;
	}
	
	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public void flipTimeFreeze() {
		if (timeFreeze == false) {
			timeFreeze = true;
		} else {
			timeFreeze = false;
		}
	}


	public float getPunch_damage() {
		return punch_damage;
	}

	public void setPunch_damage(float punch_damage) {
		this.punch_damage = punch_damage;
	}

	public float getKick_damage() {
		return kick_damage;
	}

	public void setKick_damage(float kick_damage) {
		this.kick_damage = kick_damage;
	}

	public Texture getPunch() {
		return punch;
	}


	public void setPunch(Texture punch) {
		this.punch = punch;
	}
	
	
	public Directions getDir() {
		return dir;
	}


	public void setDir(Directions dir) {
		this.dir = dir;
	}


	public void update() {
		
		bounds.x = this.x;
		bounds.y = this.y;
		
	}
	
	public Rectangle getPunchBoxRight() {
		return punchBoxRight;
	}
	
	public Rectangle getPunchBoxLeft() {
		return punchBoxLeft;
	}

	public void setPunchBoxRight(Rectangle punchBox) {
		this.punchBoxRight = punchBox;
	}
	
	public void setPunchBoxLeft(Rectangle punchBox) {
		this.punchBoxLeft = punchBox;
	}
	
	public boolean isHit(Rectangle attacker) {
		return Intersector.intersectRectangles(hitableBox, attacker);
	}
	
	public boolean punches(Rectangle attacker) {
		switch (this.getDir()) {
			case DIR_LEFT:
				return this.punchBoxLeft.overlaps(attacker);
			case DIR_RIGHT:
				return this.punchBoxRight.overlaps(attacker);
			default:
				return false;
		}
	}
	
	
	public boolean[] collides(ArrayList<Enemy> me) {
		//Create array of booleans in which each boolean corresponds with a direction
		// i.e. [<UP>, <DOWN>, <LEFT>, <RIGHT>]
		boolean[] c = {false, false, false, false};
		//boolean[] c = {true, true, true, true};
		
		for (int i = 0; i < me.size(); i++) {
			Enemy m = me.get(i);
			
			if (Intersector.overlapRectangles(this.hitableBox, m.hitableBox)) {
			//if (this.hitableBox.overlaps(m.hitableBox)) {
				m.setCollides_player(true);
				//Is Left occupied?
				if ((this.hitableBox.x <= (m.hitableBox.x + m.hitableBox.width)) && (this.hitableBox.x >= m.hitableBox.x)) {
					c[2] = true;
				}
				//Is Down occupied
				if ((this.hitableBox.y <= (m.hitableBox.y + m.hitableBox.height)) && (this.hitableBox.y >= m.hitableBox.y)) {
					c[1] = true;
				}
				//Is Right occupied?
				if (((this.hitableBox.x + this.hitableBox.width) >= m.hitableBox.x) && (m.hitableBox.x >= this.hitableBox.x)){
					c[3] = true;
				}
				// Is Up occupied?
				if (((this.hitableBox.y + this.hitableBox.height) >= m.hitableBox.y) && (m.hitableBox.y >= this.hitableBox.y)){
					c[0] = true;
				}
			} else {
				m.setCollides_player(false);
			}
			
		}
		
		return c;
	}
	

	// Remove once spritesheet becomes available
	
	public Texture getPUNCH_RIGHT() {
		return PUNCH_RIGHT;
	}

	public Texture getPUNCH_LEFT() {
		return PUNCH_LEFT;
	}

	public Texture getRIGHT() {
		return RIGHT;
	}

	public Texture getLEFT() {
		return LEFT;
	}
	
	public void setX(int x) {
		this.x = x;
		this.bounds.x = x;
		this.hitableBox.x = x + this.getBoxset();
		this.punchBoxRight.x = x + (width / 2);
		this.punchBoxLeft.x = x; //+ 10; 
		
	}
	
	public void setY(int y) {
		this.y = y;
		this.bounds.y = y;
		this.hitableBox.y = y + (height / 2);
		this.punchBoxLeft.y = y + (height / 2);
		this.punchBoxRight.y = y + (height / 2);
	}

	public Sound getRandomPunch_sounds() {
		int index = rand.nextInt(punch_sounds.length);
		return punch_sounds[index];
	}
	
	
	public static Texture getRight() {
		return RIGHT;
	}

	
	public Animation getPlayer_animation() {
		return player_animation;
	}

	public void setPlayer_animation(Animation player_animation) {
		this.player_animation = player_animation;
	}

	public void setCurrentFrame(float delta) {
		this.stateTime += delta;
		//this.currentFrame = player_animation.getKeyFrame(stateTime, true);
		
		if (player_animation == this.kickAnimationLeft || player_animation == this.kickAnimationRight
			|| player_animation == this.punchAnimationLeft || player_animation == this.punchAnimationRight) {
			this.currentFrame = player_animation.getKeyFrame(stateTime, false);
		} else {
			this.currentFrame = player_animation.getKeyFrame(stateTime, true);
		}
		
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public Animation getWalkAnimationRight() {
		return walkAnimationRight;
	}

	public void setWalkAnimationRight(Animation walkAnimationRight) {
		this.walkAnimationRight = walkAnimationRight;
	}

	public Animation getWalkAnimationLeft() {
		return walkAnimationLeft;
	}

	public void setWalkAnimationLeft(Animation walkAnimationLeft) {
		this.walkAnimationLeft = walkAnimationLeft;
	}

	public Animation getKickAnimationRight() {
		return kickAnimationRight;
	}

	public void setKickAnimationRight(Animation kickAnimationRight) {
		this.kickAnimationRight = kickAnimationRight;
	}

	public Animation getKickAnimationLeft() {
		return kickAnimationLeft;
	}

	public void setKickAnimationLeft(Animation kickAnimationLeft) {
		this.kickAnimationLeft = kickAnimationLeft;
	}

	public Animation getPunchAnimationRight() {
		return punchAnimationRight;
	}

	public void setPunchAnimationRight(Animation punchAnimationRight) {
		this.punchAnimationRight = punchAnimationRight;
	}

	public Animation getPunchAnimationLeft() {
		return punchAnimationLeft;
	}

	public void setPunchAnimationLeft(Animation punchAnimationLeft) {
		this.punchAnimationLeft = punchAnimationLeft;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public boolean isATTACK_ANIMATION() {
		return ATTACK_ANIMATION;
	}

	public void setATTACK_ANIMATION(boolean aTTACK_ANIMATION) {
		ATTACK_ANIMATION = aTTACK_ANIMATION;
	}


	

	
	
}