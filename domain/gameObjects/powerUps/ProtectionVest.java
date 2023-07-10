package domain.gameObjects.powerUps;

import java.awt.Point;
import java.awt.image.BufferedImage;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.animations.VestAnimation;

public class ProtectionVest extends PowerUp{

	public ProtectionVest(int x, int y) {
		super("protection vest", x, y, 32, 21);
		currSprite = SpriteType.PROTECTION_VEST;
		
		
		
		clickable = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(LevelScene scene) {
		// TODO Auto-generated method stub
		scene.getPlayer().putVestOn();
		Game.getInstance().addAnimation(new VestAnimation(((LevelScene)scene).getPlayer()));

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
