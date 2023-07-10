package ui.uiBuilders.sceneUIBuilders;

import ui.SpriteManager;
import ui.SpriteType;
import ui.uiBuilders.UIBuilder;
import ui.uiObjects.TextFieldUI;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.LoginButtonUI;
import ui.uiObjects.buttons.RegisterButtonUI;
import utility.Utilities;

import java.util.ArrayList;
import java.util.List;

public class LoginUIBuilder implements UIBuilder{

	@Override
	public List<UIObject> buildUI() {
		// TODO Auto-generated method stub
		//Rectangle mapBounds = Game.getInstance().getCurrScene().getMapBounds();
		List<UIObject> UIObjects = new ArrayList<UIObject>();



		int titleLabelWidth = SpriteManager.getInstance().getSprite(SpriteType.GAME_TITLE_LABEL).getWidth();
		int titleLabelHeight = SpriteManager.getInstance().getSprite(SpriteType.GAME_TITLE_LABEL).getHeight();
		int userNameLabelWidth = SpriteManager.getInstance().getSprite(SpriteType.USER_NAME_LABEL).getWidth();
		int userNameLabelHeight = SpriteManager.getInstance().getSprite(SpriteType.USER_NAME_LABEL).getHeight();

		int gameTitleX = (int) (Utilities.getScreenSize().getWidth()/2 - titleLabelWidth / 2);
		int gameTitleY = -500;

		int userNameX = gameTitleX + titleLabelWidth / 2 + 75;
		int userNameY = gameTitleY + titleLabelHeight + 75;

		int passwordX = userNameX;
		int passwordY = userNameY + userNameLabelHeight + 25;

		TextFieldUI username = new TextFieldUI(userNameX,userNameY);
		TextFieldUI password = new TextFieldUI(passwordX,passwordY);


		UIObjects.add(username);
		UIObjects.add(password);

		int width = SpriteManager.getInstance().getSprite(SpriteType.REGISTER_BUTTON).getWidth() + SpriteManager.getInstance().getSprite(SpriteType.LOGIN_BUTTON).getWidth() + 60;
		int registerX = (int) ((Utilities.getScreenSize().getWidth() / 2) - width / 2);


		UIObjects.add(new RegisterButtonUI(registerX,
				-150, username, password));
		UIObjects.add(new LoginButtonUI(registerX + SpriteManager.getInstance().getSprite(SpriteType.REGISTER_BUTTON).getWidth() + 60,
				-150, username, password));

		return UIObjects;
	}

}
