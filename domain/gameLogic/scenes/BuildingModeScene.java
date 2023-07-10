package domain.gameLogic.scenes;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameObjects.Door;
import domain.gameObjects.GameObject;
import domain.gameObjects.TallWall;
import domain.gameObjects.Wall;
import domain.gameObjects.buildables.*;
import domain.gameObjects.characters.Player;
import ui.sounds.SoundType;
import ui.uiBuilders.UIType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BuildingModeScene extends Scene{
	
	public int levelNum;
	private Map<Integer, Integer> levelMinObjMap;
	
	public BuildingModeScene(String name) {
		super(name);
		levelNum = 0;

		
		resetBuiltObjects();
		initLevelObjCntMap();

	}
	
	private void initLevelObjCntMap() {
		levelMinObjMap = new HashMap<Integer,Integer>();
		levelMinObjMap.put(1, 5);
		levelMinObjMap.put(2, 7);
		levelMinObjMap.put(3, 10);
		levelMinObjMap.put(4, 14);
		levelMinObjMap.put(5, 19);
		levelMinObjMap.put(6, 25);
	}
	
	public void resetBuiltObjects() {
		gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
		buildWalls();

		int margin = 10;
		int buildingSlotWidth = 64;
		
		
		Desk desk = new Desk(mapBounds.x - buildingSlotWidth*2 - margin + 16, mapBounds.y + 16);
		desk.setBuildable(true);
		desk.setClickable(true);
		gameObjects.add(desk);
		
		Chair chair = new Chair(mapBounds.x - buildingSlotWidth*2 - margin + 16, mapBounds.y + buildingSlotWidth + 16);
		chair.setBuildable(true);
		chair.setClickable(true);
		gameObjects.add(chair);
		
		BookShelf bookShelf = new BookShelf(mapBounds.x - buildingSlotWidth - margin + 16, mapBounds.y + 16);
		bookShelf.setBuildable(true);
		bookShelf.setClickable(true);
		gameObjects.add(bookShelf);
		
		DeskComputer deskLaptop = new DeskComputer(mapBounds.x - buildingSlotWidth - margin + 16, mapBounds.y + buildingSlotWidth + 16);
		deskLaptop.setBuildable(true);
		deskLaptop.setClickable(true);
		gameObjects.add(deskLaptop);
				
		Door door = new Door((int)(Math.random()*((mapBounds.getMaxX() - 32) - mapBounds.x)+1)+mapBounds.x, mapBounds.y-48);
		door.setClickable(false);
		gameObjects.add(door);
		

		levelNum += 1;

		
	}
	
	public Player getPlayer() {
		for(GameObject obj:gameObjects) {
			if(obj.name.equals("player")) return (Player) obj;
		}
		return null;
	}
	

	private void checkObjPlacement(Point point) {
		Rectangle targetRect = new Rectangle(((point.x-mapBounds.x)/32)*32+mapBounds.x,
				((point.y-mapBounds.y)/32)*32+mapBounds.y,32,32);

		Rectangle targetDoorRect = new Rectangle(point.x - 48,point.y - 48,48,48);
		if(selectedGameObject != null) {
			if(targetDoorRect.intersects(getDoor().getRect()) || isColliding(targetRect) || !mapBounds.intersects(selectedGameObject.getRect())) {
				gameObjects.remove(selectedGameObject);
			}else {			
				selectedGameObject.getRect().setLocation(targetRect.x,targetRect.y);
				Game.getInstance().playSound(SoundType.OBJ_PLACE_SOUND);
			}
		}
	}


	private Door getDoor() {
		for(GameObject obj: gameObjects) {
			if(obj instanceof Door) {
				return (Door)obj;
			}
		}

		return null;

	}
	
	private void buildWalls() {
		int screenWidth = getScreenSize().width;
		int screenHeight = getScreenSize().height;
		
		int widthUnit = screenWidth / 7;
		int heightUnit = screenHeight / 5;
		
		int tileCntWidth = 0;
		int tileCntHeight = 0;
		int wallWidth = 5;
		int tileWidth = 32;
		
		while(widthUnit + tileCntWidth*tileWidth <= widthUnit*4) {
			tileCntWidth++;
		}
		
		while(heightUnit + tileCntHeight*tileWidth <= heightUnit*4) {
			tileCntHeight++;
		}
		
		mapBounds = new Rectangle(widthUnit*2,heightUnit+50,tileCntWidth*tileWidth,tileCntHeight*tileWidth);
		//mapBounds = new Rectangle(widthUnit,heightUnit,getScreenSize().width - widthUnit,getScreenSize().height-heightUnit);
		
		
		
		Wall wallWest = new Wall("wall",mapBounds.x - wallWidth,mapBounds.y-TallWall.HEIGHT, wallWidth,TallWall.HEIGHT+tileCntHeight*tileWidth+wallWidth);
		Wall wallEast = new Wall("wall",tileCntWidth*tileWidth+mapBounds.x,mapBounds.y-TallWall.HEIGHT,wallWidth,TallWall.HEIGHT+tileCntHeight*tileWidth+wallWidth);
		//Wall wallNorth = new Wall("wall",50,100,517,50);
		Wall wallSouth = new Wall("wall",mapBounds.x, mapBounds.y+tileCntHeight*tileWidth,tileCntWidth*tileWidth,wallWidth);
		//TallWall wallNorth = new TallWall(50,100);
		
		
		
		for(int i=0;i<tileCntWidth;i++) {
			gameObjects.add(new TallWall(TallWall.WIDTH*i + mapBounds.x, mapBounds.y-TallWall.HEIGHT));
		}
		
		gameObjects.add(wallWest);
		gameObjects.add(wallEast);
		//gameObjects.add(wallNorth);
		gameObjects.add(wallSouth);
	}
	
	public void randomiseLevels() {
			
		new Thread(){
	        public void run(){
	        	for(int i = levelNum; i <= 6; i++) {
	    			while(getStaticObjCnt() < levelMinObjMap.get(i)) {
	    				Rectangle randRect;
	    				
	    				int randGridX;
	    				int randGridY;
	    				do {
	    					int randPosX = (int)(Math.random()*(mapBounds.getMaxX()-mapBounds.x)+1)+mapBounds.x;
	    					int randPosY = (int)(Math.random()*(mapBounds.getMaxY()-mapBounds.y)+1)+mapBounds.y;
	    					
	    					randGridX = ((randPosX-mapBounds.x)/32)*32+mapBounds.x;
	    					randGridY = ((randPosY-mapBounds.y)/32)*32+mapBounds.y;
	    					
	    					randRect = new Rectangle(randGridX, randGridY, 32,32);
	    				}	
	    				while(isColliding(randRect));
	    				GameObject randObj = randomObj(randGridX, randGridY);
	    				randObj.setBuildable(false);
	    				randObj.setClickable(true);
	    				randObj.setMoveable(false);
	    				
	    				synchronized(gameObjects) {
		    				gameObjects.add(randObj);
		    				Game.getInstance().playSound(SoundType.OBJ_PLACE_SOUND);
	    				}
	    				try {
	    					Thread.sleep(10);
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}

	    			addLeveltoSceneManager();

	    		}
	        	placePlayerInCenter();
	    		getSceneManager().setCurrScene(2);
	        }
	      }.start();

	}
	
	private void placePlayerInCenter() {
		
		getSceneManager().getScene("level 1").addGameObject(new Player("player",(int) mapBounds.getCenterX(),(int) mapBounds.getCenterY()));

		
	}
	
	private GameObject randomObj(int x, int y) {
		int random = (int)(Math.random()*(4));

		GameObject obj = null;
		switch (random) {
		  case 0:
			  obj = new Desk(x,y);
		    break;
		  case 1:
			  obj = new BookShelf(x,y);
		    break;
		  case 2:
			  obj = new Chair(x,y);
		  case 3:
			  obj = new DeskComputer(x,y);
		}
		
		return obj;
	}
	
	public void saveLevel() {
		
		if(levelNum < 6) {
			//levelMinObjMap.get(levelNum)
			if(getStaticObjCnt() >= levelMinObjMap.get(levelNum)) {
				addLeveltoSceneManager();
			}else {
				System.out.println("place more objs");
			}
			
		}else {	
			//game.getSceneManager().getScenes().stream().forEach(s -> System.out.println(s.getName()));
			if(getStaticObjCnt() >= levelMinObjMap.get(levelNum)) {
				addLeveltoSceneManager();
				getSceneManager().getScene("level 1").addGameObject(new Player("player",(int) mapBounds.getCenterX(),(int) mapBounds.getCenterY()));
				getSceneManager().setCurrScene(2);
				

			}else {
				System.out.println("place more objs");
			}
		}
		
	}
	
	private void addLeveltoSceneManager() {
		placeKeyRandomly();
		
		LevelScene newScene = new LevelScene("level "+levelNum);
		newScene.saveGameObjects(gameObjects);
		newScene.setMapBounds(mapBounds);
//		newScene.addGameObject(new ExtraLife(200,200));
//		newScene.setTimeLeft();
//		
//		newScene.addGameObject(new ExtraTime(400,300));
//		newScene.setTimeLeft();
//		
//		newScene.addGameObject(new Hint(300,550));
//		newScene.setTimeLeft();
//		
//		newScene.addGameObject(new ProtectionVest(500,600));
//		newScene.setTimeLeft();
		
		newScene.setTimeLeft();
		getSceneManager().getScenes().add(newScene);
		resetBuiltObjects();
	}
	
	private void placeKeyRandomly() {
		int rand = (int) Math.floor(Math.random()*(gameObjects.size()));
		GameObject obj = gameObjects.get(rand);
		
		while( !(obj instanceof BuildableGameObject) || obj.isBuildable()) {
			//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());
			rand = (int) Math.floor(Math.random()*(gameObjects.size()));
			obj = gameObjects.get(rand);
		}
		//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());

		BuildableGameObject randomBuildable = (BuildableGameObject) obj;
		//System.out.println(randomBuildable.name);
		randomBuildable.setKey(true);
		
		
	}
	
	
	
	@Override
	protected GameObject getClickedObject(Point p) {
		for(GameObject obj : this.gameObjects) {
			if(obj.getRect().contains(p) && obj.isClickable()) {
				//System.out.println(obj.name);
				return obj;
			}
		}

		
		return null;
	}
	
	@Override
	public void updateScene() {
		// TODO Auto-generated method stub
		moveSelectedObject();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedGameObject = getClickedObject(e.getPoint());
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		checkObjPlacement(e.getPoint());
		clearSelectedObj();
	}

	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.BUILDING_MODE_SCENE;
	}

	@Override
	public void handleEvent(GameEventType type) {
		// TODO Auto-generated method stub
		switch(type) {
			case RANDOMISE_LEVELS:
				randomiseLevels();
				break;
			case SAVE_LEVEL:
				saveLevel();
				break;
		default:
			break;
		}

	}

}
