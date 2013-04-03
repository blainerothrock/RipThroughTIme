package com.rip.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rip.RipGame;


public class GoldenRaptor extends MiniBoss {
	
	protected Animation raptor_animation;
	protected TextureRegion currentFrame;
	
	protected float stateTime = 0f;

	private static final int WALK_COLS = 10;
	private static final int WALK_ROWS = 1;
	
	private static final int ATTACK_COLS = 3;
	private static final int ATTACK_ROWS = 1;
	
	protected Animation walkAnimationRight;
	protected Animation walkAnimationLeft;
	protected Texture walkSheet;
	protected TextureRegion[] walkFramesRight;
	protected TextureRegion[] walkFramesLeft;
	protected TextureRegion currentwalkFrame;
	
	protected Animation attackAnimationRight;
	protected Animation attackAnimationLeft;
	protected Texture attackSheet;
	protected TextureRegion[] attackFramesRight;
	protected TextureRegion[] attackFramesLeft;
	protected TextureRegion currentattackFrame;
	
	
	public GoldenRaptor(int x, int y) {
		super(x, y, 224, 174, 3);
		create_animations();
		Sound s[] = {Gdx.audio.newSound(Gdx.files.internal("data/RapterGrunt_01.wav")),
				Gdx.audio.newSound(Gdx.files.internal("data/RapterGrunt_02.wav")),
				Gdx.audio.newSound(Gdx.files.internal("data/RapterGrunt_03.wav"))};
		this.hit_sounds = s;
	}
	
	public void create_animations(){
		int index;
		
		//Initiate Walk Animation
		TextureRegion temp;
		walkSheet = new Texture(Gdx.files.internal("data/golden_raptor_walk.png"));
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

		walkAnimationRight = new Animation(0.075f, walkFramesRight);
		walkAnimationLeft = new Animation(0.075f, walkFramesLeft);
		
		//Initiate Attack Animation
		attackSheet = new Texture(Gdx.files.internal("data/golden_raptor_bite.png"));
		TextureRegion[][] tmpaRight = TextureRegion.split(attackSheet, attackSheet.getWidth() / ATTACK_COLS, attackSheet.getHeight() / ATTACK_ROWS);
		TextureRegion[][] tmpaLeft = TextureRegion.split(attackSheet, attackSheet.getWidth() / ATTACK_COLS, attackSheet.getHeight() / ATTACK_ROWS);
		attackFramesRight = new TextureRegion[ATTACK_COLS * ATTACK_ROWS];
		attackFramesLeft = new TextureRegion[ATTACK_COLS * ATTACK_ROWS];
		index = 0;
		for (int i = 0; i < ATTACK_ROWS; i++) {
			for (int j = 0; j < ATTACK_COLS; j++) {
				temp = tmpaLeft[i][j];
				attackFramesLeft[index] = temp;
				//walkFramesLeft[index] = temp;
				index++;
			}
		}
		
		
		index = 0;
		for (int i = 0; i < ATTACK_ROWS; i++) {
			for (int j = 0; j < ATTACK_COLS; j++) {
				temp = tmpaRight[i][j];
				attackFramesRight[index] = temp;
				attackFramesRight[index].flip(true, false);
				index++;
			}
		}

		attackAnimationRight = new Animation(0.2f, attackFramesRight);
		attackAnimationLeft = new Animation(0.2f, attackFramesLeft);
		
		
		raptor_animation = walkAnimationRight;
		currentFrame = raptor_animation.getKeyFrame(stateTime, true);
	}
	
	public void setCurrentFrame(float delta) {
		this.stateTime += delta;
		
		if (this.attacking) {
			if ((this.dir == Directions.DIR_LEFT) && !(raptor_animation == attackAnimationLeft)) {
				raptor_animation = attackAnimationLeft;
			} else if ((this.dir == Directions.DIR_RIGHT) && !(raptor_animation == attackAnimationRight)) {
				raptor_animation = attackAnimationRight;
			}
		} else  {
			 if ((this.dir == Directions.DIR_LEFT) && !(raptor_animation == walkAnimationLeft)) {
				raptor_animation = walkAnimationLeft;
			} else if ((this.dir == Directions.DIR_RIGHT) && !(raptor_animation == walkAnimationRight)) {
				raptor_animation = walkAnimationRight;
			}
		}
		//this.currentFrame = player_animation.getKeyFrame(stateTime, true);
		if (this.raptor_animation == this.attackAnimationLeft ||
				this.raptor_animation == this.attackAnimationRight) {
			Gdx.app.log(RipGame.LOG, "setAttack");
			this.currentFrame = this.raptor_animation.getKeyFrame(this.stateTime, false);
		} else { 
			this.currentFrame = this.raptor_animation.getKeyFrame(this.stateTime, true);
		}
	}
	
	public void setAttackAnimationLeft(){
		this.setStateTime(0f);
		this.raptor_animation = this.attackAnimationLeft;
		return;
	}
	
	public void setAttackAnimationRight(){
		this.setStateTime(0f);
		this.raptor_animation = this.attackAnimationRight;
		return;
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	public Animation getRaptor_animation() {
		return raptor_animation;
	}

	public void setRaptor_animation(Animation raptor_animation) {
		this.raptor_animation = raptor_animation;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	
	
}

