package com.gameobject;

import java.awt.Graphics2D;

public abstract class Bullet extends ParticularObject {
	
	public Bullet(float x, float y, float width, float height, int damage, GameWorld gameWorld) {
		super(x, y, width, height, 1 , gameWorld);
		setDamage(damage );
		
	}
	
	public abstract void draw (Graphics2D g2);
	
	public void Update() {
		super.Update();
		setPosX(getPosX() + getSpeedX());
		setPosY(getPosY() + getSpeedY());
		
		
		  ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);//va cham va khac team 
		  if((object != null) && object.getState() ==ALIVE) {
		  setState(DEATH); //set blood cho vien dan bang 0 chuyen ve trang thai death va bi huy di 
		  object.beHurt(getDamage());
		  System.out.println("Bullet set behurt for enemy"); }
		 
	}
	

}
