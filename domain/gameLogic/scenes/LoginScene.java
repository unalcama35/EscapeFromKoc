package domain.gameLogic.scenes;

import db.DatabaseConnection;
import domain.gameLogic.GameEventType;
import domain.gameObjects.GameObject;
import ui.uiBuilders.UIType;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LoginScene extends Scene{

	public LoginScene(String name) {
		super(name);
		DatabaseConnection.getInstance();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected GameObject getClickedObject(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.LOGIN_SCENE;
	}

	@Override
	public void handleEvent(GameEventType type) {
		// TODO Auto-generated method stub
		if(type == GameEventType.PLAY_GAME){
			getSceneManager().moveToNextScene();
		}
	}

}
