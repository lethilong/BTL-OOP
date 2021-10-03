package com.gameobject;

public abstract class GameObject {
	
	private float posX;
	private float posY;
	private GameWorld gameWorld;  //có đối tượng này để có đối tượng tham chiếu đến gameobject, để dễ dàng truy nhập vào các thuộc tính ở trong gameworld
	
	



	public GameObject(float posX, float posY, GameWorld gameWorld) {
		
		this.posX=posX;
		this.posY=posY;
		this.gameWorld=gameWorld;
	}

	public float getPosX() {
		return posX;
	}
	
	public void setPosX(float x) {
		posX=x;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public void setPosY(float y) {
		posY=y;
	}
	
	public GameWorld getGameWorld () {
		return gameWorld;
	}
	
	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld=gameWorld;
	}
	
	public abstract void Update();
	

}
