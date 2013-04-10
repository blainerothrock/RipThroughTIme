package com.rip.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rip.RipGame;


public class SuperApe extends Ape {
	
	protected Animation ape_animation;
	
	private static final int WALK_COLS = 9;
	private static final int WALK_ROWS = 1;
	
	private static final int ATTACK_COLS = 5;
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

	static Texture t = new Texture("data/ape.png");

	public SuperApe(int x, int y) {
		super(x, y); 
		Sound s[] = {Gdx.audio.newSound(Gdx.files.internal("data/GorillaGrunt_01.wav")),
				Gdx.audio.newSound(Gdx.files.internal("data/GorillaGrunt_02.wav")),
				Gdx.audio.newSound(Gdx.files.internal("data/GorillaGrunt_03.wav"))};
		this.hit_sounds = s;
		this.hitableBox = new Rectangle(this.x + boxset, 
				this.y + (height/2), (width * 0.7f), (height / 3));
		
		create_animations();
		// TODO Auto-generated constructor stub
	}
	
	public void create_animations(){
		int index;
		
		//Initiate Walk Animation
		TextureRegion temp;
		walkSheet = new Texture(Gdx.files.internal("data/super_ape_walk.png"));
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

		walkAnimationRight = new Animation(0.08f, walkFramesRight);
		walkAnimationLeft = new Animation(0.08f, walkFramesLeft);
		
		//Initiate Attack Animation
		attackSheet = new Texture(Gdx.files.internal("data/super_ape_smack.png"));
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

		attackAnimationRight = new Animation(0.03f, attackFramesRight);
		attackAnimationLeft = new Animation(0.03f, attackFramesLeft);
		
		
		ape_animation = walkAnimationRight;
		currentFrame = ape_animation.getKeyFrame(stateTime, true);
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	public void setCurrentFrame(float delta) {
		this.stateTime += delta;
		
		if (this.attacking) {
			if ((this.dir == Directions.DIR_LEFT) && !(ape_animation == attackAnimationLeft)) {
				ape_animation = attackAnimationLeft;
			} else if ((this.dir == Directions.DIR_RIGHT) && !(ape_animation == attackAnimationRight)) {
				ape_animation = attackAnimationRight;
			}
		} else  {
			 if ((this.dir == Directions.DIR_LEFT) && !(ape_animation == walkAnimationLeft)) {
				ape_animation = walkAnimationLeft;
			} else if ((this.dir == Directions.DIR_RIGHT) && !(ape_animation == walkAnimationRight)) {
				ape_animation = walkAnimationRight;
			}
		}
		//this.currentFrame = player_animation.getKeyFrame(stateTime, true);
		if (this.ape_animation == this.attackAnimationLeft ||
				this.ape_animation == this.attackAnimationRight) {
			Gdx.app.log(RipGame.LOG, "setAttack");
			this.currentFrame = this.ape_animation.getKeyFrame(this.stateTime, false);
		} else { 
			this.currentFrame = this.ape_animation.getKeyFrame(this.stateTime, true);
		}
	}
	
	public void setAttackAnimationLeft(){
		this.setStateTime(0f);
		this.ape_animation = this.attackAnimationLeft;
		return;
	}
	
	public void setAttackAnimationRight(){
		this.setStateTime(0f);
		this.ape_animation = this.attackAnimationRight;
		return;
	}
	

	public Animation getApe_animation() {
		return ape_animation;
	}

	public void setApe_animation(Animation ape_animation) {
		this.ape_animation = ape_animation;
	}
	
	
	

}

