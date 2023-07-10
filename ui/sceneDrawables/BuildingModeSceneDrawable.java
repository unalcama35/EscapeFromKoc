package ui.sceneDrawables;

import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableManager;
import ui.uiBuilders.UIType;
import ui.uiObjects.UIObject;
import utility.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class BuildingModeSceneDrawable extends SceneDrawable {
	
	private BufferedImage floorTile;
	private BufferedImage buildingSlot;
	private BufferedImage background;

	
	public BuildingModeSceneDrawable() {
		loadImgs();

	}
	
	private void loadImgs() {
		floorTile = loadImg("wood_tile");
		buildingSlot = loadImg("buildingSlot");
		background = loadImg("level_background");
		//background = Utilities.tint(background, new Color(0,0,0, 100));
		background = Utilities.tint(Utilities.resizeImage(background, Utilities.getScreenSize().width, Utilities.getScreenSize().height),new Color(0,0,0, 100));
	}

	@Override
	public void renderScene(Graphics2D g2, Scene scene, List<UIObject> UIObjects) {
		// TODO Auto-generated method stub
		//g2.drawImage(background, 0,0,null);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
		//drawBackground(g2);
		drawFloor(g2, scene);
		
		
		
		drawSelectionOutline(g2, scene);

		drawBuildableSlots(g2, scene);
		
		synchronized(scene.getGameObjects()) {
			renderWalls(g2, scene);
			renderNotWalls(g2, scene);
		}

		for (UIObject obj : UIObjects) {
			DrawableManager.getInstance().addRefAndDraw(g2,obj.getDrawableType(), obj);
		}

	}
	
	
	
	private void drawFloor(Graphics2D g2, Scene scene) {
		// TODO Auto-generated method stub
		int x = 0;		
		while(x*32 < scene.getMapBounds().width) {
			int y = 0;
			while(y*32 < scene.getMapBounds().height) {
				g2.drawImage(floorTile, x*floorTile.getWidth()+scene.getMapBounds().x, y*floorTile.getWidth()+scene.getMapBounds().y, null);
				y++;
			}
			x++;
		}
	}

	
	
	private void drawSelectionOutline(Graphics2D g2, Scene scene) {
		Point point = MouseInfo.getPointerInfo().getLocation();
		
		if(scene.getSelectedObject() != null && scene.getMapBounds().contains(point)) {
			g2.setColor(Color.white);
			g2.drawRect(((point.x-scene.getMapBounds().x)/32)*32+scene.getMapBounds().x, 
					((point.y-scene.getMapBounds().y)/32)*32+scene.getMapBounds().y, 32, 32);
		}
	}
	
	private void drawBuildableSlots(Graphics2D g2, Scene scene) {
		for(int i = 1;i<3; i++) {
			for(int j = 0;j<2;j++) {
				g2.drawImage(buildingSlot, scene.getMapBounds().x - i*buildingSlot.getWidth() - 10, scene.getMapBounds().y  + j*buildingSlot.getHeight(),  null);
			}
		}
	}
	
	

	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.BUILDING_MODE_SCENE;
	}

}
