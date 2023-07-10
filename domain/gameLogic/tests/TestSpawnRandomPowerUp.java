package domain.gameLogic.tests;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.BuildingModeScene;

public class TestSpawnRandomPowerUp {

	@Test
	@DisplayName("Test One - Test Collision")
	public void testOne() {
		Game.getInstance().getSceneManager().setCurrScene(1);
		((BuildingModeScene) Game.getInstance().getSceneManager().getScene(1)).randomiseLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
