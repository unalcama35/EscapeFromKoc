package ui.uiObjects.buttons;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.uiObjects.TextFieldUI;
import ui.uiObjects.UIObject;

import java.awt.*;

public class LoginButtonUI extends UIObject{

	private TextFieldUI username, password;
	
	public LoginButtonUI(int x, int y, TextFieldUI username, TextFieldUI password) {
		super("login button");
		// TODO Auto-generated constructor stub
		icon = SpriteType.LOGIN_BUTTON;
		tintedIcon = SpriteType.TINTED_LOGIN_BUTTON;
		currSprite = icon;
		
		
		setBoundsToSprite(x, y);
		
		this.username = username;
		this.password = password;
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		if(canBeClicked()) {
			//System.out.println(scene.getName());
			//this.animations.add(new BuildableObjClickAnim(this.rect));
			
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			//loginPanel.addAnimation(new LoadingAnimation());
			
			if(Game.getInstance().login(username.getText(), password.getText())) {
				Game.getInstance().addEventToUI(GameEventType.LOGGED_IN);

				//loginPanel.endLoadingAnim();
			};

			//Game.getInstance().login(username.getText(), password.getText());
		}
	}

	@Override
	public void onMouseOver(Point p) {
		// TODO Auto-generated method stub
		currSprite = tintedIcon;
	}

	@Override
	public void mouseNotOver(Point p) {
		// TODO Auto-generated method stub
		currSprite = icon;
	}

}
