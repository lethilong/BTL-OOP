package com.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FrameImage {
	private String name;
	private BufferedImage image;
	
	public FrameImage(String name, BufferedImage image) {
		this.name=name;
		this.image=image;
	}
	public FrameImage(){
		name=null;
		image=null;
	}
	public FrameImage(FrameImage frameImage) {
		image= new BufferedImage(frameImage.getImageWidth(),frameImage.getImageHeight(),frameImage.getImage().getType()); //tao moi image: co 3 thong so rong,cao,loai; k dung dc this.image de tranh dung chung
		Graphics g=image.getGraphics();
		g.drawImage(frameImage.getImage(), 0, 0, null);
		
	
	}
	public void draw(int x,int y,Graphics2D g2) {
		g2.drawImage(image,x-image.getWidth()/2,y-image.getHeight()/2,null);
		
	}
	public int getImageWidth() {
		return image.getWidth();
	}
	public int getImageHeight() {
		return image.getHeight();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public BufferedImage getImage() {
		return image;
		
	}
	public void setImage(BufferedImage image) {
		this.image=image;
	}
	
	
}
