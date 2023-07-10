package ui.uiObjects.panels;

import domain.gameLogic.scenes.Scene;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.UIObject;

import java.awt.*;

public class LoadPanelUI extends UIObject{

	public LoadPanelUI(int x, int y) {
		super("save panel");
		icon = SpriteType.LOAD_PANEL;
		currSprite = icon;
		clickable = false;
		setBoundsToSprite(x - SpriteManager.getInstance().getSprite(icon).getWidth() / 2, y - SpriteManager.getInstance().getSprite(icon).getHeight() / 2);

	}


	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseOver(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseNotOver(Point p) {
		// TODO Auto-generated method stub
		
	}

}

