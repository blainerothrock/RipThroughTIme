package com.rip.levels;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.rip.RipGame;
import com.rip.objects.MovableEntity.Directions;
import com.rip.objects.Enemy;
import com.rip.objects.Player;


public class InputHandler implements InputProcessor {
	Level_1_1 level;
	Player player;
	ArrayList<Enemy> enemies;
	
	boolean ATTACK_ANIMATION;
	
	int newX;
	int newY;
	
	public InputHandler(Level_1_1 level) {
		this.level = level;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		player = level.getPlayer();
		enemies = level.getEnemies();
		//this.ATTACK_ANIMATION = player.isATTACK_ANIMATION();
		switch(keycode){
		case Keys.K:
			//player.setTexture(player.getPunch());
			//break;
			
			switch(player.getDir()) {
			case DIR_LEFT:
				player.setPlayer_animation(player.getPunchAnimationLeft());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				player.setTexture(player.getPUNCH_LEFT());
				break;
			case DIR_RIGHT:
				player.setPlayer_animation(player.getPunchAnimationRight());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				player.setTexture(player.getPUNCH_RIGHT());
				break;
			default:
				break;
				
			}
			
			break;
		case Keys.L:
			//player.setTexture(player.getPunch());
			//break;
			
			switch(player.getDir()) {
			case DIR_LEFT:
				player.setPlayer_animation(player.getKickAnimationLeft());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				break;
			case DIR_RIGHT:
				player.setPlayer_animation(player.getKickAnimationRight());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				break;
			default:
				break;
			}
			
			break;
		
		case Keys.W:
			break;
		case Keys.S:
			break;
		case Keys.A:
			player.setTexture(player.getLEFT());
			player.setDir(Directions.DIR_LEFT);
			player.setPlayer_animation(player.getWalkAnimationLeft());
			player.setStateTime(0f);
			player.setCurrentFrame(0f);
			break;
		case Keys.D:
			player.setTexture(player.getRIGHT());
			player.setDir(Directions.DIR_RIGHT);
			player.setPlayer_animation(player.getWalkAnimationRight());
			player.setStateTime(0f);
			player.setCurrentFrame(0f);
			break;
		
		case Keys.O:
			player.flipTimeFreeze();
		
		default:
			break;
		}
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		player = level.getPlayer();
		switch(keycode) {
		case Keys.K:
			switch(player.getDir()) {
			case DIR_LEFT:
				/*
				//player.setTexture(player.getLEFT());
				player.setPlayer_animation(player.getPunchAnimationLeft());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				player.setATTACK_ANIMATION(true);
				
				while (!player.getPlayer_animation().isAnimationFinished(player.getStateTime())) {
					continue;
				}
				
				player.setATTACK_ANIMATION(false);
				*/
				// original code
				player.setPlayer_animation(player.getWalkAnimationLeft());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				break;
			case DIR_RIGHT:
				/*
				//player.setTexture(player.getRIGHT());
				player.setPlayer_animation(player.getPunchAnimationRight());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				player.setATTACK_ANIMATION(true);
				float delta = 0f;
				while (!player.getPlayer_animation().isAnimationFinished(delta)) {
					delta += Gdx.graphics.getDeltaTime();
				}
				
				player.setATTACK_ANIMATION(false);
				*/
				//original code
				player.setPlayer_animation(player.getWalkAnimationRight());
				player.setStateTime(0f);
				player.setCurrentFrame(0f);
				break;
			default:
				break;
			}
			
			//punching action
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				if (player.punches(e.hitableBox)) {
					//Gdx.app.log(RipGame.LOG, "Punch");
					Sound punch = player.getRandomPunch_sounds();
					punch.play(1.0f);
					e.setHealth(e.getHealth() - player.getAttack_damage());
					
					// Cause enemy to be pushed back
					switch(player.getDir()) {
					case DIR_LEFT:
						e.setX(e.getX() - 10);
						break;
					case DIR_RIGHT:
						e.setX(e.getX() + 10);
						break;
					default:
						break;
					}
				}
			}
			break;
			case Keys.L:
				switch(player.getDir()) {
				case DIR_LEFT:
					/*
					//player.setTexture(player.getLEFT());
					player.setPlayer_animation(player.getKickAnimationLeft());
					player.setStateTime(0f);
					player.setCurrentFrame(0f);
					player.setATTACK_ANIMATION(true);
					
					while (!player.getPlayer_animation().isAnimationFinished(player.getStateTime())) {
						continue;
					}
					
					player.setATTACK_ANIMATION(false);
					//original code 
					*/
					player.setPlayer_animation(player.getWalkAnimationLeft());
					player.setStateTime(0f);
					player.setCurrentFrame(0f);
					break;
				case DIR_RIGHT:
					/*
					//player.setTexture(player.getRIGHT());
					player.setPlayer_animation(player.getKickAnimationRight());
					player.setStateTime(0f);
					player.setCurrentFrame(0f);
					player.setATTACK_ANIMATION(true);
					
					while (!player.getPlayer_animation().isAnimationFinished(player.getStateTime())) {
						continue;
					}
					
					player.setATTACK_ANIMATION(false);
					*/
					//original code
					player.setPlayer_animation(player.getWalkAnimationRight());
					player.setStateTime(0f);
					player.setCurrentFrame(0f);
					break;
				default:
					break;
				}
				
				//punching action
				for (int i = 0; i < enemies.size(); i++) {
					Enemy e = enemies.get(i);
					if (player.punches(e.hitableBox)) {
						//Gdx.app.log(RipGame.LOG, "Punch");
						Sound punch = player.getRandomPunch_sounds();
						punch.play(1.0f);
						e.setHealth(e.getHealth() - player.getAttack_damage());
						
						// Cause enemy to be pushed back
						switch(player.getDir()) {
						case DIR_LEFT:
							e.setX(e.getX() - 10);
							break;
						case DIR_RIGHT:
							e.setX(e.getX() + 10);
							break;
						default:
							break;
						}
					}
				}
				break;
		default:
			break;
		}
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
