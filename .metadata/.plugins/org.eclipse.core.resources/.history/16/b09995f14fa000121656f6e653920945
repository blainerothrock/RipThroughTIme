package com.rip.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.rip.RipGame;
import com.rip.objects.MovableEntity.Directions;

import renderers.LevelRenderer;

public abstract class Enemy extends MovableEntity {

	public static boolean HealthDrop;
	protected float health;
	protected float damage;
	boolean collides_player;
	public boolean attacking = false;
	float attack_chance;
	float initiate_attack_chance;
	
	Random rand = new Random();
	protected Sound hit_sounds[];
	
	//collision objects.
	public Intersector in;
	//public Rectangle hitableBox;
	
	boolean flank;
	Random r = new Random();
	int trackX;
	int trackY;
	boolean flankPoint1;
	boolean flankPoint2;
	
	public boolean spawnPoint = false;
	
	//Collision Detection
	boolean Collides_Left = false;
	boolean Collides_Right = false;
	boolean Collides_Up = false;
	boolean Collides_down = false;

	
	public Enemy(int x, int y, float width, float height, Texture texture,
			int SPEED, float health) {
		super(x, y, width, height, SPEED, texture);
		this.health = health;
		collides_player = false;
		make_flanks();
		make_drop();
		//hitableBox = new Rectangle(x, y, width, height);
	}
	
	public Enemy(int x, int y, float width, float height, int SPEED, float health) {
		super(x, y, width, height, SPEED);
		this.health = health;
		collides_player = false;
		make_flanks();
		make_drop();
		//hitableBox = new Rectangle(x, y, width, height);
	}

	public void make_drop() {

		if ((float) Math.random() >= .65) {
			this.HealthDrop = true;
		} else {
			this.HealthDrop = false;
		}
	}
	
	public HealthPack getHealthDrop() {
		HealthPack drop = new HealthPack(this.x + (int) (this.width/2), this.y);
		return drop;
	}
	
	public void make_flanks() {
		if (r.nextBoolean()) {
			flank = false;
		} else {
			Gdx.app.log(RipGame.LOG, "flank true");
			flank = true;
			trackX = 0;
			trackY = 0;
			flankPoint1 = false;
			flankPoint2 = false;
		}
	}
	
	public void setAttackAnimationLeft(){
		return;
	}
	
	public void setAttackAnimationRight(){
		return;
	}

	
	public void attack(Player p, ArrayList<Enemy> e) {
		//(player.getPlayer_animation().isAnimationFinished(player.getStateTime()))
		//Gdx.app.log(RipGame.LOG, this.getDir().toString());
		Gdx.app.log(RipGame.LOG, "Enemy Attack");
		if (this.attacking || p.hit) {
			Gdx.app.log(RipGame.LOG, "Break");
			return;
		}
		attack_chance = (float) Math.random();
		if (attack_chance >= 0.75) {
			Gdx.app.log(RipGame.LOG, "Enemy Attack Really");
			this.attacking = true;
			p.setHealth(p.getHealth() - this.damage);
			attack_chance = 0;
			//Gdx.app.log(RipGame.LOG, String.valueOf(p.getHealth()));
			
			switch (this.getDir()) {
			case DIR_LEFT:
				this.setAttackAnimationLeft();
				p.hitBack(-30, e);
				//Gdx.app.log(RipGame.LOG, "Enemy Attack Left");
				//this.raptor_animation = this.attackAnimationLeft;
				break;
			case DIR_RIGHT:
				this.setAttackAnimationRight();
				p.hitBack(30, e);
				//Gdx.app.log(RipGame.LOG, "Enemy Attack Right");
				//this.raptor_animation = this.attackAnimationRight;
				break;
			default:
				break;
			}
			//attack code
			//set animation
		} else {
			attack_chance = 0;
			//Gdx.app.log(RipGame.LOG, "Enemy Attack Failed");
			return;
		}
	}
	
