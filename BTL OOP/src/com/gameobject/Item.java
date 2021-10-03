package com.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;

public class Item extends ParticularObject {
	
	private FrameImage blood, damage, downdamage;
	
	private static final int BLOOD = 1;
	private static final int DAMAGE =2;
	private static final int DOWNDAMAGE = 3;
	private int type;

	public Item(float x, float y, GameWorld gameWorld) {
		super(x, y, 20, 20, 1, gameWorld);
		blood = CacheDataLoader.getInstance().getFrameImage("itemblood");
		damage = CacheDataLoader.getInstance().getFrameImage("itemdamage");
		downdamage = CacheDataLoader.getInstance().getFrameImage("itemdowndamage");
		setDamage(0);
		
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		if (getType() == BLOOD) {
			blood.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}else if (getType() == DAMAGE) {
			damage.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}else {
			downdamage.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
		}
		
	}
	
	
	public void Update() {
		
		
		  Rectangle rect = getGameWorld().player.getBoundForCollisionWithMap();
		  
		  if(rect.intersects(getBoundForCollisionWithMap())&&(getType() == BLOOD)) {
			  setState(DEATH);
			  getGameWorld().player.setBlood(getGameWorld().player.getBlood()+10);
			 
		  }
		  if(rect.intersects(getBoundForCollisionWithMap())&&(getType() == DAMAGE)) {
			  setState(DEATH);
			  getGameWorld().player.setBulletDamage(getGameWorld().player.getBulletDamage()+5);
		  }
		  if(rect.intersects(getBoundForCollisionWithMap())&&(getType() == DOWNDAMAGE)) {
			  setState(DEATH);
			  getGameWorld().player.setBulletDamage(getGameWorld().player.getBulletDamage()-5);
		  }
		  
		  
		  
		 
	}

	@Override
	public void attack() {
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		return getBoundForCollisionWithMap();
		
	}

	

}
