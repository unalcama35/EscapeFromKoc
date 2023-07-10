package ui.uiBuilders;

import domain.gameLogic.GameEventType;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.SaveButtonGroupUI;
import ui.uiObjects.TextFieldUI;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.ButtonUI;
import ui.uiObjects.buttons.SaveGameButtonUI;
import ui.uiObjects.panels.SavePanelUI;
import utility.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SaveWindowUIBuilder implements UIBuilder{
	
	private List<UIObject> UIObjects;

	public SaveWindowUIBuilder() {
		UIObjects = new ArrayList<UIObject>();
		Rectangle screen = new Rectangle(0,0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);
		
		SavePanelUI savePanel = new SavePanelUI((int)screen.getCenterX(), (int)screen.getCenterY());
		int menuBorderWidth = 23;
		int heightUnit = (savePanel.getRect().height - menuBorderWidth * 2) / 5;
		
		
		UIObjects.add(savePanel);
		
		int x = (int)savePanel.getRect().getCenterX() - SpriteManager.getInstance().getSprite(SpriteType.TEXT_FIELD).getWidth() / 2;
		int y = (int)savePanel.getRect().getCenterY() + menuBorderWidth + 75;
		
		TextFieldUI textField = new TextFieldUI(x, y);
		
		UIObjects.add(textField);
		
		//SaveGameButtonUI saveButton = new SaveGameButtonUI(x,y + heightUnit, textField);
		BufferedImage sprite = SpriteManager.getInstance().getSprite(SpriteType.SAVE_GAME_MENU_BUTTON);

		SaveButtonGroupUI saveButtonGroup  = new SaveButtonGroupUI((int) savePanel.getRect().getCenterX(), (int) savePanel.getRect().getCenterY(),300,200);

		UIObjects.add(new SaveGameButtonUI((int)savePanel.getRect().getCenterX() - sprite.getWidth()/ 2, 50 + y + sprite.getHeight() / 2, SpriteType.SAVE_GAME_MENU_BUTTON,SpriteType.TINTED_SAVE_GAME_MENU_BUTTON, textField, saveButtonGroup));
		UIObjects.add(new ButtonUI("close load menu", (int) savePanel.getRect().getMaxX() - SpriteManager.getInstance().getSprite(SpriteType.CROSS_BUTTON).getWidth(), savePanel.getRect().y, GameEventType.CLOSE_LOAD_MENU, false, SpriteType.CROSS_BUTTON, SpriteType.TINTED_CROSS_BUTTON));
		UIObjects.add(saveButtonGroup);

		
	}
	
	@Override
	public List<UIObject> buildUI() {
		// TODO Auto-generated method stub
		return UIObjects;
	}

}
