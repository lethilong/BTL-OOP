package com.userinterface;

import com.effect.CacheDataLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public static final int WIDTH = 960;
    public static final int HEIGHT = 675;

    GamePanel gamePanel;

    public GameFrame(){

        this.setTitle("Battle Dragons");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setResizable(false);
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();

        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.setBounds((dimension.width - WIDTH)/2, (dimension.height - HEIGHT)/2, WIDTH, HEIGHT);

        gamePanel = new GamePanel();
        this.addKeyListener(gamePanel);
   
        add(gamePanel);

    }

    public void startGame(){

            gamePanel.startGame();
            this.setVisible(true);

    }

    public static void main(String arg[]){

            GameFrame gameFrame = new GameFrame();
            gameFrame.startGame();

    }
        
}
