package ui.uiObjects;

import domain.gameLogic.scenes.Scene;
import ui.SpriteManager;
import ui.SpriteType;
import ui.drawables.DrawableType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class UIObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 849389930827582214L;
	
	protected final float delay = 250; //ms
	protected long lastClickTime;
	
	protected boolean focused;
	
	protected boolean clickable;
	
	protected SpriteType currSprite;
	protected SpriteType icon;
	protected SpriteType tintedIcon;
	

	public String name;
	protected Rectangle rect;
		
	public UIObject(String text, int x, int y, int width, int height) {
		this.name = text;
		this.rect = new Rectangle(x,y, width, height);
		lastClickTime = System.currentTimeMillis();
		clickable = true;

	}
	
	public UIObject(String name) {
		this.name = name;
		clickable = true;
	}
	
	protected boolean canBeClicked() {
		float timePassed =  (System.currentTimeMillis()-lastClickTime);
		
		//System.out.println(timePassed);

		if(clickable && timePassed >= delay) {
			lastClickTime = System.currentTimeMillis();
			return true;
		}else {
			return false;
		}
		
	}
	
	protected BufferedImage loadImg(String url) {
		BufferedImage sprite = null;
		try {
				sprite = ImageIO.read(getClass().getResource("/images/"+url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sprite;
	}
	
	public void setBoundsToSprite(int x, int y) {
		this.rect = new Rectangle(x, y, SpriteManager.getInstance().getSprite(currSprite).getWidth(),
				SpriteManager.getInstance().getSprite(currSprite).getHeight());
	}
	
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return focused;
	}
	
	public void setFocused(boolean focused) {
		this.focused = focused;
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
	public abstract void onClick(Point p, Scene scene);

	public abstract void onMouseOver(Point p);
	public abstract void mouseNotOver(Point p);
	
	public SpriteType getCurrSprite() {
		return currSprite;
	}
	
	
	
	

}
