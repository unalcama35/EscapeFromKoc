package ui.sceneDrawables;

import domain.gameLogic.scenes.Scene;
import domain.gameObjects.TallWall;
import domain.gameObjects.Wall;
import ui.drawables.DrawableManager;
import ui.uiBuilders.UIType;
import ui.uiObjects.UIObject;
import utility.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public abstract class SceneDrawable {

	public abstract void renderScene(Graphics2D g2, Scene scene, List<UIObject> UIObjects);
	public abstract UIType getSceneType();
	
	protected void drawBackground(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
	}
	
	protected BufferedImage loadImg(String url) {
		try {
			return ImageIO.read(getClass().getResource("/images/"+url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void renderWalls(Graphics2D g2, Scene scene){
		scene.getGameObjects().stream().forEach(obj -> {
			if(obj.getClass() == Wall.class || obj.getClass() == TallWall.class) {
				//DrawableFactory.getDrawable(obj).draw(g2);
				DrawableManager.getInstance().addRefAndDraw(g2, obj.getDrawableType(), obj);

			}
		});
	}
	
	protected void renderNotWalls(Graphics2D g2, Scene scene) {
		scene.getGameObjects().stream().forEach(obj -> {
			if(obj.getClass() != Wall.class && obj.getClass() != TallWall.class) {
			//obj.render(g2);
				//DrawableFactory.getDrawable(obj).draw(g2);
				DrawableManager.getInstance().addRefAndDraw(g2, obj.getDrawableType(), obj);
			}
		});
		
	
}

	
}
