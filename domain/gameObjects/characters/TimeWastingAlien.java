package domain.gameObjects.characters;

import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableType;

import java.awt.*;

public class TimeWastingAlien extends Character{

	private final int cooldown = 5000;
	private long cooldownStartTime;

	private TimeWastingAlienBehaviour behaviour;


	
	public TimeWastingAlien(int x, int y) {
		super("time-wasting alien", x, y, 32, 37);
		loadImgs("TIME_WASTING_ALIEN");


		cooldownStartTime = System.currentTimeMillis();
		currDirSpriteIndex = 0;
		currSprite = sprites.get(currDirSpriteIndex);


		//setBoundsToSprite(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(LevelScene scene) {

		if(behaviour == null){
			behaviour = (scene.getTimeLeft() < 0.3 * scene.getTotalTime()) ? (new FirstBehaviour()) : (scene.getTimeLeft() > 0.7 * scene.getTotalTime()) ? new SecondBehaviour() : new ThirdBehaviour();
		}
		behaviour.update(scene, this);

//		if(System.currentTimeMillis() - cooldownStartTime >= cooldown) {
//			cooldownStartTime = System.currentTimeMillis();
//
//			scene.moveKey();
//			//scene.updateHintAnim();
//		}
		
		if(System.currentTimeMillis() - startTimeSprite >= interval) {
			startTimeSprite = System.currentTimeMillis();			
			changeImg();
		}
		
	}

	protected void changeImg() {
		
		if(currDirSpriteIndex == 2) {
			currDirSpriteIndex = 0;
		}else {
			currDirSpriteIndex++;
		}

		currSprite = sprites.get(currDirSpriteIndex);
		//setBoundsToSprite(rect.x,rect.y);
	}
	
	


	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SIMPLE_SPRITE;
	}
}
