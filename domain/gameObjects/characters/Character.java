package domain.gameObjects.characters;

import domain.gameObjects.GameObject;
import ui.SpriteType;

import java.awt.*;
import java.util.List;

public abstract class Character extends GameObject{

	protected SpriteType[] currDirSprites;
	protected int currDirSpriteIndex;
	
	protected long startTimeSprite;
	protected long interval = 60;
	
	protected double dx, dy;

	private boolean active;

	public Character(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		active = true;
		this.dx = 0;
		this.dy = 0;
		
		startTimeSprite = System.currentTimeMillis();

		// TODO Auto-generated constructor stub
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	protected boolean checkCollision(List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		for(GameObject obj : gameObjects) {
			int rayWidth = 1;
			
			Rectangle botRay1 = new Rectangle(rect.x + 1,rect.y + rect.height,(int) rect.getWidth() - 2, 1);
			Rectangle topRay = new Rectangle(rect.x + 1,rect.y - rayWidth,(int) rect.getWidth() - 2,rayWidth);
			Rectangle leftRay1 = new Rectangle(rect.x - rayWidth,rect.y + 1,1,(int) rect.getHeight() - 2);
			Rectangle rightRay1 = new Rectangle((int) rect.getMaxX(),rect.y + 1,1,(int) rect.getHeight() - 2);

			if(obj != this) {
				if(dx > 0 && rightRay1.intersects(obj.getRect())) {
					rect.x = obj.getRect().x-rect.width;
					return true;
					//System.out.println("1");
				}else if(dx < 0 && leftRay1.intersects(obj.getRect())) {
					rect.x = obj.getRect().x + obj.getRect().width;
					return true;
					//System.out.println("2");
				}else if(dy > 0 && botRay1.intersects(obj.getRect())) {
					rect.y = obj.getRect().y - rect.height;
					return true;
					//System.out.println("down");
				}else if(dy < 0 && topRay.intersects(obj.getRect())) {
					rect.y = obj.getRect().y + obj.getRect().height;
					return true;
					//System.out.println("4");
				}
			}
		}	
		return false;
	}

}
