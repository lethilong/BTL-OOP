package com.gameobject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.userinterface.GameFrame;

public class Menu {
	

	public Rectangle play = new Rectangle (GameFrame.WIDTH/2 - 60,200,110,50);
	public Rectangle help = new Rectangle (GameFrame.WIDTH/2 -60,300,110,50);
	public Rectangle quit = new Rectangle (GameFrame.WIDTH/2 -60,400,110,50);
	public void draw (Graphics g) {
		
		
		Graphics2D g2 = (Graphics2D) g;

		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
		Font font = new Font ("arial", Font.BOLD,50);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("BATTLE DRAGONS", GameFrame.WIDTH/2 - 240, 100);
		
		Font font1 = new Font ("arial", Font.BOLD,30);
		g.setFont(font1);
		g.drawString("Play",play.x + 25,play.y+35);
		g2.draw(play);
		g.drawString("Help",help.x + 25,help.y+35);
		g2.draw(help);
		g.drawString("Quit",quit.x + 25,quit.y+35);
		g2.draw(quit);
	}

}
