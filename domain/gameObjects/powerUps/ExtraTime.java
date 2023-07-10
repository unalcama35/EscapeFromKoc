package domain.gameObjects.powerUps;

import java.awt.Point;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.animations.SpriteChanger;

public class ExtraTime extends PowerUp{
	
	private SpriteChanger anim;

	public ExtraTime(int x, int y) {
		super("extra time", x, y, 32, 32);
		
		loadImgs("EXTRA_TIME");
		currSprite = sprites.get(0);
		
		clickable = true;
		anim = new SpriteChanger(8,false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(LevelScene scene) {
		// TODO Auto-generated method stub
		if(clickable) {
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));

//			this.animations.add(new BuildableObjClickAnim(this.rect.x,this.rect.y,this.rect));
			//((LevelScene) scene).getPlayer().getBackPack().addToBackPack(this);
			((LevelScene) scene).removePowerUpFromScene(this);
			((LevelScene) scene).addTime(5);
			clickable = false;
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
		action((LevelScene) scene);
	}

	
	@Override
	public SpriteType getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
