package com.gameobject;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import com.userinterface.GameFrame;
import com.userinterface.GamePanel;

public class GameWorld {
	
	private BufferedImage bufferedImage;
	
	public Player player;
	public PhysicalMap physicalMap;
	
    public BulletManager bulletManager;
	
	public ParticularObjectManager particularObjectManager;
	
	public ParticularObject boss;
	
	public Camera camera;
	public BackgroundMap map;
	Font font = new Font("Times New Roman", Font.BOLD, 50 );
	
	private Menu menu;
	public static enum STATE{
		MENU,
		GAME,
		HELP
	};
	public static final int NONE=0;
	public static final int EASY=1;
	public static final int HARD =2;
	public static int play=NONE;
	
	
	
	public static STATE state = STATE.MENU;
	
	public GameWorld() {
		
		bufferedImage = new BufferedImage(GameFrame.WIDTH,GameFrame.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		menu = new Menu();
		physicalMap =new PhysicalMap(0,0,this);
		map = new BackgroundMap(0,0,this);
		player = new Player (900,250,this);
		player.setTeamType(ParticularObject.LEAGUE_TEAM);
		bulletManager = new BulletManager(this);
		particularObjectManager = new ParticularObjectManager(this);
		camera = new Camera(64, 75, GameFrame.WIDTH, GameFrame.HEIGHT, this);
		
		bulletManager = new BulletManager(this);
		particularObjectManager = new ParticularObjectManager(this);
		particularObjectManager.addObject(player);
	
	
		items();
		initEnemies();
		
	}
	
	
	
	
	private void initEnemies(){
        ParticularObject monster1 = new Monster1(300, 150, this);
        monster1.setDirection(ParticularObject.RIGHT_DIR);
        monster1.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(monster1);
        
        ParticularObject monster2 = new Monster1(1350, 300, this);
        monster2.setDirection(ParticularObject.DOWN_DIR);
        monster2.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(monster2);
        
        boss = new Boss(2500, 350, this);
        boss.setDirection(ParticularObject.LEFT_DIR);
        boss.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(boss);

        ParticularObject monster3 = new Monster2(200, 600, this);
        monster3.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(monster3);

        ParticularObject monster4 = new Monster2(1600, 200, this);
        monster4.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(monster4);

  }
	
	private void items() {
		Item item1 = new Item (150,200,this);
		item1.setType(1);
		particularObjectManager.addObject(item1);
		
		
		Item item2 = new Item (1810,635,this);
		item2.setType(3);
		particularObjectManager.addObject(item2);
		
		Item item3 = new Item (1950,270,this);
		item3.setType(2);
		particularObjectManager.addObject(item3);
		
		Item item4 = new Item (1350,300,this);
		item4.setType(1);
		particularObjectManager.addObject(item4);
		
		Item item5 = new Item (100,520,this);
		item5.setType(2);
		particularObjectManager.addObject(item5);
		
		Item item6 = new Item (2150,400,this);
		item6.setType(1);
		particularObjectManager.addObject(item6);
		
		Item item7 = new Item (550,550,this);
		item7.setType(1);
		particularObjectManager.addObject(item7);
		
		Item item8 = new Item (1750,170,this);
		item8.setType(1);
		particularObjectManager.addObject(item8);
	}
	
	
	
	public void Update() {
		
		if (state == STATE.GAME && play!=NONE) {
			
		
		    particularObjectManager.UpdateObjects();
		//player.Update();
		    camera.Update();
		    bulletManager.UpdateObjects();
		  
	}
		
	}
	public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
	
	public void Render () {
		

		Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();

        if(g2!=null){
        	
               if(state ==STATE.GAME ) {
            	   if(play==NONE) {
            		   g2.setColor(Color.BLACK);
            		   g2.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
            		   g2.setColor(Color.white);
            		   Font font = new Font ("arial", Font.BOLD,30);
            		   g2.setFont(font);
            		   g2.drawString("PLEASE CHOOSE YOUR LEVEL", GameFrame.WIDTH/2 - 240, 100);
            		   g2.drawString("Easy",GameFrame.WIDTH/2-25,245);
            		   g2.drawRect(GameFrame.WIDTH/2 -70,200,160,70);
            		   g2.drawString("Hard",GameFrame.WIDTH/2-25 , 365);
            		   g2.drawRect(GameFrame.WIDTH/2 -70,320,160,70);
            		   
            	   }else {
            		     
		         //player.draw(g2);
		         //physicalMap.draw(g2);
		         map.draw(g2);
		         //player.draw(g2);
		         particularObjectManager.draw(g2);
		         bulletManager.draw(g2);
		         
		         if(player.getState()==ParticularObject.DEATH) {
		        	 g2.setColor(Color.BLACK);
	                    g2.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
	                    g2.setColor(Color.WHITE);
	                    g2.setFont(font);
	                    g2.drawString("GAME OVER!", 300, 300);
	                   
	                    
		         }
		         if(boss.getState()==ParticularObject.DEATH) {
		        	 g2.setColor(Color.white);
		        	 g2.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
					 g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 280, 250 , null);
				 }
            	   }
		   
               }
               else if (state ==STATE.MENU) {
            	   menu.draw(g2);
               }else {
            	   g2.drawImage(CacheDataLoader.getInstance().getFrameImage("help").getImage(),0 ,0,null);
               }
		    
		
	  }
	}
	
	public void restart() {
		bufferedImage = new BufferedImage(GameFrame.WIDTH,GameFrame.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		menu = new Menu();
		physicalMap =new PhysicalMap(0,0,this);
		map = new BackgroundMap(0,0,this);
		player = new Player (900,250,this);
		player.setTeamType(ParticularObject.LEAGUE_TEAM);
		bulletManager = new BulletManager(this);
		particularObjectManager = new ParticularObjectManager(this);
		camera = new Camera(64, 75, GameFrame.WIDTH, GameFrame.HEIGHT, this);
		
		bulletManager = new BulletManager(this);
		particularObjectManager = new ParticularObjectManager(this);
		particularObjectManager.addObject(player);
		
		items();
		initEnemies();
		
	}
}
