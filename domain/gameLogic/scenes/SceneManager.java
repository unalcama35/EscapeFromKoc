package domain.gameLogic.scenes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameObjects.characters.Player;
import ui.uiBuilders.UIType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "_id"})
public class SceneManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8795388242332439354L;
	private List<Scene> scenes;
	private int currentScene;
	
	private boolean gameEnded;
	private boolean playerWon;
	
	private long startTime;
	private long renderedFrames;
	
	public SceneManager() {
		scenes = new ArrayList<Scene>();
		scenes.add(new LoginScene("login scene"));
		scenes.add(new BuildingModeScene("building mode"));
		
		gameEnded = false;
		playerWon = false;
		
		currentScene = 0;
		
		startTime = System.currentTimeMillis();
	}
	
	public void update() {
		//System.out.println("updating scene");
//		renderedFrames++;
//		float secondsPassed = (float) (System.currentTimeMillis() - startTime) / 1000;
//		float fps = renderedFrames/secondsPassed;
//		
//		System.out.println(fps);
//		
//		if(System.currentTimeMillis() - startTime > 5000) {
//			startTime = System.currentTimeMillis();
//			renderedFrames = 0;
//		}
		
		getCurrScene().updateScene();
	}
	
	
	public boolean getGameEnded() {
		return gameEnded;
	}
	
	public boolean getPlayerWon() {
		return playerWon;
	}
	
	
	public Scene getCurrScene() {
		return scenes.get(currentScene);
	}
	
	public void setCurrScene(int scene) {
		if(getCurrScene() instanceof LevelScene) {
			((LevelScene)getCurrScene()).setActive(false);
			currentScene = scene;
			((LevelScene)getCurrScene()).setActive(true);
			((LevelScene)getCurrScene()).startTimer();
		}
		
		currentScene = scene;
		
		if(getCurrScene() instanceof LevelScene) {
			((LevelScene)getCurrScene()).setActive(true);
			((LevelScene)getCurrScene()).startTimer();
		}
		
		Game.getInstance().sceneChange(getCurrScene().getSceneType());

	}
	
	public void moveToNextScene() {
		currentScene++;
		Game.getInstance().sceneChange(getCurrScene().getSceneType());

	}
	
	public Scene getScene(String sceneName) {
		
		return scenes.stream().filter(s -> s.getName().equals(sceneName)).findFirst().orElse(null);
		
	}
	
	public Scene getScene(int sceneInd) {
		return scenes.get(sceneInd);
	}
	
	public void moveToNextLevel() {
		
		if(currentScene <= 6) {
			movePlayerToNextScene();
			((LevelScene)getCurrScene()).setActive(false);

			currentScene++;
			((LevelScene)getCurrScene()).startTimer();
			((LevelScene)getCurrScene()).setActive(true);
			Game.getInstance().sceneChange(UIType.LEVEL_SCENE);
		}else {
			playerWon();
		}
	}
	
	private void playerWon() {
		if(!gameEnded){
			System.out.println("you won!");
			playerWon = true;
			((LevelScene)getCurrScene()).setActive(false);
			gameEnded = true;
			Game.getInstance().addEventToUI(GameEventType.WON_GAME);
		}

	}
	
	public void playerLost() {
		if(!gameEnded){
			((LevelScene)getCurrScene()).setActive(false);
			gameEnded = true;
			Game.getInstance().addEventToUI(GameEventType.LOST_GAME);
		}



		//System.out.println("lost");
	}
	
	private void movePlayerToNextScene() {
		Player tempPlayer = ((LevelScene) getScene(currentScene)).getPlayer();
		tempPlayer.getRect().y = (int) getScene(currentScene).getMapBounds().getMaxY() - tempPlayer.getRect().height;
		//getCurrScene().removeGameObject(tempPlayer);
		((LevelScene) getScene(currentScene + 1)).addGameObject(tempPlayer);
	}
	
	public List<Scene> getScenes(){
		return scenes;
	}


}
