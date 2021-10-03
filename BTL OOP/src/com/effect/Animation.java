package com.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private String name;
	private boolean isRepeated;
	private ArrayList<FrameImage> frameImages; //arraylist luu cac frameimage
	private int currentFrame; //luu giu frame hien dang duoc chay tren man hinh
	
	private ArrayList <Double> delayFrames; //chua tgian delay giua cac frame
	private long beginTime;
	
	public Animation() { //pthuc khoi tao
		delayFrames=new ArrayList<Double>();
		beginTime=0;
		currentFrame=0;
	
		frameImages= new ArrayList<FrameImage>();
		isRepeated=true;
		
		
	}
	public Animation(Animation animation) { //copy constructor
		beginTime=animation.beginTime; //bien kieu nguyen thuy k chung bo nho, truyen gia tri nen co the dung dau "=" vi no k truyen vung nho
		currentFrame=animation.currentFrame;
		
		isRepeated=animation.isRepeated;
		delayFrames= new ArrayList<Double>();//tao mang moi de tach nhau ra khac nhau hoan toan khong tham chieu vao cung 1 vi tri tren bo nho
		for (Double d: animation.delayFrames) {
			delayFrames.add(d);//them cac thong so vao mang moi
		}
		
		
		frameImages =new ArrayList<FrameImage>();
		for(FrameImage f: animation.frameImages) {
			frameImages.add(f);
		}
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public boolean getIsRepeated() {
		return isRepeated;
	}
	public void setIsRepeated(boolean isRepeated) {
		this.isRepeated=isRepeated;
	}
	public ArrayList<FrameImage> getFrameImages(){
		return frameImages;
	}
	public void setFrameImages(ArrayList<FrameImage> frameImages) {
		this.frameImages=frameImages;
	}
	public int getCurrentFrame() {
		return currentFrame;
	}
	public void setCurrentFrame(int currentFrame) {
		
			this.currentFrame=currentFrame;
		
	}
	
	
	public ArrayList<Double> getDelayFrames(){
		return delayFrames;
	}
	public void setDelayFrames(ArrayList<Double> delayFrames) {
		this.delayFrames=delayFrames;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime=beginTime;
	}
	
	
	
	public void reset() {
		currentFrame=0;
		beginTime=0;
		
	}
	public void add(FrameImage frameImage, double timeToNextFrame) {
		frameImages.add(frameImage);
	
		delayFrames.add(new Double (timeToNextFrame));
	}
	public BufferedImage getCurrentImage() {
		return frameImages.get(currentFrame).getImage();
	}
	
	public void Update(long currentTime) {
		if (beginTime==0) beginTime=currentTime;
		else
			if(currentTime-beginTime>delayFrames.get(currentFrame)) {
				nextFrame();
			    beginTime=currentTime;		  
			}
		
		
	}
	public void nextFrame() {
		if(currentFrame>=frameImages.size()-1) {
			if(isRepeated) currentFrame=0;
		} else
			currentFrame++;
		
	} 
	
	
	
	public void draw(int x,int y, Graphics2D g2) {
		//this.Update(System.nanoTime());
		BufferedImage image=getCurrentImage();
		g2.drawImage(image, x-image.getWidth()/2, y-image.getHeight()/2,null);
		
	
	}
	
	

}
