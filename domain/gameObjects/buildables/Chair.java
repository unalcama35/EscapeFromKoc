package domain.gameObjects.buildables;

import java.awt.Graphics2D;

import ui.SpriteType;

public class Chair extends BuildableGameObject{

	public Chair(int x, int y) {
		super("chair", x, y, 32, 32);
		
		currSprite = SpriteType.CHAIR;
		
		// TODO Auto-generated constructor stub
	}


	@Override
	protected BuildableGameObject newInstance(int x, int y) {
		// TODO Auto-generated method stub
		return new Chair(x,y);
	}

}

