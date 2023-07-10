package ui.uiBuilders.sceneUIBuilders;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameObjects.TallWall;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiBuilders.UIBuilder;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.ButtonUI;

public class BuildingModeUIBuilder implements UIBuilder{

	@Override
	public List<UIObject> buildUI() {
		// TODO Auto-generated method stub
		List<UIObject> UIObjects = new ArrayList<UIObject>();
		
		Rectangle mapBounds = Game.getInstance().getCurrScene().getMapBounds();
		
		int x = (int) mapBounds.getMaxX() - SpriteManager.getInstance().getSprite(SpriteType.SAVE_LEVEL_BUTTON).getWidth();
		int y = (int) mapBounds.y - TallWall.HEIGHT - SpriteManager.getInstance().getSprite(SpriteType.SAVE_LEVEL_BUTTON).getHeight() - 5;
		
		ButtonUI saveButton = new ButtonUI("save level", x, y, GameEventType.SAVE_LEVEL, true, SpriteType.SAVE_LEVEL_BUTTON, SpriteType.TINTED_SAVE_LEVEL_BUTTON);
		
		x = (int) mapBounds.getX();
		y = (int) mapBounds.y - TallWall.HEIGHT - SpriteManager.getInstance().getSprite(SpriteType.RANDOMISE_BUTTON).getHeight() - 5;
		ButtonUI randomButton = new ButtonUI("randomise", x, y, GameEventType.RANDOMISE_LEVELS, true, SpriteType.RANDOMISE_BUTTON,SpriteType.TINTED_RANDOMISE_BUTTON);

		
		UIObjects.add(saveButton);
		UIObjects.add(randomButton);
		
		return UIObjects;
	}

}
