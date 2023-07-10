package domain.gameObjects;

import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.drawables.DrawableType;

import java.awt.*;

public class Bottle extends GameObject{

	public Bottle(int x, int y) {
		super("bottle", x, y, 19, 32);
		// TODO Auto-generated constructor stub
		currSprite = SpriteType.BOTTLE;
		
		setClickable(false);
	}

	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

}
