package domain.gameObjects.characters;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.BackPack;
import domain.gameObjects.Bottle;
import domain.gameObjects.Door;
import ui.MainGamePanel;
import ui.SpriteType;
import ui.drawables.DrawableType;
import ui.sounds.SoundType;

import java.awt.*;



public class Player extends Character{
	
	private int ms = 3;
	private BackPack backPack;
	private int lives;
	private int lastDir;
	
	private boolean vestOn;
	private long vestStartTime;
	private final int VEST_DURATION = 20000; //20s
	
	private boolean bottleInHand;
	
	public Player(String name, int x, int y) {
		super(name, x, y, 20, 31);
		
		backPack = new BackPack();
		lives = 3;
		vestOn = false;
		
		this.loadImgs("PLAYER");
		this.currSprite = sprites.get(0);
		currDirSprites = new SpriteType[4];
		lastDir = 1;
		
		currDirSpriteIndex = 0;
		
		//this.setBoundsToSprite(x, y);
		
		bottleInHand = false;
		
		// TODO Auto-generated constructor stub
		
	}
	
	
	
	@Override
	public void update(LevelScene scene) {

		move(scene);
		
		checkVestDuration();
	}
	
	private void checkVestDuration() {

		if(vestOn && System.currentTimeMillis() - vestStartTime > VEST_DURATION) {
			vestOn = false;
		}
		
	}
	
	public void move(Scene scene) {

		if(moving()) {
			scene.playSound(SoundType.WALKING_SOUND);
		}else {
			scene.stopSound(SoundType.WALKING_SOUND);
		}
		
		rect.x += ms*dx;
		rect.y += ms*dy;

		changeDirSprites();
		
		if(System.currentTimeMillis() - startTimeSprite >= interval) {
			startTimeSprite = System.currentTimeMillis();			
			changeImg();
		}
		
		
		
		//stayInFrame();
		checkDoorCollision(scene);
		checkCollision(scene.getGameObjects());
		
		
				
	}
	
	private boolean moving() {
		return dx != 0 || dy != 0;
	}
	
	private void checkDoorCollision(Scene scene) {
			Door door = ((LevelScene)scene).getDoor();
			if(door.isOpen() && ((LevelScene) scene).playerIsClose(door)) {
				scene.getSceneManager().moveToNextLevel();
			}

		
			
	}
	
	private void changeDirSprites() {
		// TODO Auto-generated method stub
		if(dy > 0) {
			fillDirSprites(0);
			lastDir = 1;
		}else if(dy < 0) {
			fillDirSprites(4);
			lastDir = 5;
		}else if(dx < 0) {
			fillDirSprites(8);
			lastDir = 9;
		}else if(dx > 0) {
			fillDirSprites(12);
			lastDir = 13;
		}else if(dy == 0 && dx == 0) {
			for(int i = 0;i <= 3; i++) {
				currDirSprites[i] = sprites.get(lastDir);
			}
		}
	}
	
	private void fillDirSprites(int start) {
		for(int i = 0;i <= 3; i++) {
			currDirSprites[i] = sprites.get(start + i);
		}
		
	}
	public void addLife() {
		lives++;
	}
	
	public void removeLife(String origin) {	
		
		if(!vestOn && origin.equals("shooter alien")) {
			lives--;
			
		}else if(origin.equals("blind alien")) {
			lives--;
		}
		
		Game.getInstance().lostLife();
		
	}
	
	public int getLives() {
		return lives;
	}
	
	public void usePowerUp(String powerUp, LevelScene scene) {
		//System.out.println("using powerup");
		backPack.usePowerUp(powerUp,scene);
	}
	
	public BackPack getBackPack() {
		return backPack;
	}

	protected void changeImg() {
		
		if(currDirSpriteIndex == 3) {
			currDirSpriteIndex = 0;
		}else {
			currDirSpriteIndex++;
		}
		
		currSprite = currDirSprites[currDirSpriteIndex];
		//rect.width = currSprite.getWidth();
		//rect.height = currSprite.getHeight();
	}
	
	
	public void onHit(String source) {
		//System.out.println("im hit");
		removeLife(source);
	}
	
	private void stayInFrame() {
		// TODO Auto-generated method stub
		if(rect.x+rect.width > MainGamePanel.WIDTH) {
			rect.x = MainGamePanel.WIDTH-rect.width;
		}else if(rect.x < 0) {
			rect.x = 0;
		}
		
		if(rect.y+rect.height > MainGamePanel.HEIGHT) {
			rect.y = MainGamePanel.HEIGHT-rect.height;
		}else if(rect.y < 0) {
			rect.y = 0;
		}
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void putVestOn() {
		vestStartTime = System.currentTimeMillis();
		
		
		vestOn = true;
	}
	
	public void putBottleInHand() {
		bottleInHand = true;
	}
	
	public boolean isBottleInHand() {
		return bottleInHand;
	}
	
	public Bottle throwBottle(String dir, LevelScene scene) {
		Bottle bottle = null;
		Rectangle mapBounds = scene.getMapBounds();
		
		
		switch(dir) {
			case "NORTH":
				bottle = new Bottle((int) mapBounds.getCenterX(), mapBounds.y + 50) ;
				break;
			case "WEST":
				bottle = new Bottle(mapBounds.x + 50, (int) mapBounds.getCenterY());
				break;
			case "SOUTH":
				bottle = new Bottle((int) mapBounds.getCenterX(), (int) (mapBounds.getMaxY() - 50));
				break;
			case "EAST":
				bottle = new Bottle((int) (mapBounds.getMaxX() - 50), (int) mapBounds.getCenterY());
				break;
				
		}
		
		bottle.setClickable(false);
		scene.addGameObject(bottle);
		bottleInHand = false;
		return bottle;
	}

//	@Override
//	public void onClick(Point p, Game game) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	


	public boolean getVestOn() {
		return vestOn;
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.PLAYER;
	}
	
	
	
	

}
