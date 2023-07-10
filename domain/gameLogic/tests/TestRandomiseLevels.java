package domain.gameLogic.tests;

import static org.junit.Assert.assertEquals;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.BuildingModeScene;

public class TestRandomiseLevels {
	
	
	@Test
	@DisplayName("Test Randomise Level - Level One: Object Count")
	public void testOne() {
		Game.getInstance().getSceneManager().setCurrScene(1);
		((BuildingModeScene) Game.getInstance().getSceneManager().getScene(1)).randomiseLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Game.getInstance().getSceneManager().getScenes().size());
		assertEquals(5, Game.getInstance().getCurrScene().getStaticObjCnt());
		assertEquals(7, Game.getInstance().getSceneManager().getScene(3).getStaticObjCnt());
		assertEquals(10, Game.getInstance().getSceneManager().getScene(4).getStaticObjCnt());
		assertEquals(14, Game.getInstance().getSceneManager().getScene(5).getStaticObjCnt());
		assertEquals(19, Game.getInstance().getSceneManager().getScene(6).getStaticObjCnt());
		assertEquals(25, Game.getInstance().getSceneManager().getScene(7).getStaticObjCnt());
	}
	
	
	@Test
	@DisplayName("Test Randomise Level - Level Numbers")
	public void testTwo() {		
		System.out.println(Game.getInstance().getSceneManager().getScenes().size());
		//assertEquals(7, Game.getInstance().getSceneManager().getScene(3).getStaticObjCnt());
	}
}
