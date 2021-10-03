package com.gameobject;

import java.awt.Rectangle;

public abstract class Human extends ParticularObject {
	
	

    public Human(float x, float y, float width, float height, int blood, GameWorld gameWorld) {
        super(x, y, width, height, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();
    
    
    public abstract void stopRun();

    
    
    @Override
    public void Update(){
        
        super.Update();
        
       if(getState() == ALIVE || getState() == NOBEHURT){
    	   
    	   
    	   
    	   
    	        setPosX(getPosX() + getSpeedX());
    	        setPosY(getPosY() + getSpeedY());
        
          


                if(getDirection() == LEFT_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                else if(getDirection() == RIGHT_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                }
                
              
                else if(getDirection() == UP_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithUpWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectUpWall = getGameWorld().physicalMap.haveCollisionWithUpWall(getBoundForCollisionWithMap());
                    setPosY(rectUpWall.y + rectUpWall.height + getHeight()/2);

                }
                else if ( getDirection() == DOWN_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectDownWall = getGameWorld().physicalMap.haveCollisionWithDownWall(getBoundForCollisionWithMap());
                    setPosY(rectDownWall.y - getHeight()/2 );

                }
            



              
            
        }
    }
	

}
