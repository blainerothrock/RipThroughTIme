package com.rip.levels;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.rip.objects.Enemy;
import com.rip.objects.MovableEntity.Directions;
import com.rip.objects.Player;


public class InputHandler implements InputProcessor {
	Level_1_1 level;
	Player player;
	ArrayList<Enemy> enemies;
	
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
		switch(keycode){
		case Keys.SPACE:
			//player.setTexture(player.getPunch());
			//break;
			switch(player.getDir()) {
			case DIR_LEFT:
				player.setTexture(player.getPUNCH_LEFT());
				break;
			case DIR_RIGHT:
				player.setTexture(player.getPUNCH_RIGHT());
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
			break;
		case Keys.D:
			player.setTexture(player.getRIGHT());
			player.setDir(Directions.DIR_RIGHT);
			break;
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
		case Keys.SPACE:
			switch(player.getDir()) {
			case DIR_LEFT:
				player.setTexture(player.getLEFT());
				break;
			case DIR_RIGHT:
				player.setTexture(player.getRIGHT());
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
				}
			}
			break;
		/*	
		case Keys.W:
			break;
		case Keys.S:
			break;
		case Keys.A:
			player.setTexture(player.getLEFT());
			break;
		case Keys.D:
			player.setTexture(player.getRIGHT());
			break;
			*/
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
