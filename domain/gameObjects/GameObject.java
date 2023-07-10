package domain.gameObjects;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.characters.Player;
import ui.SpriteType;
import ui.drawables.DrawableType;

public abstract class GameObject implements Serializable{
	
	public String name;
	protected Rectangle rect;
	protected boolean pressed;
	protected boolean buildable;
	protected boolean moveable;
	protected boolean clickable;
	
	protected SpriteType currSprite;
	protected List<SpriteType> sprites;
	
	public GameObject(String name, int x, int y, int width, int height) {
		this.name = name;
		this.rect = new Rectangle(x,y, width, height);
		
	}
	
	public GameObject(String name) {
		this.name = name;
	}
	
	public abstract DrawableType getDrawableType();
	
	public SpriteType getCurrSprite() {
		if(this instanceof Player) {
			//System.out.println(currSprite);
		}
		return currSprite;
	}

	
	protected void loadImgs(String spriteName) {
		sprites = new ArrayList<SpriteType>();
		
		for (SpriteType type : SpriteType.values()) { 
		    if(type.toString().contains(spriteName)) {
		    	sprites.add(type);
		    } 
		}
	}
	
//	protected void setBoundsToSprite(int x, int y) {
//		this.rect = new Rectangle(x, y, width, height);
//	}
	
	public Rectangle getRect() {
		return rect;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public boolean isClickable() {
		return clickable;
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
	
	public boolean isBuildable() {
		return buildable;
	}
	
	public void setPosition(int x, int y) {
//		rect.x = p.x;
//		rect.y = p.y;
		rect.setLocation(x, y);
	}
	
	public void release() {
		pressed = false;
	}
	
	public void setBuildable(boolean buildingMode) {
		this.buildable = buildingMode;
	}
		
	public void update(LevelScene scene) {}
	
	public abstract void onClick(Point p, Scene scene);
	
	//public abstract void render(Graphics2D g2);

}
