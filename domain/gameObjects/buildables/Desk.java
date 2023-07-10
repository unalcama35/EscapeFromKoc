package domain.gameObjects.buildables;

import java.awt.Graphics2D;

import ui.SpriteType;

public class Desk extends BuildableGameObject{


	
	public Desk(int x, int y) {
		super("desk", x, y, 32, 32);		

		currSprite = SpriteType.DESK;
		

		
		
		// TODO Auto-generated constructor stub
	}




	@Override
	protected BuildableGameObject newInstance(int x, int y) {

		return new Desk(x,y);
	}
	
	

}
