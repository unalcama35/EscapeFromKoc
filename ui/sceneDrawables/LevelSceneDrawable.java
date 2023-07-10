package ui.sceneDrawables;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.TallWall;
import domain.gameObjects.characters.Player;
import domain.gameObjects.powerUps.PowerUp;
import ui.SpriteManager;
import ui.SpriteType;
import ui.drawables.DrawableManager;
import ui.uiBuilders.UIType;
import ui.uiObjects.UIObject;
import utility.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class LevelSceneDrawable extends SceneDrawable {
	
	private BufferedImage inventorySlot;
	private BufferedImage heartImg;
	private BufferedImage floorTile;
	private BufferedImage background;



	public LevelSceneDrawable() {
		loadImgs();
	}
	
	private void loadImgs() {
			heartImg = loadImg("heart");
			inventorySlot = loadImg("inventorySlot");
			floorTile = loadImg("wood_tile");
			background = loadImg("level_background");
			//background = Utilities.tint(background, new Color(0,0,0, 100));
			background = Utilities.tint(Utilities.resizeImage(background, Utilities.getScreenSize().width, Utilities.getScreenSize().height),new Color(0,0,0, 100));

	}
	
	@Override
	public void renderScene(Graphics2D g2, Scene scene, List<UIObject> UIObjects) {
		// TODO Auto-generated method stub
		LevelScene levelScene = (LevelScene) scene;

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
		//g2.drawImage(background, 0,0, null);
		//drawBackground(g2);
		drawFloor(g2, scene);
		drawInventory(g2, levelScene);
		drawLevelLabel(g2, levelScene);	
		drawLives(g2, levelScene);
		drawTime(g2,levelScene);
		
		synchronized(scene.getGameObjects()) {
			renderWalls(g2, scene);
			renderNotWalls(g2, scene);
		}
		
		if(!levelScene.getActive()) {
			g2.setColor(new Color(0, 0, 0, 125));
			g2.fillRect(0, 0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
			
			g2.setColor(new Color(255,255,255));
			g2.setFont(new Font("Arial", 0, 50));
			
			if(Game.getInstance().getSceneManager().getGameEnded() && Game.getInstance().getSceneManager().getPlayerWon()) {
				g2.drawString("YOU WON", Utilities.getScreenSize().width/2 - 85, Utilities.getScreenSize().height/2);
			}else if(Game.getInstance().getSceneManager().getGameEnded() && !Game.getInstance().getSceneManager().getPlayerWon()) {
				g2.drawString("YOU LOST", Utilities.getScreenSize().width/2 - 85, Utilities.getScreenSize().height/2);
			}
		}

		for (UIObject obj : UIObjects) {
			DrawableManager.getInstance().addRefAndDraw(g2,obj.getDrawableType(), obj);
		}
		//renderRays(g2, levelScene.getPlayer());
		
		

	}


	private void drawTime(Graphics2D g2, LevelScene levelScene){
		int timeLeftMins = levelScene.getTimeLeft() / 60;
		int timeLeftSecs = levelScene.getTimeLeft() - timeLeftMins * 60;

		int padding = 10;

		g2.drawImage(getDigitImg(timeLeftMins / 10), 0,0,null);
		g2.drawImage(getDigitImg(timeLeftMins % 10), 50,0,null);
		g2.drawImage(SpriteManager.getInstance().getSprite(SpriteType.COLON), 100,3,null);
		g2.drawImage(getDigitImg(timeLeftSecs / 10), 130,0,null);
		g2.drawImage(getDigitImg(timeLeftSecs % 10), 180,0,null);
	}

	private BufferedImage getDigitImg(int digit){
		switch (digit) {
			case 0:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_0);
			case 1:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_1);
			case 2:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_2);
			case 3:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_3);
			case 4:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_4);
			case 5:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_5);
			case 6:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_6);
			case 7:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_7);
			case 8:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_8);
			case 9:
				return SpriteManager.getInstance().getSprite(SpriteType.DIGIT_9);
		}
		return null;
	}
	private void renderRays(Graphics2D g2, Player player) {
		g2.setColor(Color.blue);
		Rectangle rect = player.getRect();
		int rayWidth = 1;

		g2.drawRect(rect.x + 1,rect.y + rect.height,(int) rect.getWidth() - 2, 1);
		
		g2.drawRect(rect.x - rayWidth,rect.y + 1,1,(int) rect.getHeight() - 2);
		
		g2.drawRect(rect.x + 1,rect.y - rayWidth,(int) rect.getWidth() - 2,rayWidth);
		
		g2.drawRect((int) rect.getMaxX(),rect.y + 1,1,(int) rect.getHeight() - 2);


		
//		g2.drawRect(rect.x,rect.y + rect.height,1,rayWidth);
//		g2.drawRect(rect.x + rect.width,rect.y + rect.height,1,rayWidth);
//		
//		
//		g2.drawRect(rect.x + rect.width/2,rect.y - rayWidth,1,rayWidth);
//		g2.drawRect(rect.x - rayWidth,rect.y + rect.height/2,rayWidth,1);
//		
//		
//		g2.drawRect(rect.x + rect.width,rect.y + rect.height/2,rayWidth,1);
//		g2.drawRect(rect.x + rect.width,rect.y,rayWidth,1);
//		g2.drawRect(rect.x + rect.width,rect.y + rect.height,rayWidth,1);

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
	
	private void drawLevelLabel(Graphics2D g2, LevelScene scene) {
		Rectangle mapBounds = scene.getMapBounds();
		
		BufferedImage lvlLabel = SpriteManager.getInstance().getSprite(scene.getLabelImg());
		
		g2.drawImage(lvlLabel, (int) (mapBounds.getCenterX() - lvlLabel.getWidth() / 2)
				, mapBounds.y - lvlLabel.getHeight() - TallWall.HEIGHT - 20, null);
	}
	
	private void drawInventory(Graphics2D g2, LevelScene scene) {
		
		Rectangle mapBounds = scene.getMapBounds();
		
		for(int i = 0;i < 5;i++) {
			g2.drawImage(inventorySlot,mapBounds.x + i*inventorySlot.getWidth() - 5, (int) mapBounds.getMaxY() + 5, null);
		}
		
		List<PowerUp> powerUps = scene.getPlayer().getBackPack().getPowerUps();
		
		if(!powerUps.isEmpty()) {
			for(int i=0; i< powerUps.size(); i++) {
			//g2.drawString(""+powerUps.get(i).name, i*inventorySlot.getWidth()+mapBounds.x, (int) mapBounds.getMaxY()+25);
				BufferedImage icon = SpriteManager.getInstance().getSprite(powerUps.get(i).getIcon());
				int x = mapBounds.x + i*inventorySlot.getWidth() + inventorySlot.getWidth()/2 -icon.getWidth()/2 - 6;
				int y = (int) mapBounds.getMaxY()+ inventorySlot.getHeight()/2-icon.getHeight()/2 + 5;
				g2.drawImage(icon, x, y, null);
				
			}
		}
		
	}
	
	private void drawLives(Graphics2D g2, LevelScene scene) {
		g2.setColor(Color.red);
		for(int i = 1; i <= scene.getPlayer().getLives(); i++) {
			g2.drawImage(heartImg,(int)scene.getMapBounds().getMaxX()-i*heartImg.getWidth(), (int) scene.getMapBounds().getMaxY()+5, null);
		}
	}
	
	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.LEVEL_SCENE;
	}

}
