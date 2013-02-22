package com.rip.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Player extends MovableEntity {

	Texture punch;
	float health;
	float attack_damage;
	
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
	public Rectangle punchBoxRight = new Rectangle(this.getX() + (this.width / 2), this.getY() + (this.height / 2), (this.width / 2), (this.height / 2));
	public Rectangle punchBoxLeft =  new Rectangle(this.getX(), this.getY() + (this.height / 2), (this.width / 2), (this.height / 2));
	
	//public Rectangle punchBoxRight = new Rectangle(this.getX() + 60, this.getY() + (this.height / 2), 65, (this.height / 2));
	//public Rectangle punchBoxLeft =  new Rectangle(this.getX() + 10, this.getY() + (this.height / 2), 50, (this.height / 2));
	
	
	public Player(int x, int y, float width, float height, int SPEED, Texture text) {
		super(x, y, width, height, SPEED, text);
		this.health = 100;
		this.attack_damage = 10;
	}
	
	public Player(int x, int y) {
		super(x, y, 128, 162, 3, RIGHT);
		this.health = 100;
		this.attack_damage = 10;
	}
	
	
	public float getHealth() {
		return health;
	}



	public void setHealth(float health) {
		this.health = health;
	}



	public float getAttack_damage() {
		return attack_damage;
	}



	public void setAttack_damage(float attack_damage) {
		this.attack_damage = attack_damage;
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
		return this.punchBoxLeft.overlaps(attacker) ||
				this.punchBoxRight.overlaps(attacker);
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
		this.hitableBox.x = x;
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
	
	
	
	
	
}