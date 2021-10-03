package com.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Hashtable;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class Boss extends ParticularObject{
	
	private Animation bossleft, bossright;
	
    private long startTimeForAttacked;
    
    private Hashtable<String, Long> time = new Hashtable<String, Long>(); 
    private String[] Type = new String[4];
    private int Index = 0;
    private long lastTime;
    
    private AudioClip boss;
    
    public Boss (float x, float y, GameWorld gameWorld) {
        super(x, y, 100, 60, 100, gameWorld);
        bossleft = CacheDataLoader.getInstance().getAnimation("bossleft");
        bossright = CacheDataLoader.getInstance().getAnimation("bossright");
        
        setTimeForNoBeHurt(1500*1000000);
        setDamage(15);
        boss = CacheDataLoader.getInstance().getSound("boss");
        Type[0] = "NONE";
        Type[1] = "attack";
        Type[2] = "NONE";
        Type[3] = "move";
        
        time.put("NONE", new Long(500)); //ms
        time.put("attack", new Long(1000));
        time.put("move", new Long(4500));
        
    }

    public void Update(){
        super.Update();
        
        if(getGameWorld().player.getPosX() > getPosX())
            setDirection(RIGHT_DIR);
        else setDirection(LEFT_DIR);
        
        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 300){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }
        
        if(!Type[Index].equals("NONE")){
            if(Type[Index].equals("attack")){
                
				  boss.play();
				  Bullet bullet = new BulletBoss(getPosX(), getPosY() , getGameWorld());
				  if(getDirection() == LEFT_DIR) bullet.setSpeedX(-4); 
				  else bullet.setSpeedX(4); 
				  bullet.setTeamType(getTeamType());
				  getGameWorld().bulletManager.addObject(bullet);
				 
            }else if(Type[Index].equals("move")){
                
                if(getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null) {
                	
                	//setDirection(RIGHT_DIR);
                    setSpeedX(5);
                    
                }
                if(getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null) {
                	//setDirection(LEFT_DIR);
                    setSpeedX(-5);
                }

                if(getGameWorld().physicalMap.haveCollisionWithUpWall(getBoundForCollisionWithMap())!=null) {
                	
                	
                    setSpeedY(5);
                    
                }

                if(getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap())!=null) {
                	
                	
                    setSpeedY(-5);
                    
                }
                
                setPosX(getPosX() + getSpeedX());
                setPosY(getPosY() + getSpeedY());
            }
        }else{
            // stop attack
            setSpeedX(0);
            setSpeedY(0);
        }
        
    }
  
    

    @Override
    public void attack() {
    
        
        if(System.currentTimeMillis() - lastTime > time.get(Type[Index])){
            lastTime = System.currentTimeMillis();
            
            Index ++;
            if(Index >= Type.length) Index = 1;
            
            if(Type[Index].equals("move")){
                if(getPosX() < getGameWorld().player.getPosX()) setSpeedX(5);
                else setSpeedX(-5);
                if(getPosY() < getGameWorld().player.getPosY()) setSpeedY(5);
                else setSpeedY(-5);
            }
            
        }
    
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
       
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        
        if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
        {
            System.out.println("Plash...");
        }else{
            
           if (getDirection() ==  RIGHT_DIR) {
        	   bossright.Update(System.nanoTime());
               bossright.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
           }
           else if (getDirection() == LEFT_DIR){
        	   bossleft.Update(System.nanoTime());
               bossleft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
           }
        }
       // drawBoundForCollisionWithEnemy(g2);
        g2.setColor(Color.GRAY);
        g2.fillRect((int) (getPosX() - getGameWorld().camera.getPosX()-70),(int)(getPosY() - getGameWorld().camera.getPosY() -70), 100, 10);
        g2.setColor(Color.red);
        g2.fillRect((int) (getPosX()- getGameWorld().camera.getPosX()- 70),(int)(getPosY() - getGameWorld().camera.getPosY()-70), getBlood(), 10);
    }

}
