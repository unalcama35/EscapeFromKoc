package domain.gameObjects.characters;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.GameObject;
import ui.SpriteType;
import ui.drawables.DrawableType;

public class Bullet extends GameObject{
	
	private final int WIDTH = 1, HEIGHT = 10;
//	private int dx, dy;
	private int ms = 5;
	private double angle;
	private double vX,vY;
	private Rectangle player;
	
	
	public Bullet(int x, int y, Rectangle player) {
		super("bullet");
		rect = new Rectangle(x,y,WIDTH,HEIGHT);
		
		currSprite = SpriteType.BULLET;
		
		//setBoundsToSprite(x, y);
		this.player = player;
//		dx = (int) Math.signum(player.x - x);
//		dy = (int) Math.signum(player.y - y);
		
		double xTemp = player.getCenterX() - rect.x;
		double yTemp = player.getCenterY() - rect.y;
		
		angle = Math.atan2(xTemp, yTemp);
	
		vX = Math.round(Math.sin(angle) * ms);
		vY = Math.round(Math.cos(angle) * ms);
		
		//System.out.println(vX+";;;"+vY);
	}
	
	
	public boolean isColliding(GameObject target) {
		return getBulletBounds().intersects(target.getRect()) && !(target instanceof ShooterAlien);
	}
	
	
	private Rectangle getBulletBounds() {
		double newAngle = 0;
        
        if(angle >= 0) newAngle = Math.toRadians(90) - angle;
        else newAngle = Math.toRadians(90) + Math.abs(angle);
		Rectangle initRect = new Rectangle(rect.x, rect.y, WIDTH,HEIGHT);
        AffineTransform at = AffineTransform.getRotateInstance(newAngle, initRect.x + WIDTH / 2, initRect.y + HEIGHT / 2);
		Shape rotatedRect = at.createTransformedShape(initRect);
		
		return rotatedRect.getBounds();
	}
	
	@Override
	public void update(LevelScene scene) {
		move();
	}
	
	private void move() {
		  
		rect.x += vX;
		rect.y += vY;
		//System.out.println(rect.x+"  "+rect.y);
	}
	


	public double getAngle() {
		return angle;
	}
	
	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub	
	}
	
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.BULLET;
	}

}
