package com.gameobject;

import com.userinterface.GameFrame;

public class Camera extends GameObject {
	
	private float widthView;
	private float heightView;
	
	
	public Camera (float x, float y, float widthView, float heightView, GameWorld gameWorld) {
		super(x, y, gameWorld);
		this.widthView=widthView;
		this.heightView= heightView;
	}
	
	

	@Override
	public void Update() {
	
		
	
			
			Player mainCharacter = getGameWorld().player;
			
			
			if(mainCharacter.getPosX()-getPosX() > GameFrame.WIDTH) {
				mainCharacter.setPosX(mainCharacter.getPosX()+64);
				setPosX(getPosX()+GameFrame.WIDTH+64);
			}

			
			
			if(mainCharacter.getPosY()-getPosY() >GameFrame.HEIGHT)  setPosY(getPosY()+GameFrame.HEIGHT+64);
			
		
		
	}
	
	public float getWidthView() {
		return widthView;
	}
	
	public void setWidthView(float widthView) {
		this.widthView=widthView;
	}
	
	public float getHeightView() {
		return heightView;
	}
	
	public void setHeightView(float heightView) {
		this.heightView = heightView;
	}
	

}
