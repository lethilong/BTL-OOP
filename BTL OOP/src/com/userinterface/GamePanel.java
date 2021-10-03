package com.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.effect.Animation;
import com.gameobject.GameWorld;
import com.gameobject.PhysicalMap;
import com.gameobject.Player;


public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {
	private Thread thread;
	private InputManager inputManager;
	
	
	GameWorld gameWorld;
    long beginTime= System.nanoTime();
	


	public GamePanel() {
		
		gameWorld= new GameWorld();
		inputManager= new InputManager(gameWorld);
	    this.addMouseListener(this);
		
		
	}
	
	
	
	
	
	
	
	public void UpdateGame() {
		
		gameWorld.Update();
	
		
	}
	public void RenderGame() {
		
			
			
		gameWorld.Render();
	}
	
	

	@Override
	public void run() {
		
		final long period=1000000000/80;
		long beginTime= System.nanoTime();
		
		
		
		while(true) {
			
		
			
			UpdateGame();
			RenderGame();
			
			repaint();
			long currentTime=System.nanoTime();
			
			long sleepTime=period-(currentTime -beginTime);
	
				try {
					if(sleepTime>0) {
					Thread.sleep(sleepTime/1000000);
					}else Thread.sleep(period/1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				beginTime= System.nanoTime();
				
				
	
				
		}
		
		
	}
	
	
	public void startGame() {
	
			thread=new Thread(this);
			thread.start();
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		
		g.drawImage(gameWorld.getBufferedImage(), 0, 0, this);
		//
		//g.drawImage(subImage, 10, 10, this);
		/*Graphics2D g2= (Graphics2D)g;
		frame1.draw(g2, 100, 130);
		anim1.draw(300, 300, g2);
		*/
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Key press");
		inputManager.processKeyPressed(e.getKeyCode());
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("Key Release");
		inputManager.processKeyReleased(e.getKeyCode());
		
	}







	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public void mousePressed(MouseEvent e) {
		inputManager.processMousePressed(e);
		
	}







	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}







	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
