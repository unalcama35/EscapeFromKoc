package ui.uiBuilders;

import domain.gameLogic.GameEventType;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.ButtonUI;
import ui.uiObjects.panels.MenuPanelUI;
import utility.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LevelMenuUIBuilder implements UIBuilder {
	
	private List<UIObject> UIObjects;
	
	public LevelMenuUIBuilder() {
		UIObjects = new ArrayList<UIObject>();
		Rectangle screen = new Rectangle(0,0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
		
		MenuPanelUI menuPanel = new MenuPanelUI((int)screen.getCenterX(), (int)screen.getCenterY());
		int menuBorderWidth = 23;
		int heightUnit = (menuPanel.getRect().height - menuBorderWidth * 2) / 5;
		
		UIObjects.add(menuPanel);
		
		
		int buttonWidth = SpriteManager.getInstance().getSprite(SpriteType.RESUME_BUTTON).getWidth();
		int panelCenter = (int)menuPanel.getRect().getCenterX();
		int y = (int)menuPanel.getRect().getY() + 10;

		
		UIObjects.add(new ButtonUI("resume", panelCenter - SpriteManager.getInstance().getSprite(SpriteType.RESUME_BUTTON).getWidth() / 2, y  + heightUnit - heightUnit / 2, GameEventType.RESUME_GAME, true, SpriteType.RESUME_BUTTON, SpriteType.TINTED_RESUME_BUTTON));
		UIObjects.add(new ButtonUI("save game", panelCenter - SpriteManager.getInstance().getSprite(SpriteType.SAVE_GAME_MENU_BUTTON).getWidth() / 2, y + heightUnit*2 - heightUnit / 2, GameEventType.OPEN_SAVE_GAME_MENU, false, SpriteType.SAVE_GAME_MENU_BUTTON,SpriteType.TINTED_SAVE_GAME_MENU_BUTTON));
		UIObjects.add(new ButtonUI("load game", panelCenter - SpriteManager.getInstance().getSprite(SpriteType.LOAD_BUTTON).getWidth() / 2, y + heightUnit*3 - heightUnit / 2, GameEventType.OPEN_LOAD_GAME_MENU, false, SpriteType.LOAD_BUTTON, SpriteType.TINTED_LOAD_BUTTON));
		UIObjects.add(new ButtonUI("help", panelCenter - SpriteManager.getInstance().getSprite(SpriteType.HELP_BUTTON).getWidth() / 2, y + heightUnit*4 - heightUnit / 2, GameEventType.OPEN_HELP_GAME_MENU, false, SpriteType.HELP_BUTTON, SpriteType.TINTED_HELP_BUTTON));
		UIObjects.add(new ButtonUI("quit", panelCenter - SpriteManager.getInstance().getSprite(SpriteType.QUIT_BUTTON).getWidth() / 2, y + heightUnit*5 - heightUnit / 2, GameEventType.CLOSE_GAME, false, SpriteType.QUIT_BUTTON, SpriteType.TINTED_QUIT_BUTTON));

	}

	@Override
	public List<UIObject> buildUI() {
		// TODO Auto-generated method stub
		



		return UIObjects;
	}

}
