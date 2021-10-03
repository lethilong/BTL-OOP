//xu ly input tu keyboard
package com.userinterface;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.gameobject.GameWorld;


public class InputManager {
	
	private GameWorld gameWorld;
	
	public InputManager(GameWorld gameWorld) {
		this.gameWorld=gameWorld;
	}
	public void processKeyPressed( int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			gameWorld.player.setDirection(2);
			gameWorld.player.run();
			
			
		
			break;
		case KeyEvent.VK_DOWN:
			gameWorld.player.setDirection(3);
			gameWorld.player.run();
			
			break;
		case KeyEvent.VK_LEFT:
			gameWorld.player.setDirection(0);
			
			gameWorld.player.run();
			break;
		case KeyEvent.VK_RIGHT:
			gameWorld.player.setDirection(1);
			gameWorld.player.run();
			break;
		case KeyEvent.VK_ENTER: 
			if(gameWorld.state==GameWorld.STATE.HELP) {
			gameWorld.state=GameWorld.STATE.MENU;
			gameWorld.play = GameWorld.NONE;
			}
			if(gameWorld.player.getState()==3||gameWorld.boss.getState()==3) {
				gameWorld.state=GameWorld.STATE.MENU;
				gameWorld.play = GameWorld.NONE;
				gameWorld.restart();
			}
		
			break;
		case KeyEvent.VK_SPACE:
			gameWorld.player.attack();
			
			break;
				}
	}
	public void processKeyReleased( int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			gameWorld.player.stopRun();
			break;
		case KeyEvent.VK_DOWN:
			gameWorld.player.stopRun();
			break;
		case KeyEvent.VK_LEFT:
			
			
			gameWorld.player.stopRun();
			break;
		case KeyEvent.VK_RIGHT:
			
			gameWorld.player.stopRun();
			break;
		case KeyEvent.VK_ENTER:
			
            
           
			break;
		case KeyEvent.VK_SPACE:
			
			break;
	
		
		}
	}
	
	
	
	public void processMousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
	
		/*
		 * public Rectangle play = new Rectangle (GameFrame.WIDTH/2 - 60,200,110,50);
		 * public Rectangle help = new Rectangle (GameFrame.WIDTH/2 -60,300,110,50);
		 * public Rectangle quit = new Rectangle (GameFrame.WIDTH/2 -60,400,110,50);
		 */
		//Play
		if(gameWorld.state==GameWorld.STATE.MENU) {
		if (x>=GameFrame.WIDTH/2 -60 && x <=GameFrame.WIDTH/2 +50) {
			if (y >=200 && y<=250) {
				gameWorld.state = GameWorld.STATE.GAME;
				//gameWorld.play = GameWorld.HARD;
				//gameWorld.Update();
			
			}
			
		}
		
			
		
		//Help
		if (x>=GameFrame.WIDTH/2 -60 && x <=GameFrame.WIDTH/2 +50) {
			if (y >=300 && y<=350) {
				gameWorld.state = GameWorld.STATE.HELP;
			
			}
			
		}
		//Quit
		if (x>=GameFrame.WIDTH/2 -60 && x <=GameFrame.WIDTH/2 +50) {
			if (y >=400 && y<=450) {
				System.exit(1);
			
			}
			
		}
	}else {
		
		if (x>=GameFrame.WIDTH/2 -70 && x <=GameFrame.WIDTH/2 +90) {
			if (y >=200 && y<=270) {
				
				gameWorld.play = GameWorld.EASY;
				
			
			}
			
		}
		if (x>=GameFrame.WIDTH/2 -70 && x <=GameFrame.WIDTH/2 +90) {
			if (y >=320 && y<=390) {
				gameWorld.play = GameWorld.HARD;
				
			
			}
			
		}
		
		
		
	}
				
		
	}

}
