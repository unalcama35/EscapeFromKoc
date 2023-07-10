package ui;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameObjects.buildables.BuildableGameObject;
import ui.animations.Animation;
import ui.animations.HintAnimation;
import ui.drawables.DrawableManager;
import ui.drawables.DrawableType;
import ui.drawables.PlayerDrawable;
import ui.sceneDrawables.LoginSceneDrawable;
import ui.sceneDrawables.SceneDrawableManager;
import ui.sounds.SoundFactory;
import ui.sounds.SoundManager;
import ui.sounds.SoundType;
import ui.uiBuilders.UIBuilderFactory;
import ui.uiBuilders.UIType;
import ui.uiObjects.DialogUI;
import ui.uiObjects.TextFieldUI;
import ui.uiObjects.UIObject;
import utility.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainGamePanel extends JPanel{
	
	//public static final int WIDTH = 896, HEIGHT = 640;
	
	private List<Animation> animations;
	private List<UIObject> UIObjects;

	private UIObject focusedObj;
	private UIObject hoveredObj;

	private SoundManager soundManager;
	private SceneDrawableManager sceneDrawableManager;
			
	private UIType currSceneType;


	private long startTime;
	private long renderedFrames;
	
	public MainGamePanel() {
		//this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//		this.game = game;
		animations = new ArrayList<Animation>();
		UIObjects = new ArrayList<UIObject>();

		//buildUIObjects();
		soundManager = new SoundManager();
		sceneDrawableManager = new SceneDrawableManager();
		

		startTime = System.currentTimeMillis();
		
		//currSceneType = SceneType.LOGIN_SCENE;
		sceneChange(UIType.LOGIN_SCENE);

		//soundManager.getSound(SoundType.BACKGROUND_SOUND).playSound();;
		
	}
	

	
	public void clickOnUIObject(Point p) {
		UIObject clickedObj = null;

		try{
			for(UIObject obj : this.UIObjects) {
				if (obj.getRect().contains(p)) {
					//System.out.println(obj.name);
					clickedObj = obj;
					obj.onClick(p, Game.getInstance().getCurrScene());
					if (focusedObj != null) focusedObj.setFocused(false);
					focusedObj = obj;
				}
			}
		}catch(Exception e){

		}





		if(clickedObj == null && focusedObj != null) {
			//UIObjects.stream().forEach(obj -> obj.setFocus(false));
			focusedObj.setFocused(false);
			focusedObj = null;
		}
	}


	public void mouseOverUIObj(Point p) {
		// TODO Auto-generated method stub
		UIObject mouseOverObj = null;
		for(UIObject obj : this.UIObjects) {
			if(obj.getRect().contains(p) && obj.isClickable()) {
				obj.onMouseOver(p);
				mouseOverObj = obj;
				hoveredObj = obj;
				//break;
			}

		}
		
		if(mouseOverObj == null && hoveredObj != null) {
			//UIObjects.stream().forEach(obj -> obj.setFocus(false));
			hoveredObj.mouseNotOver(p);
			hoveredObj = null;
		}
	}



	
	public void paintComponent( Graphics g) {		
		super.paintComponent( g );
        Graphics2D g2 = (Graphics2D) g;
        //game.getCurrScene().renderScene(g2);
        Scene scene = Game.getInstance().getCurrScene();
        sceneDrawableManager.getSceneDrawable(currSceneType).renderScene(g2, scene, UIObjects);
        
        animateAnims();
		renderAnims(g2);
		removeEndedAnims();


		
		if(currSceneType == UIType.LEVEL_SCENE) {
			updateHintAnim((LevelScene)scene);
		}

//        renderedFrames++;
//		float secondsPassed = (float) (System.currentTimeMillis() - startTime) / 1000;
//		float fps = renderedFrames/secondsPassed;
//		g2.setColor(Color.red);
//		g2.setFont(new Font("Arial",0,12));
//		g2.drawString(String.format("%.1ffps", fps), 0, 10);
//		
//		if(System.currentTimeMillis() - startTime > 5000) {
//			startTime = System.currentTimeMillis();
//			renderedFrames = 0;
//		}
		
		//System.out.println(renderedFrames / secondsPassed+"fps");
		
	}
	
	protected Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
		
	public void sceneChange(UIType sceneType) {
		currSceneType = sceneType;
		
		if(sceneType == UIType.LEVEL_SCENE) {
			animations.removeIf(anim -> anim instanceof HintAnimation);
			UIObjects = UIBuilderFactory.getSceneUI(sceneType).buildUI();
		}else {
			UIObjects = UIBuilderFactory.getSceneUI(sceneType).buildUI();
		}

	}
	
	private void updateHintAnim(LevelScene scene) {
		BuildableGameObject keyObject = scene.findKeyObject();
		
		if(keyObject != null) {
			animations.stream().forEach(anim -> {
				if(anim instanceof HintAnimation) {
					((HintAnimation) anim).moveHint(keyObject.getRect());
				}
			});
		}
		
	}

	
	private void renderAnims(Graphics2D g2) {
		animations.stream().forEach(anim -> anim.render(g2));
	}
	
	protected void animateAnims() {
		animations.stream().forEach(anim -> anim.animate());
	}
	
	protected void removeEndedAnims() {
		animations = animations.stream().filter(anim -> !anim.hasEnded()).collect(Collectors.toList());
	}
	
	public void addAnimation(Animation anim) {
		animations.add(anim);
	}

	public void addAndPlaySound(SoundType soundType) {
		SoundFactory.createSound(soundType).playSound();
	}
	
	public void playSound(SoundType soundType) {
		// TODO Auto-generated method stub
		soundManager.getSound(soundType).playSound();	
	}
	
	public void stopSound(SoundType soundType) {
		soundManager.getSound(soundType).stopSound();
	}

	public void lostLife() {
		// TODO Auto-generated method stub
		((PlayerDrawable) DrawableManager.getInstance().getDrawable(DrawableType.PLAYER)).lostLife();
	}



	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(focusedObj != null && focusedObj instanceof TextFieldUI) {
			((TextFieldUI) focusedObj).addChar(e);

		}

	}

	private void removeDialogWindow(){
		UIObjects.removeIf(obj -> obj instanceof DialogUI);
	}



	public void handleEvent(GameEventType type) {
		// TODO Auto-generated method stub
		
		switch(type) {
			case PAUSE_GAME:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.LEVEL_MENU).buildUI();
				break;
			case RESUME_GAME:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.LEVEL_SCENE).buildUI();
				break;
			case OPEN_SAVE_GAME_MENU:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.SAVE_GAME_MENU).buildUI();
				break;
			case OPEN_LOAD_GAME_MENU:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.LOAD_GAME_MENU).buildUI();
				break;
			case CLOSE_LOAD_MENU:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.LEVEL_MENU).buildUI();
				break;
			case LOGGED_IN:
				((LoginSceneDrawable)sceneDrawableManager.getSceneDrawable(currSceneType)).loggedIn();
				UIObjects = UIBuilderFactory.getSceneUI(UIType.MAIN_MENU).buildUI();
				break;
			case CLOSE_DIALOG_WINDOW:
				removeDialogWindow();
				break;
			case OPEN_HELP_GAME_MENU:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.HELP_MENU).buildUI();
				break;
			case OPEN_MAIN_MENU:
				UIObjects = UIBuilderFactory.getSceneUI(UIType.MAIN_MENU).buildUI();
				//Game.getInstance().addEventToDomain(GameEventType.OPEN_MAIN_MENU);
				break;
			case LOST_GAME:
				newDialog("You Lost!", GameEventType.OPEN_MAIN_MENU);
				break;
			case WON_GAME:
				newDialog("You Won!", GameEventType.OPEN_MAIN_MENU);
				break;
			default:
				break; 
		}
			

	}


	public void newDialog(String text) {
		int x = (int) (Utilities.getScreenSize().getWidth() / 2 - SpriteManager.getInstance().getSprite(SpriteType.DIALOG_PANEL).getWidth() / 2);
		int y = (int) (Utilities.getScreenSize().getHeight() / 2 - SpriteManager.getInstance().getSprite(SpriteType.DIALOG_PANEL).getHeight() / 2);

		UIObjects.add(new DialogUI(text, x, y));


	}

	public void newDialog(String text, GameEventType type) {
		int x = (int) (Utilities.getScreenSize().getWidth() / 2 - SpriteManager.getInstance().getSprite(SpriteType.DIALOG_PANEL).getWidth() / 2);
		int y = (int) (Utilities.getScreenSize().getHeight() / 2 - SpriteManager.getInstance().getSprite(SpriteType.DIALOG_PANEL).getHeight() / 2);

		UIObjects.add(new DialogUI(text, x, y, type));


	}
}
