package com.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class BulletMonster1 extends Bullet {
	
	//private Animation bulletright1, bulletleft1, bulletup1, bulletdown1;
	private Animation bullet1 ;

	public BulletMonster1(float x, float y, GameWorld gameWorld) {
		super(x, y, 50, 50, 10,gameWorld);
		if(gameWorld.play==2) {
			setDamage(getDamage()+10);
		}
		bullet1 = CacheDataLoader.getInstance().getAnimation("fire");
	}
	
	 
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
    	  bullet1.Update(System.nanoTime());
		  bullet1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int)getPosY() - (int) getGameWorld().camera.getPosY(), g2);
			
          //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
            
        super.Update();
    }

    @Override
    public void attack() {}

}
