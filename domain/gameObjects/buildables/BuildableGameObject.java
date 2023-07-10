package domain.gameObjects.buildables;

import java.awt.Point;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.GameObject;
import ui.animations.BuildableObjClickAnim;
import ui.drawables.DrawableType;

public abstract class BuildableGameObject extends GameObject{

	protected int xDstFromMouse, yDstFromMouse;
	protected boolean hasKey;

	
	public BuildableGameObject(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		pressed = false;
		hasKey = false;

		// TODO Auto-generated constructor stub
	}
	
	public BuildableGameObject(String name) {
		super(name);
		pressed = false;
		hasKey = false;
		
	}

	
	public void setKey(boolean key) {
		this.hasKey = key;
	}

	public boolean hasKey() {
		return hasKey;
	}
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
	
    protected abstract BuildableGameObject newInstance(int x, int y);

		
	@Override
	public void onClick(Point p, Scene scene) {
		if(clickable) {
			if(buildable) {
				BuildableGameObject obj = this.newInstance(p.x - (p.x - rect.x), p.y - (p.y - rect.y));
				obj.setClickable(true);
				obj.setMoveable(true);
				
				scene.buildNewObject(obj);
			}else {
				Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
				if(moveable) {
					if(!pressed) {
						xDstFromMouse = p.x - rect.x;
						yDstFromMouse = p.y - rect.y;
						pressed = true;
					}
					this.rect.x = p.x - xDstFromMouse;
					this.rect.y = p.y - yDstFromMouse;		
				}else {

					if(hasKey) {
						((LevelScene) scene).foundKey();
					}
				}
				
			}
		}
	}



	
	

}
