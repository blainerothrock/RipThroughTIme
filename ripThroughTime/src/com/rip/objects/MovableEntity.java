package com.rip.objects;

import renderers.LevelRender;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	
	public enum Directions { DIR_LEFT, DIR_RIGHT };
		
	Directions dir = Directions.DIR_RIGHT;
	
	protected TextureRegion currentFrame;
	
	protected float stateTime;

	public MovableEntity(int x, int y, float width, float height, int SPEED) {
		super(x, y, width, height);
		this.SPEED = SPEED;
		//this.createAnimations();

	}
	
	public MovableEntity(int x, int y, float width, float height, int SPEED, Texture texture) {
		super(x, y, width, height, texture);
		this.SPEED = SPEED;
		//this.createAnimations();
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

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	/*
	public void track(Player p) {
		int pX = p.getX();
		int pY = p.getY();

		int dx = pX - x;
		int dy = pY - y;

		this.setX(this.getX() + (int)((dx - this.SPEED) * LevelRender.delta));


		this.setY(this.getY() + (int)((dy - this.SPEED) * LevelRender.delta));


	}

	 */

}