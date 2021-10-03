package com.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class BulletBoss extends Bullet  {
	
	private Animation bulletBoss;
	private long startTimeForChangeSpeedY;
	
	public BulletBoss(float x, float y, GameWorld gameWorld) {
		
		super(x, y, 20, 20, 10, gameWorld);
		if(gameWorld.play==2) {
			setDamage(getDamage()+10);
		}
		bulletBoss = CacheDataLoader.getInstance().getAnimation("bulletboss");
	}
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
	}
	
	@Override
	public void draw (Graphics2D g2) {
		bulletBoss.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
	}
	
	private void changeSpeedY() {
		
		if(System.currentTimeMillis() % 3 == 0){
            setSpeedY(getSpeedX());
        }else if(System.currentTimeMillis() % 3 == 1){
            setSpeedY(-getSpeedX());
        }else {
            setSpeedY(0);
        }
		
	}
	
	@Override
	public void Update() {
		super.Update();
		
		if(System.nanoTime() - startTimeForChangeSpeedY > 500000000) {
			startTimeForChangeSpeedY = System.nanoTime();
			changeSpeedY();
 		}
		
	}
	
	@Override
	public void attack() {}

}
