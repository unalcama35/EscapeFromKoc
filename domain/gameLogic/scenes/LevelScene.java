package domain.gameLogic.scenes;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameObjects.Bottle;
import domain.gameObjects.Door;
import domain.gameObjects.GameObject;
import domain.gameObjects.buildables.BuildableGameObject;
import domain.gameObjects.characters.*;
import domain.gameObjects.powerUps.*;
import ui.SpriteType;
import ui.sounds.SoundType;
import ui.uiBuilders.UIType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LevelScene extends Scene{

	
	private int timeLeft;
	private int totalTime;
	private long startTime;
		
	private List<Integer> pressedKeys;
	
	private final int timePerObj = 5;
	
	private final int alienSpawnCooldown = 10;
	private long alienCDStartTime;
	
	private final int powerUpSpawnCooldown = 12;
	private long powerUpCDStartTime;
	
	
	
	
	private boolean active;
	private boolean keyIsFound;
	
	
	
	public LevelScene(String name) {
		super(name);
		//loadImg("/images/wood_tile",floorTile);
		//loadImg("/images/heart", heartImg);
		keyIsFound = false;
		pressedKeys = new ArrayList<Integer>();
		active = false;
		
		//this.UIObjects.add(new PauseButtonUI(50,50, this));
		
		buildMap();
		
		
		
		
	}
	
//	private void loadImgs() {
//		try {
//			labelImg = ImageIO.read(getClass().getResource("/images/level"+name.charAt(6)+"_label.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	
	public SpriteType getLabelImg() {
		switch(name) {
			case "level 1":
				return SpriteType.LEVEL_LABEL_1;
			case "level 2":
				return SpriteType.LEVEL_LABEL_2;
			case "level 3":
				return SpriteType.LEVEL_LABEL_3;
			case "level 4":
				return SpriteType.LEVEL_LABEL_4;
			case "level 5":
				return SpriteType.LEVEL_LABEL_5;
			case "level 6":
				return SpriteType.LEVEL_LABEL_6;
			default:
				return null;
		}
		
		
	}
	

	
	public void addTime(int time) {
		timeLeft += time;
	}
	
	public void setTimeLeft() {

		timeLeft = getStaticObjCnt() * timePerObj;
		totalTime = getStaticObjCnt() * timePerObj;
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
		alienCDStartTime = timeLeft;
		powerUpCDStartTime = timeLeft;
	}
	
	public Door getDoor() {
		for(GameObject obj: gameObjects) {
			if(obj instanceof Door) {
				return (Door)obj;
			}
		}
		
		return null;
		
	}
	
	private void buildMap() {
		int screenWidth = getScreenSize().width;
		int screenHeight = getScreenSize().height;
		
		int widthUnit = screenWidth / 7;
		int heightUnit = screenHeight / 5;
		
		mapBounds = new Rectangle(widthUnit,heightUnit,widthUnit*5,heightUnit*3);
		
		
	}
	
	public Player getPlayer() {
		for(GameObject obj:this.gameObjects) {
			if(obj.name.equals("player")) return (Player) obj;
		}
		return null;
	}
	
	protected GameObject getClickedObject(Point p) {
		
		if(active) {
			for(GameObject obj : this.gameObjects) {
//			if((obj.getRect().contains(p) && obj instanceof PowerUp)) {
//				
//			}
			if((obj.getRect().contains(p) && playerIsClose(obj) && obj.isClickable())
					|| (obj.getRect().contains(p) && obj instanceof PowerUp)){
				//System.out.println(obj.name);
				return obj;
				}
			}
		}

		
		return null;
	}
	
	public void pauseResume() {
		if(!getSceneManager().getGameEnded()) {
			active = !active;
			if(active) Game.getInstance().addEventToUI(GameEventType.RESUME_GAME);
			else Game.getInstance().addEventToUI(GameEventType.PAUSE_GAME);
		}
	}

	public void removePowerUpFromScene(PowerUp powerUp) {
		gameObjects.remove(powerUp);
	}
	
	public boolean playerIsClose(GameObject obj) {
		int squareWidth = 32;
		Rectangle playerRect = getPlayer().getRect();
		Rectangle biggerRect = new Rectangle((int) (playerRect.getCenterX()-squareWidth), (int) (playerRect.getCenterY()-squareWidth),
				squareWidth*2, squareWidth*2);
		
		return biggerRect.intersects(obj.getRect());
	}

	
	
	
	
	public void moveKey() {
		int rand = (int) Math.floor(Math.random()*(gameObjects.size()));
		
			GameObject obj = gameObjects.get(rand);
			GameObject objWithKey = findKeyObject();
			
			if(objWithKey != null) {
				while( !(obj instanceof BuildableGameObject) || obj.isBuildable() || obj.equals(objWithKey)) {
				//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());
				rand = (int) Math.floor(Math.random()*(gameObjects.size()));
				obj = gameObjects.get(rand);
				}
			//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());

				BuildableGameObject randomBuildable = (BuildableGameObject) obj;
				//System.out.println(randomBuildable.name);
				((BuildableGameObject) objWithKey).setKey(false);
				randomBuildable.setKey(true);
			}
		
		
		
		
	}
	@Override
	public void updateScene() {
		// TODO Auto-generated method stub
		//gameObjects.stream().forEach(obj -> obj.update(this));
			
			if(active) {
				removePowerUps();
				removeInactiveAliens();

				
				gameObjects.stream().forEach(obj -> obj.update(this));
				
		//		getPlayer().update(this);
		//		getShooterAlien().update(this);
		//		getBlindAlien().update(this);
		//		getTimeWastingAlien().update(this);
				
				if(System.currentTimeMillis() - startTime >= 1000) {
					timeLeft--;
					startTime = System.currentTimeMillis();
				}
				
				if(alienCDStartTime - timeLeft >= alienSpawnCooldown) {
					spawnRandomAlien();
				}
				
				if(powerUpCDStartTime - timeLeft >= powerUpSpawnCooldown) {
					spawnRandomPowerUp();
				}
					
			}
			
			pressKeys();

			moveSelectedObject();
			
			checkLoss();
			
		
		

		
	}
	
	private void checkLoss() {
		if(timeLeft == 0 || getPlayer().getLives() <= 0) {
			getSceneManager().playerLost();
		}
	}
	
	
	public void spawnRandomPowerUp() {
		int random = (int)(Math.random()*(5));

		GameObject powerUp = null;
		
		int minX = mapBounds.x;
		int maxX = (int) mapBounds.getMaxX();
		
		int minY = mapBounds.y;
		int maxY = (int) mapBounds.getMaxY();
		
		int randX = (int)(Math.random()*(maxX-minX+1)+minX);
		int randY = (int)(Math.random()*(maxY-minY+1)+minY);
		switch (random) {
		  case 0:
		    powerUp = new Hint(randX,randY);
		    break;
		  case 1:
			  powerUp = new ProtectionVest(randX,randY);
		    break;
		  case 2:
			  powerUp = new ExtraLife(randX,randY);
		    break;
		  case 3:
			  powerUp = new ExtraTime(randX,randY);
		    break;
		  case 4:
			  powerUp = new BottlePowerUp(randX,randY);
		    break;
		}
		
		while(isColliding(powerUp) || !mapBounds.intersects(powerUp.getRect())) {
			
			randX = (int)(Math.random()*(maxX-minX+1)+minX);
			randY = (int)(Math.random()*(maxY-minY+1)+minY);
			powerUp.setPosition(randX,randY);
		}
		
		addGameObject(powerUp);
		powerUpCDStartTime = timeLeft;
		
	}
	
	public void spawnRandomAlien() {
		int random = (int)(Math.random()*(3));
		GameObject alien = null;
		
		int minX = mapBounds.x;
		int maxX = (int) mapBounds.getMaxX();
		
		int minY = mapBounds.y;
		int maxY = (int) mapBounds.getMaxY();
		
		int randX = (int)(Math.random()*(maxX-minX+1)+minX);
		int randY = (int)(Math.random()*(maxY-minY+1)+minY);
		switch (random) {
		  	case 0:
		    	alien = new ShooterAlien(randX,randY);
		    	break;
		  	case 1:
				alien = new BlindAlien(randX,randY);
		    	break;
		  	case 2:
			  	alien = new TimeWastingAlien(randX,randY);
		    	break;
		}
		
		while(isColliding(alien)  || !mapBounds.intersects(alien.getRect())) {
			randX = (int)(Math.random()*(maxX-minX+1)+minX);
			randY = (int)(Math.random()*(maxY-minY+1)+minY);
			alien.setPosition(randX,randY);
		}
		addGameObject(alien);

		alienCDStartTime = timeLeft;
		
	}
	
	public int getAlienCount() {
		int count = 0;
		
		for(GameObject obj : gameObjects) {
			if(obj.getClass() == BlindAlien.class || obj.getClass() == ShooterAlien.class || obj.getClass() == TimeWastingAlien.class) {
				count++;
			}
		}
		return count;
	}
	
	public int getPowerUpCount() {
		int count = 0;
		
		for(GameObject obj : gameObjects) {
			if(obj instanceof PowerUp) {
				count++;
			}
		}
		return count;
	}
	
	public int getTotalTime(){
		return totalTime;
	}

	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public BuildableGameObject findKeyObject() {
		
		for(GameObject obj : gameObjects) {
			if(obj instanceof BuildableGameObject && ((BuildableGameObject)obj).hasKey()) {
				return (BuildableGameObject)obj;
			}
		}
		return null;
	}
	
	
	
	private void removePowerUps() {
		gameObjects = gameObjects.stream().filter(obj -> 
		!((obj instanceof PowerUp) && !obj.isClickable()))
				.collect(Collectors.toList());
	}

	private void removeInactiveAliens() {
		gameObjects = gameObjects.stream().filter(obj ->
						!((obj instanceof TimeWastingAlien) && !((TimeWastingAlien) obj).isActive()))
				.collect(Collectors.toList());

	}
	public void foundKey() {
		// TODO Auto-generated method stub
		//System.out.println("Found the key");
		if(!keyIsFound) {
			getDoor().openDoor(true);
			playSound(SoundType.DOOR_SOUND);
		}
	}

	
	public void addPressedKey(KeyEvent e) {
			pressedKeys.add(e.getKeyCode());
	}
	
	
	public void removePressedKey(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			getPlayer().setDx(0);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			getPlayer().setDx(0);
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			getPlayer().setDy(0);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			getPlayer().setDy(0);
		}

		pressedKeys = pressedKeys.stream().filter(key -> key != e.getKeyCode()).collect(Collectors.toList());
	}
	
	private void pressKeys() {
		if(!pressedKeys.isEmpty()) {
			int e = pressedKeys.get(pressedKeys.size()-1);
			
			pressMovementKeys(e);
			pressPowerUpKeys(e);
			pressBottleKeys(e);
			
			switch(e) {
				case KeyEvent.VK_ESCAPE:
					pauseResume();
					pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
					break;		
			}
			
		}
	
	}
	
	private void pressMovementKeys(int e) {
		
		switch(e){
			case KeyEvent.VK_LEFT:
				getPlayer().setDx(-1);
				getPlayer().setDy(0);
				break;
			case KeyEvent.VK_RIGHT:
				getPlayer().setDx(1);
				getPlayer().setDy(0);
				break;
			case KeyEvent.VK_UP:
				getPlayer().setDy(-1);
				getPlayer().setDx(0);
				break;
			case KeyEvent.VK_DOWN:
				getPlayer().setDy(1);
				getPlayer().setDx(0);
				break;
		}
	}
	
	private void pressPowerUpKeys(int e) {
		switch(e) {
			case KeyEvent.VK_H:
				getPlayer().usePowerUp("hint", this);
				pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
				break;
			case KeyEvent.VK_P:
				getPlayer().usePowerUp("protection vest", this);
				pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
				break;
			case KeyEvent.VK_B:
				getPlayer().usePowerUp("bottle powerUp", this);
				pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
				break;
		}
	}
	
	private void pressBottleKeys(int e) {
		if(getPlayer().isBottleInHand()) {
			switch(e){
				case KeyEvent.VK_W:
					setBottleToAliens(getPlayer().throwBottle("NORTH", this));
					pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
					break;
				case KeyEvent.VK_A:
					setBottleToAliens(getPlayer().throwBottle("WEST", this));
					pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
					break;
				case KeyEvent.VK_S:
					setBottleToAliens(getPlayer().throwBottle("SOUTH", this));
					pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
					break;
				case KeyEvent.VK_D:
					setBottleToAliens(getPlayer().throwBottle("EAST", this));
					pressedKeys = pressedKeys.stream().filter(key -> key != e).collect(Collectors.toList());
					break;
			}
		}
	}

	private void setBottleToAliens(Bottle bottle){
		for(GameObject obj : gameObjects){
			if(obj instanceof BlindAlien){
				((BlindAlien) obj).setBottle(bottle);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//getPlayer().getBackPack().getPowerUps().stream().forEach(powerUp -> System.out.println(powerUp.name));
		selectedGameObject = getClickedObject(e.getPoint());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//removeCollidingObj();
		clearSelectedObj();
	}

	public void setMapBounds(Rectangle mapBounds) {
		// TODO Auto-generated method stub
		this.mapBounds = mapBounds;
	}

	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.LEVEL_SCENE;
	}

	@Override
	public void handleEvent(GameEventType type) {
		// TODO Auto-generated method stub
		switch(type) {
			case RESUME_GAME:
				pauseResume();
				break;
			default:
				break;
		}
	}

	
	

}
