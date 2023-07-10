package domain.gameObjects.powerUps;

import java.awt.Rectangle;

import domain.gameLogic.scenes.LevelScene;
import domain.gameObjects.GameObject;
import ui.SpriteType;
import ui.drawables.DrawableType;

public abstract class PowerUp extends GameObject{

	
	
	public PowerUp(String name, int x, int y, int width, int height) {
		super(name);
		rect = new Rectangle(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
	
	public abstract void action(LevelScene scene);

	public abstract SpriteType getIcon();
	
}
