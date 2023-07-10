package ui.uiBuilders;

import domain.gameLogic.GameEventType;
import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.ButtonUI;
import utility.Utilities;

import java.util.ArrayList;
import java.util.List;

public class MainMenuUIBuilder implements UIBuilder {
    @Override
    public List<UIObject> buildUI() {
        List<UIObject> UIObjects = new ArrayList<UIObject>();

        int spriteWidth = SpriteManager.getInstance().getSprite(SpriteType.PLAY_BUTTON).getWidth();
        int spriteHeight = SpriteManager.getInstance().getSprite(SpriteType.PLAY_BUTTON).getHeight();


        UIObjects.add(new ButtonUI("play", (int) (Utilities.getScreenSize().getWidth() / 2 - spriteWidth / 2),
                (Utilities.getScreenSize().height / 2 - spriteHeight / 2) + 50, GameEventType.PLAY_GAME, true, SpriteType.PLAY_BUTTON, SpriteType.TINTED_PLAY_BUTTON));

        return UIObjects;
    }
}
