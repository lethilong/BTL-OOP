package com.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class Monster1 extends ParticularObject {
	
	private Animation monsterup1, monsterdown1, monsterleft1, monsterright1;
	private long startTimeToShoot;
	
	private AudioClip monster1;


	public Monster1(float x, float y, GameWorld gameWorld) {
		
		
	
		super(x, y, 100, 95, 100, gameWorld);
		
		monsterup1 = CacheDataLoader.getInstance().getAnimation("monsterup1");
		monsterdown1 = CacheDataLoader.getInstance().getAnimation("monsterdown1");
		monsterleft1 = CacheDataLoader.getInstance().getAnimation("monsterleft1");
		monsterright1 = CacheDataLoader.getInstance().getAnimation("monsterright1");
		
		
		startTimeToShoot = 0;
        setDamage(35);
        setTimeForNoBeHurt(300000000);
        
        monster1 = CacheDataLoader.getInstance().getSound("monster1");
     
	
	}	
	
	@Override
	public void attack() {
		
		monster1.play();
		
		Bullet bullet = new BulletMonster1(getPosX(), getPosY(),getGameWorld()); 
		if(getDirection() == LEFT_DIR) bullet.setSpeedX(-8); 
		if(getDirection() == RIGHT_DIR) bullet.setSpeedX(8); 
		if(getDirection() == UP_DIR) bullet.setSpeedY(-8);
		if(getDirection() == DOWN_DIR) bullet.setSpeedY(8);
		bullet.setTeamType(getTeamType());
		getGameWorld().bulletManager.addObject(bullet);
		
	}
	
	  
    public void Update(){
        super.Update();
        if(System.nanoTime() - startTimeToShoot > 550*10000000){
            attack();
           ;            
            startTimeToShoot = System.nanoTime();
        }
    }
		
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect = getBoundForCollisionWithMap();
		return rect;
	}

	@Override
	public void draw(Graphics2D g2) {
		if(!isObjectOutOfCameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
         
            }else{
                if(getDirection() == LEFT_DIR){
                    monsterleft1.Update(System.nanoTime());
                    monsterleft1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == RIGHT_DIR){
                    monsterright1.Update(System.nanoTime());
                    monsterright1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == UP_DIR){
                    monsterup1.Update(System.nanoTime());
                    monsterup1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == DOWN_DIR){
                    monsterdown1.Update(System.nanoTime());
                    monsterdown1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                
            }
            g2.setColor(Color.GRAY);
            g2.fillRect( (int) (getPosX() - getGameWorld().camera.getPosX() -50), (int)(getPosY() - getGameWorld().camera.getPosY()-70), 100, 10);
            g2.setColor(Color.red);
            g2.fillRect((int) (getPosX() - getGameWorld().camera.getPosX() -50), (int)(getPosY() - getGameWorld().camera.getPosY()-70), getBlood(), 10);
        }
       
    }
		
}
	
	
	


