package com.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class Bullet0 extends Bullet {
	
	private Animation upBulletAnim, downBulletAnim, rightBulletAnim, leftBulletAnim;
	
	public Bullet0 (float x, float y, GameWorld gameWorld) {
		super(x,y,20,20,10,gameWorld);
		
		upBulletAnim = CacheDataLoader.getInstance().getAnimation("bulletup0");
		downBulletAnim = CacheDataLoader.getInstance().getAnimation("bulletdown0");
		rightBulletAnim = CacheDataLoader.getInstance().getAnimation("bulletright0");
		leftBulletAnim = CacheDataLoader.getInstance().getAnimation("bulletleft0");
	}
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();

	}
	@Override
	public void draw(Graphics2D g2) {
		if(getSpeedX()>0) {
			rightBulletAnim.Update(System.nanoTime());
			rightBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}
		
		if(getSpeedX()<0) {
			leftBulletAnim.Update(System.nanoTime());
			leftBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}
		
		if(getSpeedY()>0) {
			downBulletAnim.Update(System.nanoTime());
			downBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}
		if(getSpeedY()<0) {
			upBulletAnim.Update(System.nanoTime());
			upBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}
		
	}
	
	@Override
    public void Update() {
		super.Update();
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}


	

}
