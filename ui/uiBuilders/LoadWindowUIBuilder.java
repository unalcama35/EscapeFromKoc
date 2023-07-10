package ui.uiBuilders;

import domain.gameLogic.GameEventType;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.LoadButtonGroupUI;
import ui.uiObjects.ScrollViewUI;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.ButtonUI;
import ui.uiObjects.buttons.LoadGameButtonUI;
import ui.uiObjects.panels.LoadPanelUI;
import utility.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoadWindowUIBuilder implements UIBuilder{

	
	@Override
	public List<UIObject> buildUI() {
		// TODO Auto-generated method stub
		List<UIObject> UIObjects = new ArrayList<UIObject>();
		Rectangle screen = new Rectangle(0,0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
		
		LoadPanelUI loadPanel = new LoadPanelUI((int)screen.getCenterX(), (int)screen.getCenterY());
		int menuBorderWidth = 23;
		int heightUnit = (loadPanel.getRect().height - menuBorderWidth * 2) / 5;


		UIObjects.add(loadPanel);
		Rectangle loadPanelRect = loadPanel.getRect();

		int x = loadPanelRect.x + menuBorderWidth;
		int y = loadPanelRect.y + menuBorderWidth;

		LoadButtonGroupUI dummyObj =  new LoadButtonGroupUI((int) loadPanel.getRect().getCenterX(),loadPanelRect.y + 15,300,200, null);

		ScrollViewUI savesScrollView = new ScrollViewUI(x, (int) dummyObj.getRect().getMaxY(),
				loadPanelRect.width - menuBorderWidth * 2, 300);


		LoadButtonGroupUI loadButtonGroupUI = new LoadButtonGroupUI((int) loadPanel.getRect().getCenterX(),loadPanelRect.y + 15,300,200, savesScrollView);

		UIObjects.add(savesScrollView);

		UIObjects.add(new LoadGameButtonUI((int) (loadPanelRect.getCenterX() - SpriteManager.getInstance().getSprite(SpriteType.LOAD_BUTTON).getWidth() / 2), (int) (loadPanelRect.getMaxY() - SpriteManager.getInstance().getSprite(SpriteType.LOAD_BUTTON).getHeight()) - 20, SpriteType.LOAD_BUTTON, SpriteType.TINTED_LOAD_BUTTON, savesScrollView, loadButtonGroupUI));
		
		UIObjects.add(new ButtonUI("close load menu", (int) loadPanel.getRect().getMaxX() - SpriteManager.getInstance().getSprite(SpriteType.CROSS_BUTTON).getWidth(), loadPanel.getRect().y, GameEventType.CLOSE_LOAD_MENU, false, SpriteType.CROSS_BUTTON, SpriteType.TINTED_CROSS_BUTTON));

		UIObjects.add(loadButtonGroupUI);
		
		return UIObjects;
	}

}
