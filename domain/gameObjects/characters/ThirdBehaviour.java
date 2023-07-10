package domain.gameObjects.characters;

import domain.gameLogic.scenes.LevelScene;

public class ThirdBehaviour implements TimeWastingAlienBehaviour {
    private final int cooldown = 2000;
    private long cooldownStartTime;

    public ThirdBehaviour(){
        cooldownStartTime = System.currentTimeMillis();
    }
    @Override
    public void update(LevelScene scene, TimeWastingAlien alien) {
        if(System.currentTimeMillis() - cooldownStartTime >= cooldown) {
            cooldownStartTime = System.currentTimeMillis();
            alien.setActive(false);
            //scene.updateHintAnim();
        }
    }
}