	public void track(Player p, ArrayList<Enemy> e) {
		
		if (this.attacking) {
			Gdx.app.log(RipGame.LOG, "attacking");
			return;
		}
		
		//Gdx.app.log(RipGame.LOG, "track");
		update_collisions(e);
		if (this.collides_player) {
			Gdx.app.log(RipGame.LOG, "collides player");
			this.initiate_attack_chance = (float) Math.random();
			if (this.initiate_attack_chance >= 0.5f) {
				this.attack(p, e);
			}
			this.initiate_attack_chance = 0f;
			return;
		} 
		if ((p.getY() > this.y) && !(this.Collides_Up)) {
			direct_movement(p);
		} else if (p.getY() > this.y) {
			if (!(this.Collides_down)) {
				flank(p);
			} else {
				return;
			}
		}
		// If enemy can go directly at player, do so
		else if ((p.getX() < this.x) && !(this.Collides_Left)) {
			direct_movement(p);
		// If enemy can go directly at player, do so
		} else if ((p.getX() > this.x) && !(this.Collides_Right)) {
			direct_movement(p);
		// If down is not blocked, flank
		} else if (!(this.Collides_down)) {
			flank(p);
		// Else, stay still
		} else {
			//Gdx.app.log(RipGame.LOG, "else");
			return;
		}
		return;
	}
	
	public void update_collisions(ArrayList<Enemy> e) {
		this.Collides_down = false;
		this.Collides_Left = false;
		this.Collides_Right = false;
		this.Collides_Up = false;
		
		for (int i = 0; i < e.size(); i++) {
			Enemy m = e.get(i);
			
			if (m == this) {
				continue;
			}
			
			if (Intersector.overlapRectangles(this.hitableBox, m.hitableBox)) {
			//if (this.hitableBox.overlaps(m.hitableBox)) {
				
				//Is Left occupied?
				if ((this.hitableBox.x <= (m.hitableBox.x + m.hitableBox.width)) && (this.hitableBox.x >= m.hitableBox.x)) {
					this.Collides_Left = true;
				}
				//Is Down occupied?
				else if ((this.hitableBox.y <= (m.hitableBox.y + m.hitableBox.height)) && (this.hitableBox.y >= m.hitableBox.y)) {
					this.Collides_down = true;
				}
				//Is Right occupied?
				else if (((this.hitableBox.x + this.hitableBox.width) >= m.hitableBox.x) && (m.hitableBox.x >= this.hitableBox.x)){
					this.Collides_Right = true;
				}
				// Is Up occupied?
				else if (((this.hitableBox.y + this.hitableBox.height) >= m.hitableBox.y) && ((m.hitableBox.y + m.hitableBox.height) >= this.hitableBox.y)){
					this.Collides_Up = true;
				}
			
			}
		}
		return;
	}
	
	public void flank(Player p) {
			int dx, dy;
	
			//Gdx.app.log(RipGame.LOG, "flank: ");
	
			if (flank == false) {
				int pX;
				int pY;
				boolean positiveY = r.nextBoolean(); 
				boolean positiveX = r.nextBoolean();
	
				if (positiveX) {
					pX = p.getX() + r.nextInt(50);
				} else {
					pX = p.getX() - r.nextInt(50);
				}
	
				if (positiveY) {
					pY = p.getY() + r.nextInt(50);
				} else {
					pY = p.getY() - r.nextInt(50);
				}
	
				if (pY > 180) { pY = 180; }
	
				dx = pX - x;
				dy = pY - y;
				
				if (dx > 0) {
					dir = Directions.DIR_RIGHT;
				} else if (dx < 0) {
					dir = Directions.DIR_LEFT;
				}
	
				this.setX(this.getX() + (int)((dx - this.SPEED) * LevelRenderer.delta));
				if ((dy > 0) && (this.Collides_Up)) {
					this.setY(this.getY());
				} else if ((dy < 0) && (this.Collides_down)){
					this.setY(this.getY());
				} else {
					this.setY(this.getY() + (int)((dy - this.SPEED) * LevelRenderer.delta));
				}
	
			} else {
				if (trackX == 0 && trackY == 0) {
					Gdx.app.log(RipGame.LOG, "flank Initiated");
	
					trackX = p.getX();
					trackY = p.getY() - (r.nextInt(400-200) + 200);
					Gdx.app.log(RipGame.LOG, "trackX: " + trackX + " trackY: " + trackY );
					Gdx.app.log(RipGame.LOG, "playerX: " + p.getX() + " playerY: " + p.getY() );
				} else if (flankPoint1 == false) {
					dx = trackX - x;
					dy = trackY - y;
					this.setX(this.getX() + (int)((dx - this.SPEED + 2) * LevelRenderer.delta));
					this.setY(this.getY() + (int)((dy - this.SPEED + 2) * LevelRenderer.delta));
					if (x <= trackX + this.width + 10 && x >= trackX - this.width + 10 && y <= trackY + this.height + 10 && y >= trackY - this.height + 10 ) {
						flankPoint1 = true;
						Gdx.app.log(RipGame.LOG, "flankPoin1 = " + flankPoint1);
						if (spawnPoint) {
							trackX = p.getX() - (r.nextInt(500-300) + 300);
							trackY = p.getY();
						} else {
							trackX = p.getX() + (r.nextInt(500-300) + 300);
							trackY = p.getY();
						}
					}
				} else if (flankPoint2 == false) {
					dx = trackX - x;
					dy = trackY - y;
					setX(getX() + (int)((dx - SPEED + 2) * LevelRenderer.delta));
					setY(getY() + (int)((dy - SPEED + 2) * LevelRenderer.delta));
					if (x <= trackX + this.width + 10 && x >= trackX - this.width + 10 && y <= trackY + this.height + 10 && y >= trackY - this.height + 10 ) {
						flankPoint2 = true;
						trackY = p.getY();
						flank = false;
						}
				} 
			}
		}
	
