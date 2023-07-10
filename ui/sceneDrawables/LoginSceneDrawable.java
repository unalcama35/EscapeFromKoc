package ui.sceneDrawables;

import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableManager;
import ui.uiBuilders.UIType;
import ui.uiObjects.UIObject;
import utility.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class LoginSceneDrawable extends SceneDrawable{

	private BufferedImage gameTitleLabel;
	private BufferedImage userNameLabel;
	private BufferedImage passwordLabel;
	private BufferedImage background;

	private int labelsY;
	private double dropSpeed = 3;
	private double acceleration = 2;

	private boolean loggedIn;

	public LoginSceneDrawable() {
		loadImgs();
		labelsY = -500;
		loggedIn = false;
	}
	
	private void loadImgs() {
		gameTitleLabel = loadImg("title_label");
		userNameLabel = loadImg("username_label");
		passwordLabel = loadImg("password_label");
		background = loadImg("login_background");
		background = Utilities.tint(Utilities.resizeImage(background, Utilities.getScreenSize().width, Utilities.getScreenSize().height),new Color(0,0,0, 150));

	}
	
	@Override
	public void renderScene(Graphics2D g2, Scene scene, List<UIObject> UIObjects) {
		// TODO Auto-generated method stub
		
		//drawBackground(g2);
		g2.drawImage(background, 0,0,null);



		int gameTitleX = (int) (Utilities.getScreenSize().getWidth()/2 - gameTitleLabel.getWidth() / 2);
		int gameTitleY = labelsY;
		g2.drawImage(gameTitleLabel, gameTitleX,gameTitleY, null);
		
		int userNameX = gameTitleX + gameTitleLabel.getWidth() / 2 - userNameLabel.getWidth();
		int userNameY = gameTitleY + gameTitleLabel.getHeight()+50;

		int passwordX = userNameX;
		int passwordY = userNameY + userNameLabel.getHeight() + 25;

		if(!loggedIn){
			g2.drawImage(userNameLabel, userNameX, userNameY, null);
			g2.drawImage(passwordLabel, passwordX, passwordY, null);
		}


		for (UIObject obj : UIObjects) {
			DrawableManager.getInstance().addRefAndDraw(g2,obj.getDrawableType(), obj);
		}

		simulateLabels(UIObjects);



	}

	public void loggedIn(){ loggedIn = true;}
	private void simulateLabels(List<UIObject> UIObjects){
		if(Math.abs(acceleration) <= 2){
			acceleration *= 1.01f;
		}

		dropSpeed += acceleration;

		//System.out.println(labelsY + ";;;;;;;" + ((Utilities.getScreenSize().getHeight()/2 - gameTitleLabel.getHeight() / 2) - 100));
		if(dropSpeed >= 0 && labelsY >= (Utilities.getScreenSize().getHeight()/2 - gameTitleLabel.getHeight() / 2) - 100) {
			dropSpeed = dropSpeed * -0.5;
		}
		//System.out.println(dropSpeed);
		if(Math.abs(dropSpeed) <= 0.45 && dropSpeed >= 0){
			//System.out.println("here\n");
			dropSpeed = 0;
			acceleration = 0;
		}

		labelsY += dropSpeed;
		for(UIObject obj : UIObjects) {
			obj.getRect().y += dropSpeed;
		}
	}

	@Override
	public UIType getSceneType() {
		// TODO Auto-generated method stub
		return UIType.LOGIN_SCENE;
	}

}
