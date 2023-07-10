package domain.gameObjects.characters;

import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.Bottle;
import ui.SpriteType;
import ui.drawables.DrawableType;
import ui.sounds.SoundType;

import java.awt.*;
import java.util.Random;

public class BlindAlien extends Character{
	
	private int ms = 1;
	private final int cooldown = 1000;
	private final int dirCooldown = 2000;

	private Bottle bottle;
	
	private long cooldownStartTime;
	private long dirCooldownStartTime;
	
	public BlindAlien(int x, int y) {
		super("blind alien", x, y, 42, 51);
		
		loadImgs("BLIND_ALIEN");
		currSprite = sprites.get(1);
		
		randomiseDirs();
		bottle = null;
		currDirSprites = new SpriteType[3];
		
		cooldownStartTime = System.currentTimeMillis();
		dirCooldownStartTime = System.currentTimeMillis();
		startTimeSprite = System.currentTimeMillis();


		// TODO Auto-generated constructor stub
	}
	
	private void changeDirSprites() {
		// TODO Auto-generated method stub
		if(dx > 0) {
			fillDirSprites(6,3);
		}else if(dx < 0) {
			fillDirSprites(3,3);
		}else if(dy > 0){
			fillDirSprites(0,3);
		}else if(dy < 0) {
			fillDirSprites(9,3);
		}
//			for(int i = 0;i <= 5; i++) {
//				currDirSprites[i] = sprites[1];
//			}
//			fillDirSprites(7,6);
//			lastDir = 5;
		
	}

	private void fillDirSprites(int start, int spriteCnt) {
		for(int i = 0;i < spriteCnt; i++) {
			currDirSprites[i] = sprites.get(start + i);
		}
	}
	
	protected void changeImg() {
//		
		if(currDirSpriteIndex == 2) {
			currDirSpriteIndex = 0;
		}else {
			currDirSpriteIndex++;
		}
//		if(currDirSpriteIndex == 5) {
//			leftToRight = false;;
//		}else if(currDirSpriteIndex == 0) {
//			leftToRight = true;
//		}
//		
//		if(leftToRight) {
//			currDirSpriteIndex++;
//		}else {
//			currDirSpriteIndex--;
//		}
		
		currSprite = currDirSprites[currDirSpriteIndex];
//		rect.width = currSprite.getWidth();
//		rect.height = currSprite.getHeight();
		
		//setBoundsToSprite(rect.x,rect.y);
	}

	@Override
	public void update(LevelScene scene) {
		move(scene);
		
		changeDirSprites();
		
		if(System.currentTimeMillis() - startTimeSprite >= interval) {
			startTimeSprite = System.currentTimeMillis();			
			changeImg();
		}
		
	}
	
	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

	
	private void move(LevelScene scene) {
		// TODO Auto-generated method stub
		
//		dx = Math.signum(scene.getPlayer().getRect().x - this.getRect().x);
//		dy = Math.signum(scene.getPlayer().getRect().y - this.getRect().y);
		//System.out.println("dx: "+dx+" dy: "+dy+"magnitude: ");
		
		if(System.currentTimeMillis() - dirCooldownStartTime >= dirCooldown || checkCollision(scene.getGameObjects())) {
			randomiseDirs();
			dirCooldownStartTime = System.currentTimeMillis();
		}

		if(bottle != null){
			dx =  Math.signum(bottle.getRect().x - rect.x);
			dy = Math.signum(bottle.getRect().y - rect.y);
		}
		rect.x += ms*dx;
		rect.y += ms*dy;
		
		if(scene.playerIsClose(this) && System.currentTimeMillis() - cooldownStartTime >= cooldown) {
			cooldownStartTime = System.currentTimeMillis();
			scene.getPlayer().onHit("blind alien");
			scene.playSound(SoundType.PLAYER_HIT);
		}
		
		

		
	}
	
	private void randomiseDirs() {
		dx = randomDir();
		
		if(dx == 0) {
			dy = randomDir();
			while(dy == 0) {
				dy = randomDir();
			}
		}else {
			dy = 0;
		}
	}

	private int randomDir() {
		if((new Random()).nextBoolean()) {
			return -1;
		}else if((new Random()).nextBoolean()) {
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}

	public void setBottle(Bottle bottle){
		this.bottle = bottle;
	}
}
