package ui.drawables;

import domain.gameObjects.GameObject;
import ui.uiObjects.UIObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawableManager {

private List<Drawable> drawablesList;
	
	private static DrawableManager single_instance = null;

	
	private DrawableManager() {
		loadDrawables();
	}
	
	public static DrawableManager getInstance()
    {

        if (single_instance == null) {
        	single_instance = new DrawableManager();

        }

        return single_instance;
    }
	
	private void loadDrawables() {
		drawablesList = new ArrayList<Drawable>();

		drawablesList.add(DrawableFactory.getDrawable(DrawableType.WALL));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.BULLET));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.PLAYER));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.SHOOTER_ALIEN));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.SIMPLE_SPRITE));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.TEXT_FIELD));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.SCROLL_VIEW));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.SAVE_LABEL));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.BUTTON_GROUP));
		drawablesList.add(DrawableFactory.getDrawable(DrawableType.DIALOG));






	}
	
	public Drawable getDrawable(DrawableType type) {
		
		for(Drawable drawable: drawablesList) {
			if(drawable.getType() == type) {
				return drawable;
			}
		}
		
		return null;	
	}

	
	public void addRefAndDraw(Graphics2D g2, DrawableType type, GameObject obj) {
		getDrawable(type).setObjRef(obj);
		getDrawable(type).draw(g2);
	}
	
	public void addRefAndDraw(Graphics2D g2, DrawableType type, UIObject obj) {
		getDrawable(type).setObjRef(obj);
		getDrawable(type).draw(g2);
	}
}
