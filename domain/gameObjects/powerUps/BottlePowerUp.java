package domain.gameObjects.powerUps;

import java.awt.Point;
import java.awt.image.BufferedImage;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;

public class BottlePowerUp extends PowerUp{

	public BottlePowerUp(int x, int y) {
		super("bottle powerUp", x, y, 19, 32);
		//loadImg("/images/bottle");
		currSprite = SpriteType.BOTTLE;
		
		
		clickable = true;
	}

	@Override
	public void action(LevelScene scene) {
		// TODO Auto-generated method stub
		scene.getPlayer().putBottleInHand();
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		if(clickable) {
			LevelScene levelScene = ((LevelScene) scene);
			
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			
			levelScene.getPlayer().getBackPack().addToBackPack(this);
			levelScene.removePowerUpFromScene(this);
			clickable = false;
		}
	}

	
	@Override
	public SpriteType getIcon() {
		// TODO Auto-generated method stub
		return currSprite;
	}

}
