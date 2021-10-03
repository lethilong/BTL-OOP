package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;

import java.awt.*;

public class Monster2 extends ParticularObject {
    private Animation monsterup4, monsterright4, monsterleft4, monsterdown4;
    
    
    
    public Monster2( float x, float y ,GameWorld gameWorld) {
        super(x, y, 98, 69, 100,gameWorld);
        setTimeForNoBeHurt(700000000);

        
      
        
        setDamage(10);
      
     	  
    	   
       
        
        monsterup4 = CacheDataLoader.getInstance().getAnimation("monsterup4");
        monsterdown4 = CacheDataLoader.getInstance().getAnimation("monsterdown4");
        monsterleft4 = CacheDataLoader.getInstance().getAnimation("monsterleft4");
        monsterright4 = CacheDataLoader.getInstance().getAnimation("monsterright4");
        
        
    }
    
    

    @Override
    public void attack() {

    }

    public void Update(){
        super.Update();

    
    		

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

    public void draw(Graphics2D g2) {
        float playerX = getGameWorld().player.getPosX();
        float playerY = getGameWorld().player.getPosY();
        float speed=0.5f;

        
		/*
		 * if (getPosY() > playerY){ setDirection(UP_DIR); setPosY(getPosY() - speed); }
		 * else { if (getPosY() == playerY){ if(getPosX() < playerX){
		 * setDirection(RIGHT_DIR); setPosX(getPosX() + speed); }else {
		 * setDirection(LEFT_DIR); setPosX(getPosX() - speed); } } else {
		 * setDirection(DOWN_DIR); setPosY(getPosY() + speed); }
		 */
        
        if(getPosY()>playerY) {
        	setDirection(UP_DIR);
            setPosY(getPosY()-speed);
        
        }else {
        	setDirection(DOWN_DIR);
            setPosY(getPosY()+speed);
        }
        if(getPosX()>playerX) {
        	setDirection(LEFT_DIR);
           setPosX(getPosX()-speed);
        
        }else {
        	setDirection(RIGHT_DIR);
            setPosX(getPosX()+speed);
        }
        
        	



        if(!isObjectOutOfCameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
             
            }else{
                if(getDirection() == LEFT_DIR){
                    monsterleft4.Update(System.nanoTime());
                    monsterleft4.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == RIGHT_DIR){
                    monsterright4.Update(System.nanoTime());
                    monsterright4.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == UP_DIR){
                    monsterup4.Update(System.nanoTime());
                    monsterup4.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
                if(getDirection() == DOWN_DIR){
                    monsterdown4.Update(System.nanoTime());
                    monsterdown4.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
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
