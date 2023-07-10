package ui.drawables;

import java.awt.Graphics2D;

import domain.gameObjects.GameObject;
import ui.uiObjects.UIObject;

public interface Drawable {
			
	public void draw(Graphics2D g2);
	
	public void setObjRef(GameObject obj);
	
	public void setObjRef(UIObject obj);
	
	public DrawableType getType();
	
}
