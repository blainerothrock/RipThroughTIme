package com.rip.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Raptor extends LowLevelEnemy {
	
	protected Animation raptor_animation;
	protected TextureRegion currentFrame;
	
	protected float stateTime = 0f;

	private static final int WALK_COLS = 10;
	private static final int WALK_ROWS = 1;
	
	protected Animation walkAnimationRight;
	protected Animation walkAnimationLeft;
	protected Texture walkSheet;
	protected TextureRegion[] walkFramesRight;
	protected TextureRegion[] walkFramesLeft;
	protected TextureRegion currentwalkFrame;
	
	public Raptor(int x, int y) {
		super(x, y, 224, 174, new Texture("data/raptor.png"), 3);
		create_animations();
	}
	
	public void create_animations(){
		int index;
		
		//Initiate Walk Animation
		TextureRegion temp;
		walkSheet = new Texture(Gdx.files.internal("data/raptor_walk.png"));
		TextureRegion[][] tmpwRight = TextureRegion.split(walkSheet, walkSheet.getWidth() / WALK_COLS, walkSheet.getHeight() / WALK_ROWS);
		TextureRegion[][] tmpwLeft = TextureRegion.split(walkSheet, walkSheet.getWidth() / WALK_COLS, walkSheet.getHeight() / WALK_ROWS);
		walkFramesRight = new TextureRegion[WALK_COLS * WALK_ROWS];
		walkFramesLeft = new TextureRegion[WALK_COLS * WALK_ROWS];
		index = 0;
		for (int i = 0; i < WALK_ROWS; i++) {
			for (int j = 0; j < WALK_COLS; j++) {
				temp = tmpwLeft[i][j];
				walkFramesLeft[index] = temp;
				//walkFramesLeft[index] = temp;
				index++;
			}
		}
		
		
		index = 0;
		for (int i = 0; i < WALK_ROWS; i++) {
			for (int j = 0; j < WALK_COLS; j++) {
				temp = tmpwRight[i][j];
				walkFramesRight[index] = temp;
				walkFramesRight[index].flip(true, false);
				index++;
			}
		}
		/*
		for (TextureRegion region : walkFramesLeft) {
			region.flip(true, false);
		}
		*/
		walkAnimationRight = new Animation(0.075f, walkFramesRight);
		walkAnimationLeft = new Animation(0.075f, walkFramesLeft);
		
		
		raptor_animation = walkAnimationRight;
		currentFrame = raptor_animation.getKeyFrame(stateTime, true);
	}
	
	public void setCurrentFrame(float delta) {
		this.stateTime += delta;
		if ((this.dir == Directions.DIR_LEFT) && !(raptor_animation == walkAnimationLeft)) {
			raptor_animation = walkAnimationLeft;
		} else if ((this.dir == Directions.DIR_RIGHT) && !(raptor_animation == walkAnimationRight)) {
			raptor_animation = walkAnimationRight;
		}
		//this.currentFrame = player_animation.getKeyFrame(stateTime, true);
		this.currentFrame = raptor_animation.getKeyFrame(this.stateTime, true);
		
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
}
