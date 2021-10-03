package com.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;
import com.effect.CacheDataLoader;

public class Player extends Human {
	
	public static final int RUNSPEED = 3;
	private Animation up, down, left, right, stopup, stopdown, stopleft, stopright;
	
	private long lastShootingTime;
    private boolean isShooting = false;
    private int bulletDamage;
    private AudioClip player;

	
	

	public Player(float x, float y, GameWorld gameWorld) {
		super(x, y, 35, 50, 100, gameWorld);
		
		player = CacheDataLoader.getInstance().getSound("player");
		
		setTeamType(LEAGUE_TEAM);

        setTimeForNoBeHurt(1200*1000000);
        
        setBulletDamage(10);
        
        up = CacheDataLoader.getInstance().getAnimation("up");
        down = CacheDataLoader.getInstance().getAnimation("down");
        left = CacheDataLoader.getInstance().getAnimation("left");
        right = CacheDataLoader.getInstance().getAnimation("right");
        stopup = CacheDataLoader.getInstance().getAnimation("stopup");
        stopdown = CacheDataLoader.getInstance().getAnimation("stopdown");
        stopleft = CacheDataLoader.getInstance().getAnimation("stopleft");
        stopright = CacheDataLoader.getInstance().getAnimation("stopright");
       
	}
	public int getBulletDamage() {
		return bulletDamage;
	}
	public void setBulletDamage(int bulletDamage) {
		this.bulletDamage=bulletDamage;
	}
	
	
	@Override
	public void Update() {
		super.Update();
		
		if(isShooting){
            if(System.nanoTime() - lastShootingTime > 400*1000000){
                isShooting = false;
            }
        }
		
		
	
		/* up.reset(); down.reset(); left.reset(); right.reset(); */
		   
		 }
	 
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect = getBoundForCollisionWithMap();
		return rect;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		 switch(getState()){
	        
         case ALIVE:
         case NOBEHURT: //ca 2 truong hop alive vaf nobe hurt deu thuc hien doan lenh duoi
             if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1) //lam cho con nhan vat nhap nhay, lam ra luc co hien ra luc khong
             {
                 System.out.println("Plash...");
             }else{
            	
            	 if (getDirection()==UP_DIR) {
            		 if(getSpeedY()<0) {
            		 
            		 up.Update(System.nanoTime());
            		 
            		 up.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            		 }else {
            			 
            			 stopup.Update(System.nanoTime());
                		 
                		 stopup.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            			 
            		 }
            		 
            		
            	 } 
            	 if (getDirection()==DOWN_DIR) {
            		 if(getSpeedY()>0) {
            		 down.Update(System.nanoTime());
            		 down.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            		// if (down.getCurrentFrame()==1) down.setIgnoreFrame(0);
            		 }else {
            			 stopdown.Update(System.nanoTime());
                		 stopdown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            			 
            		 }
            	 }
            	 if (getDirection()==LEFT_DIR) {
            		 
            		 if(getSpeedX()<0) {
            		 
            		 left.Update(System.nanoTime());
            		 left.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            		
            		 }else {
            			 stopleft.Update(System.nanoTime());
                		 stopleft.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            		 }
            	 }
            	 if (getDirection()==RIGHT_DIR) {
            		 
            		 if(getSpeedX()>0) {
            			 
            		
            		 right.Update(System.nanoTime());
            		 right.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            		
            		 }else {
            			 stopright.Update(System.nanoTime());
                		 stopright.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            			 
            		 }
            	 }
            	 
            	 
             }
         case BEHURT:
        	 
        	 break;
         case FEY:
        	 
        	 break;
        
        	 
       
		 }
		 g2.setColor(Color.GRAY);
	        g2.fillRect((int) (getPosX() - getGameWorld().camera.getPosX()-25),(int)(getPosY() - getGameWorld().camera.getPosY() -47), 50, 4);
	        g2.setColor(Color.red);
	        g2.fillRect((int) (getPosX()- getGameWorld().camera.getPosX()-25),(int)(getPosY() - getGameWorld().camera.getPosY()-47), getBlood()/2, 4);
	        
			
			  g2.setColor(Color.GRAY); g2.fillRect((int) (getPosX() -
			  getGameWorld().camera.getPosX()-25),(int)(getPosY() -
			  getGameWorld().camera.getPosY() -42), 50, 4); g2.setColor(Color.blue);
			  g2.fillRect((int) (getPosX()-
			  getGameWorld().camera.getPosX()-25),(int)(getPosY() -
			  getGameWorld().camera.getPosY()-42),getBulletDamage(), 4);
			 
	        
		 //drawBoundForCollisionWithMap(g2);
	     //drawBoundForCollisionWithEnemy(g2);
		
	}
	
	

	@Override
	public void run() {
		if(getDirection() == LEFT_DIR) {
			setSpeedX(-3);
			//left.reset();
		}
            
		if(getDirection() == RIGHT_DIR) {
			 setSpeedX(3);
			// right.reset();
		}
           
		if(getDirection() == UP_DIR) {
			setSpeedY(-3);
			//up.reset();
		}
            
		if(getDirection() == DOWN_DIR) {
            setSpeedY(3);
            //down.reset();
		
		}
	}

	@Override
	public void stopRun() {
		
		if(getDirection() == LEFT_DIR) {
			setSpeedX(0);
			
		}
		
		if(getDirection() == RIGHT_DIR) {
			setSpeedX(0);
			
		}
		
		if(getDirection() == UP_DIR) {
			
			setSpeedY(0);
			
		}
		if(getDirection() == DOWN_DIR) {
			setSpeedY(0);
	
		}
		
		/*
		 * setSpeedX(0); setSpeedY(0); left.reset(); right.reset(); up.reset();
		 * down.reset();
		 */
		//left.unIgnoreFrame(0);
		//right.unIgnoreFrame(0);
		//up.unIgnoreFrame(0);
		//down.unIgnoreFrame(0);
		
		
	}

	@Override
	public void attack() {
		if(!isShooting) {
			
			player.play();
			 Bullet bullet = new Bullet0 (getPosX(), getPosY(), getGameWorld());
			 bullet.setDamage(getBulletDamage());
		
		if(getDirection() == LEFT_DIR) {
			bullet.setSpeedX(-10);
            bullet.setPosX(bullet.getPosX() - 20); 
            
		}
		if(getDirection() == RIGHT_DIR) {
			bullet.setSpeedX(10);
            bullet.setPosX(bullet.getPosX() + 20); 
			
		}
		if(getDirection() == UP_DIR) {
			    bullet.setSpeedY(-10);
	            bullet.setPosY(bullet.getPosY() - 30); 
	            
		}
		if(getDirection() == DOWN_DIR) {
		    bullet.setSpeedY(10);
            bullet.setPosY(bullet.getPosY() + 20); 
           
            
	    }
		 bullet.setTeamType(getTeamType());//vien team megaman thi k lam dau megaman vaf lam dau ke dich
         getGameWorld().bulletManager.addObject(bullet);
		 
         lastShootingTime = System.nanoTime();
         isShooting = true;
		  }
	}
	
	

	

	


}
