package ui.drawables;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import domain.gameObjects.GameObject;
import domain.gameObjects.characters.Bullet;
import ui.SpriteManager;
import ui.uiObjects.UIObject;

public class BulletDrawable implements Drawable{

	private Bullet bullet;
	
	public BulletDrawable(){

	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		AffineTransform old = g2.getTransform();
        
        double newAngle = 0;
        
        if(bullet.getAngle() >= 0) newAngle = Math.toRadians(90) - bullet.getAngle();
        else newAngle = Math.toRadians(90) + Math.abs(bullet.getAngle());

		BufferedImage sprite = SpriteManager.getInstance().getSprite(bullet.getCurrSprite());

		g2.rotate(newAngle,bullet.getRect().x+sprite.getWidth()/2,bullet.getRect().y+sprite.getHeight()/2);
		
		
		g2.drawImage(sprite, bullet.getRect().x, bullet.getRect().y, null);
 
        g2.setTransform(old);
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		bullet = (Bullet) obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.BULLET;
	}

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		
	}

}
