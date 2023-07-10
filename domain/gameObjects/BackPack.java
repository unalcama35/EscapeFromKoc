package domain.gameObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.gameLogic.scenes.LevelScene;
import domain.gameObjects.powerUps.PowerUp;

public class BackPack implements Serializable{
	
	private List<PowerUp> powerUps;
	
	public BackPack() {
		powerUps = new ArrayList<PowerUp>();
	}
	
	public void addToBackPack(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	
	
	public void usePowerUp(String powerUpName, LevelScene scene) {
		PowerUp powerUp = powerUps.stream().filter(p -> p.name == powerUpName).findFirst().orElse(null);
		
		if(powerUp != null) {
			powerUp.action(scene);
			powerUps.remove(powerUp);
		}
		
	}

	
	public PowerUp getPowerUp(int i) {
		return powerUps.get(i);
	}
	
	public List<PowerUp> getPowerUps(){
		return powerUps;
	}

}
