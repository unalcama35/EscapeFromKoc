package domain.gameObjects.characters;

import domain.gameLogic.scenes.LevelScene;

public class SecondBehaviour implements TimeWastingAlienBehaviour {

    private final int cooldown = 3000;
    private long cooldownStartTime;

    public SecondBehaviour(){
        cooldownStartTime = System.currentTimeMillis();
    }
    @Override
    public void update(LevelScene scene, TimeWastingAlien alien) {
        if(System.currentTimeMillis() - cooldownStartTime >= cooldown) {
            cooldownStartTime = System.currentTimeMillis();
            scene.moveKey();
            //scene.updateHintAnim();
        }
    }
}
