package ui.animations;

import ui.SpriteType;

import java.awt.*;
import java.util.ArrayList;

public abstract class Animation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4141480189445479707L;
	protected boolean hasEnded;
	
	public Animation() {

		hasEnded = false;
	}
	
	public abstract void render(Graphics2D g2);
	
	public boolean hasEnded() {
		return hasEnded;
	}
	
	public abstract boolean animate();
	
	protected ArrayList<SpriteType> loadImgs(String spriteName) {
		ArrayList<SpriteType> sprites = new ArrayList<SpriteType>();
		
		for (SpriteType type : SpriteType.values()) { 
		    if(type.toString().contains(spriteName)) {
		    	sprites.add(type);
		    } 
		}
		
		return sprites;
	}

}
