package domain.gameLogic.scenes;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameObjects.GameObject;
import domain.gameObjects.buildables.BuildableGameObject;
import ui.sounds.SoundType;
import ui.uiBuilders.UIType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Scene implements Serializable{
	
	protected List<GameObject> gameObjects;
	protected String name;
	protected GameObject selectedGameObject;
	protected Rectangle mapBounds;



	
	public Scene(String name) {
		this.name = name;
		//this.game = game;
		
	}

	public Rectangle getMapBounds() {
		return mapBounds;
	}
	
	public GameObject getSelectedObject() {
		return selectedGameObject;
	}
	
	//public abstract void renderScene(Graphics2D g2);
	
	public abstract void updateScene();

	public void playSound(SoundType soundType) {
		Game.getInstance().playSound(soundType);
	}
	
	public void addAndPlaySound(SoundType soundType) {
		Game.getInstance().addAndPlaySound(soundType);
	}
	
	protected void moveSelectedObject() {
		if(selectedGameObject != null) {	
			selectedGameObject.onClick(MouseInfo.getPointerInfo().getLocation(), this);;
		}
	}

	
	protected Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	
	public int getStaticObjCnt() {
		int cnt = 0;
		
		for(GameObject obj : gameObjects) {
			if(obj instanceof BuildableGameObject && !obj.isBuildable()) {
				cnt++;
			}
		}
		return cnt;
		
	}
	public void buildNewObject(GameObject obj) {
		GameObject newObj = obj;
		newObj.setBuildable(false);
		
		selectedGameObject = newObj;
		gameObjects.add(newObj);	
	}
	
	public boolean isColliding(GameObject obj) {
		for(GameObject obj1 : gameObjects){
			if (obj != null && obj1.getRect().intersects(obj.getRect()) && !obj1.isBuildable()
					&& obj1 != obj) {
				return true;
				}
		}		
		return false;
	}
	
	public boolean isColliding(Rectangle rect) {
		for(GameObject obj1 : gameObjects){
			if (rect != null && obj1 != selectedGameObject 
					&& obj1.getRect().intersects(rect) && !obj1.isBuildable()) {
				return true;
				}
		}		
		return false;
	}
	
	public SceneManager getSceneManager() {
		return Game.getInstance().getSceneManager();
	}
	
	public List<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
	public void addGameObject(GameObject obj) {
		this.gameObjects.add(obj);
	}
	
	public void removeGameObject(GameObject obj) {
		this.gameObjects.remove(obj);
	}
	
	
	
	public void saveGameObjects(List<GameObject> gameObjects) {
		this.gameObjects = new ArrayList<>(gameObjects);
		removeBuildableObjects();
		freezeMoveableObjects();
		
		//this.UIObjects = new ArrayList<>();
	}
	
	protected void loadImg(String url, BufferedImage img) {
		try {
				img = ImageIO.read(getClass().getResource(url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void removeBuildableObjects() {
		gameObjects = gameObjects.stream().filter(obj -> !obj.isBuildable()).collect(Collectors.toList());
	}

	
	private void freezeMoveableObjects() {
		gameObjects.stream().forEach(obj -> obj.setMoveable(false));
	}
	
	public String getName() {
		return name;
	}
	
	
	protected void clearSelectedObj() {
		
		if(selectedGameObject != null) 	selectedGameObject.release();
		selectedGameObject = null;
	}
	protected abstract GameObject getClickedObject(Point p);

	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);

	public void stopSound(SoundType soundType) {
		// TODO Auto-generated method stub
		Game.getInstance().stopSound(soundType);
	}

	public abstract UIType getSceneType();

	public abstract void handleEvent(GameEventType type);
	
}
