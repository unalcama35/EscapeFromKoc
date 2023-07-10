package domain.gameObjects;

import java.awt.Point;

import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableType;

public class Door extends GameObject{

	private boolean isOpen;
	private int currSpriteInd;
	
	private long startTime;
	private long interval = 10;
	
	public Door(int x, int y) {
		super("door", x, y , 32, 48);
		loadImgs("DOOR");
		
		currSpriteInd = 0;
		this.currSprite = sprites.get(currSpriteInd);
		
		
		isOpen = false;
		startTime = System.currentTimeMillis();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void openDoor(Boolean open) {
		isOpen = open;
		
	}
	protected void changeImg() {
		
		if(currSpriteInd != 3) {
			currSpriteInd++;
		}
		
		currSprite = sprites.get(currSpriteInd);
		//rect.width = currSprite.getWidth();
		//rect.height = currSprite.getHeight();
	}
	
	@Override
	public void update(LevelScene scene) {
		if(isOpen && System.currentTimeMillis() - startTime >= interval) {
			startTime = System.currentTimeMillis();			
			changeImg();
		}
	}


	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
	

}
