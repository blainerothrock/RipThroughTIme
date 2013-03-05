package com.rip.objects;

import java.util.Random;

import renderers.LevelRender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rip.RipGame;

public abstract class MovableEntity extends Entity {
	
	protected int SPEED;
	protected Animation walkAnimationRight;
	protected Animation walkAnimationLeft;
	protected Animation attackAnimationRight;
	protected Animation attackAnimationLeft;
	
	protected TextureRegion[] walkFramesRight;
	protected TextureRegion[] walkFramesLeft;
	protected TextureRegion[] attackFramesRight;
	protected TextureRegion[] attackFramesLeft;
	
	//SpriteBatch spriteBatch;
	
	public static enum Directions { DIR_LEFT, DIR_RIGHT };
		
	public Directions dir = Directions.DIR_RIGHT;
	
	protected TextureRegion currentFrame;
	
	protected float stateTime;
	
	boolean flank;
	Random r = new Random();
	int trackX;
	int trackY;
	boolean flankPoint1;
	boolean flankPoint2;
	
	public boolean spawnPoint = false; // true = right (facing left), false = left (facing right)

	public MovableEntity(int x, int y, float width, float height, int SPEED) {
		super(x, y, width, height);
		this.SPEED = SPEED;
		//this.createAnimations();
		
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
	
	public MovableEntity(int x, int y, float width, float height, int SPEED, Texture texture) {
		super(x, y, width, height, texture);
		this.SPEED = SPEED;
		//this.createAnimations();
		
//		if (r.nextInt(4) < 3) {
//			flank = false;
//		} else {
			flank = true;
			trackX = 0;
			trackY = 0;
			flankPoint1 = false;
			flankPoint2 = false;
//		}
	}
	
	public void createAnimations() {
		
	}

	public int getSPEED() {
		return SPEED;
	}

	public void setSPEED(int sPEED) {
		SPEED = sPEED;
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

	public Animation getAttackAnimationRight() {
		return attackAnimationRight;
	}

	public void setAttackAnimationRight(Animation attackAnimationRight) {
		this.attackAnimationRight = attackAnimationRight;
	}

	public Animation getAttackAnimationLeft() {
		return attackAnimationLeft;
	}

	public void setAttackAnimationLeft(Animation attackAnimationLeft) {
		this.attackAnimationLeft = attackAnimationLeft;
	}

	public TextureRegion[] getWalkFramesRight() {
		return walkFramesRight;
	}

	public void setWalkFramesRight(TextureRegion[] walkFramesRight) {
		this.walkFramesRight = walkFramesRight;
	}

	public TextureRegion[] getWalkFramesLeft() {
		return walkFramesLeft;
	}

	public void setWalkFramesLeft(TextureRegion[] walkFramesLeft) {
		this.walkFramesLeft = walkFramesLeft;
	}

	public TextureRegion[] getAttackFramesRight() {
		return attackFramesRight;
	}

	public void setAttackFramesRight(TextureRegion[] attackFramesRight) {
		this.attackFramesRight = attackFramesRight;
	}

	public TextureRegion[] getAttackFramesLeft() {
		return attackFramesLeft;
	}

	public void setAttackFramesLeft(TextureRegion[] attackFramesLeft) {
		this.attackFramesLeft = attackFramesLeft;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	public Directions getDir() {
		return dir;
	}
	
	public void flipDir() {
		if (dir == Directions.DIR_LEFT) {
			dir = Directions.DIR_RIGHT;
		} else {
			dir = Directions.DIR_LEFT;
		}
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	public void track(Player p) {
		int dx, dy;
		
//		Gdx.app.log(RipGame.LOG, "flank: " + flank);
		
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

			this.setX(this.getX() + (int)((dx - this.SPEED) * LevelRender.delta));
			this.setY(this.getY() + (int)((dy - this.SPEED) * LevelRender.delta));
			
			
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
				this.setX(this.getX() + (int)((dx - this.SPEED + 2) * LevelRender.delta));
				this.setY(this.getY() + (int)((dy - this.SPEED + 2) * LevelRender.delta));
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
				setX(getX() + (int)((dx - SPEED + 2) * LevelRender.delta));
				setY(getY() + (int)((dy - SPEED + 2) * LevelRender.delta));
				if (x <= trackX + this.width + 10 && x >= trackX - this.width + 10 && y <= trackY + this.height + 10 && y >= trackY - this.height + 10 ) {
					flankPoint2 = true;
					trackY = p.getY();
					flank = false;
					}
			} 
		}

	}

	

}