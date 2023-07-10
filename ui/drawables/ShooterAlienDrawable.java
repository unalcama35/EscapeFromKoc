package ui.drawables;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import domain.gameObjects.GameObject;
import domain.gameObjects.characters.ShooterAlien;
import ui.SpriteManager;
import ui.uiObjects.UIObject;

public class ShooterAlienDrawable implements Drawable{

	private ShooterAlien alien;

	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub

		AffineTransform old = g2.getTransform();
        
        double newAngle = 0;
        
        if(alien.getAngle() >= 0) newAngle = Math.toRadians(90) - alien.getAngle();
        else newAngle = Math.toRadians(90) + Math.abs(alien.getAngle());

		BufferedImage sprite = SpriteManager.getInstance().getSprite(alien.getCurrSprite());

        
		g2.rotate(newAngle,alien.getRect().x+sprite.getWidth()/2,alien.getRect().y+sprite.getHeight()/2);
		
		g2.drawImage(sprite, alien.getRect().x, alien.getRect().y, null);
        g2.setTransform(old);

		alien.getBullets().stream().forEach(bullet -> 
		{Drawable bulletDrawable = DrawableFactory.getDrawable(DrawableType.BULLET);
		bulletDrawable.setObjRef(bullet);
		bulletDrawable.draw(g2);});
		
	}


	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		alien = (ShooterAlien) obj;
	}


	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.SHOOTER_ALIEN;
	}


	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		
	}

}
