package ui.drawables;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import domain.gameObjects.GameObject;
import ui.SpriteManager;
import ui.uiObjects.UIObject;

public class SimpleSpriteDrawable implements Drawable{

	private GameObject obj;
	private UIObject uiObj;

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub

		
		if(obj != null) {
			BufferedImage sprite = SpriteManager.getInstance().getSprite(obj.getCurrSprite());
			g2.drawImage(sprite, obj.getRect().x, obj.getRect().y,null);
		}else {
			BufferedImage sprite = SpriteManager.getInstance().getSprite(uiObj.getCurrSprite());
			g2.drawImage(sprite, uiObj.getRect().x, uiObj.getRect().y,null);
		}
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		this.obj = obj;
		uiObj = null;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}

	@Override
	public void setObjRef(UIObject object) {
		// TODO Auto-generated method stub
		uiObj = object;
		obj = null;
	}

}
