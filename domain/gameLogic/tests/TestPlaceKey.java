package domain.gameLogic.tests;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.BuildingModeScene;
import domain.gameLogic.scenes.LevelScene;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertNotNull;

public class TestPlaceKey {

	
	@Test
	@DisplayName("Test Place Key - Key is randomly placed in one of the objects")
	public void testOne() {
		Game.getInstance().getSceneManager().setCurrScene(1);
		((BuildingModeScene) Game.getInstance().getSceneManager().getScene(1)).randomiseLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(((LevelScene)Game.getInstance().getSceneManager().getScene(4)).findKeyObject());
	
	}
	
}