package domain.gameObjects.characters;

import domain.gameLogic.scenes.LevelScene;

public class FirstBehaviour implements TimeWastingAlienBehaviour{

    @Override
    public void update(LevelScene scene, TimeWastingAlien alien) {
        scene.moveKey();
        alien.setActive(false);

    }
}
