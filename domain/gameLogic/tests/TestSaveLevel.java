package domain.gameLogic.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.BuildingModeScene;
import domain.gameObjects.GameObject;
import domain.gameObjects.characters.Player;

public class TestSaveLevel {
	
	
	@Test
	@DisplayName("Test Save Level - Number of Levels")
	public void testOne() {
		Game.getInstance().getSceneManager().setCurrScene(1);
		((BuildingModeScene) Game.getInstance().getSceneManager().getScene(1)).randomiseLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(6, (Game.getInstance().getSceneManager().getScenes().size()) - 2);
	}
	
	@Test
	@DisplayName("Test Save Level - Check if player in level 1")
	public void testTwo() {
		boolean isPlayer = false;
		for (GameObject obj : Game.getInstance().getSceneManager().getScene(2).getGameObjects()) {
			if(obj.getClass() == Player.class) {
				isPlayer = true;
			}
		}

		assertEquals(true, isPlayer);
	}
	
	
	

}