	public void hitBack(int distance, ArrayList<Enemy> e) {
		if (distance == 0) {
			return;
		}
		
		this.setX(this.getX() + distance);
		
		for (Enemy m : e) {
			if (m == this) {
				continue;
			}
			
			if (Intersector.overlapRectangles(this.hitableBox, m.hitableBox)) {
				if (distance < 0) {
					if (((this.hitableBox.x + this.hitableBox.width) >= m.hitableBox.x) && (m.hitableBox.x >= this.hitableBox.x)){
						m.hitBack(distance / 2, e);
						break;
					}
						
				} else {
					if ((this.hitableBox.x <= (m.hitableBox.x + m.hitableBox.width)) && (this.hitableBox.x >= m.hitableBox.x)) {
						m.hitBack(distance / 2, e);
						break;
					}
				}
			}
		}
		
		return;
	}
	
	public void direct_movement(Player p) {
		//Gdx.app.log(RipGame.LOG, "direct");
		int pX = p.getX();
		int pY = p.getY();

		int dx = pX - x;
		int dy = pY - y;


		
		if ((dx > 0) && (this.Collides_Right)) {
			this.setX(this.getX());
		} else if ((dx < 0) && (this.Collides_Left)){
			this.setX(this.getX());
		} else {
			if (dx > 0) {
				dir = Directions.DIR_RIGHT;
			} else if (dx < 0) {
				dir = Directions.DIR_LEFT;
			}
			this.setX(this.getX() + (int)((dx - this.SPEED) * LevelRenderer.delta));
		}
		
		if ((dy > 0) && (this.Collides_Up)) {
			this.setY(this.getY());
		} else if ((dy < 0) && (this.Collides_down)){
			this.setY(this.getY());
		} else {
			this.setY(this.getY() + (int)((dy - this.SPEED) * LevelRenderer.delta));
		}
		
		return;
	}
	
	
	
	public float getDamage() {
		return damage;
	}


	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}


	public boolean isCollides_player() {
		return collides_player;
	}

	public void setCollides_player(boolean collides_player) {
		this.collides_player = collides_player;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	public Sound gethitSound() {
		int index = rand.nextInt(hit_sounds.length);
		return hit_sounds[index];
	}

	public boolean isCollides_Left() {
		return Collides_Left;
	}

	public void setCollides_Left(boolean collides_Left) {
		Collides_Left = collides_Left;
	}

	public boolean isCollides_Right() {
		return Collides_Right;
	}

	public void setCollides_Right(boolean collides_Right) {
		Collides_Right = collides_Right;
	}

	public boolean isCollides_Up() {
		return Collides_Up;
	}

	public void setCollides_Up(boolean collides_Up) {
		Collides_Up = collides_Up;
	}

	public boolean isCollides_down() {
		return Collides_down;
	}

	public void setCollides_down(boolean collides_down) {
		Collides_down = collides_down;
	}
	
	
	

	

}
