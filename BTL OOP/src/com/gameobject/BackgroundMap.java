/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.CacheDataLoader;
import com.userinterface.GameFrame;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Binh
 */
public class BackgroundMap extends GameObject {

    
    
    public BackgroundMap(float x, float y, GameWorld gameWorld) {
        super(x, y, gameWorld);
      
    }

    @Override
    public void Update() {}
    
    public void draw(Graphics2D g2){
        
        Camera camera = getGameWorld().camera;
        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("map1").getImage(),64 -(int)camera.getPosX() ,75-(int)camera.getPosY(),null);
        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("map02").getImage(),1088-(int)camera.getPosX() ,75-(int)camera.getPosY(),null);
        g2.drawImage(CacheDataLoader.getInstance().getFrameImage("map3").getImage(),2112 -(int)camera.getPosX() ,75-(int)camera.getPosY(),null);
        
                        
        
          
     }
 }
    

    
    

