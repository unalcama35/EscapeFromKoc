package domain.gameObjects.powerUps;

import java.awt.Point;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.buildables.BuildableGameObject;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.animations.HintAnimation;
import ui.animations.SpriteChanger;

public class Hint extends PowerUp {
	
	private SpriteChanger anim;

	public Hint(int x, int y) {
		super("hint", x, y, 35, 32);
		loadImgs("HINT");
		currSprite = sprites.get(0);
		
		clickable = true;
		anim = new SpriteChanger(7,true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(LevelScene scene) {
		// TODO Auto-generated method stub
		//System.out.println(scene.findKeyObject().name);
		BuildableGameObject keyObject = scene.findKeyObject();
		if(keyObject != null) {
			Game.getInstance().addAnimation(new HintAnimation(keyObject.getRect()));
		}
		
	}
	
	@Override
	public void update(LevelScene scene) {		
		if(anim.animate()) {
			currSprite = sprites.get(anim.getCurrSpriteInd());
			//rect.width = currSprite.getWidth();
			//rect.height = currSprite.getHeight();
		}
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		if(clickable) {
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			//this.animations.add(new BuildableObjClickAnim(this.rect.x,this.rect.y,this.rect));
			((LevelScene) scene).getPlayer().getBackPack().addToBackPack(this);
			((LevelScene) scene).removePowerUpFromScene(this);
			clickable = false;
		}
	}



	@Override
	public SpriteType getIcon() {
		// TODO Auto-generated method stub
		return sprites.get(5);
	}

}
