package domain.gameObjects;

import java.awt.Point;

import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.drawables.DrawableType;

public class TallWall extends GameObject{

	public static final int WIDTH = 32, HEIGHT = 96;
	
	public TallWall(int x, int y) {
		super("tall wall", x, y, WIDTH, HEIGHT);
		
		currSprite = SpriteType.TALL_WALL;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
	
	

}
