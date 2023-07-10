package ui.drawables;

import java.awt.Color;
import java.awt.Graphics2D;

import domain.gameObjects.GameObject;
import ui.uiObjects.UIObject;

public class WallDrawable implements Drawable{

	GameObject obj;
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(new Color(114, 18, 31));
		g2.fillRect(obj.getRect().x, obj.getRect().y, obj.getRect().width, obj.getRect().height);
		
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		this.obj = obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.WALL;
	}

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		
	}

}
