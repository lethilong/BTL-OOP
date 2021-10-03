package com.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.effect.Animation;

public abstract class ParticularObject extends GameObject {

	public static final int LEAGUE_TEAM =1; //cung phe
	public static final int ENEMY_TEAM =2; //phe dich
	
	public static final int LEFT_DIR =0;
	public static final int RIGHT_DIR = 1;
	public static final int UP_DIR = 2;
	public static final int DOWN_DIR =3;
	
    //cac trang thai nhan vat
    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2; //trạng thái sắp chết
    public static final int DEATH = 3;
    public static final int NOBEHURT = 4; //k bị ảnh hưởng bởi sát thương 
    private int state = ALIVE;
    
    private float width;
    private float height;
    private float speedX;
    private float speedY;
    private int blood;
    
    private int damage;
    
    private int direction = RIGHT_DIR;
    
    
    private int teamType; 
    
    private long startTimeNoBeHurt;
    private long timeForNoBeHurt;

    public ParticularObject(float x, float y, float width, float height, int blood, GameWorld gameWorld){

        
        super(x, y, gameWorld);
        setWidth(width);
        setHeight(height);
        setBlood(blood);
        
        //direction = RIGHT_DIR;

    }
    
    
    
    public void setTimeForNoBeHurt(long time){
        timeForNoBeHurt = time;
    }
    
    public long getTimeForNoBeHurt(){
        return timeForNoBeHurt;
    }
    
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return state;
    }
    
    public void setDamage(int damage){
            this.damage = damage;
    }

    public int getDamage(){
            return damage;
    }

    
    public void setTeamType(int team){
        teamType = team;
    }
    
    public int getTeamType(){
        return teamType;
    }
    
    

    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    public float getSpeedX(){
        return speedX;
    }

    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public float getSpeedY(){
        return speedY;
    }

    public void setBlood(int blood){
        if(blood>=0)
                this.blood = blood;
        else this.blood = 0;
    }

    public int getBlood(){
        return blood;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getHeight(){
        return height;
    }
    
    public void setDirection(int dir){
        direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public abstract void attack();
    
    public boolean isObjectOutOfCameraView() {
    	
    	if(getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView() || getPosX() - getGameWorld().camera.getPosX() < -50 ||getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()||
    	    	   getPosY() - getGameWorld().camera.getPosY() < -50){
    		return true;
    		   
    	   }else {
    		   return false;
    	   }
    }
    
    public Rectangle getBoundForCollisionWithMap() {
    	Rectangle bound= new Rectangle();
    	bound.x =(int) (getPosX()- (getWidth()/2));
    	bound.y =(int) (getPosY()- (getHeight()/2));
    	bound.width= (int) getWidth();
    	bound.height= (int) getHeight();
    	return bound;
    }
    
    public void beHurt (int damgeEat) {
    	setBlood(getBlood()- damgeEat);
    	state = BEHURT;
    }	
    
    @Override
	public void Update() {
		switch (state) {
		case ALIVE:
			
			ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
			if (object!=null) {
				if(object.getDamage()>0) {
				
					beHurt(object.getDamage());
				}
				
			}
			
			break;
			
		case BEHURT:
			  state = NOBEHURT;
			  startTimeNoBeHurt=System.nanoTime(); 
			  if (getBlood()==0) 
				  state = FEY;
			
	
			
			break;
			
		case FEY:
			state =DEATH;
			
			break;
			
			
		case DEATH:
			
			break; 
			
		case NOBEHURT:	
			System.out.println("state = nobehurt");
			if(System.nanoTime()-startTimeNoBeHurt > timeForNoBeHurt)
				state = ALIVE;
			
			break;
		
		
		
		}
		
	}
    
    public void drawBoundForCollisionWithMap(Graphics2D g2) {
    	Rectangle rect = getBoundForCollisionWithMap();
    	g2.setColor(Color.BLUE);
    	g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    	
    }
    
    public void drawBoundForCollisionWithEnemy(Graphics2D g2) {
    	Rectangle rect = getBoundForCollisionWithEnemy();
    	g2.setColor(Color.RED);
    	g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
    
    public abstract Rectangle getBoundForCollisionWithEnemy();
    
    public abstract void draw (Graphics2D g2);
    
    

	
    
    




}
