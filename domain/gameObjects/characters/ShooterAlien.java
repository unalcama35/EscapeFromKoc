package domain.gameObjects.characters;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.GameObject;
import ui.SpriteType;
import ui.animations.ImpactAnimation;
import ui.drawables.DrawableType;
import ui.sounds.SoundType;

public class ShooterAlien extends Character{
	
	private int ms = 1;
	
	private long shootInterval = 1000;
	private long startTimeGun; 
	private List<Bullet> bullets;
	private double angle;


	public ShooterAlien(int x, int y) {
		super("shooter alien", x, y, 30, 40);
		//loadImgs("/images/shooter_alien",1);
		this.currSprite = SpriteType.SHOOTER_ALIEN;
		
		currDirSprites = new SpriteType[6];
		dx = 0;
		dy = 0;
		angle = 0;

		
		bullets = new ArrayList<Bullet>();
		startTimeGun = System.currentTimeMillis();
		
		//this.setBoundsToSprite(x, y);


	}
	
	
	
	private void checkBulletCollisions(LevelScene scene) {
		for(GameObject obj : scene.getGameObjects()) {
			if(obj instanceof Player) {
				bullets.stream().forEach(bullet -> {
					if(bullet.isColliding(obj)) {
						((Player) obj).onHit("shooter alien");
						scene.playSound(SoundType.PLAYER_HIT);
					}
				});			
			}
			
			List<Bullet> newBullets = new ArrayList<>();
			
			for(Bullet bullet:bullets) {
				if(bullet.isColliding(obj)) {
					//scene.addAndPlaySound(SoundType.WALL_IMPACT_SOUND);
					scene.playSound(SoundType.WALL_IMPACT_SOUND);

					Game.getInstance().addAnimation(new ImpactAnimation(bullet.getRect().x,bullet.getRect().y));
				}else {
					newBullets.add(bullet);
				}
			}
			
			bullets = newBullets;
			//bullets = bullets.stream().filter(bullet -> !bullet.isColliding(obj)).collect(Collectors.toList());
			}
		}

//	@Override
//	public void onClick(Point p, Game game) {
//		// TODO Auto-generated method stub
//		
//	}



	
	
	private void shoot(Rectangle player) {
		
		bullets.add(new Bullet((int)this.rect.getCenterX(),(int)this.rect.getCenterY(), player));
	}
	
	@Override
	public void update(LevelScene scene) {
		//move(scene);
		
//		changeDirSprites();
//		
//		if(System.currentTimeMillis() - startTimeSprite >= interval) {
//			startTimeSprite = System.currentTimeMillis();			
//			changeImg();
//		}
		
//		double xTemp = scene.getPlayer().getRect().x + scene.getPlayer().getRect().getWidth()/2 - rect.x - rect.width/2;
//		double yTemp = scene.getPlayer().getRect().y - rect.y;
		double xTemp = scene.getPlayer().getRect().getCenterX() - rect.getCenterX();
		double yTemp = scene.getPlayer().getRect().getCenterY() - rect.getCenterY();

		
		angle = Math.atan2(xTemp, yTemp);
		
		if(System.currentTimeMillis() - startTimeGun >= shootInterval) {
			startTimeGun = System.currentTimeMillis();			
			shoot(scene.getPlayer().getRect());
			//scene.addAndPlaySound(SoundType.LASER_SOUND);
			scene.playSound(SoundType.LASER_SOUND);

		}
		
		checkBulletCollisions(scene);
		checkCollision(scene.getGameObjects());
		
		bullets.stream().forEach(bullet -> bullet.update(scene));

	}
	
	private void move(LevelScene scene) {
		// TODO Auto-generated method stub
		
		dx = Math.signum(scene.getPlayer().getRect().x - this.getRect().x);
		dy = Math.signum(scene.getPlayer().getRect().y - this.getRect().y);
		//System.out.println("dx: "+dx+" dy: "+dy+"magnitude: ");
		
		rect.x += ms*dx;
		rect.y += ms*dy;
		
		
	}
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SHOOTER_ALIEN;
	}

	public double getAngle() {
		return angle;
	}
	
	public List<Bullet> getBullets(){
		return bullets;
	}
	
//	@Override
//	public Rectangle getRect() {
//		double newAngle = 0;
//        
//        if(angle >= 0) newAngle = Math.toRadians(90) - angle;
//        else newAngle = Math.toRadians(90) + Math.abs(angle);
//		Rectangle initRect = new Rectangle(rect.x,rect.y,currSprite.getWidth(),currSprite.getHeight());
//        AffineTransform at = AffineTransform.getRotateInstance(newAngle,initRect.x+currSprite.getWidth()/2,initRect.y+currSprite.getHeight()/2);
//		Shape rotatedRect = at.createTransformedShape(initRect);
//		
//		return rotatedRect.getBounds();
//	}
	
	
	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

}
