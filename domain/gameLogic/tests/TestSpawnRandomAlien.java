package domain.gameLogic.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.BuildingModeScene;
import domain.gameLogic.scenes.LevelScene;

public class TestSpawnRandomAlien {
	
	@Test
	@DisplayName("Test Spawn Random Alien - Level One: Alien exists")
	public void testOne() {
		((BuildingModeScene) Game.getInstance().getSceneManager().getScene(1)).randomiseLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((LevelScene) Game.getInstance().getSceneManager().getCurrScene()).spawnRandomAlien();
		assertEquals(1, ((LevelScene) Game.getInstance().getSceneManager().getCurrScene()).getAlienCount());
	}
	

}
